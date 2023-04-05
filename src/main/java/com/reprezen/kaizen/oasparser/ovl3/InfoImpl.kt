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
import com.reprezen.kaizen.oasparser.ovl3.LicenseImpl;
import com.reprezen.kaizen.oasparser.ovl3.ContactImpl;
import com.reprezen.jsonoverlay.ObjectOverlay;
import com.reprezen.jsonoverlay.JsonOverlay;
import kotlin.collections.Map;

class InfoImpl : PropertiesOverlay<Info> ,Info {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(info : Info?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(info, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Title
	override fun getTitle() : String? {
		return _get("title", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setTitle(title : String) {
		_setScalar("title", title, String::class.java)
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
	// TermsOfService
	override fun getTermsOfService() : String? {
		return _get("termsOfService", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setTermsOfService(termsOfService : String) {
		_setScalar("termsOfService", termsOfService, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Contact
	override fun getContact() : Contact? {
		return _get("contact", Contact::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getContact(elaborate : Boolean) : Contact? {
		return _get("contact", elaborate, Contact::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setContact(contact : Contact) {
		_setScalar("contact", contact, Contact::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// License
	override fun getLicense() : License? {
		return _get("license", License::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getLicense(elaborate : Boolean) : License? {
		return _get("license", elaborate, License::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setLicense(license : License) {
		_setScalar("license", license, License::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Version
	override fun getVersion() : String? {
		return _get("version", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setVersion(version : String) {
		_setScalar("version", version, String::class.java)
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

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Info> {
		return Companion.factory
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
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Info>> {
				return InfoImpl::class.java
			}
		
			override fun _create(info : Info?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Info> {
				return InfoImpl(info, parent, refMgr)
			}
		
			override fun _create(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Info> {
				return InfoImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(info : Info) : Class<out Info> {
			return Info::class.java
		}

		private fun getSubtypeOf(json : JsonNode) : Class<out Info> {
			return Info::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<Info> {
			return Builder<Info>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Info {
			return builder(modelMember).build() as Info
		}
	}

}
