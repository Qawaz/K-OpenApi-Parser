package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.kaizen.oasparser.model3.Schema
import com.reprezen.kaizen.oasparser.model3.Discriminator
import com.reprezen.kaizen.oasparser.model3.ExternalDocs
import com.reprezen.kaizen.oasparser.model3.Xml
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.List
import kotlin.collections.Map

interface Schema : IJsonOverlay<Schema>, IModelPart<OpenApi3, Schema> {

    fun getName() : String?

	// Title
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTitle() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setTitle(title : String)

	// MultipleOf
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMultipleOf() : Number?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setMultipleOf(multipleOf : Number)

	// Maximum
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMaximum() : Number?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setMaximum(maximum : Number)

	// ExclusiveMaximum
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExclusiveMaximum() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isExclusiveMaximum() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExclusiveMaximum(exclusiveMaximum : Boolean)

	// Minimum
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMinimum() : Number?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setMinimum(minimum : Number)

	// ExclusiveMinimum
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExclusiveMinimum() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isExclusiveMinimum() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExclusiveMinimum(exclusiveMinimum : Boolean)

	// MaxLength
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMaxLength() : Int?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setMaxLength(maxLength : Int)

	// MinLength
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMinLength() : Int?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setMinLength(minLength : Int)

	// Pattern
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPattern() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPattern(pattern : String)

	// MaxItems
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMaxItems() : Int?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setMaxItems(maxItems : Int)

	// MinItems
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMinItems() : Int?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setMinItems(minItems : Int)

	// UniqueItems
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getUniqueItems() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isUniqueItems() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setUniqueItems(uniqueItems : Boolean)

	// MaxProperties
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMaxProperties() : Int?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setMaxProperties(maxProperties : Int)

	// MinProperties
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMinProperties() : Int?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setMinProperties(minProperties : Int)

	// RequiredField
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequiredFields() : List<String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequiredFields(elaborate : Boolean) : List<String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasRequiredFields() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequiredField(index : Int) : String

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setRequiredFields(requiredFields : MutableList<String>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setRequiredField(index : Int, requiredField : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addRequiredField(requiredField : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertRequiredField(index : Int,requiredField : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeRequiredField(index : Int)

	// Enum
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEnums() : List<Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEnums(elaborate : Boolean) : List<Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasEnums() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEnum(index : Int) : Any

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setEnums(enums : MutableList<Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setEnum(index : Int, enumValue : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addEnum(enumValue : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertEnum(index : Int,enumValue : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeEnum(index : Int)

	// Type
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getType() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setType(type : String)

	// AllOfSchema
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAllOfSchemas() : List<Schema>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAllOfSchemas(elaborate : Boolean) : List<Schema>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasAllOfSchemas() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAllOfSchema(index : Int) : Schema

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAllOfSchemas(allOfSchemas : MutableList<Schema>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAllOfSchema(index : Int, allOfSchema : Schema)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addAllOfSchema(allOfSchema : Schema)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertAllOfSchema(index : Int,allOfSchema : Schema)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeAllOfSchema(index : Int)

	// OneOfSchema
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOneOfSchemas() : List<Schema>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOneOfSchemas(elaborate : Boolean) : List<Schema>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasOneOfSchemas() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOneOfSchema(index : Int) : Schema

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOneOfSchemas(oneOfSchemas : MutableList<Schema>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOneOfSchema(index : Int, oneOfSchema : Schema)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addOneOfSchema(oneOfSchema : Schema)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertOneOfSchema(index : Int,oneOfSchema : Schema)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeOneOfSchema(index : Int)

	// AnyOfSchema
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAnyOfSchemas() : List<Schema>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAnyOfSchemas(elaborate : Boolean) : List<Schema>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasAnyOfSchemas() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAnyOfSchema(index : Int) : Schema

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAnyOfSchemas(anyOfSchemas : MutableList<Schema>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAnyOfSchema(index : Int, anyOfSchema : Schema)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addAnyOfSchema(anyOfSchema : Schema)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertAnyOfSchema(index : Int,anyOfSchema : Schema)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeAnyOfSchema(index : Int)

	// NotSchema
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getNotSchema() : Schema?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getNotSchema(elaborate : Boolean) : Schema?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setNotSchema(notSchema : Schema)

	// ItemsSchema
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getItemsSchema() : Schema?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getItemsSchema(elaborate : Boolean) : Schema?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setItemsSchema(itemsSchema : Schema)

	// Property
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getProperties() : MutableMap<String, Schema>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getProperties(elaborate : Boolean) : MutableMap<String, Schema>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasProperties() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasProperty(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getProperty(name : String) : Schema?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setProperties(properties : MutableMap<String, Schema>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setProperty(name : String,property : Schema)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeProperty(name : String)

	// AdditionalPropertiesSchema
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAdditionalPropertiesSchema() : Schema?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAdditionalPropertiesSchema(elaborate : Boolean) : Schema?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAdditionalPropertiesSchema(additionalPropertiesSchema : Schema)

	// AdditionalProperties
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAdditionalProperties() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isAdditionalProperties() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAdditionalProperties(additionalProperties : Boolean)

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

	// Format
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getFormat() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setFormat(format : String)

	// Default
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDefault() : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDefault(defaultValue : Any)

	// Nullable
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getNullable() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isNullable() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setNullable(nullable : Boolean)

	// Discriminator
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDiscriminator() : Discriminator?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDiscriminator(elaborate : Boolean) : Discriminator?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDiscriminator(discriminator : Discriminator)

	// ReadOnly
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getReadOnly() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isReadOnly() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setReadOnly(readOnly : Boolean)

	// WriteOnly
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getWriteOnly() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isWriteOnly() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setWriteOnly(writeOnly : Boolean)

	// Xml
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getXml() : Xml?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getXml(elaborate : Boolean) : Xml?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setXml(xml : Xml)

	// ExternalDocs
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExternalDocs() : ExternalDocs?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExternalDocs(elaborate : Boolean) : ExternalDocs?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExternalDocs(externalDocs : ExternalDocs)

	// Example
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExample() : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExample(example : Any)

	// Deprecated
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDeprecated() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isDeprecated() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDeprecated(deprecated : Boolean)

	// Extension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtensions(elaborate : Boolean) : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExtensions() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExtension(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtension(name : String) : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExtensions(extensions : MutableMap<String, Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExtension(name : String,extension : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeExtension(name : String)

}
