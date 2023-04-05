package com.reprezen.jsonoverlay.gen

import java.util.*
import java.util.stream.Collectors

class KTypeData(
    val imports: Map<String, String>,
    val typeMap: MutableMap<String, Type>,
    val modelType: String?,
    val discriminator: String?,
) {

    val types: Collection<Type>
        get() = typeMap.values

    enum class Structure {
        scalar,
        collection,
        map
    }

    class Field(
        val container: Type,
        val id: String,
        val name: String,
        val structure: Structure,
        val keyName: String,
        val isNoImpl: Boolean,
        val boolDefault: Boolean,
        val keyPattern: String?,
        private val _type: String,
        private val _parentPath: String?,
        private val _plural: String?
    ) {

        val type: String
            get() = if (_type == "Primitive") "Any" else _type

        val isScalarType: Boolean
            get() {
                return when (type) {
                    "String", "Int", "Number", "Boolean", "Primitive", "Any" -> true
                    else -> false
                }
            }

        val isBoolean: Boolean
            get() = type == "Boolean"

        val lcName: String
            get() {
                var lcName = lcFirst(name)
                when (lcName) {
                    "default", "enum", "in" -> lcName += "Value"
                }
                return lcName
            }

        val plural: String
            get() = _plural ?: (name + "s")

        val lcPlural: String
            get() = lcFirst(plural)

        val propertyName: String
            get() = if (structure == Structure.scalar) lcName else lcPlural

        val parentPath: String
            get() = _parentPath ?: id;

        val implType: String
            get() = Type.getImplType(container.typeData.typeMap[type]?.name ?: _type)

        val overlayType: String
            get() = type + if (isScalarType) "Overlay" else ""

        constructor(container: Type, field: TypeData.Field) : this(
            container = container,
            id = field.id,
            name = field.name,
            structure = when (field.structure!!) {
                TypeData.Structure.scalar -> Structure.scalar
                TypeData.Structure.collection -> Structure.collection
                TypeData.Structure.map -> Structure.map
            },
            keyName = field.keyName,
            isNoImpl = field.noImpl,
            keyPattern = field.keyPattern,
            boolDefault = field.boolDefault,
            _type = field.type,
            _parentPath = field.parentPath,
            _plural = field.plural
        )

    }

    class Type(
        val typeData: KTypeData,
        val name: String,
        val isNoGen: Boolean,
        val fields: MutableMap<String, Field>,
        val enumValues: List<String>,
        val extensionOf: String?,
        val extendInterfaces: List<String>,
        val abstract: Boolean,
        val imports: Map<String, Collection<String>>,
        private val _discriminator: String?,
        private val _discriminatorValue: String?,
    ) {

        val implType: String
            get() = if (isNoGen) name else getImplType(name)

        val lcName: String
            get() = lcFirst(name)

        val discriminator: String?
            get() = _discriminator ?: typeData.discriminator

        val discriminatorValue: String
            get() = _discriminatorValue ?: name

        val intfExtendsDecl: String
            get() = " extends " + extendInterfaces.joinToString(",")

        constructor(typeData: KTypeData, type: TypeData.Type) : this(
            typeData = typeData,
            name = type.name,
            isNoGen = type.noGen,
            fields = mutableMapOf(),
            enumValues = type.enumValues,
            extensionOf = type.extensionOf,
            extendInterfaces = type.extendInterfaces,
            abstract = type.abstractType,
            imports = type.imports,
            _discriminator = type.discriminator,
            _discriminatorValue = type.discriminatorValue
        ) {
            fields.apply {
                for (key in type.fields.keys) {
                    put(key, Field(this@Type, type.fields[key]!!))
                }
            }
        }


        fun getRequiredImports(vararg moduleTypes: String): MutableCollection<String> {
            val results: MutableSet<String> = LinkedHashSet(extendInterfaces)
            for (moduleType in moduleTypes) {
                if (imports[moduleType] != null) {
                    imports[moduleType]?.let { results.addAll(it) }
                }
            }
            return results
        }

        companion object {
            fun getImplType(typeName: String): String {
                return when (typeName) {
                    "String", "Number", "Boolean", "Primitive" -> typeName + "Overlay"
                    "Int" -> "IntegerOverlay"
                    "Any" -> "ObjectOverlay"
                    else -> typeName + "Impl"
                }
            }
        }

    }

    constructor(type: TypeData) : this(
        imports = type.imports,
        typeMap = mutableMapOf(),
        modelType = type.modelType,
        discriminator = type.discriminator
    ) {
        typeMap.apply {
            for (key in type.typeMap.keys) {
                put(key, Type(this@KTypeData, type.typeMap[key]!!))
            }
        }
    }

    companion object {
        private fun lcFirst(s: String): String {
            return s.substring(0, 1).lowercase(Locale.getDefault()) + s.substring(1)
        }
    }

}
