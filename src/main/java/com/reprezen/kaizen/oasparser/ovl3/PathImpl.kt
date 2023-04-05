package com.reprezen.kaizen.oasparser.ovl3;

import com.reprezen.kaizen.oasparser.model3.*;
import com.reprezen.jsonoverlay.MapOverlay;
import java.util.stream.Collectors;
import com.reprezen.jsonoverlay.StringOverlay;
import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.ListOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.jsonoverlay.Builder;
import com.fasterxml.jackson.databind.JsonNode;
import com.reprezen.jsonoverlay.PropertiesOverlay;
import com.fasterxml.jackson.core.JsonPointer;
import com.reprezen.jsonoverlay.ReferenceManager;
import com.reprezen.kaizen.oasparser.ovl3.OperationImpl;
import com.reprezen.kaizen.oasparser.ovl3.ServerImpl;
import com.reprezen.kaizen.oasparser.ovl3.ParameterImpl;
import com.reprezen.jsonoverlay.ObjectOverlay;
import kotlin.collections.List;
import com.reprezen.jsonoverlay.JsonOverlay;
import com.reprezen.jsonoverlay.Overlay;
import kotlin.collections.Map;

class PathImpl : PropertiesOverlay<Path> ,Path {

    private val overlay : Overlay<Path> = Overlay.Companion.of(this);

	override fun getPathString() : String? {
		return if(overlay.parent is MapOverlay<*>) overlay.pathInParent else null
	}

	override fun getGet() : Operation? {
		return getOperations()["get"]
	}

	override fun getGet(elaborate : Boolean) : Operation? {
		return getOperations(elaborate)["get"]
	}

	override fun setGet(get : Operation) {
		getOperations().put("get", get)
	}

	override fun getPut() : Operation? {
		return getOperations().get("put")
	}

	override fun getPut(elaborate : Boolean) : Operation? {
		return getOperations(elaborate)["put"]
	}

	override fun setPut(put : Operation) {
		getOperations().put("put", put)
	}

	override fun getPost() : Operation? {
		return getOperations()["post"]
	}

	override fun getPost(elaborate : Boolean) : Operation? {
		return getOperations(elaborate)["post"]
	}

	override fun setPost(post : Operation) {
		getOperations().put("post", post)
	}

	override fun getDelete() : Operation? {
		return getOperations()["delete"]
	}

	override fun getDelete(elaborate : Boolean) : Operation? {
		return getOperations(elaborate)["delete"]
	}

	override fun setDelete(delete : Operation) {
		getOperations().put("delete", delete)
	}

	override fun getOptions() : Operation? {
		return getOperations()["options"]
	}

	override fun getOptions(elaborate : Boolean) : Operation? {
		return getOperations(elaborate)["options"]
	}

	override fun setOptions(options : Operation) {
		getOperations().put("options", options)
	}

	override fun getHead() : Operation? {
		return getOperations()["head"]
	}

	override fun getHead(elaborate : Boolean) : Operation? {
		return getOperations(elaborate)["head"]
	}

	override fun setHead(head : Operation) {
		getOperations().put("head", head)
	}

	override fun getPatch() : Operation? {
		return getOperations()["patch"]
	}

	override fun getPatch(elaborate : Boolean) : Operation? {
		return getOperations(elaborate)["patch"]
	}

	override fun setPatch(patch : Operation) {
		getOperations().put("patch", patch)
	}

	override fun getTrace() : Operation? {
		return getOperations()["trace"]
	}

	override fun getTrace(elaborate : Boolean) : Operation? {
		return getOperations(elaborate)["trace"]
	}

	override fun setTrace(trace : Operation) {
		getOperations().put("trace", trace)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(path : Path?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(path, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Summary
	override fun getSummary() : String? {
		return _get("summary", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setSummary(summary : String) {
		_setScalar("summary", summary, String::class.java)
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
	// Operation
	override fun getOperations() : MutableMap<String, Operation> {
		return _getMap("operations", Operation::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getOperations(elaborate : Boolean) : MutableMap<String, Operation> {
		return _getMap("operations", elaborate, Operation::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasOperations() : Boolean {
		return _isPresent("operations")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasOperation(name : String) : Boolean {
		return _getMap("operations", Operation::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getOperation(name : String) : Operation? {
		return _get("operations", name, Operation::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setOperations(operations : MutableMap<String, Operation>) {
		_setMap("operations", operations, Operation::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setOperation(name : String, operation : Operation) {
		_set("operations", name, operation, Operation::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeOperation(name : String) {
		_remove("operations", name, Operation::class.java)
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
	// Parameter
	override fun getParameters() : List<Parameter> {
		return _getList("parameters", Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getParameters(elaborate : Boolean) : List<Parameter> {
		return _getList("parameters", elaborate, Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasParameters() : Boolean {
		return _isPresent("parameters")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getParameter(index : Int) : Parameter {
		return _get("parameters", index, Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setParameters(parameters : MutableList<Parameter>) {
		_setList("parameters", parameters, Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setParameter(index : Int, parameter : Parameter) {
		_set("parameters", index, parameter, Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addParameter(parameter : Parameter) {
		_add("parameters", parameter, Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertParameter(index : Int, parameter : Parameter) {
		_insert("parameters", index, parameter, Parameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeParameter(index : Int) {
		_remove("parameters", index, Parameter::class.java)
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
		_createScalar("summary", "summary", StringOverlay.factory)
		_createScalar("description", "description", StringOverlay.factory)
		_createMap("operations", "", OperationImpl.factory, "get|put|post|delete|options|head|patch|trace")
		_createList("servers", "servers", ServerImpl.factory)
		_createList("parameters", "parameters", ParameterImpl.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Path> {
		return Companion.factory
	}

	companion object {
		const val F_summary : String = "summary"

		const val F_description : String = "description"

		const val F_operations : String = "operations"

		const val F_servers : String = "servers"

		const val F_parameters : String = "parameters"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<Path>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Path>> {
				return PathImpl::class.java
			}
		
			override fun _create(path : Path?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Path> {
				return PathImpl(path, parent, refMgr)
			}
		
			override fun _create(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Path> {
				return PathImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(path : Path) : Class<out Path> {
			return Path::class.java
		}

		private fun getSubtypeOf(json : JsonNode) : Class<out Path> {
			return Path::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<Path> {
			return Builder<Path>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Path {
			return builder(modelMember).build() as Path
		}
	}

}
