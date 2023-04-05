package com.reprezen.kaizen.oasparser.ovl3;

import com.reprezen.kaizen.oasparser.model3.*;
import com.reprezen.jsonoverlay.MapOverlay;
import java.util.stream.Collectors;
import com.reprezen.jsonoverlay.StringOverlay;
import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.jsonoverlay.Builder;
import com.fasterxml.jackson.databind.JsonNode;
import com.reprezen.jsonoverlay.PropertiesOverlay;
import com.fasterxml.jackson.core.JsonPointer;
import com.reprezen.jsonoverlay.ReferenceManager;
import com.reprezen.kaizen.oasparser.ovl3.SchemaImpl;
import com.reprezen.kaizen.oasparser.ovl3.MediaTypeImpl;
import com.reprezen.jsonoverlay.ObjectOverlay;
import com.reprezen.jsonoverlay.JsonOverlay;
import com.reprezen.jsonoverlay.Overlay;
import com.reprezen.kaizen.oasparser.ovl3.ExampleImpl;
import kotlin.collections.Map;
import com.reprezen.jsonoverlay.BooleanOverlay;

class ParameterImpl : PropertiesOverlay<Parameter> ,Parameter {

    private val overlay : Overlay<Parameter> = Overlay.Companion.of(this);

    override fun getKey() : String? {
        return if(overlay.parent is MapOverlay<*>) overlay.pathInParent else null
    }

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(parameter : Parameter?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(parameter, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Extension
	override fun getExtensions() : MutableMap<String, Any> {
		return _getMap("extensions", Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExtensions(elaborate : Boolean) : MutableMap<String, Any> {
		return _getMap("extensions", elaborate, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExtensions() : Boolean {
		return _isPresent("extensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExtension(name : String) : Boolean {
		return _getMap("extensions", Any::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExtension(name : String) : Any? {
		return _get("extensions", name, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExtensions(extensions : MutableMap<String, Any>) {
		_setMap("extensions", extensions, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExtension(name : String, extension : Any) {
		_set("extensions", name, extension, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeExtension(name : String) {
		_remove("extensions", name, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ContentMediaType
	override fun getContentMediaTypes() : MutableMap<String, MediaType> {
		return _getMap("contentMediaTypes", MediaType::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getContentMediaTypes(elaborate : Boolean) : MutableMap<String, MediaType> {
		return _getMap("contentMediaTypes", elaborate, MediaType::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasContentMediaTypes() : Boolean {
		return _isPresent("contentMediaTypes")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasContentMediaType(name : String) : Boolean {
		return _getMap("contentMediaTypes", MediaType::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getContentMediaType(name : String) : MediaType? {
		return _get("contentMediaTypes", name, MediaType::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setContentMediaTypes(contentMediaTypes : MutableMap<String, MediaType>) {
		_setMap("contentMediaTypes", contentMediaTypes, MediaType::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setContentMediaType(name : String, contentMediaType : MediaType) {
		_set("contentMediaTypes", name, contentMediaType, MediaType::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeContentMediaType(name : String) {
		_remove("contentMediaTypes", name, MediaType::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Example
	override fun getExamples() : MutableMap<String, Example> {
		return _getMap("examples", Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExamples(elaborate : Boolean) : MutableMap<String, Example> {
		return _getMap("examples", elaborate, Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExamples() : Boolean {
		return _isPresent("examples")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExample(name : String) : Boolean {
		return _getMap("examples", Example::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExample(name : String) : Example? {
		return _get("examples", name, Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExamples(examples : MutableMap<String, Example>) {
		_setMap("examples", examples, Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExample(name : String, example : Example) {
		_set("examples", name, example, Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeExample(name : String) {
		_remove("examples", name, Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Example
	override fun getExample() : Any? {
		return _get("example", Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExample(example : Any) {
		_setScalar("example", example, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Schema
	override fun getSchema() : Schema? {
		return _get("schema", Schema::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getSchema(elaborate : Boolean) : Schema? {
		return _get("schema", elaborate, Schema::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSchema(schema : Schema) {
		_setScalar("schema", schema, Schema::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// AllowReserved
	override fun getAllowReserved() : Boolean? {
		return _get("allowReserved", Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isAllowReserved() : Boolean {
		return _get("allowReserved", Boolean::class.java) ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAllowReserved(allowReserved : Boolean) {
		_setScalar("allowReserved", allowReserved, Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Explode
	override fun getExplode() : Boolean? {
		return _get("explode", Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isExplode() : Boolean {
		return _get("explode", Boolean::class.java) ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExplode(explode : Boolean) {
		_setScalar("explode", explode, Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Style
	override fun getStyle() : String? {
		return _get("style", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setStyle(style : String) {
		_setScalar("style", style, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// AllowEmptyValue
	override fun getAllowEmptyValue() : Boolean? {
		return _get("allowEmptyValue", Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isAllowEmptyValue() : Boolean {
		return _get("allowEmptyValue", Boolean::class.java) ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAllowEmptyValue(allowEmptyValue : Boolean) {
		_setScalar("allowEmptyValue", allowEmptyValue, Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Deprecated
	override fun getDeprecated() : Boolean? {
		return _get("deprecated", Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isDeprecated() : Boolean {
		return _get("deprecated", Boolean::class.java) ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setDeprecated(deprecated : Boolean) {
		_setScalar("deprecated", deprecated, Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Required
	override fun getRequired() : Boolean? {
		return _get("required", Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isRequired() : Boolean {
		return _get("required", Boolean::class.java) ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequired(required : Boolean) {
		_setScalar("required", required, Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Description
	override fun getDescription() : String? {
		return _get("description", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setDescription(description : String) {
		_setScalar("description", description, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Name
	override fun getName() : String? {
		return _get("name", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setName(name : String) {
		_setScalar("name", name, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// In
	override fun getIn() : String? {
		return _get("inValue", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setIn(inValue : String) {
		_setScalar("inValue", inValue, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _elaborateJson() {
		super._elaborateJson()
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
		_createMap("contentMediaTypes", "content", MediaTypeImpl.factory, null)
		_createMap("examples", "examples", ExampleImpl.factory, "[a-zA-Z0-9\\._-]+")
		_createScalar("example", "example", ObjectOverlay.factory)
		_createScalar("schema", "schema", SchemaImpl.factory)
		_createScalar("allowReserved", "allowReserved", BooleanOverlay.factory)
		_createScalar("explode", "explode", BooleanOverlay.factory)
		_createScalar("style", "style", StringOverlay.factory)
		_createScalar("allowEmptyValue", "allowEmptyValue", BooleanOverlay.factory)
		_createScalar("deprecated", "deprecated", BooleanOverlay.factory)
		_createScalar("required", "required", BooleanOverlay.factory)
		_createScalar("description", "description", StringOverlay.factory)
		_createScalar("name", "name", StringOverlay.factory)
		_createScalar("inValue", "in", StringOverlay.factory)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Parameter> {
		return Companion.factory
	}

	companion object {
		const val F_extensions : String = "extensions"

		const val F_contentMediaTypes : String = "contentMediaTypes"

		const val F_examples : String = "examples"

		const val F_example : String = "example"

		const val F_schema : String = "schema"

		const val F_allowReserved : String = "allowReserved"

		const val F_explode : String = "explode"

		const val F_style : String = "style"

		const val F_allowEmptyValue : String = "allowEmptyValue"

		const val F_deprecated : String = "deprecated"

		const val F_required : String = "required"

		const val F_description : String = "description"

		const val F_name : String = "name"

		const val F_inValue : String = "inValue"

		val factory = object : OverlayFactory<Parameter>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Parameter>> {
				return ParameterImpl::class.java
			}
		
			override fun _create(parameter : Parameter?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Parameter> {
				return ParameterImpl(parameter, parent, refMgr)
			}
		
			override fun _create(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Parameter> {
				return ParameterImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(parameter : Parameter) : Class<out Parameter> {
			return Parameter::class.java
		}

		private fun getSubtypeOf(json : JsonNode) : Class<out Parameter> {
			return Parameter::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<Parameter> {
			return Builder<Parameter>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Parameter {
			return builder(modelMember).build() as Parameter
		}
	}

}
