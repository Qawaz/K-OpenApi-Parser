package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.IModelPart;
import kotlin.collections.List;
import kotlin.collections.Map;

interface ServerVariable : IJsonOverlay<ServerVariable>, IModelPart<OpenApi3, ServerVariable> {

	// EnumValue
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEnumValues() : List<String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEnumValues(elaborate : Boolean) : List<String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasEnumValues() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEnumValue(index : Int) : String

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setEnumValues(enumValues : MutableList<String>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setEnumValue(index : Int, enumValue : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addEnumValue(enumValue : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertEnumValue(index : Int,enumValue : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeEnumValue(index : Int)

	// Default
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDefault() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDefault(defaultValue : String)

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

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
