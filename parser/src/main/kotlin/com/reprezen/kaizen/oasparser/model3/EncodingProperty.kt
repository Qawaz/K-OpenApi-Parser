package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.Map

interface EncodingProperty : IJsonOverlay<EncodingProperty>, IModelPart<OpenApi3, EncodingProperty> {

	// ContentType
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getContentType() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setContentType(contentType : String)

	// Header
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getHeaders() : MutableMap<String, String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasHeaders() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasHeader(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getHeader(name : String) : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setHeaders(headers : MutableMap<String, String>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setHeader(name : String,header : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeHeader(name : String)

	// Style
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getStyle() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setStyle(style : String)

	// Explode
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExplode() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isExplode() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExplode(explode : Boolean)

	// AllowReserved
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAllowReserved() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isAllowReserved() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAllowReserved(allowReserved : Boolean)

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
