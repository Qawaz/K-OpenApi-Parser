package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.MapOverlay
import kotlin.reflect.KClass
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import kotlin.collections.Map
import com.reprezen.kaizen.oasparser.ovl3.SecurityParameterImpl
import com.reprezen.jsonoverlay.ReferenceManager

class SecurityRequirementImpl : PropertiesOverlay<SecurityRequirement> ,SecurityRequirement {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(securityRequirement : SecurityRequirement?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(securityRequirement, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Requirement
	override fun getRequirements() : MutableMap<String, SecurityParameter> {
		return _getMap("requirements")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasRequirements() : Boolean {
		return _isPresent("requirements")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasRequirement(name : String) : Boolean {
		return _getMap<SecurityParameter>("requirements").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getRequirement(name : String) : SecurityParameter? {
		return _get("requirements", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequirements(requirements : MutableMap<String, SecurityParameter>) {
		_setMap("requirements", requirements)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequirement(name : String, requirement : SecurityParameter) {
		_set("requirements", name, requirement)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeRequirement(name : String) {
		_remove("requirements", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _elaborateJson() {
		super._elaborateJson()
		_createMap("requirements", "", SecurityParameterImpl.factory, "[a-zA-Z0-9\\._-]+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	companion object {
		const val F_requirements : String = "requirements"

		val factory = object : OverlayFactory<SecurityRequirement>() {
		
			override val signature: String?
				get() = SecurityRequirementImpl::class.simpleName
		
			override fun _create(value : SecurityRequirement?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<SecurityRequirement> {
				return SecurityRequirementImpl(value, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<SecurityRequirement> {
				return SecurityRequirementImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<SecurityRequirement> {
			return Builder<SecurityRequirement>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : SecurityRequirement {
			return builder(modelMember).build() as SecurityRequirement
		}
	}

}
