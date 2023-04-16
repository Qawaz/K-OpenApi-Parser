package com.wakaztahir.generator

import java.io.File

class JavaInterfaceGenerator : TypeGenerator {

    constructor(dir: File, intfPackage: String, implPackage: String, suffix: String) : super(
        dir,
        intfPackage,
        implPackage,
        suffix
    )

    override fun getPackage(): String {
        return intfPackage
    }

    override fun getTypeDeclaration(type: KTypeData.Type, suffix: String?): TypeDeclaration {
        val decl = if(type.enumValues.isEmpty()) ClassOrInterfaceDeclaration(type.name,
            isInterface = true,
            isPublic = true
        ) else EnumDeclaration(
            type.name,isPublic = true
        )
        when(decl){
            is ClassOrInterfaceDeclaration -> {

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
                    decl.addEntry(enumValue)
                }
            }
        }
        return decl
    }

    private fun getSuperType(type : KTypeData.Type): String {
        return type.extensionOf ?: "IJsonOverlay<${type.name}>"
    }

    override fun getImports(type: KTypeData.Type): MutableCollection<String> {
        return type.getRequiredImports("intf", "both")
    }

    override fun getFieldMethods(field: KTypeData.Field): Members {
        val methods = Members()
        requireTypes(field.type)
        var first = true
        when(field.structure){
            KTypeData.Structure.scalar -> {
                for(method in getScalarMethods(field)){
                    if(first) method.comment(field.name)
                    methods.add(method)
                    first = false
                }
            }
            KTypeData.Structure.collection -> {
                for (method in getCollectionMethods(field)) {
                    if(first) method.comment(field.name)
                    methods.add(method)
                    first = false

                }
            }
            KTypeData.Structure.map -> {
                for (method in getMapMethods(field)) {
                    if(first) method.comment(field.name)
                    methods.add(method)
                    first = false
                }
            }
        }
        return methods
    }

    private fun getScalarMethods(f : KTypeData.Field): Members {
        val methods = Members()
        methods.addMember("fun get${f.name}() : ${f.type}?")
        if (f.isBoolean) {
            methods.addMember("fun is${f.name}() : Boolean")
        }
        methods.addMember("fun set${f.name}(${f.lcName} : ${f.type})")
        return methods
    }

    private fun getCollectionMethods(f : KTypeData.Field): Members {
        val methods = Members()
        requireTypes(List::class)
        methods.addMember("fun get${f.plural}() : List<${f.type}>")
        methods.addMember("fun has${f.plural}() : Boolean")
        methods.addMember("fun get${f.name}(index : Int) : ${f.type}")
        methods.addMember("fun set${f.plural}(${f.lcPlural} : MutableList<${f.type}>)")
        methods.addMember("fun set${f.name}(index : Int, ${f.lcName} : ${f.type})")
        methods.addMember("fun add${f.name}(${f.lcName} : ${f.type})")
        methods.addMember("fun insert${f.name}(index : Int,${f.lcName} : ${f.type})")
        methods.addMember("fun remove${f.name}(index : Int)")
        return methods
    }

    private fun getMapMethods(f : KTypeData.Field): Members {
        requireTypes(Map::class)
        val methods = Members()
        methods.addMember("fun get${f.plural}() : MutableMap<String, ${f.type}>")
        methods.addMember("fun has${f.plural}() : Boolean")
        methods.addMember("fun has${f.name}(${f.keyName} : String) : Boolean")
        methods.addMember("fun get${f.name}(${f.keyName} : String) : ${f.type}?")
        methods.addMember("fun set${f.plural}(${f.lcPlural} : MutableMap<String, ${f.type}>)")
        methods.addMember("fun set${f.name}(${f.keyName} : String,${f.lcName} : ${f.type})")
        methods.addMember("fun remove${f.name}(${f.keyName} : String)")
        return methods
    }
}