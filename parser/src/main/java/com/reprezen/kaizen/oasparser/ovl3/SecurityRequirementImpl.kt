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
import com.reprezen.jsonoverlay.ReferenceManager;
import com.reprezen.jsonoverlay.JsonOverlay;
import kotlin.collections.Map;
import com.reprezen.kaizen.oasparser.ovl3.SecurityParameterImpl;

class SecurityRequirementImpl : PropertiesOverlay<SecurityRequirement> ,SecurityRequirement {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(securityRequirement : SecurityRequirement?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(securityRequirement, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Requirement
	override fun getRequirements() : MutableMap<String, SecurityParameter> {
		return _getMap("requirements", SecurityParameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getRequirements(elaborate : Boolean) : MutableMap<String, SecurityParameter> {
		return _getMap("requirements", elaborate, SecurityParameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasRequirements() : Boolean {
		return _isPresent("requirements")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasRequirement(name : String) : Boolean {
		return _getMap("requirements", SecurityParameter::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getRequirement(name : String) : SecurityParameter? {
		return _get("requirements", name, SecurityParameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequirements(requirements : MutableMap<String, SecurityParameter>) {
		_setMap("requirements", requirements, SecurityParameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequirement(name : String, requirement : SecurityParameter) {
		_set("requirements", name, requirement, SecurityParameter::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeRequirement(name : String) {
		_remove("requirements", name, SecurityParameter::class.java)
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

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<SecurityRequirement> {
		return Companion.factory
	}

	companion object {
		const val F_requirements : String = "requirements"

		val factory = object : OverlayFactory<SecurityRequirement>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in SecurityRequirement>> {
				return SecurityRequirementImpl::class.java
			}
		
			override fun _create(securityRequirement : SecurityRequirement?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<SecurityRequirement> {
				return SecurityRequirementImpl(securityRequirement, parent, refMgr)
			}
		
			override fun _create(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<SecurityRequirement> {
				return SecurityRequirementImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(securityRequirement : SecurityRequirement) : Class<out SecurityRequirement> {
			return SecurityRequirement::class.java
		}

		private fun getSubtypeOf(json : JsonNode) : Class<out SecurityRequirement> {
			return SecurityRequirement::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<SecurityRequirement> {
			return Builder<SecurityRequirement>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : SecurityRequirement {
			return builder(modelMember).build() as SecurityRequirement
		}
	}

}
