package com.reprezen.jsonoverlay.model.impl;

import com.reprezen.jsonoverlay.model.intf.*;
import javax.annotation.Generated;
import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.OverlayFactory;
import com.reprezen.jsonoverlay.Builder;
import com.reprezen.jsonoverlay.EnumOverlay;
import com.fasterxml.jackson.databind.JsonNode;
import com.reprezen.jsonoverlay.JsonOverlay;
import com.reprezen.jsonoverlay.ReferenceManager;

public class ColorImpl extends EnumOverlay<Color> {

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public ColorImpl(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(json, parent, factory, refMgr);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public ColorImpl(Color color, JsonOverlay<?> parent, ReferenceManager refMgr) {
		super(color, parent, factory, refMgr);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	@Override
	protected Color getEnumValue(String value) {
		return Color.valueOf(value);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static OverlayFactory<Color> factory = new OverlayFactory<Color>() {
	
		@Override
		protected Class<? extends JsonOverlay<? super Color>> getOverlayClass() {
			return ColorImpl.class;
		}
	
		@Override
		public JsonOverlay<Color> _create(Color color, JsonOverlay<?> parent, ReferenceManager refMgr) {
			return new ColorImpl(color, parent, refMgr);
		}
	
		@Override
		public JsonOverlay<Color> _create(JsonNode json, JsonOverlay<?> parent, ReferenceManager refMgr) {
			return new ColorImpl(json, parent, refMgr);
		}
	};

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	@Override
	protected OverlayFactory<?> _getFactory() {
		return factory;
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static Builder<Color> builder(JsonOverlay<?> modelMember) {
		return new Builder<Color>(factory, modelMember);
	}

	@Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
	public static IJsonOverlay<Color> create(JsonOverlay<?> modelMember) {
		return builder(modelMember).build();
	}

}
