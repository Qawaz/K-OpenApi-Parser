/*********************************************************************
 * Copyright (c) 2023 WaqasTahir, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * WaqasTahir
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

import com.fasterxml.jackson.databind.JsonNode

abstract class EnumOverlay<V : Enum<V>> : ScalarOverlay<V> {

    protected constructor(
        json: JsonNode,
        parent: JsonOverlay<*>?,
        factory: OverlayFactory<V>,
        refMgr: ReferenceManager
    ) : super(json, parent, factory, refMgr)

    protected constructor(
        value: V?,
        parent: JsonOverlay<*>?,
        factory: OverlayFactory<V>?,
        refMgr: ReferenceManager?
    ) : super(value, parent, factory, refMgr)

    override fun _fromJson(json: JsonNode): V? {
        if (!json.isTextual) {
            return null
        }
        return try {
            getEnumValue(json.asText())
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonNode? {
        return if (value != null) {
            _jsonScalar(value!!.name)
        } else {
            _jsonMissing()
        }
    }

    protected abstract fun getEnumValue(value: String): V?

}
