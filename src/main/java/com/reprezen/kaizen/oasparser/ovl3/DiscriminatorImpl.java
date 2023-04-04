package com.reprezen.kaizen.oasparser.ovl3;

import com.reprezen.kaizen.oasparser.model3.*;
import com.reprezen.jsonoverlay.MapOverlay;
import com.reprezen.jsonoverlay.StringOverlay;
import java.util.stream.Collectors;
import javax.annotation.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.jsonoverlay.Builder;
import com.fasterxml.jackson.databind.JsonNode;
import com.reprezen.jsonoverlay.PropertiesOverlay;
import com.fasterxml.jackson.core.JsonPointer;
import com.reprezen.jsonoverlay.ReferenceManager;
import com.reprezen.jsonoverlay.JsonOverlay;
import java.util.Map;

public class DiscriminatorImpl extends PropertiesOverlay<Discriminator> implements Discriminator {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public DiscriminatorImpl(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(json, parent, factory, refMgr);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public DiscriminatorImpl(Discriminator discriminator, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(discriminator, parent, factory, refMgr);
	}

	// PropertyName
	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public String getPropertyName() {
		return _get("propertyName", String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public void setPropertyName(String propertyName) {
		_setScalar("propertyName", propertyName, String.class);
	}

	// Mapping
	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public Map<String, String> getMappings() {
		return _getMap("mappings", String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public Map<String, String> getMappings(boolean elaborate) {
		return _getMap("mappings", elaborate, String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public boolean hasMappings() {
		return _isPresent("mappings");
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public boolean hasMapping(String name) {
		return _getMap("mappings", String.class).containsKey(name);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public String getMapping(String name) {
		return _get("mappings", name, String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public void setMappings(Map<String, String> mappings) {
		_setMap("mappings", mappings, String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public void setMapping(String name, String mapping) {
		_set("mappings", name, mapping, String.class);
	}

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public void removeMapping(String name) {
		_remove("mappings", name, String.class);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static final String F_propertyName = "propertyName";

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static final String F_mappings = "mappings";

	@Override
	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	protected void _elaborateJson() {
		super._elaborateJson();
		_createScalar("propertyName", "propertyName", StringOverlay.factory);
		_createMap("mappings", "mapping", StringOverlay.factory, null);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static OverlayFactory<Discriminator> factory = new OverlayFactory<Discriminator>() {
	
		@Override
		protected Class<? extends JsonOverlay<? super Discriminator>> getOverlayClass() {
			return DiscriminatorImpl.class;
		}
	
		@Override
		public JsonOverlay<Discriminator> _create(Discriminator discriminator, JsonOverlay<?> parent, ReferenceManager refMgr) {
			JsonOverlay<?> overlay;
			overlay = new DiscriminatorImpl(discriminator, parent, refMgr);
			@SuppressWarnings("unchecked")
			JsonOverlay<Discriminator> castOverlay = (JsonOverlay<Discriminator>) overlay;
			return castOverlay;
		}
	
		@Override
		public JsonOverlay<Discriminator> _create(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
			JsonOverlay<?> overlay;
			overlay = new DiscriminatorImpl(json, parent, refMgr);
			@SuppressWarnings("unchecked")
			JsonOverlay<Discriminator> castOverlay = (JsonOverlay<Discriminator>) overlay;
			return castOverlay;
		}
	
		@Override
		protected boolean isExtendedType() {
			return false;
		}
	};

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	private static Class<? extends Discriminator> getSubtypeOf(Discriminator discriminator) {
		return Discriminator.class;
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	private static Class<? extends Discriminator> getSubtypeOf(JsonNode json) {
		return Discriminator.class;
	}
	

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	@Override
	public Class<?> _getModelType() {
		return OpenApi3.class;
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	@Override
	protected OverlayFactory<?> _getFactory() {
		return factory;
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static Builder<Discriminator> builder(JsonOverlay<?> modelMember) {
		return new Builder<Discriminator>(factory, modelMember);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static Discriminator create(JsonOverlay<?> modelMember) {
		return (Discriminator) builder(modelMember).build();
	}

}
