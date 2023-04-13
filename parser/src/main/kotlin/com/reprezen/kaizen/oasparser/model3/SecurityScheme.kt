package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.kaizen.oasparser.model3.OAuthFlow
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.Map

interface SecurityScheme : IJsonOverlay<SecurityScheme>, IModelPart<OpenApi3, SecurityScheme> {

    fun getKey() : String?

	// Type
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getType() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setType(type : String)

	// Description
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getDescription() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setDescription(description : String)

	// Name
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getName() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setName(name : String)

	// In
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getIn() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setIn(inValue : String)

	// Scheme
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getScheme() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setScheme(scheme : String)

	// BearerFormat
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getBearerFormat() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setBearerFormat(bearerFormat : String)

	// ImplicitOAuthFlow
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getImplicitOAuthFlow() : OAuthFlow?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getImplicitOAuthFlow(elaborate : Boolean) : OAuthFlow?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setImplicitOAuthFlow(implicitOAuthFlow : OAuthFlow)

	// PasswordOAuthFlow
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPasswordOAuthFlow() : OAuthFlow?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getPasswordOAuthFlow(elaborate : Boolean) : OAuthFlow?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setPasswordOAuthFlow(passwordOAuthFlow : OAuthFlow)

	// ClientCredentialsOAuthFlow
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getClientCredentialsOAuthFlow() : OAuthFlow?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getClientCredentialsOAuthFlow(elaborate : Boolean) : OAuthFlow?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setClientCredentialsOAuthFlow(clientCredentialsOAuthFlow : OAuthFlow)

	// AuthorizationCodeOAuthFlow
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAuthorizationCodeOAuthFlow() : OAuthFlow?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAuthorizationCodeOAuthFlow(elaborate : Boolean) : OAuthFlow?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAuthorizationCodeOAuthFlow(authorizationCodeOAuthFlow : OAuthFlow)

	// OAuthFlowsExtension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOAuthFlowsExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOAuthFlowsExtensions(elaborate : Boolean) : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasOAuthFlowsExtensions() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasOAuthFlowsExtension(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOAuthFlowsExtension(name : String) : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOAuthFlowsExtensions(oAuthFlowsExtensions : MutableMap<String, Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOAuthFlowsExtension(name : String,oAuthFlowsExtension : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeOAuthFlowsExtension(name : String)

	// OpenIdConnectUrl
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getOpenIdConnectUrl() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setOpenIdConnectUrl(openIdConnectUrl : String)

	// Extension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtensions(elaborate : Boolean) : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExtensions() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasExtension(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getExtension(name : String) : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExtensions(extensions : MutableMap<String, Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setExtension(name : String,extension : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeExtension(name : String)

}
