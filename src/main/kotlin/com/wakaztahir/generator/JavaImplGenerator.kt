package com.wakaztahir.generator

import com.reprezen.jsonoverlay.*
import kotlinx.serialization.json.JsonElement
import java.io.File
import java.util.stream.Collectors

class JavaImplGenerator : TypeGenerator {

    constructor(dir: File, intfPackage: String, implPackage: String, suffix: String) : super(
        dir,
        intfPackage,
        implPackage,
        suffix
    )

    override fun getPackage(): String {
        return implPackage
    }

    override fun skipField(field: KTypeData.Field): Boolean {
        return field.isNoImpl
    }

    override fun getImports(type: KTypeData.Type): MutableCollection<String> {
        return type.getRequiredImports("impl", "both")
    }

    override fun needIntfImports(): Boolean {
        return true
    }

    override fun getCompanionMembers(type: KTypeData.Type): Members {
        val members = Members()
        if(isEnum(type)){
            members.add(getEnumFactoryMember(type))
        } else {
            members.addAll(getFieldNameConstants(type))
            members.addAll(getFactoryMembers(type))
        }
        members.addAll(getBuilderMethods(type))
        return members
    }

    override fun getOtherMembers(type: KTypeData.Type): Members {
        val members = Members()
        if (isEnum(type)) {
            members.add(
                ClassMember(
                    """override fun getEnumValue(value : String) : ${type.name} {
            |${"\t"}return ${type.name}.valueOf(value)
            |}""".trimMargin("|")
                )
            )
        } else {
            members.add(getElaborateJsonMethod(type))
            if (type.typeData.modelType !== null) {
                members.addMember(
                    """override fun _getModelType() : Class<*> {
                    |${"\t"}return ${type.typeData.modelType}::class.java
                    |}""".trimMargin("|")
                )
            }
        }
        members.add(
            ClassMember(
                """override fun _getFactory() : OverlayFactory<${type.name}> {
            |${"\t"}return Companion.factory
            |}""".trimMargin("|")
            )
        )
        return members
    }

    override fun getTypeDeclaration(type: KTypeData.Type, suffix: String?): TypeDeclaration {
        val decl = ClassOrInterfaceDeclaration(
            name = type.name + suffix,
            isInterface = false,
            isPublic = true
        )
        if (isEnum(type)) {
            requireTypes(EnumOverlay::class)
            decl.addExtendedType("""EnumOverlay<${type.name}>""")
        } else {
            decl.addExtendedType(getSuperType(type))
            decl.addImplementedType(type.name)
        }
        return decl
    }

    private fun isEnum(type: KTypeData.Type): Boolean {
        return type.enumValues.isNotEmpty()
    }

    private fun getSuperType(type: KTypeData.Type): String {
        val superType = type.extensionOf
        return if (superType == null) {
            requireTypes("PropertiesOverlay")
            "PropertiesOverlay<${type.name}>"
        } else {
            superType + suffix
        }
    }

    override fun getConstructors(type: KTypeData.Type): Members {
        val members = Members()
        requireTypes(JsonElement::class, JsonOverlay::class)
        val factoryEx = if (type.extensionOf == null) "Companion.factory, " else ""
        members.addMember(
            """constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, ${factoryEx}refMgr)"""
        )
        members.addMember(
            """constructor(${type.lcName} : ${type.name}?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(${type.lcName}, parent, ${factoryEx}refMgr)"""
        )
        return members
    }

    private fun getBuilderMethods(type: KTypeData.Type): Members {
        val members = Members()
        requireTypes(Builder::class, OverlayFactory::class, IJsonOverlay::class)
        val createType = if (isEnum(type)) "IJsonOverlay<${type.name}>" else type.name
        members.addMember(
            """fun builder(modelMember : JsonOverlay<*>) : Builder<${type.name}> {
                |${"\t"}return Builder<${type.name}>(factory, modelMember)
            |}""".trimMargin("|")
        )
        val notIsEnumTypeThen = if (!isEnum(type)) " as ${type.name}" else ""
        members.addMember(
            """fun create(modelMember : JsonOverlay<*>) : $createType {
            |${"\t"}return builder(modelMember).build()${notIsEnumTypeThen}
            |}""".trimMargin("|")
        )
        return members
    }

