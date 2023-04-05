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

class BooleanOverlay : ScalarOverlay<Boolean> {

    private constructor(value: Boolean?, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
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

    override fun _fromJson(json: JsonNode): Boolean? {
        return if (json.isBoolean) json.booleanValue() else null
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonNode? {
        return if (value != null) _jsonBoolean(value!!) else _jsonMissing()
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
                json: JsonNode,
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