package com.reprezen.kaizen.oasparser.ovl3;

import com.reprezen.kaizen.oasparser.model3.*;
import com.reprezen.jsonoverlay.MapOverlay;
import java.util.stream.Collectors;
import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.jsonoverlay.Builder;
import com.fasterxml.jackson.databind.JsonNode;
import com.reprezen.jsonoverlay.PropertiesOverlay;
import com.fasterxml.jackson.core.JsonPointer;
import com.reprezen.kaizen.oasparser.ovl3.PathImpl;
import com.reprezen.jsonoverlay.ReferenceManager;
import com.reprezen.jsonoverlay.ObjectOverlay;
import com.reprezen.jsonoverlay.JsonOverlay;
import com.reprezen.jsonoverlay.Overlay;
import kotlin.collections.Map;

class CallbackImpl : PropertiesOverlay<Callback> ,Callback {

    private val overlay : Overlay<Callback> = Overlay.Companion.of(this);

    override fun getName() : String? {
        return if(overlay.parent is MapOverlay<*>) overlay.pathInParent else null
    }

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(callback : Callback?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(callback, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// CallbackPath
	override fun getCallbackPaths() : MutableMap<String, Path> {
		return _getMap("callbackPaths", Path::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getCallbackPaths(elaborate : Boolean) : MutableMap<String, Path> {
		return _getMap("callbackPaths", elaborate, Path::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasCallbackPaths() : Boolean {
		return _isPresent("callbackPaths")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasCallbackPath(expression : String) : Boolean {
		return _getMap("callbackPaths", Path::class.java).containsKey(expression)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getCallbackPath(expression : String) : Path? {
		return _get("callbackPaths", expression, Path::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setCallbackPaths(callbackPaths : MutableMap<String, Path>) {
		_setMap("callbackPaths", callbackPaths, Path::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setCallbackPath(expression : String, callbackPath : Path) {
		_set("callbackPaths", expression, callbackPath, Path::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeCallbackPath(expression : String) {
		_remove("callbackPaths", expression, Path::class.java)
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
		_createMap("callbackPaths", "", PathImpl.factory, "(?!x-).*")
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Callback> {
		return Companion.factory
	}

	companion object {
		const val F_callbackPaths : String = "callbackPaths"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<Callback>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Callback>> {
				return CallbackImpl::class.java
			}
		
			override fun _create(callback : Callback?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Callback> {
				return CallbackImpl(callback, parent, refMgr)
			}
		
			override fun _create(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Callback> {
				return CallbackImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(callback : Callback) : Class<out Callback> {
			return Callback::class.java
		}

		private fun getSubtypeOf(json : JsonNode) : Class<out Callback> {
			return Callback::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<Callback> {
			return Builder<Callback>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Callback {
			return builder(modelMember).build() as Callback
		}
	}

}
