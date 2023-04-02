package com.reprezen.jsonoverlay.gen

import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.databind.JsonNode
import com.reprezen.jsonoverlay.*
import java.io.File
import java.util.stream.Collectors

class JavaImplGenerator : TypeGenerator {

    constructor(dir: File, intfPackage: String, implPackage: String, suffix: String, preserve: Boolean) : super(
        dir,
        intfPackage,
        implPackage,
        suffix,
        preserve
    )

    override fun getPackage(): String {
        return implPackage
    }

    override fun getImports(type: TypeData.Type): MutableCollection<String> {
        return type.getRequiredImports("impl", "both")
    }

    override fun needIntfImports(): Boolean {
        return true
    }

    override fun getOtherMembers(type: TypeData.Type): Members {
        val members = Members()
        if (isEnum(type)) {
            members.add(
                ClassMember(
                    """@Override
            |protected Class<${type.name}> getEnumClass() {
            |    return ${type.name}.class;
            |}"""
                )
            )
            members.add(getEnumFactoryMember(type))
        } else {
            members.addAll(getFieldNameConstants(type))
            members.add(getElaborateJsonMethod(type))
            members.addAll(getFactoryMembers(type))
            if (type.typeData.modelType !== null) {
                members.addMember(
                    """@Override
                    |public Class <? > _getModelType() {
                    |    return ${type.typeData.modelType}.class;
                    |} """
                )
            }
        }
        members.add(
            ClassMember(
                """@Override
            |protected OverlayFactory <? > _getFactory() {
            |    return factory;
            |}""".trimMargin("|")
            )
        )
        members.addAll(getBuilderMethods(type))
        return members
    }

    override fun getTypeDeclaration(type: TypeData.Type, suffix: String?): TypeDeclaration {
        val decl = ClassOrInterfaceDeclaration(
            name = type.name + suffix,
            isInterface = false,
            isPublic = true
        )
        if (isEnum(type)) {
            requireTypes(EnumOverlay::class.java)
            decl.addExtendedType("""EnumOverlay<${type.name}>""")
        } else {
            decl.addExtendedType(getSuperType(type))
            decl.addImplementedType(type.name)
        }
        return decl
    }

    private fun isEnum(type: TypeData.Type): Boolean {
        return type.enumValues.isNotEmpty()
    }

    private fun getSuperType(type: TypeData.Type): String {
        val superType = type.extensionOf
        return if (superType == null) {
            requireTypes("PropertiesOverlay")
            "PropertiesOverlay<${type.name}>"
        } else {
            superType + suffix
        }
    }

    override fun getConstructors(type: TypeData.Type): Members {
        val members = Members()
        requireTypes(JsonNode::class.java, JsonOverlay::class.java)
        val factoryEx = if (type.extensionOf == null) "factory, " else ""
        members.addMember(
            """public ${type.implType}(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
               |    super(json, parent, ${factoryEx}refMgr);
               |}""".trimMargin("|")
        )
        val factoryStr = if (type.extensionOf == null) "factory, " else ""
        members.addMember(
            """public ${type.implType}(${type.name} ${type.lcName}, JsonOverlay<?> parent, ReferenceManager refMgr) {
                |   supr(${type.lcName}, parent, ${factoryStr}refMgr);
                |}""".trimMargin("|")
        )
        return members
    }

    private fun getBuilderMethods(type: TypeData.Type): Members {
        val members = Members()
        requireTypes(Builder::class.java, OverlayFactory::class.java, IJsonOverlay::class.java)
        val createType = if (isEnum(type)) "IJsonOverlay<${type.name}>" else type.name
        members.addMember(
            """public static < OV extends IJsonOverlay<?> > Builder < ${type.name} > builder(OV modelMember) {
                |   return new Builder <${type.name}>(factory, modelMember);
            |}""".trimMargin("|")
        )
        val notIsEnumTypeThen = if (!isEnum(type)) "(${type.name}) " else ""
        members.addMember(
            """public static < OV extends IJsonOverlay<?> > $createType create (OV modelMember) {
            |    return ${notIsEnumTypeThen}builder(modelMember).build();
            |}""".trimMargin("|")
        )
        return members
    }

    override fun skipField(field: TypeData.Field): Boolean {
        return field.isNoImpl
    }

