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
import com.reprezen.jsonoverlay.ObjectOverlay;
import com.reprezen.jsonoverlay.JsonOverlay;
import kotlin.collections.Map;
import com.reprezen.jsonoverlay.BooleanOverlay;

class EncodingPropertyImpl : PropertiesOverlay<EncodingProperty> ,EncodingProperty {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(encodingProperty : EncodingProperty?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(encodingProperty, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ContentType
	override fun getContentType() : String? {
		return _get("contentType", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setContentType(contentType : String) {
		_setScalar("contentType", contentType, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Header
	override fun getHeaders() : MutableMap<String, String> {
		return _getMap("headers", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getHeaders(elaborate : Boolean) : MutableMap<String, String> {
		return _getMap("headers", elaborate, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasHeaders() : Boolean {
		return _isPresent("headers")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasHeader(name : String) : Boolean {
		return _getMap("headers", String::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getHeader(name : String) : String? {
		return _get("headers", name, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setHeaders(headers : MutableMap<String, String>) {
		_setMap("headers", headers, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setHeader(name : String, header : String) {
		_set("headers", name, header, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeHeader(name : String) {
		_remove("headers", name, String::class.java)
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
		_createScalar("contentType", "contentType", StringOverlay.factory)
		_createMap("headers", "headers", StringOverlay.factory, null)
		_createScalar("style", "style", StringOverlay.factory)
		_createScalar("explode", "explode", BooleanOverlay.factory)
		_createScalar("allowReserved", "allowReserved", BooleanOverlay.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<EncodingProperty> {
		return Companion.factory
	}

	companion object {
		const val F_contentType : String = "contentType"

		const val F_headers : String = "headers"

		const val F_style : String = "style"

		const val F_explode : String = "explode"

		const val F_allowReserved : String = "allowReserved"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<EncodingProperty>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in EncodingProperty>> {
				return EncodingPropertyImpl::class.java
			}
		
			override fun _create(encodingProperty : EncodingProperty?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<EncodingProperty> {
				return EncodingPropertyImpl(encodingProperty, parent, refMgr)
			}
		
			override fun _create(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<EncodingProperty> {
				return EncodingPropertyImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(encodingProperty : EncodingProperty) : Class<out EncodingProperty> {
			return EncodingProperty::class.java
		}

		private fun getSubtypeOf(json : JsonNode) : Class<out EncodingProperty> {
			return EncodingProperty::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<EncodingProperty> {
			return Builder<EncodingProperty>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : EncodingProperty {
			return builder(modelMember).build() as EncodingProperty
		}
	}

}
