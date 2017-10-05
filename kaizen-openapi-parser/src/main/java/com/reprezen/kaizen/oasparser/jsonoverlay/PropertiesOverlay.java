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

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.google.common.collect.Maps;
import com.reprezen.kaizen.oasparser.ovl3.ChildListOverlay;

public abstract class PropertiesOverlay<V extends IPropertiesOverlay<V>> extends JsonOverlay<V> {

	protected Map<String, ChildOverlay<?, ?>> children = Maps.newLinkedHashMap();

	private JsonNode initJson;

	protected PropertiesOverlay(JsonNode json, ReferenceRegistry refReg) {
		super(json, refReg);
		this.initJson = json;
	}

	public PropertiesOverlay(V value, ReferenceRegistry refReg) {
		super(value, refReg);
		this.initJson = MissingNode.getInstance();
	}

	@Override
	public V fromJson(JsonNode json) {
		// parsing of the json node is expected to be done in the constructor of the
		// subclass, so nothing is done here. But we do establish this object as its own
		// value.
		@SuppressWarnings("unchecked")
		V result = (V) this;
		return result;
	}

	@Override
	public JsonNode toJson(boolean keepEmpty) {
		JsonNode obj = jsonMissing();
		for (ChildOverlay<?, ?> child : children.values()) {
			JsonNode childJson = child.getOverlay().toJson();
			if (!childJson.isMissingNode()) {
				obj = child.getPath().setNode(obj, childJson, isMergeable(child.getOverlay()));
			}
		}
		JsonNode result = fixJson(obj);
		return result.size() > 0 || keepEmpty ? result : jsonMissing();
	}

	protected JsonNode fixJson(JsonNode json) {
		return json;
	}

	private boolean isMergeable(JsonOverlay<?> child) {
		return child instanceof MapOverlay<?, ?> && ((MapOverlay<?, ?>) child).getKeyPattern() != null;
	}

	@Override
	public void set(V value) {
		super.set(value);
	}

	protected <VC, OVC extends JsonOverlay<VC>> ChildOverlay<VC, OVC> createChild(String path,
			OverlayFactory<VC, OVC> factory) {
		ChildOverlay<VC, OVC> child = new ChildOverlay<VC, OVC>(path, initJson.at("/" + path), factory, refReg);
		children.put(path, child);
		return child;
	}

	protected <VC, OVC extends JsonOverlay<VC>> ChildMapOverlay<VC, OVC> createChildMap(String path,
			OverlayFactory<VC, OVC> factory, String keyPattern) {
		ChildMapOverlay<VC, OVC> child = new ChildMapOverlay<VC, OVC>(path,
				initJson.at(path.isEmpty() ? "" : "/" + path), MapOverlay.getFactory(factory, keyPattern), refReg);
		children.put(path, child);
		return child;
	}

	protected <VC, OVC extends JsonOverlay<VC>> ChildListOverlay<VC, OVC> createChildList(String path,
			OverlayFactory<VC, OVC> factory) {
		ChildListOverlay<VC, OVC> child = new ChildListOverlay<VC, OVC>(path,
				initJson.at(path.isEmpty() ? "" : "/" + path), ListOverlay.getFactory(factory), refReg);
		children.put(path, child);
		return child;
	}

}
