package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.MapOverlay
import java.util.stream.Collectors
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.JsonPointer
import com.reprezen.jsonoverlay.ReferenceManager
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.JsonOverlay
import kotlin.collections.Map

class DiscriminatorImpl : PropertiesOverlay<Discriminator> ,Discriminator {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(discriminator : Discriminator?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(discriminator, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// PropertyName
	override fun getPropertyName() : String? {
		return _get("propertyName")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setPropertyName(propertyName : String) {
		_setScalar("propertyName", propertyName)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Mapping
	override fun getMappings() : MutableMap<String, String> {
		return _getMap("mappings")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasMappings() : Boolean {
		return _isPresent("mappings")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasMapping(name : String) : Boolean {
		return _getMap<String>("mappings").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getMapping(name : String) : String? {
		return _get("mappings", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setMappings(mappings : MutableMap<String, String>) {
		_setMap("mappings", mappings)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setMapping(name : String, mapping : String) {
		_set("mappings", name, mapping)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeMapping(name : String) {
		_remove("mappings", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _elaborateJson() {
		super._elaborateJson()
		_createScalar("propertyName", "propertyName", StringOverlay.factory)
		_createMap("mappings", "mapping", StringOverlay.factory, null)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Discriminator> {
		return Companion.factory
	}

	companion object {
		const val F_propertyName : String = "propertyName"

		const val F_mappings : String = "mappings"

		val factory = object : OverlayFactory<Discriminator>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Discriminator>> {
				return DiscriminatorImpl::class.java
			}
		
			override fun _create(discriminator : Discriminator?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Discriminator> {
				return DiscriminatorImpl(discriminator, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Discriminator> {
				return DiscriminatorImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(discriminator : Discriminator) : Class<out Discriminator> {
			return Discriminator::class.java
		}

		private fun getSubtypeOf(json : JsonElement) : Class<out Discriminator> {
			return Discriminator::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<Discriminator> {
			return Builder<Discriminator>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Discriminator {
			return builder(modelMember).build() as Discriminator
		}
	}

}
