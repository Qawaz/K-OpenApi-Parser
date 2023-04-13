package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.MapOverlay
import java.util.stream.Collectors
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.JsonPointer
import com.reprezen.jsonoverlay.ReferenceManager
import com.reprezen.kaizen.oasparser.ovl3.ServerImpl
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.Overlay
import kotlin.collections.Map

class LinkImpl : PropertiesOverlay<Link> ,Link {

    private val overlay : Overlay<Link> = Overlay.Companion.of(this);

    override fun getName() : String? {
        return if(overlay.parent is MapOverlay<*>) overlay.pathInParent else null
    }

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(link : Link?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(link, parent, Companion.factory, refMgr)

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
	// OperationRef
	override fun getOperationRef() : String? {
		return _get("operationRef")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setOperationRef(operationRef : String) {
		_setScalar("operationRef", operationRef)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Parameter
	override fun getParameters() : MutableMap<String, String> {
		return _getMap("parameters")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getParameters(elaborate : Boolean) : MutableMap<String, String> {
		return _getMap("parameters")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasParameters() : Boolean {
		return _isPresent("parameters")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasParameter(name : String) : Boolean {
		return _getMap<String>("parameters").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getParameter(name : String) : String? {
		return _get("parameters", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setParameters(parameters : MutableMap<String, String>) {
		_setMap("parameters", parameters)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setParameter(name : String, parameter : String) {
		_set("parameters", name, parameter)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeParameter(name : String) {
		_remove("parameters", name)
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
	// Server
	override fun getServer() : Server? {
		return _get("server")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getServer(elaborate : Boolean) : Server? {
		return _get("server")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setServer(server : Server) {
		_setScalar("server", server)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// RequestBody
	override fun getRequestBody() : Any? {
		return _get("requestBody")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequestBody(requestBody : Any) {
		_setScalar("requestBody", requestBody)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Extension
	override fun getExtensions() : MutableMap<String, Any> {
		return _getMap("extensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExtensions(elaborate : Boolean) : MutableMap<String, Any> {
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
		_createScalar("operationId", "operationId", StringOverlay.factory)
		_createScalar("operationRef", "operationRef", StringOverlay.factory)
		_createMap("parameters", "parameters", StringOverlay.factory, null)
		_createScalar("description", "description", StringOverlay.factory)
		_createScalar("server", "server", ServerImpl.factory)
		_createScalar("requestBody", "requestBody", ObjectOverlay.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Link> {
		return Companion.factory
	}

	companion object {
		const val F_operationId : String = "operationId"

		const val F_operationRef : String = "operationRef"

		const val F_parameters : String = "parameters"

		const val F_description : String = "description"

		const val F_server : String = "server"

		const val F_requestBody : String = "requestBody"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<Link>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Link>> {
				return LinkImpl::class.java
			}
		
			override fun _create(link : Link?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Link> {
				return LinkImpl(link, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Link> {
				return LinkImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(link : Link) : Class<out Link> {
			return Link::class.java
		}

		private fun getSubtypeOf(json : JsonElement) : Class<out Link> {
			return Link::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<Link> {
			return Builder<Link>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Link {
			return builder(modelMember).build() as Link
		}
	}

}
