package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.kaizen.oasparser.model3.Example
import com.reprezen.kaizen.oasparser.model3.Schema
import com.reprezen.kaizen.oasparser.model3.MediaType
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.Map

interface Parameter : IJsonOverlay<Parameter>, IModelPart<OpenApi3, Parameter> {

    fun getKey() : String?

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

	// ContentMediaType
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getContentMediaTypes() : MutableMap<String, MediaType>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasContentMediaTypes() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasContentMediaType(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getContentMediaType(name : String) : MediaType?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setContentMediaTypes(contentMediaTypes : MutableMap<String, MediaType>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setContentMediaType(name : String,contentMediaType : MediaType)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeContentMediaType(name : String)

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

	// Schema
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getSchema() : Schema?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setSchema(schema : Schema)

	// AllowReserved
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAllowReserved() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isAllowReserved() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAllowReserved(allowReserved : Boolean)

	// Explode
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExplode() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isExplode() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExplode(explode : Boolean)

	// Style
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getStyle() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setStyle(style : String)

	// AllowEmptyValue
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAllowEmptyValue() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isAllowEmptyValue() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAllowEmptyValue(allowEmptyValue : Boolean)

	// Deprecated
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDeprecated() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isDeprecated() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDeprecated(deprecated : Boolean)

	// Required
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRequired() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isRequired() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setRequired(required : Boolean)

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

	// Name
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getName() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setName(name : String)

	// In
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getIn() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setIn(inValue : String)

}
