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
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.Overlay
import kotlin.collections.Map

class ExampleImpl : PropertiesOverlay<Example> ,Example {

    private val overlay : Overlay<Example> = Overlay.Companion.of(this);

    override fun getName() : String? {
        return if(overlay.parent is MapOverlay<*>) overlay.pathInParent else null
    }

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(example : Example?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(example, parent, Companion.factory, refMgr)

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
	// Value
	override fun getValue() : Any? {
		return _get("value")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setValue(value : Any) {
		_setScalar("value", value)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ExternalValue
	override fun getExternalValue() : String? {
		return _get("externalValue")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExternalValue(externalValue : String) {
		_setScalar("externalValue", externalValue)
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
		_createScalar("summary", "summary", StringOverlay.factory)
		_createScalar("description", "description", StringOverlay.factory)
		_createScalar("value", "value", ObjectOverlay.factory)
		_createScalar("externalValue", "externalValue", StringOverlay.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Example> {
		return Companion.factory
	}

	companion object {
		const val F_summary : String = "summary"

		const val F_description : String = "description"

		const val F_value : String = "value"

		const val F_externalValue : String = "externalValue"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<Example>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Example>> {
				return ExampleImpl::class.java
			}
		
			override fun _create(example : Example?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Example> {
				return ExampleImpl(example, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Example> {
				return ExampleImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(example : Example) : Class<out Example> {
			return Example::class.java
		}

		private fun getSubtypeOf(json : JsonElement) : Class<out Example> {
			return Example::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<Example> {
			return Builder<Example>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Example {
			return builder(modelMember).build() as Example
		}
	}

}
