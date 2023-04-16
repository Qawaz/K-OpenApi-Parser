package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.ReferenceManager
import kotlin.reflect.KClass
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import kotlin.collections.Map
import com.reprezen.jsonoverlay.BooleanOverlay

class EncodingPropertyImpl : PropertiesOverlay<EncodingProperty> ,EncodingProperty {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(encodingProperty : EncodingProperty?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(encodingProperty, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ContentType
	override fun getContentType() : String? {
		return _get("contentType")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setContentType(contentType : String) {
		_setScalar("contentType", contentType)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Header
	override fun getHeaders() : MutableMap<String, String> {
		return _getMap("headers")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasHeaders() : Boolean {
		return _isPresent("headers")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasHeader(name : String) : Boolean {
		return _getMap<String>("headers").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getHeader(name : String) : String? {
		return _get("headers", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setHeaders(headers : MutableMap<String, String>) {
		_setMap("headers", headers)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setHeader(name : String, header : String) {
		_set("headers", name, header)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeHeader(name : String) {
		_remove("headers", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Style
	override fun getStyle() : String? {
		return _get("style")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setStyle(style : String) {
		_setScalar("style", style)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Explode
	override fun getExplode() : Boolean? {
		return _get("explode")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isExplode() : Boolean {
		return _get("explode") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExplode(explode : Boolean) {
		_setScalar("explode", explode)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// AllowReserved
	override fun getAllowReserved() : Boolean? {
		return _get("allowReserved")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isAllowReserved() : Boolean {
		return _get("allowReserved") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAllowReserved(allowReserved : Boolean) {
		_setScalar("allowReserved", allowReserved)
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

	companion object {
		const val F_contentType : String = "contentType"

		const val F_headers : String = "headers"

		const val F_style : String = "style"

		const val F_explode : String = "explode"

		const val F_allowReserved : String = "allowReserved"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<EncodingProperty>() {
		
			override val signature: String?
				get() = EncodingPropertyImpl::class.simpleName
		
			override fun _create(value : EncodingProperty?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<EncodingProperty> {
				return EncodingPropertyImpl(value, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<EncodingProperty> {
				return EncodingPropertyImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<EncodingProperty> {
			return Builder<EncodingProperty>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : EncodingProperty {
			return builder(modelMember).build() as EncodingProperty
		}
	}

}
