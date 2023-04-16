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
import com.reprezen.kaizen.oasparser.ovl3.OAuthFlowImpl
import kotlinx.serialization.json.JsonElement
import com.reprezen.jsonoverlay.ObjectOverlay
import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.Overlay
import kotlin.collections.Map

class SecuritySchemeImpl : PropertiesOverlay<SecurityScheme> ,SecurityScheme {

    private val overlay : Overlay<SecurityScheme> = Overlay.Companion.of(this);

    override fun getKey() : String? {
        return if(overlay.parent is MapOverlay<*>) overlay.pathInParent else null
    }

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(json, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	constructor(securityScheme : SecurityScheme?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : super(securityScheme, parent, Companion.factory, refMgr)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Type
	override fun getType() : String? {
		return _get("type")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setType(type : String) {
		_setScalar("type", type)
	}

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
	// Name
	override fun getName() : String? {
		return _get("name")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setName(name : String) {
		_setScalar("name", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// In
	override fun getIn() : String? {
		return _get("inValue")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setIn(inValue : String) {
		_setScalar("inValue", inValue)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// Scheme
	override fun getScheme() : String? {
		return _get("scheme")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setScheme(scheme : String) {
		_setScalar("scheme", scheme)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// BearerFormat
	override fun getBearerFormat() : String? {
		return _get("bearerFormat")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setBearerFormat(bearerFormat : String) {
		_setScalar("bearerFormat", bearerFormat)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ImplicitOAuthFlow
	override fun getImplicitOAuthFlow() : OAuthFlow? {
		return _get("implicitOAuthFlow")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setImplicitOAuthFlow(implicitOAuthFlow : OAuthFlow) {
		_setScalar("implicitOAuthFlow", implicitOAuthFlow)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// PasswordOAuthFlow
	override fun getPasswordOAuthFlow() : OAuthFlow? {
		return _get("passwordOAuthFlow")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setPasswordOAuthFlow(passwordOAuthFlow : OAuthFlow) {
		_setScalar("passwordOAuthFlow", passwordOAuthFlow)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// ClientCredentialsOAuthFlow
	override fun getClientCredentialsOAuthFlow() : OAuthFlow? {
		return _get("clientCredentialsOAuthFlow")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setClientCredentialsOAuthFlow(clientCredentialsOAuthFlow : OAuthFlow) {
		_setScalar("clientCredentialsOAuthFlow", clientCredentialsOAuthFlow)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// AuthorizationCodeOAuthFlow
	override fun getAuthorizationCodeOAuthFlow() : OAuthFlow? {
		return _get("authorizationCodeOAuthFlow")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setAuthorizationCodeOAuthFlow(authorizationCodeOAuthFlow : OAuthFlow) {
		_setScalar("authorizationCodeOAuthFlow", authorizationCodeOAuthFlow)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// OAuthFlowsExtension
	override fun getOAuthFlowsExtensions() : MutableMap<String, Any> {
		return _getMap("oAuthFlowsExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasOAuthFlowsExtensions() : Boolean {
		return _isPresent("oAuthFlowsExtensions")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun hasOAuthFlowsExtension(name : String) : Boolean {
		return _getMap<Any>("oAuthFlowsExtensions").containsKey(name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun getOAuthFlowsExtension(name : String) : Any? {
		return _get("oAuthFlowsExtensions", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setOAuthFlowsExtensions(oAuthFlowsExtensions : MutableMap<String, Any>) {
		_setMap("oAuthFlowsExtensions", oAuthFlowsExtensions)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setOAuthFlowsExtension(name : String, oAuthFlowsExtension : Any) {
		_set("oAuthFlowsExtensions", name, oAuthFlowsExtension)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun removeOAuthFlowsExtension(name : String) {
		_remove("oAuthFlowsExtensions", name)
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	// OpenIdConnectUrl
	override fun getOpenIdConnectUrl() : String? {
		return _get("openIdConnectUrl")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun setOpenIdConnectUrl(openIdConnectUrl : String) {
		_setScalar("openIdConnectUrl", openIdConnectUrl)
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
		_createScalar("type", "type", StringOverlay.factory)
		_createScalar("description", "description", StringOverlay.factory)
		_createScalar("name", "name", StringOverlay.factory)
		_createScalar("inValue", "in", StringOverlay.factory)
		_createScalar("scheme", "scheme", StringOverlay.factory)
		_createScalar("bearerFormat", "bearerFormat", StringOverlay.factory)
		_createScalar("implicitOAuthFlow", "flow/implicit", OAuthFlowImpl.factory)
		_createScalar("passwordOAuthFlow", "flow/password", OAuthFlowImpl.factory)
		_createScalar("clientCredentialsOAuthFlow", "flow/clientCredentials", OAuthFlowImpl.factory)
		_createScalar("authorizationCodeOAuthFlow", "flow/authorizationCode", OAuthFlowImpl.factory)
		_createMap("oAuthFlowsExtensions", "flow", ObjectOverlay.factory, "x-.+")
		_createScalar("openIdConnectUrl", "openIdConnectUrl", StringOverlay.factory)
		_createMap("extensions", "", ObjectOverlay.factory, "x-.+")
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getModelType() : Class<*> {
		return OpenApi3::class.java
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	override fun _getFactory() : OverlayFactory<SecurityScheme> {
		return Companion.factory
	}

	companion object {
		const val F_type : String = "type"

		const val F_description : String = "description"

		const val F_name : String = "name"

		const val F_inValue : String = "inValue"

		const val F_scheme : String = "scheme"

		const val F_bearerFormat : String = "bearerFormat"

		const val F_implicitOAuthFlow : String = "implicitOAuthFlow"

		const val F_passwordOAuthFlow : String = "passwordOAuthFlow"

		const val F_clientCredentialsOAuthFlow : String = "clientCredentialsOAuthFlow"

		const val F_authorizationCodeOAuthFlow : String = "authorizationCodeOAuthFlow"

		const val F_oAuthFlowsExtensions : String = "oAuthFlowsExtensions"

		const val F_openIdConnectUrl : String = "openIdConnectUrl"

		const val F_extensions : String = "extensions"

		val factory = object : OverlayFactory<SecurityScheme>() {
		
			override fun getOverlayClass() : Class<out JsonOverlay<in SecurityScheme>> {
				return SecuritySchemeImpl::class.java
			}
		
			override fun _create(securityScheme : SecurityScheme?, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<SecurityScheme> {
				return SecuritySchemeImpl(securityScheme, parent, refMgr)
			}
		
			override fun _create(json : JsonElement, parent : JsonOverlay<*>?, refMgr : ReferenceManager) : JsonOverlay<SecurityScheme> {
				return SecuritySchemeImpl(json, parent, refMgr)
			}
		
			override val isExtendedType : Boolean get() = false
		};

		private fun getSubtypeOf(securityScheme : SecurityScheme) : Class<out SecurityScheme> {
			return SecurityScheme::class.java
		}

		private fun getSubtypeOf(json : JsonElement) : Class<out SecurityScheme> {
			return SecurityScheme::class.java
		}
		

		fun builder(modelMember : JsonOverlay<*>) : Builder<SecurityScheme> {
			return Builder<SecurityScheme>(factory, modelMember)
		}

		fun create(modelMember : JsonOverlay<*>) : SecurityScheme {
			return builder(modelMember).build() as SecurityScheme
		}
	}

}
