package com.reprezen.kaizen.oasparser.ovl3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.OverlayFactory
import com.reprezen.kaizen.oasparser.ovl3.HeaderImpl
import com.reprezen.jsonoverlay.Builder
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.jsonoverlay.ReferenceManager
import kotlinx.serialization.json.JsonElement
import com.reprezen.kaizen.oasparser.ovl3.MediaTypeImpl
import com.reprezen.kaizen.oasparser.ovl3.LinkImpl
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.Overlay
import kotlin.collections.Map

class ResponseImpl : PropertiesOverlay<Response> ,Response {

    private val overlay : Overlay<Response> = Overlay.Companion.of(this);

    override fun getName() : String? {
        return if(overlay.parent is MapOverlay<*>) overlay.pathInParent else null
    }

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(response : Response?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(response, parent, Companion.factory, refMgr)

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
	// Header
	override fun getHeaders() : MutableMap<String, Header> {
		return _getMap("headers")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasHeaders() : Boolean {
		return _isPresent("headers")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasHeader(name : String) : Boolean {
		return _getMap<Header>("headers").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getHeader(name : String) : Header? {
		return _get("headers", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setHeaders(headers : MutableMap<String, Header>) {
		_setMap("headers", headers)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setHeader(name : String, header : Header) {
		_set("headers", name, header)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeHeader(name : String) {
		_remove("headers", name)
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
	// Link
	override fun getLinks() : MutableMap<String, Link> {
		return _getMap("links")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasLinks() : Boolean {
		return _isPresent("links")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasLink(name : String) : Boolean {
		return _getMap<Link>("links").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getLink(name : String) : Link? {
		return _get("links", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setLinks(links : MutableMap<String, Link>) {
		_setMap("links", links)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setLink(name : String, link : Link) {
		_set("links", name, link)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeLink(name : String) {
		_remove("links", name)
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
		_createMap("headers", "headers", HeaderImpl.factory, null)
		_createMap("contentMediaTypes", "content", MediaTypeImpl.factory, null)
		_createMap("links", "links", LinkImpl.factory, null)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<Response> {
		return Companion.factory
	}

	companion object {
		const val F_description : String = "description"

		const val F_headers : String = "headers"

		const val F_contentMediaTypes : String = "contentMediaTypes"

		const val F_links : String = "links"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<Response>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in Response>> {
				return ResponseImpl::class.java
			}
		
			override fun _create(response : Response?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Response> {
				return ResponseImpl(response, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<Response> {
				return ResponseImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		fun builder(modelMember : JsonOverlay<*>) : Builder<Response> {
			return Builder<Response>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : Response {
			return builder(modelMember).build() as Response
		}
	}

}
