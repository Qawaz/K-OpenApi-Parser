/*********************************************************************
 * Copyright (c) 2017 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * ModelSolv, Inc.
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

import com.fasterxml.jackson.databind.JsonNode

class IntegerOverlay : ScalarOverlay<Int> {

    private constructor(value: Int?, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        value,
        parent,
        Companion.factory,
        refMgr
    )

    private constructor(json: JsonNode, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        json,
        parent,
        Companion.factory,
        refMgr
    )

    override fun _fromJson(json: JsonNode): Int? {
        return if (json.isInt) json.intValue() else null
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonNode? {
        return if (value != null) _jsonScalar(value!!) else _jsonMissing()
    }

    override fun _getFactory(): OverlayFactory<Int>? {
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
                json: JsonNode,
                parent: JsonOverlay<*>?,
                refMgr: ReferenceManager
            ): IntegerOverlay {
                return IntegerOverlay(json, parent, refMgr)
            }
        }

        fun builder(modelMember: JsonOverlay<*>?): Builder<Int> {
            return Builder(factory, modelMember)
        }

        fun create(modelMember: JsonOverlay<*>?): JsonOverlay<Int> {
            return builder(modelMember).build()
        }

        fun create(value: Int, modelMember: JsonOverlay<*>?): JsonOverlay<Int> {
            val result = create(modelMember)
            result._set(value)
            return result
        }
    }
}
