package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.kaizen.oasparser.model3.Server;
import com.reprezen.jsonoverlay.IModelPart;
import kotlin.collections.Map;

interface Link : IJsonOverlay<Link>, IModelPart<OpenApi3, Link> {

    fun getName() : String?

	// OperationId
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOperationId() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOperationId(operationId : String)

	// OperationRef
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOperationRef() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOperationRef(operationRef : String)

	// Parameter
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameters() : MutableMap<String, String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameters(elaborate : Boolean) : MutableMap<String, String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasParameters() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasParameter(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameter(name : String) : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setParameters(parameters : MutableMap<String, String>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setParameter(name : String,parameter : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeParameter(name : String)

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

	// Server
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServer() : Server?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServer(elaborate : Boolean) : Server?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setServer(server : Server)

	// RequestBody
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequestBody() : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setRequestBody(requestBody : Any)

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