    override fun getFieldMethods(field: TypeData.Field): Members {
        val methods = Members()
        var first = true
        when (field.structure) {
            TypeData.Structure.scalar -> {
                for (method in getScalarMethods(field)) {
                    method.override().also { if (first) it.comment(field.name) }
                    methods.add(method)
                    first = false
                }
            }

            TypeData.Structure.collection -> {
                for (method in getCollectionMethods(field)) {
                    method.override().also { if (first) it.comment(field.name) }
                    methods.add(method)
                    first = false
                }
            }

            TypeData.Structure.map -> {
                for (method in getMapMethods(field)) {
                    method.override().also { if (first) it.comment(field.name) }
                    methods.add(method)
                    first = false
                }
            }
        }
        return methods
    }

    private fun getScalarMethods(f: TypeData.Field): Members {
        val methods = Members()
        methods.addMember(
            """public ${f.type} get${f.name}() {
            |    return _get("${f.propertyName}", ${f.type}.class);
            |}""".trimMargin("|")
        )
        if (f.structure == TypeData.Structure.scalar && !f.isScalarType) {
            methods.addMember(
                """public ${f.type} get${f.name}(boolean elaborate) {
                |    return _get("${f.propertyName}", elaborate, ${f.type}.class);
                |}""".trimMargin("|")
            )
        }
        if (f.isBoolean) {
            methods.addMember(
                """public boolean is${f.name}() {
                |    Boolean bool = _get ("${f.propertyName}", Boolean.class);
                |    return bool != null ? bool : ${f.boolDefault};
                |}""".trimMargin("|")
            )
        }
        methods.addMember(
            """public void set${f.name}(${f.type} ${f.lcName}) {
        |    _setScalar("${f.propertyName}", ${f.lcName}, ${f.type}.class);
        |}""".trimMargin("|")
        )
        return methods
    }


    private fun getCollectionMethods(f: TypeData.Field): Members {
        requireTypes(List::class.java, ListOverlay::class.java)
        val methods = Members()
        methods.addMember(
            """public List < ${f.type} > get${f.plural}() {
            |    return _getList("${f.propertyName}", ${f.type}.class);
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public List < ${f.type} > get${f.plural}(boolean elaborate) {
            |    return _getList("${f.propertyName}", elaborate, ${f.type}.class);
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public boolean has${f.plural}() {
            |    return _isPresent("${f.propertyName}");
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public ${f.type} get${f.name}(int index) {
            |    return _get("${f.propertyName}", index, ${f.type}.class);
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public void set${f.plural}(List < ${f.type} > ${f.lcPlural}) {
            |    _setList("${f.propertyName}", ${f.lcPlural}, ${f.type}.class);
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public void set${f.name}(int index, ${f.type} ${f.lcName}) {
            |    _set("${f.propertyName}", index, ${f.lcName}, ${f.type}.class);
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public void add${f.name}(${f.type} ${f.lcName}) {
            |    _add("${f.propertyName}", ${f.lcName}, ${f.type}.class);
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public void insert${f.name}(int index, ${f.type} ${f.lcName}) {
            |    _insert("${f.propertyName}", index, ${f.lcName}, ${f.type}.class);
            |}""".trimMargin("|")
        )

        methods.addMember(
            """public void remove${f.name}(int index) {
            |    _remove("${f.propertyName}", index, ${f.type}.class);
            |}""".trimMargin("|")
        )
        return methods
    }

    private fun getMapMethods(f: TypeData.Field): Members {
        requireTypes(Map::class.java, MapOverlay::class.java)
        val methods = Members()
        methods.addMember(
            """public Map < String, ${f.type} > get${f.plural}() {
            |    return _getMap("${f.propertyName}", ${f.type}.class);
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public Map < String, ${f.type} > get${f.plural}(boolean elaborate) {
            |    return _getMap("${f.propertyName}", elaborate, ${f.type}.class);
            |}""".trimMargin("|")
        )

        methods.addMember(
            """public boolean has${f.plural}() {
            |    return _isPresent("${f.propertyName}");
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public boolean has${f.name}(String ${f.keyName}) {
            |    return _getMap("${f.propertyName}", ${f.type}.class).containsKey(${f.keyName});
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public ${f.type} get${f.name}(String ${f.keyName}) {
            |    return _get("${f.propertyName}", ${f.keyName}, ${f.type}.class);
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public void set${f.plural}(Map < String, ${f.type} > ${f.lcPlural}) {
            |    _setMap("${f.propertyName}", ${f.lcPlural}, ${f.type}.class);
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public void set${f.name}(String ${f.keyName}, ${f.type} ${f.lcName}) {
            |    _set("${f.propertyName}", ${f.keyName}, ${f.lcName}, ${f.type}.class);
            |}""".trimMargin("|")
        )
        methods.addMember(
            """public void remove${f.name}(String ${f.keyName}) {
            |    _remove("${f.propertyName}", ${f.keyName}, ${f.type}.class);
            |}""".trimMargin("|")
        )
        return methods
    }

