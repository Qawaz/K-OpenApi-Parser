package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.ListOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.JsonPointer
import com.reprezen.kaizen.oasparser.ovl3.XmlImpl
import com.reprezen.jsonoverlay.ReferenceManager
import com.reprezen.jsonoverlay.IntegerOverlay
import com.reprezen.kaizen.oasparser.ovl3.SchemaImpl
import kotlin.collections.List
import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.Overlay
import java.util.stream.Collectors
import com.reprezen.kaizen.oasparser.ovl3.DiscriminatorImpl
import com.reprezen.jsonoverlay.parser.Generated
import java.util.Optional
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.NumberOverlay
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.ObjectOverlay
import kotlin.collections.Map
import com.reprezen.jsonoverlay.BooleanOverlay
import com.reprezen.kaizen.oasparser.ovl3.ExternalDocsImpl

class SchemaImpl : PropertiesOverlay<Schema> ,Schema {

    private val overlay : Overlay<Schema> = Overlay.Companion.of(this);

    override fun getName() : String? {
        return if(overlay.parent is MapOverlay<*>) overlay.pathInParent else null
    }

    override fun findByPointer(path: JsonPointer): JsonOverlay<*>? {
        return if(path.segments.firstOrNull() == "additionalProperties"){
            val tail = JsonPointer(path.segments.drop(1))
            if(json != null && tail.navigate(json!!) != null){
                _getOverlay<Boolean>("additionalProperties") as BooleanOverlay
            } else {
                _get<SchemaImpl>("additionalPropertiesSchema")?.findByPointer(tail)
            }
        } else {
            super<Schema>.findByPointer(path)
        }
    }

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(schema : Schema?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(schema, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Title
	override fun getTitle() : String? {
		return _get("title")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setTitle(title : String) {
		_setScalar("title", title)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// MultipleOf
	override fun getMultipleOf() : Number? {
		return _get("multipleOf")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setMultipleOf(multipleOf : Number) {
		_setScalar("multipleOf", multipleOf)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Maximum
	override fun getMaximum() : Number? {
		return _get("maximum")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setMaximum(maximum : Number) {
		_setScalar("maximum", maximum)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ExclusiveMaximum
	override fun getExclusiveMaximum() : Boolean? {
		return _get("exclusiveMaximum")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isExclusiveMaximum() : Boolean {
		return _get("exclusiveMaximum") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExclusiveMaximum(exclusiveMaximum : Boolean) {
		_setScalar("exclusiveMaximum", exclusiveMaximum)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Minimum
	override fun getMinimum() : Number? {
		return _get("minimum")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setMinimum(minimum : Number) {
		_setScalar("minimum", minimum)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ExclusiveMinimum
	override fun getExclusiveMinimum() : Boolean? {
		return _get("exclusiveMinimum")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isExclusiveMinimum() : Boolean {
		return _get("exclusiveMinimum") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExclusiveMinimum(exclusiveMinimum : Boolean) {
		_setScalar("exclusiveMinimum", exclusiveMinimum)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// MaxLength
	override fun getMaxLength() : Int? {
		return _get("maxLength")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setMaxLength(maxLength : Int) {
		_setScalar("maxLength", maxLength)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// MinLength
	override fun getMinLength() : Int? {
		return _get("minLength")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setMinLength(minLength : Int) {
		_setScalar("minLength", minLength)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Pattern
	override fun getPattern() : String? {
		return _get("pattern")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setPattern(pattern : String) {
		_setScalar("pattern", pattern)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// MaxItems
	override fun getMaxItems() : Int? {
		return _get("maxItems")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setMaxItems(maxItems : Int) {
		_setScalar("maxItems", maxItems)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// MinItems
	override fun getMinItems() : Int? {
		return _get("minItems")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setMinItems(minItems : Int) {
		_setScalar("minItems", minItems)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// UniqueItems
	override fun getUniqueItems() : Boolean? {
		return _get("uniqueItems")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isUniqueItems() : Boolean {
		return _get("uniqueItems") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setUniqueItems(uniqueItems : Boolean) {
		_setScalar("uniqueItems", uniqueItems)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// MaxProperties
	override fun getMaxProperties() : Int? {
		return _get("maxProperties")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setMaxProperties(maxProperties : Int) {
		_setScalar("maxProperties", maxProperties)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// MinProperties
	override fun getMinProperties() : Int? {
		return _get("minProperties")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setMinProperties(minProperties : Int) {
		_setScalar("minProperties", minProperties)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// RequiredField
	override fun getRequiredFields() : List<String> {
		return _getList("requiredFields")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getRequiredFields(elaborate : Boolean) : List<String> {
		return _getList("requiredFields")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasRequiredFields() : Boolean {
		return _isPresent("requiredFields")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getRequiredField(index : Int) : String {
		return _get("requiredFields", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequiredFields(requiredFields : MutableList<String>) {
		_setList("requiredFields", requiredFields)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequiredField(index : Int, requiredField : String) {
		_set("requiredFields", index, requiredField)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addRequiredField(requiredField : String) {
		_add("requiredFields", requiredField)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertRequiredField(index : Int, requiredField : String) {
		_insert("requiredFields", index, requiredField)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeRequiredField(index : Int) {
		_remove("requiredFields", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Enum
	override fun getEnums() : List<Any> {
		return _getList("enums")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getEnums(elaborate : Boolean) : List<Any> {
		return _getList("enums")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasEnums() : Boolean {
		return _isPresent("enums")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getEnum(index : Int) : Any {
		return _get("enums", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setEnums(enums : MutableList<Any>) {
		_setList("enums", enums)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setEnum(index : Int, enumValue : Any) {
		_set("enums", index, enumValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addEnum(enumValue : Any) {
		_add("enums", enumValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertEnum(index : Int, enumValue : Any) {
		_insert("enums", index, enumValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeEnum(index : Int) {
		_remove("enums", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Type
	override fun getType() : String? {
		return _get("type")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setType(type : String) {
		_setScalar("type", type)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// AllOfSchema
	override fun getAllOfSchemas() : List<Schema> {
		return _getList("allOfSchemas")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getAllOfSchemas(elaborate : Boolean) : List<Schema> {
		return _getList("allOfSchemas")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasAllOfSchemas() : Boolean {
		return _isPresent("allOfSchemas")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getAllOfSchema(index : Int) : Schema {
		return _get("allOfSchemas", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAllOfSchemas(allOfSchemas : MutableList<Schema>) {
		_setList("allOfSchemas", allOfSchemas)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAllOfSchema(index : Int, allOfSchema : Schema) {
		_set("allOfSchemas", index, allOfSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addAllOfSchema(allOfSchema : Schema) {
		_add("allOfSchemas", allOfSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertAllOfSchema(index : Int, allOfSchema : Schema) {
		_insert("allOfSchemas", index, allOfSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeAllOfSchema(index : Int) {
		_remove("allOfSchemas", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// OneOfSchema
	override fun getOneOfSchemas() : List<Schema> {
		return _getList("oneOfSchemas")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getOneOfSchemas(elaborate : Boolean) : List<Schema> {
		return _getList("oneOfSchemas")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasOneOfSchemas() : Boolean {
		return _isPresent("oneOfSchemas")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getOneOfSchema(index : Int) : Schema {
		return _get("oneOfSchemas", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setOneOfSchemas(oneOfSchemas : MutableList<Schema>) {
		_setList("oneOfSchemas", oneOfSchemas)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setOneOfSchema(index : Int, oneOfSchema : Schema) {
		_set("oneOfSchemas", index, oneOfSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addOneOfSchema(oneOfSchema : Schema) {
		_add("oneOfSchemas", oneOfSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertOneOfSchema(index : Int, oneOfSchema : Schema) {
		_insert("oneOfSchemas", index, oneOfSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeOneOfSchema(index : Int) {
		_remove("oneOfSchemas", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// AnyOfSchema
	override fun getAnyOfSchemas() : List<Schema> {
		return _getList("anyOfSchemas")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getAnyOfSchemas(elaborate : Boolean) : List<Schema> {
		return _getList("anyOfSchemas")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasAnyOfSchemas() : Boolean {
		return _isPresent("anyOfSchemas")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getAnyOfSchema(index : Int) : Schema {
		return _get("anyOfSchemas", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAnyOfSchemas(anyOfSchemas : MutableList<Schema>) {
		_setList("anyOfSchemas", anyOfSchemas)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAnyOfSchema(index : Int, anyOfSchema : Schema) {
		_set("anyOfSchemas", index, anyOfSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addAnyOfSchema(anyOfSchema : Schema) {
		_add("anyOfSchemas", anyOfSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertAnyOfSchema(index : Int, anyOfSchema : Schema) {
		_insert("anyOfSchemas", index, anyOfSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeAnyOfSchema(index : Int) {
		_remove("anyOfSchemas", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// NotSchema
	override fun getNotSchema() : Schema? {
		return _get("notSchema")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getNotSchema(elaborate : Boolean) : Schema? {
		return _get("notSchema")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setNotSchema(notSchema : Schema) {
		_setScalar("notSchema", notSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ItemsSchema
	override fun getItemsSchema() : Schema? {
		return _get("itemsSchema")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getItemsSchema(elaborate : Boolean) : Schema? {
		return _get("itemsSchema")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setItemsSchema(itemsSchema : Schema) {
		_setScalar("itemsSchema", itemsSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Property
	override fun getProperties() : MutableMap<String, Schema> {
		return _getMap("properties")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getProperties(elaborate : Boolean) : MutableMap<String, Schema> {
		return _getMap("properties")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasProperties() : Boolean {
		return _isPresent("properties")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasProperty(name : String) : Boolean {
		return _getMap<Schema>("properties").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getProperty(name : String) : Schema? {
		return _get("properties", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setProperties(properties : MutableMap<String, Schema>) {
		_setMap("properties", properties)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setProperty(name : String, property : Schema) {
		_set("properties", name, property)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeProperty(name : String) {
		_remove("properties", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// AdditionalPropertiesSchema
	override fun getAdditionalPropertiesSchema() : Schema? {
		return _get("additionalPropertiesSchema")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getAdditionalPropertiesSchema(elaborate : Boolean) : Schema? {
		return _get("additionalPropertiesSchema")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAdditionalPropertiesSchema(additionalPropertiesSchema : Schema) {
		_setScalar("additionalPropertiesSchema", additionalPropertiesSchema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// AdditionalProperties
	override fun getAdditionalProperties() : Boolean? {
		return _get("additionalProperties")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isAdditionalProperties() : Boolean {
		return _get("additionalProperties") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAdditionalProperties(additionalProperties : Boolean) {
		_setScalar("additionalProperties", additionalProperties)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Description
	override fun getDescription() : String? {
		return _get("description")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setDescription(description : String) {
		_setScalar("description", description)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Format
	override fun getFormat() : String? {
		return _get("format")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setFormat(format : String) {
		_setScalar("format", format)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Default
	override fun getDefault() : Any? {
		return _get("defaultValue")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setDefault(defaultValue : Any) {
		_setScalar("defaultValue", defaultValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Nullable
	override fun getNullable() : Boolean? {
		return _get("nullable")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isNullable() : Boolean {
		return _get("nullable") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setNullable(nullable : Boolean) {
		_setScalar("nullable", nullable)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Discriminator
	override fun getDiscriminator() : Discriminator? {
		return _get("discriminator")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getDiscriminator(elaborate : Boolean) : Discriminator? {
		return _get("discriminator")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setDiscriminator(discriminator : Discriminator) {
		_setScalar("discriminator", discriminator)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ReadOnly
	override fun getReadOnly() : Boolean? {
		return _get("readOnly")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isReadOnly() : Boolean {
		return _get("readOnly") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setReadOnly(readOnly : Boolean) {
		_setScalar("readOnly", readOnly)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// WriteOnly
	override fun getWriteOnly() : Boolean? {
		return _get("writeOnly")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isWriteOnly() : Boolean {
		return _get("writeOnly") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setWriteOnly(writeOnly : Boolean) {
		_setScalar("writeOnly", writeOnly)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Xml
	override fun getXml() : Xml? {
		return _get("xml")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getXml(elaborate : Boolean) : Xml? {
		return _get("xml")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setXml(xml : Xml) {
		_setScalar("xml", xml)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ExternalDocs
	override fun getExternalDocs() : ExternalDocs? {
		return _get("externalDocs")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExternalDocs(elaborate : Boolean) : ExternalDocs? {
		return _get("externalDocs")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExternalDocs(externalDocs : ExternalDocs) {
		_setScalar("externalDocs", externalDocs)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Example
	override fun getExample() : Any? {
		return _get("example")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExample(example : Any) {
		_setScalar("example", example)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Deprecated
	override fun getDeprecated() : Boolean? {
		return _get("deprecated")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isDeprecated() : Boolean {
		return _get("deprecated") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setDeprecated(deprecated : Boolean) {
		_setScalar("deprecated", deprecated)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Extension
	override fun getExtensions() : MutableMap<String, Any> {
		return _getMap("extensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExtensions(elaborate : Boolean) : MutableMap<String, Any> {
		return _getMap("extensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExtensions() : Boolean {
		return _isPresent("extensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExtension(name : String) : Boolean {
		return _getMap<Any>("extensions").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExtension(name : String) : Any? {
		return _get("extensions", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExtensions(extensions : MutableMap<String, Any>) {
		_setMap("extensions", extensions)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExtension(name : String, extension : Any) {
		_set("extensions", name, extension)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeExtension(name : String) {
		_remove("extensions", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _elaborateJson() {
		super._elaborateJson()
		_createScalar("title", "title", StringOverlay.factory)
		_createScalar("multipleOf", "multipleOf", NumberOverlay.factory)
		_createScalar("maximum", "maximum", NumberOverlay.factory)
		_createScalar("exclusiveMaximum", "exclusiveMaximum", BooleanOverlay.factory)
		_createScalar("minimum", "minimum", NumberOverlay.factory)
		_createScalar("exclusiveMinimum", "exclusiveMinimum", BooleanOverlay.factory)
		_createScalar("maxLength", "maxLength", IntegerOverlay.factory)
		_createScalar("minLength", "minLength", IntegerOverlay.factory)
		_createScalar("pattern", "pattern", StringOverlay.factory)
		_createScalar("maxItems", "maxItems", IntegerOverlay.factory)
		_createScalar("minItems", "minItems", IntegerOverlay.factory)
		_createScalar("uniqueItems", "uniqueItems", BooleanOverlay.factory)
		_createScalar("maxProperties", "maxProperties", IntegerOverlay.factory)
		_createScalar("minProperties", "minProperties", IntegerOverlay.factory)
		_createList("requiredFields", "required", StringOverlay.factory)
		_createList("enums", "enum", ObjectOverlay.factory)
		_createScalar("type", "type", StringOverlay.factory)
		_createList("allOfSchemas", "allOf", SchemaImpl.factory)
		_createList("oneOfSchemas", "oneOf", SchemaImpl.factory)
		_createList("anyOfSchemas", "anyOf", SchemaImpl.factory)
		_createScalar("notSchema", "not", SchemaImpl.factory)
		_createScalar("itemsSchema", "items", SchemaImpl.factory)
		_createMap("properties", "properties", SchemaImpl.factory, null)
		_createScalar("additionalPropertiesSchema", "additionalProperties", SchemaImpl.factory)
		_createScalar("additionalProperties", "additionalProperties", BooleanOverlay.factory)
		_createScalar("description", "description", StringOverlay.factory)
		_createScalar("format", "format", StringOverlay.factory)
		_createScalar("defaultValue", "default", ObjectOverlay.factory)
		_createScalar("nullable", "nullable", BooleanOverlay.factory)
		_createScalar("discriminator", "discriminator", DiscriminatorImpl.factory)
		_createScalar("readOnly", "readOnly", BooleanOverlay.factory)
		_createScalar("writeOnly", "writeOnly", BooleanOverlay.factory)
		_createScalar("xml", "xml", XmlImpl.factory)
		_createScalar("externalDocs", "externalDocs", ExternalDocsImpl.factory)
		_createScalar("example", "example", ObjectOverlay.factory)
		_createScalar("deprecated", "deprecated", BooleanOverlay.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Schema> {
		return Companion.factory
	}

	companion object {
		const val F_title : String = "title"

		const val F_multipleOf : String = "multipleOf"

		const val F_maximum : String = "maximum"

		const val F_exclusiveMaximum : String = "exclusiveMaximum"

		const val F_minimum : String = "minimum"

		const val F_exclusiveMinimum : String = "exclusiveMinimum"

		const val F_maxLength : String = "maxLength"

		const val F_minLength : String = "minLength"

		const val F_pattern : String = "pattern"

		const val F_maxItems : String = "maxItems"

		const val F_minItems : String = "minItems"

		const val F_uniqueItems : String = "uniqueItems"

		const val F_maxProperties : String = "maxProperties"

		const val F_minProperties : String = "minProperties"

		const val F_requiredFields : String = "requiredFields"

		const val F_enums : String = "enums"

		const val F_type : String = "type"

		const val F_allOfSchemas : String = "allOfSchemas"

		const val F_oneOfSchemas : String = "oneOfSchemas"

		const val F_anyOfSchemas : String = "anyOfSchemas"

		const val F_notSchema : String = "notSchema"

		const val F_itemsSchema : String = "itemsSchema"

		const val F_properties : String = "properties"

		const val F_additionalPropertiesSchema : String = "additionalPropertiesSchema"

		const val F_additionalProperties : String = "additionalProperties"

		const val F_description : String = "description"

		const val F_format : String = "format"

		const val F_defaultValue : String = "defaultValue"

		const val F_nullable : String = "nullable"

		const val F_discriminator : String = "discriminator"

		const val F_readOnly : String = "readOnly"

		const val F_writeOnly : String = "writeOnly"

		const val F_xml : String = "xml"

		const val F_externalDocs : String = "externalDocs"

		const val F_example : String = "example"

		const val F_deprecated : String = "deprecated"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<Schema>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Schema>> {
				return SchemaImpl::class.java
			}
		
			override fun _create(schema : Schema?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Schema> {
				return SchemaImpl(schema, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Schema> {
				return SchemaImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(schema : Schema) : Class<out Schema> {
			return Schema::class.java
		}

		private fun getSubtypeOf(json : JsonElement) : Class<out Schema> {
			return Schema::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<Schema> {
			return Builder<Schema>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Schema {
			return builder(modelMember).build() as Schema
		}
	}

}
