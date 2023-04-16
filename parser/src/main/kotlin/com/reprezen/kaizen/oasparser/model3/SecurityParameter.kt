package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.List

interface SecurityParameter : IJsonOverlay<SecurityParameter>, IModelPart<OpenApi3, SecurityParameter> {

	// Parameter
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameters() : List<String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasParameters() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameter(index : Int) : String

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setParameters(parameters : MutableList<String>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setParameter(index : Int, parameter : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addParameter(parameter : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertParameter(index : Int,parameter : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeParameter(index : Int)

}
