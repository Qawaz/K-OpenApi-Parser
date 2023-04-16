package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.kaizen.oasparser.model3.License
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.Map
import com.reprezen.kaizen.oasparser.model3.Contact

interface Info : IJsonOverlay<Info>, IModelPart<OpenApi3, Info> {

	// Title
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTitle() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setTitle(title : String)

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

	// TermsOfService
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTermsOfService() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setTermsOfService(termsOfService : String)

	// Contact
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getContact() : Contact?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setContact(contact : Contact)

	// License
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getLicense() : License?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setLicense(license : License)

	// Version
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getVersion() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setVersion(version : String)

	// Extension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExtensions() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExtension(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtension(name : String) : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExtensions(extensions : MutableMap<String, Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExtension(name : String,extension : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeExtension(name : String)

}
