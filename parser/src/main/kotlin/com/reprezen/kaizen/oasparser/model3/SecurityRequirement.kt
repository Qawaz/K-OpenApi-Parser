package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.IModelPart
import com.reprezen.kaizen.oasparser.model3.SecurityParameter
import kotlin.collections.Map

interface SecurityRequirement : IJsonOverlay<SecurityRequirement>, IModelPart<OpenApi3, SecurityRequirement> {

	// Requirement
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequirements() : MutableMap<String, SecurityParameter>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasRequirements() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasRequirement(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequirement(name : String) : SecurityParameter?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setRequirements(requirements : MutableMap<String, SecurityParameter>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setRequirement(name : String,requirement : SecurityParameter)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeRequirement(name : String)

}
