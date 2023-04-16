package com.reprezen.jsonoverlay.model.intf

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.IModelPart

interface Item : IJsonOverlay<Item>, IModelPart<TestModel, Item> {

	// Title
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTitle() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setTitle(title : String)

}
