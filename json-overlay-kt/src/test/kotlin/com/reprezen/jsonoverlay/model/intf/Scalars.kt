package com.reprezen.jsonoverlay.model.intf

import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.model.intf.Color;
import com.reprezen.jsonoverlay.model.intf.Scalars;
import com.reprezen.jsonoverlay.IModelPart;

interface Scalars : IJsonOverlay<Scalars>, IModelPart<TestModel, Scalars> {

	// StringValue
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getStringValue() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setStringValue(stringValue : String)

	// IntValue
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getIntValue() : Int?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setIntValue(intValue : Int)

	// NumberValue
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getNumberValue() : Number?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setNumberValue(numberValue : Number)

	// BoolValue
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getBoolValue() : Boolean?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun isBoolValue() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setBoolValue(boolValue : Boolean)

	// ObjValue
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getObjValue() : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setObjValue(objValue : Any)

	// PrimValue
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPrimValue() : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPrimValue(primValue : Any)

	// ColorValue
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getColorValue() : Color?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getColorValue(elaborate : Boolean) : Color?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setColorValue(colorValue : Color)

	// EmbeddedScalars
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEmbeddedScalars() : Scalars?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getEmbeddedScalars(elaborate : Boolean) : Scalars?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setEmbeddedScalars(embeddedScalars : Scalars)

}
