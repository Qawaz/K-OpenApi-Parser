package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.kaizen.oasparser.model3.MediaType
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.Map

interface RequestBody : IJsonOverlay<RequestBody>, IModelPart<OpenApi3, RequestBody> {

    fun getName() : String?

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

	// ContentMediaType
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getContentMediaTypes() : MutableMap<String, MediaType>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getContentMediaTypes(elaborate : Boolean) : MutableMap<String, MediaType>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasContentMediaTypes() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasContentMediaType(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getContentMediaType(name : String) : MediaType?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setContentMediaTypes(contentMediaTypes : MutableMap<String, MediaType>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setContentMediaType(name : String,contentMediaType : MediaType)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeContentMediaType(name : String)

	// Required
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequired() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isRequired() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setRequired(required : Boolean)

	// Extension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtensions(elaborate : Boolean) : MutableMap<String, Any>

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
