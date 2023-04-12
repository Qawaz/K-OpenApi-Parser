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


class BooleanOverlay : ScalarOverlay<Boolean> {

    private constructor(value: Boolean?, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        value,
        parent,
        Companion.factory,
        refMgr
    )

    private constructor(json: JsonElement, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        json,
        parent,
        Companion.factory,
        refMgr
    )

    override fun _fromJson(json: JsonElement): Boolean? {
        return (json as? JsonPrimitive)?.booleanOrNull
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement {
        return if (value != null) JsonPrimitive(value!!) else JsonNull
    }

    override fun _getFactory(): OverlayFactory<*> {
        return Companion.factory
    }

    companion object {

        @JvmField
        var factory: OverlayFactory<Boolean> = object : OverlayFactory<Boolean>() {
            override fun getOverlayClass(): Class<BooleanOverlay> {
                return BooleanOverlay::class.java
            }

            override fun _create(value: Boolean?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): BooleanOverlay {
                return BooleanOverlay(value, parent, refMgr)
            }

            public override fun _create(
                json: JsonElement,
                parent: JsonOverlay<*>?,
                refMgr: ReferenceManager
            ): BooleanOverlay {
                return BooleanOverlay(json, parent, refMgr)
            }
        }

        fun builder(modelMember: JsonOverlay<*>?): Builder<Boolean> {
            return Builder(factory, modelMember)
        }

        fun create(modelMember: JsonOverlay<*>?): JsonOverlay<Boolean> {
            return builder(modelMember).build()
        }

        fun create(value: Boolean, modelMember: JsonOverlay<*>?): JsonOverlay<Boolean> {
            val result = create(modelMember)
            result._set(value)
            return result
        }
    }
}