    override fun getFieldMethods(field: KTypeData.Field): Members {
        val methods = Members()
        when (field.structure) {
            KTypeData.Structure.scalar -> {
                for (method in getScalarMethods(field)) {
                    methods.add(method)
                }
            }

            KTypeData.Structure.collection -> {
                for (method in getCollectionMethods(field)) {
                    methods.add(method)
                }
            }

            KTypeData.Structure.map -> {
                for (method in getMapMethods(field)) {
                    methods.add(method)
                }
            }
        }
        return methods
    }

    private fun getScalarMethods(f: KTypeData.Field): Members {
        val methods = Members()
        methods.addMember(
            """// ${f.name}${'\n'}override fun get${f.name}() : ${f.type}? {
            |${"\t"}return _get("${f.propertyName}")
            |}""".trimMargin("|")
        )
        if (f.isBoolean) {
            methods.addMember(
                """override fun is${f.name}() : Boolean {
                |${"\t"}return _get("${f.propertyName}") ?: ${f.boolDefault}
                |}""".trimMargin("|")
            )
        }
        methods.addMember(
            """override fun set${f.name}(${f.lcName} : ${f.type}) {
        |${"\t"}_setScalar("${f.propertyName}", ${f.lcName})
        |}""".trimMargin("|")
        )
        return methods
    }


    private fun getCollectionMethods(f: KTypeData.Field): Members {
        requireTypes(List::class, ListOverlay::class)
        val methods = Members()
        methods.addMember(
            """// ${f.name}${'\n'}override fun get${f.plural}() : List<${f.type}> {
            |${"\t"}return _getList("${f.propertyName}")
            |}""".trimMargin("|")
        )
        methods.addMember(
            """override fun has${f.plural}() : Boolean {
            |${"\t"}return _isPresent("${f.propertyName}")
            |}""".trimMargin("|")
        )
        methods.addMember(
            """override fun get${f.name}(index : Int) : ${f.type} {
            |${"\t"}return _get("${f.propertyName}", index)
            |}""".trimMargin("|")
        )
        methods.addMember(
            """override fun set${f.plural}(${f.lcPlural} : MutableList<${f.type}>) {
            |${"\t"}_setList("${f.propertyName}", ${f.lcPlural})
            |}""".trimMargin("|")
        )
        methods.addMember(
            """override fun set${f.name}(index : Int, ${f.lcName} : ${f.type}) {
            |${"\t"}_set("${f.propertyName}", index, ${f.lcName})
            |}""".trimMargin("|")
        )
        methods.addMember(
            """override fun add${f.name}(${f.lcName} : ${f.type}) {
            |${"\t"}_add("${f.propertyName}", ${f.lcName})
            |}""".trimMargin("|")
        )
        methods.addMember(
            """override fun insert${f.name}(index : Int, ${f.lcName} : ${f.type}) {
            |${"\t"}_insert("${f.propertyName}", index, ${f.lcName})
            |}""".trimMargin("|")
        )

        methods.addMember(
            """override fun remove${f.name}(index : Int) {
            |${"\t"}_remove("${f.propertyName}", index)
            |}""".trimMargin("|")
        )
        return methods
    }

    private fun getMapMethods(f: KTypeData.Field): Members {
        requireTypes(Map::class, MapOverlay::class)
        val methods = Members()
        methods.addMember(
            """// ${f.name}${'\n'}override fun get${f.plural}() : MutableMap<String, ${f.type}> {
            |${"\t"}return _getMap("${f.propertyName}")
            |}""".trimMargin("|")
        )

        methods.addMember(
            """override fun has${f.plural}() : Boolean {
            |${"\t"}return _isPresent("${f.propertyName}")
            |}""".trimMargin("|")
        )
        methods.addMember(
            """override fun has${f.name}(${f.keyName} : String) : Boolean {
            |${"\t"}return _getMap<${f.type}>("${f.propertyName}").containsKey(${f.keyName})
            |}""".trimMargin("|")
        )
        methods.addMember(
            """override fun get${f.name}(${f.keyName} : String) : ${f.type}? {
            |${"\t"}return _get("${f.propertyName}", ${f.keyName})
            |}""".trimMargin("|")
        )
        methods.addMember(
            """override fun set${f.plural}(${f.lcPlural} : MutableMap<String, ${f.type}>) {
            |${"\t"}_setMap("${f.propertyName}", ${f.lcPlural})
            |}""".trimMargin("|")
        )
        methods.addMember(
            """override fun set${f.name}(${f.keyName} : String, ${f.lcName} : ${f.type}) {
            |${"\t"}_set("${f.propertyName}", ${f.keyName}, ${f.lcName})
            |}""".trimMargin("|")
        )
        methods.addMember(
            """override fun remove${f.name}(${f.keyName} : String) {
            |${"\t"}_remove("${f.propertyName}", ${f.keyName})
            |}""".trimMargin("|")
        )
        return methods
    }

