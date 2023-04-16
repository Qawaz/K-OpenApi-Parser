package com.reprezen.jsonoverlay.model.impl

import com.reprezen.jsonoverlay.model.intf.*
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.NumberOverlay
import com.reprezen.jsonoverlay.ReferenceManager
import com.reprezen.jsonoverlay.IntegerOverlay
import com.reprezen.jsonoverlay.PrimitiveOverlay
import com.reprezen.jsonoverlay.model.impl.ScalarsImpl
import kotlin.reflect.KClass
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.model.impl.ColorImpl
import com.reprezen.jsonoverlay.BooleanOverlay

class ScalarsImpl : PropertiesOverlay<Scalars> ,Scalars {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(scalars : Scalars?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(scalars, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// StringValue
	override fun getStringValue() : String? {
		return _get("stringValue")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setStringValue(stringValue : String) {
		_setScalar("stringValue", stringValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// IntValue
	override fun getIntValue() : Int? {
		return _get("intValue")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setIntValue(intValue : Int) {
		_setScalar("intValue", intValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// NumberValue
	override fun getNumberValue() : Number? {
		return _get("numberValue")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setNumberValue(numberValue : Number) {
		_setScalar("numberValue", numberValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// BoolValue
	override fun getBoolValue() : Boolean? {
		return _get("boolValue")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isBoolValue() : Boolean {
		return _get("boolValue") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setBoolValue(boolValue : Boolean) {
		_setScalar("boolValue", boolValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ObjValue
	override fun getObjValue() : Any? {
		return _get("objValue")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setObjValue(objValue : Any) {
		_setScalar("objValue", objValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// PrimValue
	override fun getPrimValue() : Any? {
		return _get("primValue")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setPrimValue(primValue : Any) {
		_setScalar("primValue", primValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ColorValue
	override fun getColorValue() : Color? {
		return _get("colorValue")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setColorValue(colorValue : Color) {
		_setScalar("colorValue", colorValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// EmbeddedScalars
	override fun getEmbeddedScalars() : Scalars? {
		return _get("embeddedScalars")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setEmbeddedScalars(embeddedScalars : Scalars) {
		_setScalar("embeddedScalars", embeddedScalars)
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
		
			override val signature: String?
				get() = ScalarsImpl::class.simpleName
		
			override fun _create(value : Scalars?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Scalars> {
				return ScalarsImpl(value, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Scalars> {
				return ScalarsImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<Scalars> {
			return Builder<Scalars>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Scalars {
			return builder(modelMember).build() as Scalars
		}
	}

}
