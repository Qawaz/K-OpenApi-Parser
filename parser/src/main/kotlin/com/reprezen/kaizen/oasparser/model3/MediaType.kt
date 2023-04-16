package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.kaizen.oasparser.model3.EncodingProperty
import com.reprezen.kaizen.oasparser.model3.Schema
import com.reprezen.kaizen.oasparser.model3.Example
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.Map

interface MediaType : IJsonOverlay<MediaType>, IModelPart<OpenApi3, MediaType> {

	// Schema
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSchema() : Schema?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setSchema(schema : Schema)

	// Example
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExamples() : MutableMap<String, Example>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExamples() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExample(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExample(name : String) : Example?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExamples(examples : MutableMap<String, Example>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExample(name : String,example : Example)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeExample(name : String)

	// Example
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExample() : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExample(example : Any)

	// EncodingProperty
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEncodingProperties() : MutableMap<String, EncodingProperty>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasEncodingProperties() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasEncodingProperty(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEncodingProperty(name : String) : EncodingProperty?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setEncodingProperties(encodingProperties : MutableMap<String, EncodingProperty>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setEncodingProperty(name : String,encodingProperty : EncodingProperty)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeEncodingProperty(name : String)

	// Extension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtensions() : MutableMap<String, Any>

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
