package com.reprezen.kaizen.oasparser.model3

import com.reprezen.kaizen.oasparser.model3.Path
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.kaizen.oasparser.model3.Server
import com.reprezen.kaizen.oasparser.model3.Schema
import com.reprezen.kaizen.oasparser.model3.Info
import com.reprezen.kaizen.oasparser.model3.ExternalDocs
import com.reprezen.kaizen.oasparser.model3.Response
import com.reprezen.kaizen.oasparser.model3.Callback
import com.reprezen.kaizen.oasparser.model3.Header
import com.reprezen.kaizen.oasparser.model3.Example
import kotlin.collections.Collection
import com.reprezen.kaizen.oasparser.model3.Parameter
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.List
import com.reprezen.kaizen.oasparser.model3.Tag
import kotlin.collections.Map
import com.reprezen.kaizen.oasparser.model3.SecurityScheme
import com.reprezen.kaizen.oasparser.model3.Link
import com.reprezen.kaizen.oasparser.model3.RequestBody
import com.reprezen.kaizen.oasparser.model3.SecurityRequirement

interface OpenApi3 : IJsonOverlay<OpenApi3>, IModelPart<OpenApi3, OpenApi3> {

	// OpenApi
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOpenApi() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOpenApi(openApi : String)

	// Info
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getInfo() : Info?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setInfo(info : Info)

	// Server
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServers() : List<Server>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasServers() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServer(index : Int) : Server

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setServers(servers : MutableList<Server>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setServer(index : Int, server : Server)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addServer(server : Server)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertServer(index : Int,server : Server)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeServer(index : Int)

	// Path
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPaths() : MutableMap<String, Path>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasPaths() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasPath(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPath(name : String) : Path?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPaths(paths : MutableMap<String, Path>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPath(name : String,path : Path)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removePath(name : String)

	// PathsExtension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPathsExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasPathsExtensions() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasPathsExtension(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPathsExtension(name : String) : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPathsExtensions(pathsExtensions : MutableMap<String, Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPathsExtension(name : String,pathsExtension : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removePathsExtension(name : String)

	// Schema
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSchemas() : MutableMap<String, Schema>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasSchemas() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasSchema(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSchema(name : String) : Schema?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setSchemas(schemas : MutableMap<String, Schema>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setSchema(name : String,schema : Schema)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeSchema(name : String)

	// Response
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getResponses() : MutableMap<String, Response>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasResponses() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasResponse(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getResponse(name : String) : Response?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setResponses(responses : MutableMap<String, Response>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setResponse(name : String,response : Response)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeResponse(name : String)

	// Parameter
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameters() : MutableMap<String, Parameter>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasParameters() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasParameter(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameter(name : String) : Parameter?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setParameters(parameters : MutableMap<String, Parameter>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setParameter(name : String,parameter : Parameter)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeParameter(name : String)

	// Example
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExamples() : MutableMap<String, Example>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExamples() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExample(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExample(name : String) : Example?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExamples(examples : MutableMap<String, Example>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExample(name : String,example : Example)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeExample(name : String)

	// RequestBody
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequestBodies() : MutableMap<String, RequestBody>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasRequestBodies() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasRequestBody(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequestBody(name : String) : RequestBody?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setRequestBodies(requestBodies : MutableMap<String, RequestBody>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setRequestBody(name : String,requestBody : RequestBody)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeRequestBody(name : String)

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

	// SecurityScheme
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSecuritySchemes() : MutableMap<String, SecurityScheme>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasSecuritySchemes() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasSecurityScheme(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSecurityScheme(name : String) : SecurityScheme?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setSecuritySchemes(securitySchemes : MutableMap<String, SecurityScheme>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setSecurityScheme(name : String,securityScheme : SecurityScheme)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeSecurityScheme(name : String)

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

	// Callback
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getCallbacks() : MutableMap<String, Callback>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasCallbacks() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasCallback(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getCallback(name : String) : Callback?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setCallbacks(callbacks : MutableMap<String, Callback>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setCallback(name : String,callback : Callback)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeCallback(name : String)

	// ComponentsExtension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getComponentsExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasComponentsExtensions() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasComponentsExtension(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getComponentsExtension(name : String) : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setComponentsExtensions(componentsExtensions : MutableMap<String, Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setComponentsExtension(name : String,componentsExtension : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeComponentsExtension(name : String)

	// SecurityRequirement
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSecurityRequirements() : List<SecurityRequirement>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasSecurityRequirements() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSecurityRequirement(index : Int) : SecurityRequirement

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setSecurityRequirements(securityRequirements : MutableList<SecurityRequirement>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setSecurityRequirement(index : Int, securityRequirement : SecurityRequirement)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addSecurityRequirement(securityRequirement : SecurityRequirement)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertSecurityRequirement(index : Int,securityRequirement : SecurityRequirement)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeSecurityRequirement(index : Int)

	// Tag
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTags() : List<Tag>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasTags() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTag(index : Int) : Tag

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setTags(tags : MutableList<Tag>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setTag(index : Int, tag : Tag)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addTag(tag : Tag)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertTag(index : Int,tag : Tag)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeTag(index : Int)

	// ExternalDocs
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExternalDocs() : ExternalDocs?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExternalDocs(externalDocs : ExternalDocs)

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
