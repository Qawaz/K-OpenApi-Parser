package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.ReferenceManager
import kotlinx.serialization.json.JsonElement
import com.reprezen.kaizen.oasparser.ovl3.MediaTypeImpl
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.Overlay
import kotlin.collections.Map
import com.reprezen.jsonoverlay.BooleanOverlay

class RequestBodyImpl : PropertiesOverlay<RequestBody> ,RequestBody {

    private val overlay : Overlay<RequestBody> = Overlay.Companion.of(this);

    override fun getName() : String? {
        return if(overlay.parent is MapOverlay<*>) overlay.pathInParent else null
    }

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(requestBody : RequestBody?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(requestBody, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Description
	override fun getDescription() : String? {
		return _get("description")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setDescription(description : String) {
		_setScalar("description", description)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ContentMediaType
	override fun getContentMediaTypes() : MutableMap<String, MediaType> {
		return _getMap("contentMediaTypes")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasContentMediaTypes() : Boolean {
		return _isPresent("contentMediaTypes")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasContentMediaType(name : String) : Boolean {
		return _getMap<MediaType>("contentMediaTypes").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getContentMediaType(name : String) : MediaType? {
		return _get("contentMediaTypes", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setContentMediaTypes(contentMediaTypes : MutableMap<String, MediaType>) {
		_setMap("contentMediaTypes", contentMediaTypes)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setContentMediaType(name : String, contentMediaType : MediaType) {
		_set("contentMediaTypes", name, contentMediaType)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeContentMediaType(name : String) {
		_remove("contentMediaTypes", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Required
	override fun getRequired() : Boolean? {
		return _get("required")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun isRequired() : Boolean {
		return _get("required") ?: false
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRequired(required : Boolean) {
		_setScalar("required", required)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Extension
	override fun getExtensions() : MutableMap<String, Any> {
		return _getMap("extensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExtensions() : Boolean {
		return _isPresent("extensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExtension(name : String) : Boolean {
		return _getMap<Any>("extensions").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExtension(name : String) : Any? {
		return _get("extensions", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExtensions(extensions : MutableMap<String, Any>) {
		_setMap("extensions", extensions)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExtension(name : String, extension : Any) {
		_set("extensions", name, extension)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeExtension(name : String) {
		_remove("extensions", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _elaborateJson() {
		super._elaborateJson()
		_createScalar("description", "description", StringOverlay.factory)
		_createMap("contentMediaTypes", "content", MediaTypeImpl.factory, null)
		_createScalar("required", "required", BooleanOverlay.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<RequestBody> {
		return Companion.factory
	}

	companion object {
		const val F_description : String = "description"

		const val F_contentMediaTypes : String = "contentMediaTypes"

		const val F_required : String = "required"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<RequestBody>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in RequestBody>> {
				return RequestBodyImpl::class.java
			}
		
			override fun _create(requestBody : RequestBody?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<RequestBody> {
				return RequestBodyImpl(requestBody, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<RequestBody> {
				return RequestBodyImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<RequestBody> {
			return Builder<RequestBody>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : RequestBody {
			return builder(modelMember).build() as RequestBody
		}
	}

}
