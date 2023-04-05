package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.IModelPart;
import kotlin.collections.Map;

interface Xml : IJsonOverlay<Xml>, IModelPart<OpenApi3, Xml> {

	// Name
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getName() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setName(name : String)

	// Namespace
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getNamespace() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setNamespace(namespace : String)

	// Prefix
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPrefix() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPrefix(prefix : String)

	// Attribute
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAttribute() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isAttribute() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAttribute(attribute : Boolean)

	// Wrapped
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getWrapped() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isWrapped() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setWrapped(wrapped : Boolean)

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
