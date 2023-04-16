package com.reprezen.jsonoverlay.model.impl

import com.reprezen.jsonoverlay.model.intf.*
import kotlin.reflect.KClass
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.EnumOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.ReferenceManager

class ColorImpl : EnumOverlay<Color> {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(color : Color?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(color, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getEnumValue(value : String) : Color {
		return Color.valueOf(value)
	}

	companion object {
		val factory = object : OverlayFactory<Color>() {
		
			override val signature: String?
				get() = ColorImpl::class.simpleName
		
			override fun _create(value : Color?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Color> {
				return ColorImpl(value, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Color> {
				return ColorImpl(json, parent, refMgr)
			}
		}

		fun builder(modelMember : JsonOverlay<*>) : Builder<Color> {
			return Builder<Color>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : IJsonOverlay<Color> {
			return builder(modelMember).build()
		}
	}

}
