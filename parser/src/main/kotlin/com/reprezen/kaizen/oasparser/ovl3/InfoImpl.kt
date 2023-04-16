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
import com.reprezen.kaizen.oasparser.ovl3.LicenseImpl
import kotlin.reflect.KClass
import com.reprezen.kaizen.oasparser.ovl3.ContactImpl
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import kotlin.collections.Map

class InfoImpl : PropertiesOverlay<Info> ,Info {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(info : Info?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(info, parent, Companion.factory, refMgr)

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
	// Description
	override fun getDescription() : String? {
		return _get("description")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setDescription(description : String) {
		_setScalar("description", description)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// TermsOfService
	override fun getTermsOfService() : String? {
		return _get("termsOfService")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setTermsOfService(termsOfService : String) {
		_setScalar("termsOfService", termsOfService)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Contact
	override fun getContact() : Contact? {
		return _get("contact")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setContact(contact : Contact) {
		_setScalar("contact", contact)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// License
	override fun getLicense() : License? {
		return _get("license")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setLicense(license : License) {
		_setScalar("license", license)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Version
	override fun getVersion() : String? {
		return _get("version")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setVersion(version : String) {
		_setScalar("version", version)
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
		_createScalar("title", "title", StringOverlay.factory)
		_createScalar("description", "description", StringOverlay.factory)
		_createScalar("termsOfService", "termsOfService", StringOverlay.factory)
		_createScalar("contact", "contact", ContactImpl.factory)
		_createScalar("license", "license", LicenseImpl.factory)
		_createScalar("version", "version", StringOverlay.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	companion object {
		const val F_title : String = "title"

		const val F_description : String = "description"

		const val F_termsOfService : String = "termsOfService"

		const val F_contact : String = "contact"

		const val F_license : String = "license"

		const val F_version : String = "version"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<Info>() {
		
			override val signature: String?
				get() = InfoImpl::class.simpleName
		
			override fun _create(value : Info?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Info> {
				return InfoImpl(value, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Info> {
				return InfoImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<Info> {
			return Builder<Info>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Info {
			return builder(modelMember).build() as Info
		}
	}

}