    private fun getFieldNameConstants(type: KTypeData.Type): Members {
        val members = Members()
        type.fields.values.filter { !it.isNoImpl }.forEach { f ->
            members.add(
                ClassMember(
                    """const val F_${f.propertyName} : String = "${f.propertyName}""""
                )
            )
        }
        return members
    }

    private fun getElaborateJsonMethod(type: KTypeData.Type): ClassMember {
        val elaborateStatement = type.fields.values.filter { !it.isNoImpl }.joinToString("\n") {
            "\t" + getElaborateStatement(it)
        }
        return ClassMember(
            """override fun _elaborateJson() {
        |${"\t"}super._elaborateJson()
        |$elaborateStatement
        |}""".trimMargin("|")
        )
    }

    private fun getElaborateStatement(f: KTypeData.Field): String {
        requireTypes(f.implType)
        return when (f.structure) {
            KTypeData.Structure.scalar -> {
                """_createScalar("${f.propertyName}", "${f.parentPath}", ${f.implType}.factory)"""
            }

            KTypeData.Structure.collection -> {
                """_createList("${f.propertyName}", "${f.parentPath}", ${f.implType}.factory)"""
            }

            KTypeData.Structure.map -> {
                val pat = f.keyPattern?.replace("\\", "\\\\")?.let { "\"$it\"" } ?: "null"
                "_createMap(\"${f.propertyName}\", \"${f.parentPath}\", ${f.implType}.factory, $pat)"
            }
        }
    }

    private fun getEnumFactoryMember(type: KTypeData.Type): ClassMember {
        requireTypes(
            OverlayFactory::class,
            JsonOverlay::class,
            JsonElement::class,
            ReferenceManager::class
        )
        return ClassMember(
            """val factory = object : OverlayFactory<${type.name}>() {
        |${"\t"}override fun getOverlayClass() : Class<out JsonOverlay<in ${type.name}>> {
        |${"\t"}${"\t"}return ${type.implType}::class.java
        |${"\t"}}
        |
        |${"\t"}override fun _create(${type.lcName} : ${type.name}?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<${type.name}> {
        |${"\t"}${"\t"}return ${type.implType}(${type.lcName}, parent, refMgr)
        |${"\t"}}
        |
        |${"\t"}override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<${type.name}> {
        |${"\t"}${"\t"}return ${type.implType}(json, parent, refMgr)
        |${"\t"}}
        |}""".trimMargin("|")
        )
    }

    private fun getFactoryMembers(type: KTypeData.Type): Members {
        val members = Members()
        members.add(getFactoryMember(type))
        members.addAll(getSubtypeMethods(type))
        return members
    }

    private fun getFactoryMember(type: KTypeData.Type): ClassMember {
        requireTypes(
            OverlayFactory::class,
            JsonElement::class,
            ReferenceManager::class,
            JsonOverlay::class
        )
        val _createSubTypesImpl = if (getSubTypes(type).isEmpty()) {
            "return ${type.implType}(${type.lcName}, parent, refMgr)"
        } else {
            """return getSubtypeOf(${type.lcName})${"\n"}""" + getSubtypeCreate(type, type.lcName) + "  as JsonOverlay<${type.name}>"
        }
        val _createSubTypesImpl2 = if (getSubTypes(type).isEmpty()) {
            "return ${type.implType}(json, parent, refMgr)"
        } else {
            """return getSubtypeOf(json)${"\n"}""" + getSubtypeCreate(type, ".json") + "  as JsonOverlay<${type.name}>"
        }
        return ClassMember(
            """val factory = object : OverlayFactory<${type.name}>() {
        |
        |${"\t"}override fun getOverlayClass() : Class<out JsonOverlay<in ${type.name}>> {
        |${"\t"}${"\t"}return ${type.implType}::class.java
        |${"\t"}}
        |
        |${"\t"}override fun _create(${type.lcName} : ${type.name}?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<${type.name}> {
        |${"\t"}${"\t"}$_createSubTypesImpl
        |${"\t"}}
        |
        |${"\t"}override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<${type.name}> {
        |${"\t"}${"\t"}$_createSubTypesImpl2
        |${"\t"}}
        |
        |${"\t"}${getIsExtendedType(!getSubTypes(type).isEmpty()).replace("\n", "\n\t")}
        |};""".trimMargin("|")
        )
    }