    private fun getFieldNameConstants(type: TypeData.Type): Members {
        val members = Members()
        type.fields.values.filter { !it.isNoImpl }.forEach { f ->
            members.add(
                ClassMember(
                    """public static final String F_${f.propertyName} = "${f.propertyName}";"""
                )
            )
        }
        return members
    }

    private fun getElaborateJsonMethod(type: TypeData.Type): ClassMember {
        val elaborateStatement = type.fields.values.filter { !it.isNoImpl }.joinToString("\n") {
            getElaborateStatement(it)
        }
        return ClassMember(
            """protected void _elaborateJson() {
        |    super._elaborateJson();
        |    $elaborateStatement
        |}""".trimMargin("|")).override()
    }

    private fun getElaborateStatement(f: TypeData.Field): String {
        requireTypes(f.implType)
        return when (f.structure) {
            TypeData.Structure.scalar -> {
                """_createScalar("${f.propertyName}", "${f.parentPath}", ${f.implType}.factory);"""
            }

            TypeData.Structure.collection -> {
                """_createList("${f.propertyName}", "${f.parentPath}", ${f.implType}.factory);"""
            }

            TypeData.Structure.map -> {
                val pat = f.keyPattern?.replace("\\\\", "\\\\\\\\")?.let { "\"$it\"" } ?: "null"
                "_createMap(\"${f.propertyName}\", \"${f.parentPath}\", ${f.implType}.factory, $pat);"
            }
        }
    }

    private fun getEnumFactoryMember(type: TypeData.Type): ClassMember {
        requireTypes(
            OverlayFactory::class.java,
            JsonOverlay::class.java,
            JsonNode::class.java,
            ReferenceManager::class.java
        )
        return ClassMember(
    """public static OverlayFactory < ${type.name} > factory = new OverlayFactory <${type.name}>() {
        |   @Override
        |   protected Class <? extends JsonOverlay<? super ${type.name}>> getOverlayClass() {
        |       return ${type.implType}.class;
        |   }
        |   
        |   @Override
        |   public JsonOverlay <${type.name}> _create(${type.name} ${type.lcName}, JsonOverlay<?> parent, ReferenceManager refMgr) {
        |       return new ${type.implType}(${type.lcName}, parent, refMgr);
        |   }
        |   
        |   @Override
        |   public JsonOverlay <${type.name}> _create(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
        |       return new ${type.implType}(json, parent, refMgr);
        |   }
        |};""".trimMargin("|")
        )
    }

    private fun getFactoryMembers(type: TypeData.Type): Members {
        val members = Members()
        members.add(getFactoryMember(type))
        members.addAll(getSubtypeMethods(type))
        return members
    }

    private fun getFactoryMember(type: TypeData.Type): ClassMember {
        requireTypes(
            OverlayFactory::class.java,
            JsonNode::class.java,
            ReferenceManager::class.java,
            JsonOverlay::class.java
        )
        val _createSubTypesImpl = if (getSubTypes(type).isEmpty()) {
            "overlay = new ${type.implType}(${type.lcName}, parent, refMgr);"
        } else {
            """Class < ? extends ${type.name} > subtype = getSubtypeOf(${type.lcName});${"\n"}""" + getSubtypeCreate(
                type,
                type.lcName
            )
        }
        val _createSubTypesImpl2 = if (getSubTypes(type).isEmpty()) {
            "overlay = new ${type.implType}(json, parent, refMgr);"
        } else {
            """Class < ? extends ${type.name} > subtype = getSubtypeOf(json);${"\n"}""" + getSubtypeCreate(
                type,
                ".json"
            )
        }
        return ClassMember(
            """public static OverlayFactory < ${type.name} > factory = new OverlayFactory <${type.name}>(){
        |    @Override
        |    protected Class <? extends JsonOverlay<? super ${type.name}>> getOverlayClass() {
        |        return ${type.implType}.class;
        |    }
        |
        |    @Override
        |    public JsonOverlay <${type.name}> _create(${type.name} ${type.lcName}, JsonOverlay<?> parent, ReferenceManager refMgr) {
        |    JsonOverlay<?> overlay;
        |    $_createSubTypesImpl
        |    @SuppressWarnings("unchecked")
        |    JsonOverlay < ${type.name} > castOverlay = (JsonOverlay < ${type.name} >) overlay;
        |        return castOverlay;
        |    }
        |
        |    @Override
        |    public JsonOverlay <${type.name}> _create(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
        |    JsonOverlay<?> overlay;
        |    $_createSubTypesImpl2
        |        @SuppressWarnings("unchecked")
        |        JsonOverlay < ${type.name} > castOverlay = (JsonOverlay < ${type.name} >) overlay;
        |        return castOverlay;
        |    }
        |${getIsExtendedType(!getSubTypes(type).isEmpty())}
        |};""".trimMargin("|")
        )
    }

