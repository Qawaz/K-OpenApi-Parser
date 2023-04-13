package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.kaizen.oasparser.model3.Server
import com.reprezen.kaizen.oasparser.model3.Parameter
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.List
import com.reprezen.kaizen.oasparser.model3.Operation
import kotlin.collections.Map

interface Path : IJsonOverlay<Path>, IModelPart<OpenApi3, Path> {

    fun getPathString() : String?

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

	// Operation
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOperations() : MutableMap<String, Operation>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOperations(elaborate : Boolean) : MutableMap<String, Operation>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasOperations() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasOperation(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOperation(name : String) : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOperations(operations : MutableMap<String, Operation>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOperation(name : String,operation : Operation)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeOperation(name : String)

	// Get
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getGet() : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getGet(elaborate : Boolean) : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setGet(get : Operation)

	// Put
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPut() : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPut(elaborate : Boolean) : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPut(put : Operation)

	// Post
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPost() : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPost(elaborate : Boolean) : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPost(post : Operation)

	// Delete
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDelete() : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDelete(elaborate : Boolean) : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDelete(delete : Operation)

	// Options
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOptions() : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOptions(elaborate : Boolean) : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOptions(options : Operation)

	// Head
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getHead() : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getHead(elaborate : Boolean) : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setHead(head : Operation)

	// Patch
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPatch() : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPatch(elaborate : Boolean) : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPatch(patch : Operation)

	// Trace
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTrace() : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTrace(elaborate : Boolean) : Operation?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setTrace(trace : Operation)

	// Server
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServers() : List<Server>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServers(elaborate : Boolean) : List<Server>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasServers() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getServer(index : Int) : Server

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setServers(servers : MutableList<Server>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setServer(index : Int, server : Server)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addServer(server : Server)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertServer(index : Int,server : Server)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeServer(index : Int)

	// Parameter
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameters() : List<Parameter>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameters(elaborate : Boolean) : List<Parameter>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasParameters() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getParameter(index : Int) : Parameter

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setParameters(parameters : MutableList<Parameter>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setParameter(index : Int, parameter : Parameter)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun addParameter(parameter : Parameter)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun insertParameter(index : Int,parameter : Parameter)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeParameter(index : Int)

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