    private fun getSubtypeMethods(type: KTypeData.Type): Members {
        val members = Members()
        val subTypes = getSubTypes(type)
        if (!subTypes.isEmpty() && !type.abstract) {
            subTypes.add(type)
        }
        members.add(getValueSubtypeSelector(type, subTypes))
        members.add(getJsonSubtypeSelector(type, subTypes))
        return members
    }

    private fun getIsExtendedType(isExtended: Boolean): String {
        return """override val isExtendedType : Boolean get() = ${if (isExtended) "true" else "false"}""".trimMargin("|")
    }

    private fun getValueSubtypeSelector(
        t: KTypeData.Type,
        subTypes: Collection<KTypeData.Type>
    ): ClassMember {
        val switchExpr = """${t.lcName}.getClass().getSimpleName()"""
        val subTypeSwitch = getSubtypeSwitch(t, subTypes, switchExpr) { it.name }
        return ClassMember(
            """private fun getSubtypeOf(${t.lcName} : ${t.name}) : Class<out ${t.name}> {
        |${"\t"}$subTypeSwitch
        |}""".trimMargin("|")
        )
    }

    private fun getJsonSubtypeSelector(
        t: KTypeData.Type,
        subTypes: Collection<KTypeData.Type>
    ): ClassMember {
        requireTypes(JsonPointer::class, Collectors::class)
        val switchExpr = """json.at(JsonPointer("/${t.discriminator}")).asText()"""
        val subTypeSwitch = getSubtypeSwitch(t, subTypes, switchExpr) { it.discriminatorValue }
        return ClassMember(
            """private fun getSubtypeOf(json : JsonElement) : Class<out ${t.name}> {
        |${"\t"}$subTypeSwitch
        |}
        |""".trimMargin("|")
        )
    }

    private fun getSubtypeSwitch(
        t: KTypeData.Type,
        subTypes: Collection<KTypeData.Type>,
        switchExpr: String,
        discFn: (KTypeData.Type) -> String
    ): String {
        if (subTypes.isEmpty()) return "return ${t.name}::class.java" else {
            val cases = subTypes.joinToString { sub ->
                "case \"${discFn(sub)}\":\n\treturn ${sub.name}::class.java\n"
            }
            return """switch($switchExpr) {
        |${"\t"}$cases
        |${"\t"}default:
        |${"\t"}${"\t"}return null
        |}""".trimMargin("|")
        }
    }

    private fun getSubtypeCreate(t: KTypeData.Type, arg0: String): String {
        val subtypes = getSubTypes(t)
        if (subtypes.isEmpty()) {
            return """
            |overlay = ${t.implType}(${castArg0(t, arg0)}, parent, refMgr)
            """.trimMargin("|")
        } else {
            val cases = subtypes.joinToString { sub ->
                "case \"${sub.name}\":\n" +
                        "overlay = ${sub.implType}.factory.create(${castArg0(sub, arg0)}, parent, refMgr);\nbreak;"
            }
            return """switch(subtype != null ? subtype . getSimpleName () : "") {
            |${"\t"}$cases
            |${"\t"}default:
            |${"\t"}overlay = ${t.implType}(${castArg0(t, arg0)}, parent, refMgr)
            |}""".trimMargin("|")
        }
    }

    private fun castArg0(type: KTypeData.Type, arg0: String): String {
        return if (arg0 == ".json") "json" else """(${type.name}) $arg0"""
    }

    private fun getSubTypes(type: KTypeData.Type): MutableCollection<KTypeData.Type> {
        val subTypes = HashSet<KTypeData.Type>()
        val todo = ArrayDeque<KTypeData.Type>()
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