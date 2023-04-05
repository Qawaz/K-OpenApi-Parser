package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.kaizen.oasparser.model3.Server;
import com.reprezen.kaizen.oasparser.model3.ExternalDocs;
import com.reprezen.kaizen.oasparser.model3.Response;
import com.reprezen.kaizen.oasparser.model3.Callback;
import com.reprezen.kaizen.oasparser.model3.Parameter;
import com.reprezen.jsonoverlay.IModelPart;
import kotlin.collections.List;
import kotlin.collections.Map;
import com.reprezen.kaizen.oasparser.model3.RequestBody;
import com.reprezen.kaizen.oasparser.model3.SecurityRequirement;

interface Operation : IJsonOverlay<Operation>, IModelPart<OpenApi3, Operation> {

	// Tag
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTags() : List<String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTags(elaborate : Boolean) : List<String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasTags() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTag(index : Int) : String

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setTags(tags : MutableList<String>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setTag(index : Int, tag : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addTag(tag : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertTag(index : Int,tag : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeTag(index : Int)

	// Summary
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSummary() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setSummary(summary : String)

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

	// ExternalDocs
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExternalDocs() : ExternalDocs?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExternalDocs(elaborate : Boolean) : ExternalDocs?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExternalDocs(externalDocs : ExternalDocs)

	// OperationId
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOperationId() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOperationId(operationId : String)

	// Parameter
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameters() : List<Parameter>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameters(elaborate : Boolean) : List<Parameter>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasParameters() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameter(index : Int) : Parameter

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setParameters(parameters : MutableList<Parameter>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setParameter(index : Int, parameter : Parameter)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addParameter(parameter : Parameter)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertParameter(index : Int,parameter : Parameter)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeParameter(index : Int)

	// RequestBody
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequestBody() : RequestBody?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequestBody(elaborate : Boolean) : RequestBody?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setRequestBody(requestBody : RequestBody)

	// Response
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getResponses() : MutableMap<String, Response>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getResponses(elaborate : Boolean) : MutableMap<String, Response>

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

	// ResponsesExtension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getResponsesExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getResponsesExtensions(elaborate : Boolean) : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasResponsesExtensions() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasResponsesExtension(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getResponsesExtension(name : String) : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setResponsesExtensions(responsesExtensions : MutableMap<String, Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setResponsesExtension(name : String,responsesExtension : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeResponsesExtension(name : String)

	// Callback
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getCallbacks() : MutableMap<String, Callback>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getCallbacks(elaborate : Boolean) : MutableMap<String, Callback>

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

	// CallbacksExtension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getCallbacksExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getCallbacksExtensions(elaborate : Boolean) : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasCallbacksExtensions() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasCallbacksExtension(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getCallbacksExtension(name : String) : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setCallbacksExtensions(callbacksExtensions : MutableMap<String, Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setCallbacksExtension(name : String,callbacksExtension : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeCallbacksExtension(name : String)

	// Deprecated
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDeprecated() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isDeprecated() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDeprecated(deprecated : Boolean)

	// SecurityRequirement
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSecurityRequirements() : List<SecurityRequirement>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSecurityRequirements(elaborate : Boolean) : List<SecurityRequirement>

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

	// Server
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServers() : List<Server>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServers(elaborate : Boolean) : List<Server>

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
