package com.reprezen.kaizen.oasparser.ovl3;

import com.reprezen.kaizen.oasparser.model3.*;
import com.reprezen.jsonoverlay.MapOverlay;
import java.util.stream.Collectors;
import com.reprezen.jsonoverlay.StringOverlay;
import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.jsonoverlay.Builder;
import com.fasterxml.jackson.databind.JsonNode;
import com.reprezen.jsonoverlay.PropertiesOverlay;
import com.fasterxml.jackson.core.JsonPointer;
import com.reprezen.jsonoverlay.ReferenceManager;
import com.reprezen.kaizen.oasparser.ovl3.ServerVariableImpl;
import com.reprezen.jsonoverlay.ObjectOverlay;
import com.reprezen.jsonoverlay.JsonOverlay;
import kotlin.collections.Map;

class ServerImpl : PropertiesOverlay<Server> ,Server {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(server : Server?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(server, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Url
	override fun getUrl() : String? {
		return _get("url", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setUrl(url : String) {
		_setScalar("url", url, String::class.java)
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
	// ServerVariable
	override fun getServerVariables() : MutableMap<String, ServerVariable> {
		return _getMap("serverVariables", ServerVariable::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getServerVariables(elaborate : Boolean) : MutableMap<String, ServerVariable> {
		return _getMap("serverVariables", elaborate, ServerVariable::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasServerVariables() : Boolean {
		return _isPresent("serverVariables")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasServerVariable(name : String) : Boolean {
		return _getMap("serverVariables", ServerVariable::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getServerVariable(name : String) : ServerVariable? {
		return _get("serverVariables", name, ServerVariable::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setServerVariables(serverVariables : MutableMap<String, ServerVariable>) {
		_setMap("serverVariables", serverVariables, ServerVariable::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setServerVariable(name : String, serverVariable : ServerVariable) {
		_set("serverVariables", name, serverVariable, ServerVariable::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeServerVariable(name : String) {
		_remove("serverVariables", name, ServerVariable::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// VariablesExtension
	override fun getVariablesExtensions() : MutableMap<String, Any> {
		return _getMap("variablesExtensions", Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getVariablesExtensions(elaborate : Boolean) : MutableMap<String, Any> {
		return _getMap("variablesExtensions", elaborate, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasVariablesExtensions() : Boolean {
		return _isPresent("variablesExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasVariablesExtension(name : String) : Boolean {
		return _getMap("variablesExtensions", Any::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getVariablesExtension(name : String) : Any? {
		return _get("variablesExtensions", name, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setVariablesExtensions(variablesExtensions : MutableMap<String, Any>) {
		_setMap("variablesExtensions", variablesExtensions, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setVariablesExtension(name : String, variablesExtension : Any) {
		_set("variablesExtensions", name, variablesExtension, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeVariablesExtension(name : String) {
		_remove("variablesExtensions", name, Any::class.java)
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
		_createScalar("url", "url", StringOverlay.factory)
		_createScalar("description", "description", StringOverlay.factory)
		_createMap("serverVariables", "variables", ServerVariableImpl.factory, "(?!x-)[a-zA-Z0-9\\._-]+")
		_createMap("variablesExtensions", "variables", ObjectOverlay.factory, "x-.+")
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Server> {
		return Companion.factory
	}

	companion object {
		const val F_url : String = "url"

		const val F_description : String = "description"

		const val F_serverVariables : String = "serverVariables"

		const val F_variablesExtensions : String = "variablesExtensions"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<Server>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Server>> {
				return ServerImpl::class.java
			}
		
			override fun _create(server : Server?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Server> {
				return ServerImpl(server, parent, refMgr)
			}
		
			override fun _create(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Server> {
				return ServerImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(server : Server) : Class<out Server> {
			return Server::class.java
		}

		private fun getSubtypeOf(json : JsonNode) : Class<out Server> {
			return Server::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<Server> {
			return Builder<Server>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Server {
			return builder(modelMember).build() as Server
		}
	}

}