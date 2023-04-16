package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.kaizen.oasparser.ovl3.CallbackImpl
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.ListOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import com.reprezen.jsonoverlay.Builder
import com.reprezen.kaizen.oasparser.ovl3.RequestBodyImpl
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.kaizen.oasparser.ovl3.ResponseImpl
import com.reprezen.jsonoverlay.ReferenceManager
import kotlin.reflect.KClass
import com.reprezen.kaizen.oasparser.ovl3.ServerImpl
import com.reprezen.kaizen.oasparser.ovl3.ParameterImpl
import kotlinx.serialization.json.JsonElement
import com.reprezen.kaizen.oasparser.ovl3.SecurityRequirementImpl
import com.reprezen.jsonoverlay.ObjectOverlay
import kotlin.collections.List
import com.reprezen.jsonoverlay.JsonOverlay
import kotlin.collections.Map
import com.reprezen.jsonoverlay.BooleanOverlay
import com.reprezen.kaizen.oasparser.ovl3.ExternalDocsImpl

class OperationImpl : PropertiesOverlay<Operation> ,Operation {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(operation : Operation?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(operation, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Tag
	override fun getTags() : List<String> {
		return _getList("tags")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasTags() : Boolean {
		return _isPresent("tags")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getTag(index : Int) : String {
		return _get("tags", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setTags(tags : MutableList<String>) {
		_setList("tags", tags)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setTag(index : Int, tag : String) {
		_set("tags", index, tag)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addTag(tag : String) {
		_add("tags", tag)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertTag(index : Int, tag : String) {
		_insert("tags", index, tag)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeTag(index : Int) {
		_remove("tags", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Summary
	override fun getSummary() : String? {
		return _get("summary")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSummary(summary : String) {
		_setScalar("summary", summary)
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
	// ExternalDocs
	override fun getExternalDocs() : ExternalDocs? {
		return _get("externalDocs")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExternalDocs(externalDocs : ExternalDocs) {
		_setScalar("externalDocs", externalDocs)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// OperationId
	override fun getOperationId() : String? {
		return _get("operationId")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setOperationId(operationId : String) {
		_setScalar("operationId", operationId)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Parameter
	override fun getParameters() : List<Parameter> {
		return _getList("parameters")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasParameters() : Boolean {
		return _isPresent("parameters")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getParameter(index : Int) : Parameter {
		return _get("parameters", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setParameters(parameters : MutableList<Parameter>) {
		_setList("parameters", parameters)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setParameter(index : Int, parameter : Parameter) {
		_set("parameters", index, parameter)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addParameter(parameter : Parameter) {
		_add("parameters", parameter)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertParameter(index : Int, parameter : Parameter) {
		_insert("parameters", index, parameter)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeParameter(index : Int) {
		_remove("parameters", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// RequestBody
	override fun getRequestBody() : RequestBody? {
		return _get("requestBody")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequestBody(requestBody : RequestBody) {
		_setScalar("requestBody", requestBody)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Response
	override fun getResponses() : MutableMap<String, Response> {
		return _getMap("responses")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasResponses() : Boolean {
		return _isPresent("responses")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasResponse(name : String) : Boolean {
		return _getMap<Response>("responses").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getResponse(name : String) : Response? {
		return _get("responses", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setResponses(responses : MutableMap<String, Response>) {
		_setMap("responses", responses)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setResponse(name : String, response : Response) {
		_set("responses", name, response)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeResponse(name : String) {
		_remove("responses", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ResponsesExtension
	override fun getResponsesExtensions() : MutableMap<String, Any> {
		return _getMap("responsesExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasResponsesExtensions() : Boolean {
		return _isPresent("responsesExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasResponsesExtension(name : String) : Boolean {
		return _getMap<Any>("responsesExtensions").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getResponsesExtension(name : String) : Any? {
		return _get("responsesExtensions", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setResponsesExtensions(responsesExtensions : MutableMap<String, Any>) {
		_setMap("responsesExtensions", responsesExtensions)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setResponsesExtension(name : String, responsesExtension : Any) {
		_set("responsesExtensions", name, responsesExtension)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeResponsesExtension(name : String) {
		_remove("responsesExtensions", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Callback
	override fun getCallbacks() : MutableMap<String, Callback> {
		return _getMap("callbacks")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasCallbacks() : Boolean {
		return _isPresent("callbacks")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasCallback(name : String) : Boolean {
		return _getMap<Callback>("callbacks").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getCallback(name : String) : Callback? {
		return _get("callbacks", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setCallbacks(callbacks : MutableMap<String, Callback>) {
		_setMap("callbacks", callbacks)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setCallback(name : String, callback : Callback) {
		_set("callbacks", name, callback)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeCallback(name : String) {
		_remove("callbacks", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// CallbacksExtension
	override fun getCallbacksExtensions() : MutableMap<String, Any> {
		return _getMap("callbacksExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasCallbacksExtensions() : Boolean {
		return _isPresent("callbacksExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasCallbacksExtension(name : String) : Boolean {
		return _getMap<Any>("callbacksExtensions").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getCallbacksExtension(name : String) : Any? {
		return _get("callbacksExtensions", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setCallbacksExtensions(callbacksExtensions : MutableMap<String, Any>) {
		_setMap("callbacksExtensions", callbacksExtensions)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setCallbacksExtension(name : String, callbacksExtension : Any) {
		_set("callbacksExtensions", name, callbacksExtension)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeCallbacksExtension(name : String) {
		_remove("callbacksExtensions", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Deprecated
	override fun getDeprecated() : Boolean? {
		return _get("deprecated")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isDeprecated() : Boolean {
		return _get("deprecated") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setDeprecated(deprecated : Boolean) {
		_setScalar("deprecated", deprecated)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// SecurityRequirement
	override fun getSecurityRequirements() : List<SecurityRequirement> {
		return _getList("securityRequirements")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasSecurityRequirements() : Boolean {
		return _isPresent("securityRequirements")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getSecurityRequirement(index : Int) : SecurityRequirement {
		return _get("securityRequirements", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSecurityRequirements(securityRequirements : MutableList<SecurityRequirement>) {
		_setList("securityRequirements", securityRequirements)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSecurityRequirement(index : Int, securityRequirement : SecurityRequirement) {
		_set("securityRequirements", index, securityRequirement)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addSecurityRequirement(securityRequirement : SecurityRequirement) {
		_add("securityRequirements", securityRequirement)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertSecurityRequirement(index : Int, securityRequirement : SecurityRequirement) {
		_insert("securityRequirements", index, securityRequirement)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeSecurityRequirement(index : Int) {
		_remove("securityRequirements", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Server
	override fun getServers() : List<Server> {
		return _getList("servers")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasServers() : Boolean {
		return _isPresent("servers")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getServer(index : Int) : Server {
		return _get("servers", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setServers(servers : MutableList<Server>) {
		_setList("servers", servers)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setServer(index : Int, server : Server) {
		_set("servers", index, server)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addServer(server : Server) {
		_add("servers", server)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertServer(index : Int, server : Server) {
		_insert("servers", index, server)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeServer(index : Int) {
		_remove("servers", index)
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
		_createList("tags", "tags", StringOverlay.factory)
		_createScalar("summary", "summary", StringOverlay.factory)
		_createScalar("description", "description", StringOverlay.factory)
		_createScalar("externalDocs", "externalDocs", ExternalDocsImpl.factory)
		_createScalar("operationId", "operationId", StringOverlay.factory)
		_createList("parameters", "parameters", ParameterImpl.factory)
		_createScalar("requestBody", "requestBody", RequestBodyImpl.factory)
		_createMap("responses", "responses", ResponseImpl.factory, "default|(\\d[0-9X]{2})")
		_createMap("responsesExtensions", "responses", ObjectOverlay.factory, "x-.+")
		_createMap("callbacks", "callbacks", CallbackImpl.factory, "(?!x-)[a-zA-Z0-9\\._-]+")
		_createMap("callbacksExtensions", "callbacks", ObjectOverlay.factory, "x-.+")
		_createScalar("deprecated", "deprecated", BooleanOverlay.factory)
		_createList("securityRequirements", "security", SecurityRequirementImpl.factory)
		_createList("servers", "servers", ServerImpl.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	companion object {
		const val F_tags : String = "tags"

		const val F_summary : String = "summary"

		const val F_description : String = "description"

		const val F_externalDocs : String = "externalDocs"

		const val F_operationId : String = "operationId"

		const val F_parameters : String = "parameters"

		const val F_requestBody : String = "requestBody"

		const val F_responses : String = "responses"

		const val F_responsesExtensions : String = "responsesExtensions"

		const val F_callbacks : String = "callbacks"

		const val F_callbacksExtensions : String = "callbacksExtensions"

		const val F_deprecated : String = "deprecated"

		const val F_securityRequirements : String = "securityRequirements"

		const val F_servers : String = "servers"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<Operation>() {
		
			override val signature: String?
				get() = OperationImpl::class.simpleName
		
			override fun _create(value : Operation?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Operation> {
				return OperationImpl(value, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Operation> {
				return OperationImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<Operation> {
			return Builder<Operation>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Operation {
			return builder(modelMember).build() as Operation
		}
	}

}
