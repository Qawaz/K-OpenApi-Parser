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
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import kotlin.collections.Map
import com.reprezen.jsonoverlay.BooleanOverlay

class XmlImpl : PropertiesOverlay<Xml> ,Xml {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(xml : Xml?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(xml, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Name
	override fun getName() : String? {
		return _get("name")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setName(name : String) {
		_setScalar("name", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Namespace
	override fun getNamespace() : String? {
		return _get("namespace")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setNamespace(namespace : String) {
		_setScalar("namespace", namespace)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Prefix
	override fun getPrefix() : String? {
		return _get("prefix")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setPrefix(prefix : String) {
		_setScalar("prefix", prefix)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Attribute
	override fun getAttribute() : Boolean? {
		return _get("attribute")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isAttribute() : Boolean {
		return _get("attribute") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAttribute(attribute : Boolean) {
		_setScalar("attribute", attribute)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Wrapped
	override fun getWrapped() : Boolean? {
		return _get("wrapped")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isWrapped() : Boolean {
		return _get("wrapped") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setWrapped(wrapped : Boolean) {
		_setScalar("wrapped", wrapped)
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
		_createScalar("name", "name", StringOverlay.factory)
		_createScalar("namespace", "namespace", StringOverlay.factory)
		_createScalar("prefix", "prefix", StringOverlay.factory)
		_createScalar("attribute", "attribute", BooleanOverlay.factory)
		_createScalar("wrapped", "wrapped", BooleanOverlay.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Xml> {
		return Companion.factory
	}

	companion object {
		const val F_name : String = "name"

		const val F_namespace : String = "namespace"

		const val F_prefix : String = "prefix"

		const val F_attribute : String = "attribute"

		const val F_wrapped : String = "wrapped"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<Xml>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Xml>> {
				return XmlImpl::class.java
			}
		
			override fun _create(xml : Xml?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Xml> {
				return XmlImpl(xml, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Xml> {
				return XmlImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<Xml> {
			return Builder<Xml>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Xml {
			return builder(modelMember).build() as Xml
		}
	}

}
