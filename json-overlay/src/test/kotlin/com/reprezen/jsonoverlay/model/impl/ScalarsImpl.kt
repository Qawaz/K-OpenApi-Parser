package com.reprezen.jsonoverlay.model.impl;

import com.reprezen.jsonoverlay.model.intf.*;
import java.util.stream.Collectors;
import com.reprezen.jsonoverlay.StringOverlay;
import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.jsonoverlay.Builder;
import com.fasterxml.jackson.databind.JsonNode;
import com.reprezen.jsonoverlay.PropertiesOverlay;
import com.fasterxml.jackson.core.JsonPointer;
import com.reprezen.jsonoverlay.NumberOverlay;
import com.reprezen.jsonoverlay.ReferenceManager;
import com.reprezen.jsonoverlay.IntegerOverlay;
import com.reprezen.jsonoverlay.PrimitiveOverlay;
import com.reprezen.jsonoverlay.model.impl.ScalarsImpl;
import com.reprezen.jsonoverlay.ObjectOverlay;
import com.reprezen.jsonoverlay.JsonOverlay;
import com.reprezen.jsonoverlay.model.impl.ColorImpl;
import com.reprezen.jsonoverlay.BooleanOverlay;

class ScalarsImpl : PropertiesOverlay<Scalars> ,Scalars {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(scalars : Scalars?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(scalars, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// StringValue
	override fun getStringValue() : String? {
		return _get("stringValue", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setStringValue(stringValue : String) {
		_setScalar("stringValue", stringValue, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// IntValue
	override fun getIntValue() : Int? {
		return _get("intValue", Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setIntValue(intValue : Int) {
		_setScalar("intValue", intValue, Int::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// NumberValue
	override fun getNumberValue() : Number? {
		return _get("numberValue", Number::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setNumberValue(numberValue : Number) {
		_setScalar("numberValue", numberValue, Number::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// BoolValue
	override fun getBoolValue() : Boolean? {
		return _get("boolValue", Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isBoolValue() : Boolean {
		return _get("boolValue", Boolean::class.java) ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setBoolValue(boolValue : Boolean) {
		_setScalar("boolValue", boolValue, Boolean::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ObjValue
	override fun getObjValue() : Any? {
		return _get("objValue", Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setObjValue(objValue : Any) {
		_setScalar("objValue", objValue, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// PrimValue
	override fun getPrimValue() : Any? {
		return _get("primValue", Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setPrimValue(primValue : Any) {
		_setScalar("primValue", primValue, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ColorValue
	override fun getColorValue() : Color? {
		return _get("colorValue", Color::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getColorValue(elaborate : Boolean) : Color? {
		return _get("colorValue", elaborate, Color::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setColorValue(colorValue : Color) {
		_setScalar("colorValue", colorValue, Color::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// EmbeddedScalars
	override fun getEmbeddedScalars() : Scalars? {
		return _get("embeddedScalars", Scalars::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getEmbeddedScalars(elaborate : Boolean) : Scalars? {
		return _get("embeddedScalars", elaborate, Scalars::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setEmbeddedScalars(embeddedScalars : Scalars) {
		_setScalar("embeddedScalars", embeddedScalars, Scalars::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _elaborateJson() {
		super._elaborateJson()
		_createScalar("stringValue", "stringValue", StringOverlay.factory)
		_createScalar("intValue", "intValue", IntegerOverlay.factory)
		_createScalar("numberValue", "numberValue", NumberOverlay.factory)
		_createScalar("boolValue", "boolValue", BooleanOverlay.factory)
		_createScalar("objValue", "objValue", ObjectOverlay.factory)
		_createScalar("primValue", "primValue", PrimitiveOverlay.factory)
		_createScalar("colorValue", "colorValue", ColorImpl.factory)
		_createScalar("embeddedScalars", "embeddedScalars", ScalarsImpl.factory)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return TestModel::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Scalars> {
		return Companion.factory
	}

	companion object {
		const val F_stringValue : String = "stringValue"

		const val F_intValue : String = "intValue"

		const val F_numberValue : String = "numberValue"

		const val F_boolValue : String = "boolValue"

		const val F_objValue : String = "objValue"

		const val F_primValue : String = "primValue"

		const val F_colorValue : String = "colorValue"

		const val F_embeddedScalars : String = "embeddedScalars"

		val factory = object : OverlayFactory<Scalars>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Scalars>> {
				return ScalarsImpl::class.java
			}
		
			override fun _create(scalars : Scalars?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Scalars> {
				return ScalarsImpl(scalars, parent, refMgr)
			}
		
			override fun _create(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Scalars> {
				return ScalarsImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(scalars : Scalars) : Class<out Scalars> {
			return Scalars::class.java
		}

		private fun getSubtypeOf(json : JsonNode) : Class<out Scalars> {
			return Scalars::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<Scalars> {
			return Builder<Scalars>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Scalars {
			return builder(modelMember).build() as Scalars
		}
	}

}