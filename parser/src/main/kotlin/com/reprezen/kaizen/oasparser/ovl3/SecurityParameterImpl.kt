package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.ListOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.PropertiesOverlay
import kotlinx.serialization.json.JsonNull
import com.reprezen.jsonoverlay.ReferenceManager
import kotlinx.serialization.json.JsonArray
import kotlin.reflect.KClass
import kotlinx.serialization.json.JsonElement
import kotlin.collections.List
import com.reprezen.jsonoverlay.JsonOverlay

class SecurityParameterImpl : PropertiesOverlay<SecurityParameter> ,SecurityParameter {

    override fun _fixJson(json : JsonElement) : JsonElement {
        return if(json is JsonNull) JsonArray(listOf()) else json
    }

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(securityParameter : SecurityParameter?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(securityParameter, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Parameter
	override fun getParameters() : List<String> {
		return _getList("parameters")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasParameters() : Boolean {
		return _isPresent("parameters")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getParameter(index : Int) : String {
		return _get("parameters", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setParameters(parameters : MutableList<String>) {
		_setList("parameters", parameters)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setParameter(index : Int, parameter : String) {
		_set("parameters", index, parameter)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun addParameter(parameter : String) {
		_add("parameters", parameter)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun insertParameter(index : Int, parameter : String) {
		_insert("parameters", index, parameter)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeParameter(index : Int) {
		_remove("parameters", index)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _elaborateJson() {
		super._elaborateJson()
		_createList("parameters", "", StringOverlay.factory)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	companion object {
		const val F_parameters : String = "parameters"

		val factory = object : OverlayFactory<SecurityParameter>() {
		
			override val signature: String?
				get() = SecurityParameterImpl::class.simpleName
		
			override fun _create(value : SecurityParameter?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<SecurityParameter> {
				return SecurityParameterImpl(value, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<SecurityParameter> {
				return SecurityParameterImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<SecurityParameter> {
			return Builder<SecurityParameter>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : SecurityParameter {
			return builder(modelMember).build() as SecurityParameter
		}
	}

}
