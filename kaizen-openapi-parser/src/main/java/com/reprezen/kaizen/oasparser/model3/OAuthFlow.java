package com.reprezen.kaizen.oasparser.model3;

import java.util.Map;

import javax.annotation.Generated;

import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.IModelPart;

public interface OAuthFlow extends IJsonOverlay<OAuthFlow>, IModelPart<OpenApi3, OAuthFlow> {

	// AuthorizationUrl
	@Generated("com.reprezen.gen.CodeGenerator")
	String getAuthorizationUrl();

	@Generated("com.reprezen.gen.CodeGenerator")
	String getAuthorizationUrl(boolean elaborate);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setAuthorizationUrl(String authorizationUrl);

	// TokenUrl
	@Generated("com.reprezen.gen.CodeGenerator")
	String getTokenUrl();

	@Generated("com.reprezen.gen.CodeGenerator")
	String getTokenUrl(boolean elaborate);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setTokenUrl(String tokenUrl);

	// RefreshUrl
	@Generated("com.reprezen.gen.CodeGenerator")
	String getRefreshUrl();

	@Generated("com.reprezen.gen.CodeGenerator")
	String getRefreshUrl(boolean elaborate);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setRefreshUrl(String refreshUrl);

	// Scope
	@Generated("com.reprezen.gen.CodeGenerator")
	Map<String, String> getScopes();

	@Generated("com.reprezen.gen.CodeGenerator")
	Map<String, String> getScopes(boolean elaborate);

	@Generated("com.reprezen.gen.CodeGenerator")
	boolean hasScope(String name);

	@Generated("com.reprezen.gen.CodeGenerator")
	String getScope(String name);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setScopes(Map<String, String> scopes);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setScope(String name, String scope);

	@Generated("com.reprezen.gen.CodeGenerator")
	void removeScope(String name);

	// ScopesExtension
	@Generated("com.reprezen.gen.CodeGenerator")
	Map<String, Object> getScopesExtensions();

	@Generated("com.reprezen.gen.CodeGenerator")
	Map<String, Object> getScopesExtensions(boolean elaborate);

	@Generated("com.reprezen.gen.CodeGenerator")
	boolean hasScopesExtension(String name);

	@Generated("com.reprezen.gen.CodeGenerator")
	Object getScopesExtension(String name);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setScopesExtensions(Map<String, Object> scopesExtensions);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setScopesExtension(String name, Object scopesExtension);

	@Generated("com.reprezen.gen.CodeGenerator")
	void removeScopesExtension(String name);

	// Extension
	@Generated("com.reprezen.gen.CodeGenerator")
	Map<String, Object> getExtensions();

	@Generated("com.reprezen.gen.CodeGenerator")
	Map<String, Object> getExtensions(boolean elaborate);

	@Generated("com.reprezen.gen.CodeGenerator")
	boolean hasExtension(String name);

	@Generated("com.reprezen.gen.CodeGenerator")
	Object getExtension(String name);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setExtensions(Map<String, Object> extensions);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setExtension(String name, Object extension);

	@Generated("com.reprezen.gen.CodeGenerator")
	void removeExtension(String name);
}
