package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.IModelPart;
import com.reprezen.kaizen.oasparser.model3.ServerVariable;
import kotlin.collections.Map;

interface Server : IJsonOverlay<Server>, IModelPart<OpenApi3, Server> {

	// Url
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getUrl() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setUrl(url : String)

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

	// ServerVariable
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServerVariables() : MutableMap<String, ServerVariable>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServerVariables(elaborate : Boolean) : MutableMap<String, ServerVariable>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasServerVariables() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasServerVariable(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServerVariable(name : String) : ServerVariable?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setServerVariables(serverVariables : MutableMap<String, ServerVariable>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setServerVariable(name : String,serverVariable : ServerVariable)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeServerVariable(name : String)

	// VariablesExtension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getVariablesExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getVariablesExtensions(elaborate : Boolean) : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasVariablesExtensions() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasVariablesExtension(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getVariablesExtension(name : String) : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setVariablesExtensions(variablesExtensions : MutableMap<String, Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setVariablesExtension(name : String,variablesExtension : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeVariablesExtension(name : String)

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
