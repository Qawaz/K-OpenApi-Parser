package com.reprezen.kaizen.oasparser.ovl3;

import com.reprezen.kaizen.oasparser.model3.*;
import com.reprezen.jsonoverlay.MapOverlay;
import com.reprezen.jsonoverlay.StringOverlay;
import com.reprezen.kaizen.oasparser.ovl3.CallbackImpl;
import com.reprezen.jsonoverlay.ListOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.kaizen.oasparser.ovl3.HeaderImpl;
import com.reprezen.jsonoverlay.Builder;
import com.reprezen.kaizen.oasparser.ovl3.SecuritySchemeImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonPointer;
import com.reprezen.jsonoverlay.ReferenceManager;
import com.reprezen.kaizen.oasparser.ovl3.SchemaImpl;
import com.reprezen.kaizen.oasparser.ovl3.ServerImpl;
import com.reprezen.kaizen.oasparser.ovl3.SecurityRequirementImpl;
import com.reprezen.kaizen.oasparser.ovl3.LinkImpl;
import kotlin.collections.List;
import com.reprezen.jsonoverlay.JsonOverlay;
import com.reprezen.jsonoverlay.Overlay;
import com.reprezen.kaizen.oasparser.ovl3.ExampleImpl;
import java.util.stream.Collectors;
import com.reprezen.jsonoverlay.parser.Generated;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.kaizen.oasparser.ovl3.TagImpl;
import com.reprezen.kaizen.oasparser.validate.ValidationResults.Severity;
import com.reprezen.kaizen.oasparser.ovl3.RequestBodyImpl;
import com.reprezen.jsonoverlay.PropertiesOverlay;
import com.reprezen.kaizen.oasparser.ovl3.PathImpl;
import com.reprezen.kaizen.oasparser.ovl3.ResponseImpl;
import com.reprezen.kaizen.oasparser.validate.ValidationContext;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.reprezen.kaizen.oasparser.validate.ValidationResults;
import com.reprezen.kaizen.oasparser.ovl3.ParameterImpl;
import kotlin.collections.Collection;
import com.reprezen.jsonoverlay.ObjectOverlay;
import com.reprezen.jsonoverlay.ReferenceRegistry;
import com.reprezen.kaizen.oasparser.OpenApi;
import com.reprezen.kaizen.oasparser.val3.OpenApi3Validator;
import kotlin.collections.Map;
import com.reprezen.kaizen.oasparser.validate.Validator;
import com.reprezen.kaizen.oasparser.ovl3.InfoImpl;
import com.reprezen.kaizen.oasparser.ovl3.ExternalDocsImpl;

class OpenApi3Impl : PropertiesOverlay<OpenApi3> ,OpenApi3 {

    private var validationResults : ValidationResults? = null;

    override fun _fixJson(json : JsonNode) : JsonNode {
        var json = json
        if (json.isMissingNode) {
            json = _jsonObject()
        }
        if (!json.has("paths")) {
            (json as ObjectNode).putObject("paths")
        }
        return json
    }

    override fun validate() {
        ValidationContext.open().use { context ->
            validationResults = ValidationContext.getValidationResults()
            OpenApi3Validator().validate(Overlay.of(this))
        }
    }

    override fun isValid() : Boolean {
        if (validationResults == null) {
            validate()
        }
        return validationResults!!.severity.lt(Severity.ERROR);
    }

    override fun getValidationResults() : ValidationResults {
        if (validationResults == null) {
            validate()
        }
        return validationResults!!
    }

