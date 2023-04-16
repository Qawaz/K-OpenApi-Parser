package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import kotlin.collections.Map
import com.reprezen.jsonoverlay.ReferenceManager

class ContactImpl : PropertiesOverlay<Contact> ,Contact {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(contact : Contact?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(contact, parent, Companion.factory, refMgr)

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
	// Url
	override fun getUrl() : String? {
		return _get("url")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setUrl(url : String) {
		_setScalar("url", url)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Email
	override fun getEmail() : String? {
		return _get("email")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setEmail(email : String) {
		_setScalar("email", email)
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
		_createScalar("url", "url", StringOverlay.factory)
		_createScalar("email", "email", StringOverlay.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	companion object {
		const val F_name : String = "name"

		const val F_url : String = "url"

		const val F_email : String = "email"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<Contact>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Contact>> {
				return ContactImpl::class.java
			}
		
			override fun _create(contact : Contact?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Contact> {
				return ContactImpl(contact, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Contact> {
				return ContactImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<Contact> {
			return Builder<Contact>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Contact {
			return builder(modelMember).build() as Contact
		}
	}

}
