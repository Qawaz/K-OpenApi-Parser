package com.reprezen.jsonoverlay.model.intf

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.IModelPart

interface Entry : IJsonOverlay<Entry>, IModelPart<TestModel, Entry> {

	// Title
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTitle() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setTitle(title : String)

}