    override fun getValidationItems() : Collection<ValidationResults.ValidationItem> {
        return getValidationResults().getItems()
    }

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(openApi3 : OpenApi3?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(openApi3, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// OpenApi
	override fun getOpenApi() : String? {
		return _get("openApi", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setOpenApi(openApi : String) {
		_setScalar("openApi", openApi, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Info
	override fun getInfo() : Info? {
		return _get("info", Info::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getInfo(elaborate : Boolean) : Info? {
		return _get("info", elaborate, Info::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setInfo(info : Info) {
		_setScalar("info", info, Info::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Server
	override fun getServers() : List<Server> {
		return _getList("servers", Server::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getServers(elaborate : Boolean) : List<Server> {
		return _getList("servers", elaborate, Server::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasServers() : Boolean {
		return _isPresent("servers")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getServer(index : Int) : Server {
		return _get("servers", index, Server::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setServers(servers : MutableList<Server>) {
		_setList("servers", servers, Server::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setServer(index : Int, server : Server) {
		_set("servers", index, server, Server::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addServer(server : Server) {
		_add("servers", server, Server::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertServer(index : Int, server : Server) {
		_insert("servers", index, server, Server::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeServer(index : Int) {
		_remove("servers", index, Server::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Path
	override fun getPaths() : MutableMap<String, Path> {
		return _getMap("paths", Path::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getPaths(elaborate : Boolean) : MutableMap<String, Path> {
		return _getMap("paths", elaborate, Path::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasPaths() : Boolean {
		return _isPresent("paths")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasPath(name : String) : Boolean {
		return _getMap("paths", Path::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getPath(name : String) : Path? {
		return _get("paths", name, Path::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setPaths(paths : MutableMap<String, Path>) {
		_setMap("paths", paths, Path::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setPath(name : String, path : Path) {
		_set("paths", name, path, Path::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removePath(name : String) {
		_remove("paths", name, Path::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// PathsExtension
	override fun getPathsExtensions() : MutableMap<String, Any> {
		return _getMap("pathsExtensions", Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getPathsExtensions(elaborate : Boolean) : MutableMap<String, Any> {
		return _getMap("pathsExtensions", elaborate, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasPathsExtensions() : Boolean {
		return _isPresent("pathsExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasPathsExtension(name : String) : Boolean {
		return _getMap("pathsExtensions", Any::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getPathsExtension(name : String) : Any? {
		return _get("pathsExtensions", name, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setPathsExtensions(pathsExtensions : MutableMap<String, Any>) {
		_setMap("pathsExtensions", pathsExtensions, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setPathsExtension(name : String, pathsExtension : Any) {
		_set("pathsExtensions", name, pathsExtension, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removePathsExtension(name : String) {
		_remove("pathsExtensions", name, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Schema
	override fun getSchemas() : MutableMap<String, Schema> {
		return _getMap("schemas", Schema::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getSchemas(elaborate : Boolean) : MutableMap<String, Schema> {
		return _getMap("schemas", elaborate, Schema::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasSchemas() : Boolean {
		return _isPresent("schemas")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasSchema(name : String) : Boolean {
		return _getMap("schemas", Schema::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getSchema(name : String) : Schema? {
		return _get("schemas", name, Schema::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSchemas(schemas : MutableMap<String, Schema>) {
		_setMap("schemas", schemas, Schema::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSchema(name : String, schema : Schema) {
		_set("schemas", name, schema, Schema::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeSchema(name : String) {
		_remove("schemas", name, Schema::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Response
	override fun getResponses() : MutableMap<String, Response> {
		return _getMap("responses", Response::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getResponses(elaborate : Boolean) : MutableMap<String, Response> {
		return _getMap("responses", elaborate, Response::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasResponses() : Boolean {
		return _isPresent("responses")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasResponse(name : String) : Boolean {
		return _getMap("responses", Response::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getResponse(name : String) : Response? {
		return _get("responses", name, Response::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setResponses(responses : MutableMap<String, Response>) {
		_setMap("responses", responses, Response::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setResponse(name : String, response : Response) {
		_set("responses", name, response, Response::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeResponse(name : String) {
		_remove("responses", name, Response::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Parameter
	override fun getParameters() : MutableMap<String, Parameter> {
		return _getMap("parameters", Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getParameters(elaborate : Boolean) : MutableMap<String, Parameter> {
		return _getMap("parameters", elaborate, Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasParameters() : Boolean {
		return _isPresent("parameters")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasParameter(name : String) : Boolean {
		return _getMap("parameters", Parameter::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getParameter(name : String) : Parameter? {
		return _get("parameters", name, Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setParameters(parameters : MutableMap<String, Parameter>) {
		_setMap("parameters", parameters, Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setParameter(name : String, parameter : Parameter) {
		_set("parameters", name, parameter, Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeParameter(name : String) {
		_remove("parameters", name, Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Example
	override fun getExamples() : MutableMap<String, Example> {
		return _getMap("examples", Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExamples(elaborate : Boolean) : MutableMap<String, Example> {
		return _getMap("examples", elaborate, Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExamples() : Boolean {
		return _isPresent("examples")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExample(name : String) : Boolean {
		return _getMap("examples", Example::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExample(name : String) : Example? {
		return _get("examples", name, Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExamples(examples : MutableMap<String, Example>) {
		_setMap("examples", examples, Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExample(name : String, example : Example) {
		_set("examples", name, example, Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeExample(name : String) {
		_remove("examples", name, Example::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// RequestBody
	override fun getRequestBodies() : MutableMap<String, RequestBody> {
		return _getMap("requestBodies", RequestBody::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getRequestBodies(elaborate : Boolean) : MutableMap<String, RequestBody> {
		return _getMap("requestBodies", elaborate, RequestBody::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasRequestBodies() : Boolean {
		return _isPresent("requestBodies")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasRequestBody(name : String) : Boolean {
		return _getMap("requestBodies", RequestBody::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getRequestBody(name : String) : RequestBody? {
		return _get("requestBodies", name, RequestBody::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequestBodies(requestBodies : MutableMap<String, RequestBody>) {
		_setMap("requestBodies", requestBodies, RequestBody::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequestBody(name : String, requestBody : RequestBody) {
		_set("requestBodies", name, requestBody, RequestBody::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeRequestBody(name : String) {
		_remove("requestBodies", name, RequestBody::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Header
	override fun getHeaders() : MutableMap<String, Header> {
		return _getMap("headers", Header::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getHeaders(elaborate : Boolean) : MutableMap<String, Header> {
		return _getMap("headers", elaborate, Header::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasHeaders() : Boolean {
		return _isPresent("headers")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasHeader(name : String) : Boolean {
		return _getMap("headers", Header::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getHeader(name : String) : Header? {
		return _get("headers", name, Header::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setHeaders(headers : MutableMap<String, Header>) {
		_setMap("headers", headers, Header::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setHeader(name : String, header : Header) {
		_set("headers", name, header, Header::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeHeader(name : String) {
		_remove("headers", name, Header::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// SecurityScheme
	override fun getSecuritySchemes() : MutableMap<String, SecurityScheme> {
		return _getMap("securitySchemes", SecurityScheme::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getSecuritySchemes(elaborate : Boolean) : MutableMap<String, SecurityScheme> {
		return _getMap("securitySchemes", elaborate, SecurityScheme::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasSecuritySchemes() : Boolean {
		return _isPresent("securitySchemes")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasSecurityScheme(name : String) : Boolean {
		return _getMap("securitySchemes", SecurityScheme::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getSecurityScheme(name : String) : SecurityScheme? {
		return _get("securitySchemes", name, SecurityScheme::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSecuritySchemes(securitySchemes : MutableMap<String, SecurityScheme>) {
		_setMap("securitySchemes", securitySchemes, SecurityScheme::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSecurityScheme(name : String, securityScheme : SecurityScheme) {
		_set("securitySchemes", name, securityScheme, SecurityScheme::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeSecurityScheme(name : String) {
		_remove("securitySchemes", name, SecurityScheme::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Link
	override fun getLinks() : MutableMap<String, Link> {
		return _getMap("links", Link::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getLinks(elaborate : Boolean) : MutableMap<String, Link> {
		return _getMap("links", elaborate, Link::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasLinks() : Boolean {
		return _isPresent("links")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasLink(name : String) : Boolean {
		return _getMap("links", Link::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getLink(name : String) : Link? {
		return _get("links", name, Link::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setLinks(links : MutableMap<String, Link>) {
		_setMap("links", links, Link::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setLink(name : String, link : Link) {
		_set("links", name, link, Link::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeLink(name : String) {
		_remove("links", name, Link::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Callback
	override fun getCallbacks() : MutableMap<String, Callback> {
		return _getMap("callbacks", Callback::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getCallbacks(elaborate : Boolean) : MutableMap<String, Callback> {
		return _getMap("callbacks", elaborate, Callback::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasCallbacks() : Boolean {
		return _isPresent("callbacks")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasCallback(name : String) : Boolean {
		return _getMap("callbacks", Callback::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getCallback(name : String) : Callback? {
		return _get("callbacks", name, Callback::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setCallbacks(callbacks : MutableMap<String, Callback>) {
		_setMap("callbacks", callbacks, Callback::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setCallback(name : String, callback : Callback) {
		_set("callbacks", name, callback, Callback::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeCallback(name : String) {
		_remove("callbacks", name, Callback::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ComponentsExtension
	override fun getComponentsExtensions() : MutableMap<String, Any> {
		return _getMap("componentsExtensions", Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getComponentsExtensions(elaborate : Boolean) : MutableMap<String, Any> {
		return _getMap("componentsExtensions", elaborate, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasComponentsExtensions() : Boolean {
		return _isPresent("componentsExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasComponentsExtension(name : String) : Boolean {
		return _getMap("componentsExtensions", Any::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getComponentsExtension(name : String) : Any? {
		return _get("componentsExtensions", name, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setComponentsExtensions(componentsExtensions : MutableMap<String, Any>) {
		_setMap("componentsExtensions", componentsExtensions, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setComponentsExtension(name : String, componentsExtension : Any) {
		_set("componentsExtensions", name, componentsExtension, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeComponentsExtension(name : String) {
		_remove("componentsExtensions", name, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// SecurityRequirement
	override fun getSecurityRequirements() : List<SecurityRequirement> {
		return _getList("securityRequirements", SecurityRequirement::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getSecurityRequirements(elaborate : Boolean) : List<SecurityRequirement> {
		return _getList("securityRequirements", elaborate, SecurityRequirement::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasSecurityRequirements() : Boolean {
		return _isPresent("securityRequirements")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getSecurityRequirement(index : Int) : SecurityRequirement {
		return _get("securityRequirements", index, SecurityRequirement::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSecurityRequirements(securityRequirements : MutableList<SecurityRequirement>) {
		_setList("securityRequirements", securityRequirements, SecurityRequirement::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSecurityRequirement(index : Int, securityRequirement : SecurityRequirement) {
		_set("securityRequirements", index, securityRequirement, SecurityRequirement::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addSecurityRequirement(securityRequirement : SecurityRequirement) {
		_add("securityRequirements", securityRequirement, SecurityRequirement::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertSecurityRequirement(index : Int, securityRequirement : SecurityRequirement) {
		_insert("securityRequirements", index, securityRequirement, SecurityRequirement::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeSecurityRequirement(index : Int) {
		_remove("securityRequirements", index, SecurityRequirement::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Tag
	override fun getTags() : List<Tag> {
		return _getList("tags", Tag::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getTags(elaborate : Boolean) : List<Tag> {
		return _getList("tags", elaborate, Tag::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasTags() : Boolean {
		return _isPresent("tags")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getTag(index : Int) : Tag {
		return _get("tags", index, Tag::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setTags(tags : MutableList<Tag>) {
		_setList("tags", tags, Tag::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setTag(index : Int, tag : Tag) {
		_set("tags", index, tag, Tag::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addTag(tag : Tag) {
		_add("tags", tag, Tag::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertTag(index : Int, tag : Tag) {
		_insert("tags", index, tag, Tag::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeTag(index : Int) {
		_remove("tags", index, Tag::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ExternalDocs
	override fun getExternalDocs() : ExternalDocs? {
		return _get("externalDocs", ExternalDocs::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExternalDocs(elaborate : Boolean) : ExternalDocs? {
		return _get("externalDocs", elaborate, ExternalDocs::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExternalDocs(externalDocs : ExternalDocs) {
		_setScalar("externalDocs", externalDocs, ExternalDocs::class.java)
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
		_createScalar("openApi", "openapi", StringOverlay.factory)
		_createScalar("info", "info", InfoImpl.factory)
		_createList("servers", "servers", ServerImpl.factory)
		_createMap("paths", "paths", PathImpl.factory, "/.*")
		_createMap("pathsExtensions", "paths", ObjectOverlay.factory, "x-.+")
		_createMap("schemas", "components/schemas", SchemaImpl.factory, "[a-zA-Z0-9\\._-]+")
		_createMap("responses", "components/responses", ResponseImpl.factory, "[a-zA-Z0-9\\._-]+")
		_createMap("parameters", "components/parameters", ParameterImpl.factory, "[a-zA-Z0-9\\._-]+")
		_createMap("examples", "components/examples", ExampleImpl.factory, "[a-zA-Z0-9\\._-]+")
		_createMap("requestBodies", "components/requestBodies", RequestBodyImpl.factory, "[a-zA-Z0-9\\._-]+")
		_createMap("headers", "components/headers", HeaderImpl.factory, "[a-zA-Z0-9\\._-]+")
		_createMap("securitySchemes", "components/securitySchemes", SecuritySchemeImpl.factory, "[a-zA-Z0-9\\._-]+")
		_createMap("links", "components/links", LinkImpl.factory, "[a-zA-Z0-9\\._-]+")
		_createMap("callbacks", "components/callbacks", CallbackImpl.factory, "(?!x-)[a-zA-Z0-9\\._-]+")
		_createMap("componentsExtensions", "components", ObjectOverlay.factory, "x-.+")
		_createList("securityRequirements", "security", SecurityRequirementImpl.factory)
		_createList("tags", "tags", TagImpl.factory)
		_createScalar("externalDocs", "externalDocs", ExternalDocsImpl.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<OpenApi3> {
		return Companion.factory
	}

	companion object {
		const val F_openApi : String = "openApi"

		const val F_info : String = "info"

		const val F_servers : String = "servers"

		const val F_paths : String = "paths"

		const val F_pathsExtensions : String = "pathsExtensions"

		const val F_schemas : String = "schemas"

		const val F_responses : String = "responses"

		const val F_parameters : String = "parameters"

		const val F_examples : String = "examples"

		const val F_requestBodies : String = "requestBodies"

		const val F_headers : String = "headers"

		const val F_securitySchemes : String = "securitySchemes"

		const val F_links : String = "links"

		const val F_callbacks : String = "callbacks"

		const val F_componentsExtensions : String = "componentsExtensions"

		const val F_securityRequirements : String = "securityRequirements"

		const val F_tags : String = "tags"

		const val F_externalDocs : String = "externalDocs"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<OpenApi3>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in OpenApi3>> {
				return OpenApi3Impl::class.java
			}
		
			override fun _create(openApi3 : OpenApi3?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<OpenApi3> {
				return OpenApi3Impl(openApi3, parent, refMgr)
			}
		
			override fun _create(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<OpenApi3> {
				return OpenApi3Impl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(openApi3 : OpenApi3) : Class<out OpenApi3> {
			return OpenApi3::class.java
		}

		private fun getSubtypeOf(json : JsonNode) : Class<out OpenApi3> {
			return OpenApi3::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<OpenApi3> {
			return Builder<OpenApi3>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : OpenApi3 {
			return builder(modelMember).build() as OpenApi3
		}
	}

}
