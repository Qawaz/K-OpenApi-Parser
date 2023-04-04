package com.reprezen.kaizen.oasparser.ovl3;

import com.reprezen.kaizen.oasparser.model3.*;
import com.reprezen.jsonoverlay.StringOverlay;
import java.util.stream.Collectors;
import javax.annotation.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.ListOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.jsonoverlay.Builder;
import com.fasterxml.jackson.databind.JsonNode;
import com.reprezen.jsonoverlay.PropertiesOverlay;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.reprezen.jsonoverlay.ReferenceManager;
import java.util.List;
import com.reprezen.jsonoverlay.JsonOverlay;

public class SecurityParameterImpl extends PropertiesOverlay<SecurityParameter> implements SecurityParameter {

    @Override
    protected JsonNode _fixJson(JsonNode json) {
        return json.isMissingNode() ? _jsonArray() : json;
    }

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public SecurityParameterImpl(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(json, parent, factory, refMgr);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public SecurityParameterImpl(SecurityParameter securityParameter, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(securityParameter, parent, factory, refMgr);
	}

	// Parameter
	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public List<String> getParameters() {
		return _getList("parameters", String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public List<String> getParameters(boolean elaborate) {
		return _getList("parameters", elaborate, String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public boolean hasParameters() {
		return _isPresent("parameters");
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public String getParameter(int index) {
		return _get("parameters", index, String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public void setParameters(List<String> parameters) {
		_setList("parameters", parameters, String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public void setParameter(int index, String parameter) {
		_set("parameters", index, parameter, String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public void addParameter(String parameter) {
		_add("parameters", parameter, String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public void insertParameter(int index, String parameter) {
		_insert("parameters", index, parameter, String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public void removeParameter(int index) {
		_remove("parameters", index, String.class);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static final String F_parameters = "parameters";

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	protected void _elaborateJson() {
		super._elaborateJson();
		_createList("parameters", "", StringOverlay.factory);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static OverlayFactory<SecurityParameter> factory = new OverlayFactory<SecurityParameter>() {
	
		@Override
		public Class<? extends JsonOverlay<? super SecurityParameter>> getOverlayClass() {
			return SecurityParameterImpl.class;
		}
	
		@Override
		public JsonOverlay<SecurityParameter> _create(SecurityParameter securityParameter, JsonOverlay<?> parent, ReferenceManager refMgr) {
			JsonOverlay<?> overlay;
			overlay = new SecurityParameterImpl(securityParameter, parent, refMgr);
			@SuppressWarnings("unchecked")
			JsonOverlay<SecurityParameter> castOverlay = (JsonOverlay<SecurityParameter>) overlay;
			return castOverlay;
		}
	
		@Override
		public JsonOverlay<SecurityParameter> _create(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
			JsonOverlay<?> overlay;
			overlay = new SecurityParameterImpl(json, parent, refMgr);
			@SuppressWarnings("unchecked")
			JsonOverlay<SecurityParameter> castOverlay = (JsonOverlay<SecurityParameter>) overlay;
			return castOverlay;
		}
	
		@Override
		protected boolean isExtendedType() {
			return false;
		}
	};

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	private static Class<? extends SecurityParameter> getSubtypeOf(SecurityParameter securityParameter) {
		return SecurityParameter.class;
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	private static Class<? extends SecurityParameter> getSubtypeOf(JsonNode json) {
		return SecurityParameter.class;
	}
	

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	@Override
	public Class<?> _getModelType() {
		return OpenApi3.class;
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	@Override
	public OverlayFactory<?> _getFactory() {
		return factory;
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static Builder<SecurityParameter> builder(JsonOverlay<?> modelMember) {
		return new Builder<SecurityParameter>(factory, modelMember);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static SecurityParameter create(JsonOverlay<?> modelMember) {
		return (SecurityParameter) builder(modelMember).build();
	}

}
