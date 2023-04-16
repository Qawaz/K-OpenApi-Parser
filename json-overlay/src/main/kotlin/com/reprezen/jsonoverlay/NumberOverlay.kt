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

class NumberOverlay : ScalarOverlay<Number> {

    private constructor(json: JsonElement, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        json,
        parent,
        Companion.factory,
        refMgr
    )

    private constructor(value: Number?, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        value,
        parent,
        Companion.factory,
        refMgr
    )

    override fun _fromJson(json: JsonElement): Number? {
        if (json !is JsonPrimitive || json.contentOrNull == null) return null
        return json.toNumber()
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement {
        if (value == null) return JsonNull
        return JsonPrimitive(value)
    }

    companion object {
        @JvmField
        var factory: OverlayFactory<Number> = object : OverlayFactory<Number>() {

            override val signature: String?
                get() = NumberOverlay::class.simpleName

            override fun _create(value: Number?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): NumberOverlay {
                return NumberOverlay(value, parent, refMgr)
            }

            public override fun _create(
                json: JsonElement,
                parent: JsonOverlay<*>?,
                refMgr: ReferenceManager
            ): NumberOverlay {
                return NumberOverlay(json, parent, refMgr)
            }
        }

        fun builder(modelMember: JsonOverlay<*>): Builder<Number> {
            return Builder(factory, modelMember)
        }

        fun create(modelMember: JsonOverlay<*>): JsonOverlay<Number> {
            return builder(modelMember).build()
        }

        fun create(value: Number?, modelMember: JsonOverlay<*>): JsonOverlay<Number> {
            val result = create(modelMember)
            result._set(value)
            return result
        }
    }
}