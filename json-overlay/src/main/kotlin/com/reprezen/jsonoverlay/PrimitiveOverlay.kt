/*********************************************************************
 * Copyright (c) 2023 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * WaqasTahir - Contributions after 2022
 * ModelSolv, Inc.
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

import kotlinx.serialization.json.*

class PrimitiveOverlay : ScalarOverlay<Any> {

    private constructor(parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        parent = parent,
        factory = ObjectOverlay.factory,
        refMgr = refMgr
    )

    private constructor(json: JsonElement, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        json = json,
        parent = parent,
        factory = ObjectOverlay.factory,
        refMgr = refMgr
    )

    private constructor(value: Any, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        value = value,
        parent = parent,
        ObjectOverlay.factory,
        refMgr
    )

    override fun _fromJson(json: JsonElement): Any? {
        if (json !is JsonPrimitive) return null
        return json.toValue()
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement {
        return value.toJsonElement()
    }

    companion object {
        @JvmField
        var factory: OverlayFactory<Any> = object : OverlayFactory<Any>() {
            override fun getOverlayClass(): Class<PrimitiveOverlay> {
                return PrimitiveOverlay::class.java
            }

            override fun _create(value: Any?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): PrimitiveOverlay {
                return if (value != null) PrimitiveOverlay(value, parent, refMgr) else PrimitiveOverlay(parent, refMgr)
            }

            public override fun _create(
                json: JsonElement,
                parent: JsonOverlay<*>?,
                refMgr: ReferenceManager
            ): PrimitiveOverlay {
                return PrimitiveOverlay(json, parent, refMgr)
            }
        }
    }
}
