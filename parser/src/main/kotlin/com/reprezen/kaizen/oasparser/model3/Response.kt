package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.kaizen.oasparser.model3.Header
import com.reprezen.kaizen.oasparser.model3.MediaType
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.Map
import com.reprezen.kaizen.oasparser.model3.Link

interface Response : IJsonOverlay<Response>, IModelPart<OpenApi3, Response> {

    fun getName() : String?

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

	// Header
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getHeaders() : MutableMap<String, Header>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasHeaders() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasHeader(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getHeader(name : String) : Header?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setHeaders(headers : MutableMap<String, Header>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setHeader(name : String,header : Header)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeHeader(name : String)

	// ContentMediaType
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getContentMediaTypes() : MutableMap<String, MediaType>

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

	// Link
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getLinks() : MutableMap<String, Link>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasLinks() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasLink(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getLink(name : String) : Link?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setLinks(links : MutableMap<String, Link>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setLink(name : String,link : Link)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeLink(name : String)

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
