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

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.intOrNull

class IntegerOverlay : ScalarOverlay<Int> {

    private constructor(value: Int?, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
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

    override fun _fromJson(json: JsonElement): Int? {
        return if (json is JsonPrimitive) json.intOrNull else null
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement {
        return if (value != null) JsonPrimitive(value!!) else JsonNull
    }

    override fun _getFactory(): OverlayFactory<*> {
        return Companion.factory
    }

    companion object {
        @JvmField
        var factory: OverlayFactory<Int> = object : OverlayFactory<Int>() {
            override fun getOverlayClass(): Class<IntegerOverlay> {
                return IntegerOverlay::class.java
            }

            override fun _create(value: Int?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): IntegerOverlay {
                return IntegerOverlay(value, parent, refMgr)
            }

            public override fun _create(
                json: JsonElement,
                parent: JsonOverlay<*>?,
                refMgr: ReferenceManager
            ): IntegerOverlay {
                return IntegerOverlay(json, parent, refMgr)
            }
        }

        fun builder(modelMember: JsonOverlay<*>): Builder<Int> {
            return Builder(factory, modelMember)
        }

        fun create(modelMember: JsonOverlay<*>): JsonOverlay<Int> {
            return builder(modelMember).build()
        }

        fun create(value: Int, modelMember: JsonOverlay<*>): JsonOverlay<Int> {
            val result = create(modelMember)
            result._set(value)
            return result
        }
    }
}
