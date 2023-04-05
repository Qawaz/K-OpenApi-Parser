package com.reprezen.kaizen.oasparser.ovl3;

import com.reprezen.kaizen.oasparser.model3.*;
import com.reprezen.jsonoverlay.MapOverlay;
import java.util.stream.Collectors;
import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.jsonoverlay.Builder;
import com.fasterxml.jackson.databind.JsonNode;
import com.reprezen.jsonoverlay.PropertiesOverlay;
import com.fasterxml.jackson.core.JsonPointer;
import com.reprezen.kaizen.oasparser.ovl3.EncodingPropertyImpl;
import com.reprezen.jsonoverlay.ReferenceManager;
import com.reprezen.kaizen.oasparser.ovl3.SchemaImpl;
import com.reprezen.jsonoverlay.ObjectOverlay;
import com.reprezen.jsonoverlay.JsonOverlay;
import com.reprezen.kaizen.oasparser.ovl3.ExampleImpl;
import kotlin.collections.Map;

class MediaTypeImpl : PropertiesOverlay<MediaType> ,MediaType {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(mediaType : MediaType?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(mediaType, parent, Companion.factory, refMgr)

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
	// EncodingProperty
	override fun getEncodingProperties() : MutableMap<String, EncodingProperty> {
		return _getMap("encodingProperties", EncodingProperty::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getEncodingProperties(elaborate : Boolean) : MutableMap<String, EncodingProperty> {
		return _getMap("encodingProperties", elaborate, EncodingProperty::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasEncodingProperties() : Boolean {
		return _isPresent("encodingProperties")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasEncodingProperty(name : String) : Boolean {
		return _getMap("encodingProperties", EncodingProperty::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getEncodingProperty(name : String) : EncodingProperty? {
		return _get("encodingProperties", name, EncodingProperty::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setEncodingProperties(encodingProperties : MutableMap<String, EncodingProperty>) {
		_setMap("encodingProperties", encodingProperties, EncodingProperty::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setEncodingProperty(name : String, encodingProperty : EncodingProperty) {
		_set("encodingProperties", name, encodingProperty, EncodingProperty::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeEncodingProperty(name : String) {
		_remove("encodingProperties", name, EncodingProperty::class.java)
	}

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
	override fun _elaborateJson() {
		super._elaborateJson()
		_createScalar("schema", "schema", SchemaImpl.factory)
		_createMap("examples", "examples", ExampleImpl.factory, "[a-zA-Z0-9\\._-]+")
		_createScalar("example", "example", ObjectOverlay.factory)
		_createMap("encodingProperties", "encoding", EncodingPropertyImpl.factory, null)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<MediaType> {
		return Companion.factory
	}

	companion object {
		const val F_schema : String = "schema"

		const val F_examples : String = "examples"

		const val F_example : String = "example"

		const val F_encodingProperties : String = "encodingProperties"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<MediaType>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in MediaType>> {
				return MediaTypeImpl::class.java
			}
		
			override fun _create(mediaType : MediaType?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<MediaType> {
				return MediaTypeImpl(mediaType, parent, refMgr)
			}
		
			override fun _create(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<MediaType> {
				return MediaTypeImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(mediaType : MediaType) : Class<out MediaType> {
			return MediaType::class.java
		}

		private fun getSubtypeOf(json : JsonNode) : Class<out MediaType> {
			return MediaType::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<MediaType> {
			return Builder<MediaType>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : MediaType {
			return builder(modelMember).build() as MediaType
		}
	}

}
