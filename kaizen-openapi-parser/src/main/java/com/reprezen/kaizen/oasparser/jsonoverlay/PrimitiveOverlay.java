/*******************************************************************************
 *  Copyright (c) 2017 ModelSolv, Inc. and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *     ModelSolv, Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package com.reprezen.kaizen.oasparser.jsonoverlay;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.JsonNode;

public class PrimitiveOverlay extends ScalarOverlay<Object> {

	private PrimitiveOverlay(JsonNode json, ReferenceRegistry refReg) {
		super(json, refReg);
	}

	private PrimitiveOverlay(Object value, ReferenceRegistry refReg) {
		super(value, refReg);
	}

	@Override
	public Object fromJson(JsonNode json) {
		if (json.isTextual()) {
			return json.textValue();
		} else if (json.isNumber()) {
			return json.numberValue();
		} else if (json.isBoolean()) {
			return json.booleanValue();
		} else {
			return null;
		}
	}

	@Override
	public JsonNode _toJson() {
		if (value instanceof String) {
			return jsonScalar((String) value);
		} else if (value instanceof BigDecimal) {
			return jsonScalar((BigDecimal) value);
		} else if (value instanceof BigInteger) {
			return jsonScalar((BigInteger) value);
		} else if (value instanceof Byte) {
			return jsonScalar((Byte) value);
		} else if (value instanceof Double) {
			return jsonScalar((Double) value);
		} else if (value instanceof Float) {
			return jsonScalar((Float) value);
		} else if (value instanceof Integer) {
			return jsonScalar((Integer) value);
		} else if (value instanceof Long) {
			return jsonScalar((Long) value);
		} else if (value instanceof Short) {
			return jsonScalar((Short) value);
		} else {
			return null;
		}
	}

	public static OverlayFactory<Object, PrimitiveOverlay> factory = new OverlayFactory<Object, PrimitiveOverlay>() {

		@Override
		protected Class<PrimitiveOverlay> getOverlayClass() {
			return PrimitiveOverlay.class;
		}

		@Override
		public PrimitiveOverlay _create(Object value, ReferenceRegistry refReg) {
			return new PrimitiveOverlay(value, refReg);
		}

		@Override
		public PrimitiveOverlay _create(JsonNode json, ReferenceRegistry refReg) {
			return new PrimitiveOverlay(json, refReg);
		}

	};
}
