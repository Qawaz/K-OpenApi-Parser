package com.reprezen.jsonoverlay.gen

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.EnumConstantDeclaration
import com.github.javaparser.ast.body.EnumDeclaration
import com.github.javaparser.ast.body.TypeDeclaration
import com.reprezen.jsonoverlay.gen.TypeData.Field
import java.io.File

class JavaInterfaceGenerator : TypeGenerator {

    constructor(dir: File, intfPackage: String, implPackage: String, suffix: String?, preserve: Boolean) : super(dir, intfPackage, implPackage, suffix, preserve)

    override fun getPackage(): String {
        return intfPackage
    }

    override fun getTypeDeclaration(type: TypeData.Type, suffix: String?): TypeDeclaration<*> {
        val decl = if(type.enumValues.isEmpty()) ClassOrInterfaceDeclaration() else EnumDeclaration()
        decl.setName(type.name)
        decl.setPublic(true)
        when(decl){
            is ClassOrInterfaceDeclaration -> {
                decl.setInterface(true)

                decl.addExtendedType(getSuperType(type))
                requireTypes(getSuperType(type))

                if (type.typeData.modelType !== null) {
                    requireTypes("IModelPart")
                    decl.addExtendedType("IModelPart<${type.typeData.modelType}, ${type.name}>")
                }
                for(it in type.extendInterfaces){
                    requireTypes(it)
                    decl.addExtendedType(it)
                }
            }
            is EnumDeclaration -> {
                for(enumValue in type.enumValues){
                    decl.addEntry(EnumConstantDeclaration().setName(enumValue))
                }
            }
        }
        return decl
    }

    private fun getSuperType(type : TypeData.Type): String {
        return type.extensionOf ?: "IJsonOverlay<${type.name}>"
    }

    override fun getImports(type: TypeData.Type): MutableCollection<String> {
        return type.getRequiredImports("intf", "both")
    }

    override fun getFieldMethods(field: Field): Members {
        val methods = Members()
        requireTypes(field.type)
        var first = true
        when(field.structure){
            TypeData.Structure.scalar -> {
                for(method in getScalarMethods(field)){
                    methods.addMember(method).comment(if(first) field.name else "")
                    first = false
                }
            }
            TypeData.Structure.collection -> {
                for (method in getCollectionMethods(field)) {
                    methods.addMember(method).comment(if(first) field.name else "")
                    first = false

                }
            }
            TypeData.Structure.map -> {
                for (method in getMapMethods(field)) {
                    methods.addMember(method).comment(if(first) field.name else "")
                    first = false
                }
            }
        }
        return methods
    }

    private fun getScalarMethods(f : Field): Members {
        val methods = Members()
        methods.addMember("${f.type} get${f.name}();")
        if (f.structure === TypeData.Structure.scalar && !f.isScalarType) {
            methods.addMember("${f.type} get${f.name}(boolean elaborate);")
        }
        if (f.type == "Boolean") {
            methods.addMember("boolean is${f.name}();")
        }
        methods.addMember("void set${f.name}(${f.type} ${f.lcName});")
        return methods
    }

    private fun getCollectionMethods(f : Field): Members {
        val methods = Members()
        requireTypes(List::class.java)
        methods.addMember("List<${f.type}> get${f.plural}();")
        methods.addMember("List<${f.type}> get${f.plural}(boolean elaborate);")
        methods.addMember("boolean has${f.plural}();")
        methods.addMember("${f.type} get${f.name}(int index);")
        methods.addMember("void set${f.plural}(List<${f.type}> ${f.lcPlural});")
        methods.addMember("void set${f.name}(int index, ${f.type} ${f.lcName});")
        methods.addMember("void add${f.name}(${f.type} ${f.lcName});")
        methods.addMember("void insert${f.name}(int index, ${f.type} ${f.lcName});")
        methods.addMember("void remove${f.name}(int index);")
        return methods
    }

    private fun getMapMethods(f : Field): Members {
        requireTypes(Map::class.java)
        val methods = Members()
        methods.addMember("Map<String, ${f.type}> get${f.plural}();")
        methods.addMember("Map<String, ${f.type}> get${f.plural}(boolean elaborate);")
        methods.addMember("boolean has${f.plural}();")
        methods.addMember("boolean has${f.name}(String ${f.keyName});")
        methods.addMember("${f.type} get${f.name}(String ${f.keyName});")
        methods.addMember("void set${f.plural}(Map<String, ${f.type}> ${f.lcPlural});")
        methods.addMember("void set${f.name}(String ${f.keyName}, ${f.type} ${f.lcName});")
        methods.addMember("void remove${f.name}(String ${f.keyName});")
        return methods
    }
}