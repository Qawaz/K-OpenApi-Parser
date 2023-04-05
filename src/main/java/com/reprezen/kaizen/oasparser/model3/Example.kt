package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.IModelPart;
import kotlin.collections.Map;

interface Example : IJsonOverlay<Example>, IModelPart<OpenApi3, Example> {

    fun getName() : String?

	// Summary
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSummary() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setSummary(summary : String)

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

	// Value
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getValue() : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setValue(value : Any)

	// ExternalValue
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExternalValue() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExternalValue(externalValue : String)

	// Extension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtensions(elaborate : Boolean) : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExtensions() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExtension(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtension(name : String) : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExtensions(extensions : MutableMap<String, Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExtension(name : String,extension : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeExtension(name : String)

}
