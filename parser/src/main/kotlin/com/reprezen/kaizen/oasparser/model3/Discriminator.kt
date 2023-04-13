package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.Map

interface Discriminator : IJsonOverlay<Discriminator>, IModelPart<OpenApi3, Discriminator> {

	// PropertyName
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPropertyName() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPropertyName(propertyName : String)

	// Mapping
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMappings() : MutableMap<String, String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMappings(elaborate : Boolean) : MutableMap<String, String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasMappings() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasMapping(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getMapping(name : String) : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setMappings(mappings : MutableMap<String, String>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setMapping(name : String,mapping : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeMapping(name : String)

}
