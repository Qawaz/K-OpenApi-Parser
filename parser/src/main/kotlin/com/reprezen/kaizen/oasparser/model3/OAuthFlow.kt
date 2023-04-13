package com.reprezen.kaizen.oasparser.model3

import com.reprezen.jsonoverlay.parser.Generated
import com.reprezen.jsonoverlay.IJsonOverlay
import com.reprezen.jsonoverlay.IModelPart
import kotlin.collections.Map

interface OAuthFlow : IJsonOverlay<OAuthFlow>, IModelPart<OpenApi3, OAuthFlow> {

	// AuthorizationUrl
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getAuthorizationUrl() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setAuthorizationUrl(authorizationUrl : String)

	// TokenUrl
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getTokenUrl() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setTokenUrl(tokenUrl : String)

	// RefreshUrl
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getRefreshUrl() : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setRefreshUrl(refreshUrl : String)

	// Scope
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getScopes() : MutableMap<String, String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getScopes(elaborate : Boolean) : MutableMap<String, String>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasScopes() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasScope(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getScope(name : String) : String?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setScopes(scopes : MutableMap<String, String>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setScope(name : String,scope : String)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeScope(name : String)

	// ScopesExtension
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getScopesExtensions() : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getScopesExtensions(elaborate : Boolean) : MutableMap<String, Any>

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasScopesExtensions() : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun hasScopesExtension(name : String) : Boolean

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun getScopesExtension(name : String) : Any?

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setScopesExtensions(scopesExtensions : MutableMap<String, Any>)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun setScopesExtension(name : String,scopesExtension : Any)

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	fun removeScopesExtension(name : String)

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
