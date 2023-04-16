package com.reprezen.jsonoverlay.model.impl

import com.reprezen.jsonoverlay.model.intf.*
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.ReferenceManager

class EntryImpl : PropertiesOverlay<Entry> ,Entry {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(entry : Entry?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(entry, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Title
	override fun getTitle() : String? {
		return _get("title")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setTitle(title : String) {
		_setScalar("title", title)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _elaborateJson() {
		super._elaborateJson()
		_createScalar("title", "title", StringOverlay.factory)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return TestModel::class.java
	}

	companion object {
		const val F_title : String = "title"

		val factory = object : OverlayFactory<Entry>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Entry>> {
				return EntryImpl::class.java
			}
		
			override fun _create(entry : Entry?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Entry> {
				return EntryImpl(entry, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Entry> {
				return EntryImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<Entry> {
			return Builder<Entry>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Entry {
			return builder(modelMember).build() as Entry
		}
	}

}
