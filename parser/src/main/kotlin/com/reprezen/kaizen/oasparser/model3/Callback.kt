package com.reprezen.kaizen.oasparser.model3

import com.reprezen.kaizen.oasparser.model3.Path
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.Map

interface Callback : IJsonOverlay<Callback>, IModelPart<OpenApi3, Callback> {

    fun getName() : String?

	// CallbackPath
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getCallbackPaths() : MutableMap<String, Path>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getCallbackPaths(elaborate : Boolean) : MutableMap<String, Path>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasCallbackPaths() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasCallbackPath(expression : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getCallbackPath(expression : String) : Path?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setCallbackPaths(callbackPaths : MutableMap<String, Path>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setCallbackPath(expression : String,callbackPath : Path)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeCallbackPath(expression : String)

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
