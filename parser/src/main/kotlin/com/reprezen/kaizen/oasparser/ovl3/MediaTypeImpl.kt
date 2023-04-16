package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.MapOverlay
import java.util.stream.Collectors
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.JsonPointer
import com.reprezen.kaizen.oasparser.ovl3.EncodingPropertyImpl
import com.reprezen.jsonoverlay.ReferenceManager
import com.reprezen.kaizen.oasparser.ovl3.SchemaImpl
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.kaizen.oasparser.ovl3.ExampleImpl
import kotlin.collections.Map

class MediaTypeImpl : PropertiesOverlay<MediaType> ,MediaType {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(mediaType : MediaType?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(mediaType, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Schema
	override fun getSchema() : Schema? {
		return _get("schema")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSchema(schema : Schema) {
		_setScalar("schema", schema)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Example
	override fun getExamples() : MutableMap<String, Example> {
		return _getMap("examples")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExamples() : Boolean {
		return _isPresent("examples")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExample(name : String) : Boolean {
		return _getMap<Example>("examples").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExample(name : String) : Example? {
		return _get("examples", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExamples(examples : MutableMap<String, Example>) {
		_setMap("examples", examples)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExample(name : String, example : Example) {
		_set("examples", name, example)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeExample(name : String) {
		_remove("examples", name)
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
	// EncodingProperty
	override fun getEncodingProperties() : MutableMap<String, EncodingProperty> {
		return _getMap("encodingProperties")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasEncodingProperties() : Boolean {
		return _isPresent("encodingProperties")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasEncodingProperty(name : String) : Boolean {
		return _getMap<EncodingProperty>("encodingProperties").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getEncodingProperty(name : String) : EncodingProperty? {
		return _get("encodingProperties", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setEncodingProperties(encodingProperties : MutableMap<String, EncodingProperty>) {
		_setMap("encodingProperties", encodingProperties)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setEncodingProperty(name : String, encodingProperty : EncodingProperty) {
		_set("encodingProperties", name, encodingProperty)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeEncodingProperty(name : String) {
		_remove("encodingProperties", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Extension
	override fun getExtensions() : MutableMap<String, Any> {
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
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<MediaType> {
				return MediaTypeImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(mediaType : MediaType) : Class<out MediaType> {
			return MediaType::class.java
		}

		private fun getSubtypeOf(json : JsonElement) : Class<out MediaType> {
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
