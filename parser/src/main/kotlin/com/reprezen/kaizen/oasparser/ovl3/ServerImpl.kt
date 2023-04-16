package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.ReferenceManager
import com.reprezen.kaizen.oasparser.ovl3.ServerVariableImpl
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import kotlin.collections.Map

class ServerImpl : PropertiesOverlay<Server> ,Server {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(server : Server?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(server, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Url
	override fun getUrl() : String? {
		return _get("url")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setUrl(url : String) {
		_setScalar("url", url)
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
	// ServerVariable
	override fun getServerVariables() : MutableMap<String, ServerVariable> {
		return _getMap("serverVariables")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasServerVariables() : Boolean {
		return _isPresent("serverVariables")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasServerVariable(name : String) : Boolean {
		return _getMap<ServerVariable>("serverVariables").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getServerVariable(name : String) : ServerVariable? {
		return _get("serverVariables", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setServerVariables(serverVariables : MutableMap<String, ServerVariable>) {
		_setMap("serverVariables", serverVariables)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setServerVariable(name : String, serverVariable : ServerVariable) {
		_set("serverVariables", name, serverVariable)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeServerVariable(name : String) {
		_remove("serverVariables", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// VariablesExtension
	override fun getVariablesExtensions() : MutableMap<String, Any> {
		return _getMap("variablesExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasVariablesExtensions() : Boolean {
		return _isPresent("variablesExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasVariablesExtension(name : String) : Boolean {
		return _getMap<Any>("variablesExtensions").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getVariablesExtension(name : String) : Any? {
		return _get("variablesExtensions", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setVariablesExtensions(variablesExtensions : MutableMap<String, Any>) {
		_setMap("variablesExtensions", variablesExtensions)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setVariablesExtension(name : String, variablesExtension : Any) {
		_set("variablesExtensions", name, variablesExtension)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeVariablesExtension(name : String) {
		_remove("variablesExtensions", name)
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
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Server> {
				return ServerImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<Server> {
			return Builder<Server>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Server {
			return builder(modelMember).build() as Server
		}
	}

}