    private fun getSubtypeMethods(type: TypeData.Type): Members {
        val members = Members()
        val subTypes = getSubTypes(type)
        if (!subTypes.isEmpty() && !type.isAbstract()) {
            subTypes.add(type)
        }
        members.add(getValueSubtypeSelector(type, subTypes))
        members.add(getJsonSubtypeSelector(type, subTypes))
        return members
    }

    private fun getIsExtendedType(isExtended: Boolean): String {
        return """@Override
        |protected boolean isExtendedType() {
        |    return ${if (isExtended) "true" else "false"};
        |}""".trimMargin("|")
    }

    private fun getValueSubtypeSelector(
        t: TypeData.Type,
        subTypes: Collection<TypeData.Type>
    ): ClassMember {
        val switchExpr = """${t.lcName}.getClass().getSimpleName()"""
        val subTypeSwitch = getSubtypeSwitch(t, subTypes, switchExpr) { it.name }
        return ClassMember(
            """private static Class < ? extends ${t.name} > getSubtypeOf(${t.name} ${t.lcName}) {
        |    $subTypeSwitch
        |}""".trimMargin("|")
        )
    }

    private fun getJsonSubtypeSelector(
        t: TypeData.Type,
        subTypes: Collection<TypeData.Type>
    ): ClassMember {
        requireTypes(JsonPointer::class.java, Collectors::class.java)
        val switchExpr = """json.at(JsonPointer.compile("/${t.discriminator}")).asText()"""
        val subTypeSwitch = getSubtypeSwitch(t, subTypes, switchExpr) { it.discriminatorValue }
        return ClassMember(
            """private static Class < ? extends ${t.name} > getSubtypeOf(JsonNode json) {
        |    $subTypeSwitch
        |}
        |""".trimMargin("|")
        )
    }

    private fun getSubtypeSwitch(
        t: TypeData.Type,
        subTypes: Collection<TypeData.Type>,
        switchExpr: String,
        discFn: (TypeData.Type) -> String
    ): String {
        if (subTypes.isEmpty()) return "return ${t.name}.class;" else {
            val cases = subTypes.joinToString { sub ->
                "case \"${discFn(sub)}\":\n\treturn ${sub.name}.class;\n"
            }
            return """switch($switchExpr) {
        |    $cases
        |    default:
        |        return null;
        |}""".trimMargin("|")
        }
    }

    private fun getSubtypeCreate(t: TypeData.Type, arg0: String): String {
        val subtypes = getSubTypes(t)
        if (subtypes.isEmpty()) {
            return """
            |overlay = new ${t.implType}(${castArg0(t, arg0)}, parent, refMgr);
            """.trimMargin("|")
        } else {
            val cases = subtypes.joinToString { sub ->
                "case \"${sub.name}\":\n" +
                        "overlay = ${sub.implType}.factory.create(${castArg0(sub, arg0)}, parent, refMgr);\nbreak;"
            }
            return """switch(subtype != null ? subtype . getSimpleName () : "") {
            |    $cases
            |    default:
            |    overlay = new ${t.implType}(${castArg0(t, arg0)}, parent, refMgr);
            |}""".trimMargin("|")
        }
    }

    private fun castArg0(type: TypeData.Type, arg0: String): String {
        return if (arg0 == ".json") "json" else """(${type.name}) $arg0"""
    }

    private fun getSubTypes(type: TypeData.Type): MutableCollection<TypeData.Type> {
        val subTypes = HashSet<TypeData.Type>()
        val todo = ArrayDeque<TypeData.Type>()
        todo.add(type)
        while (!todo.isEmpty()) {
            val nextType = todo.removeFirst()
            if (!subTypes.contains(nextType)) {
                subTypes.add(nextType)
                val directSubtypes = type.typeData.types.filter { it.extensionOf == nextType.name }
                todo.addAll(directSubtypes)
            }
        }
        subTypes.remove(type)
        return subTypes
    }

}