package com.reprezen.kaizen.oasparser.ovl3;

import com.reprezen.kaizen.oasparser.model3.*;
import com.reprezen.jsonoverlay.MapOverlay;
import java.util.stream.Collectors;
import com.reprezen.jsonoverlay.StringOverlay;
import com.reprezen.jsonoverlay.parser.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.jsonoverlay.Builder;
import com.fasterxml.jackson.databind.JsonNode;
import com.reprezen.jsonoverlay.PropertiesOverlay;
import com.fasterxml.jackson.core.JsonPointer;
import com.reprezen.jsonoverlay.ReferenceManager;
import com.reprezen.jsonoverlay.ObjectOverlay;
import com.reprezen.jsonoverlay.JsonOverlay;
import kotlin.collections.Map;

class OAuthFlowImpl : PropertiesOverlay<OAuthFlow> ,OAuthFlow {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(oAuthFlow : OAuthFlow?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(oAuthFlow, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// AuthorizationUrl
	override fun getAuthorizationUrl() : String? {
		return _get("authorizationUrl", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAuthorizationUrl(authorizationUrl : String) {
		_setScalar("authorizationUrl", authorizationUrl, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// TokenUrl
	override fun getTokenUrl() : String? {
		return _get("tokenUrl", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setTokenUrl(tokenUrl : String) {
		_setScalar("tokenUrl", tokenUrl, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// RefreshUrl
	override fun getRefreshUrl() : String? {
		return _get("refreshUrl", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setRefreshUrl(refreshUrl : String) {
		_setScalar("refreshUrl", refreshUrl, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Scope
	override fun getScopes() : MutableMap<String, String> {
		return _getMap("scopes", String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getScopes(elaborate : Boolean) : MutableMap<String, String> {
		return _getMap("scopes", elaborate, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasScopes() : Boolean {
		return _isPresent("scopes")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasScope(name : String) : Boolean {
		return _getMap("scopes", String::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getScope(name : String) : String? {
		return _get("scopes", name, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setScopes(scopes : MutableMap<String, String>) {
		_setMap("scopes", scopes, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setScope(name : String, scope : String) {
		_set("scopes", name, scope, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeScope(name : String) {
		_remove("scopes", name, String::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ScopesExtension
	override fun getScopesExtensions() : MutableMap<String, Any> {
		return _getMap("scopesExtensions", Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getScopesExtensions(elaborate : Boolean) : MutableMap<String, Any> {
		return _getMap("scopesExtensions", elaborate, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasScopesExtensions() : Boolean {
		return _isPresent("scopesExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasScopesExtension(name : String) : Boolean {
		return _getMap("scopesExtensions", Any::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getScopesExtension(name : String) : Any? {
		return _get("scopesExtensions", name, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setScopesExtensions(scopesExtensions : MutableMap<String, Any>) {
		_setMap("scopesExtensions", scopesExtensions, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setScopesExtension(name : String, scopesExtension : Any) {
		_set("scopesExtensions", name, scopesExtension, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeScopesExtension(name : String) {
		_remove("scopesExtensions", name, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Extension
	override fun getExtensions() : MutableMap<String, Any> {
		return _getMap("extensions", Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExtensions(elaborate : Boolean) : MutableMap<String, Any> {
		return _getMap("extensions", elaborate, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExtensions() : Boolean {
		return _isPresent("extensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasExtension(name : String) : Boolean {
		return _getMap("extensions", Any::class.java).containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getExtension(name : String) : Any? {
		return _get("extensions", name, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExtensions(extensions : MutableMap<String, Any>) {
		_setMap("extensions", extensions, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setExtension(name : String, extension : Any) {
		_set("extensions", name, extension, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeExtension(name : String) {
		_remove("extensions", name, Any::class.java)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _elaborateJson() {
		super._elaborateJson()
		_createScalar("authorizationUrl", "authorizationUrl", StringOverlay.factory)
		_createScalar("tokenUrl", "tokenUrl", StringOverlay.factory)
		_createScalar("refreshUrl", "refreshUrl", StringOverlay.factory)
		_createMap("scopes", "scopes", StringOverlay.factory, "(?!x-).*")
		_createMap("scopesExtensions", "scopes", ObjectOverlay.factory, "x-.+")
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<OAuthFlow> {
		return Companion.factory
	}

	companion object {
		const val F_authorizationUrl : String = "authorizationUrl"

		const val F_tokenUrl : String = "tokenUrl"

		const val F_refreshUrl : String = "refreshUrl"

		const val F_scopes : String = "scopes"

		const val F_scopesExtensions : String = "scopesExtensions"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<OAuthFlow>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in OAuthFlow>> {
				return OAuthFlowImpl::class.java
			}
		
			override fun _create(oAuthFlow : OAuthFlow?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<OAuthFlow> {
				return OAuthFlowImpl(oAuthFlow, parent, refMgr)
			}
		
			override fun _create(json : JsonNode, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<OAuthFlow> {
				return OAuthFlowImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(oAuthFlow : OAuthFlow) : Class<out OAuthFlow> {
			return OAuthFlow::class.java
		}

		private fun getSubtypeOf(json : JsonNode) : Class<out OAuthFlow> {
			return OAuthFlow::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<OAuthFlow> {
			return Builder<OAuthFlow>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : OAuthFlow {
			return builder(modelMember).build() as OAuthFlow
		}
	}

}
