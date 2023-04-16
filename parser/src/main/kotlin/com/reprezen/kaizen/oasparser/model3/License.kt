package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.Map

interface License : IJsonOverlay<License>, IModelPart<OpenApi3, License> {

	// Name
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getName() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setName(name : String)

	// Url
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getUrl() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setUrl(url : String)

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
