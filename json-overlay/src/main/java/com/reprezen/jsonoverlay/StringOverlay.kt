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

class StringOverlay : ScalarOverlay<String> {

    private constructor(json: JsonNode, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        json,
        parent,
        Companion.factory,
        refMgr
    )

    private constructor(value: String?, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        value,
        parent,
        Companion.factory,
        refMgr
    )

    override fun _fromJson(json: JsonNode): String? {
        return if (json.isTextual) json.textValue() else null
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonNode {
        return if (value != null) _jsonScalar(value) else _jsonMissing()
    }

    override fun _getFactory(): OverlayFactory<*> {
        return Companion.factory
    }

    override fun toString(): String {
        // we don't want quotes here; the default rendering uses toJson, which does
        // include them.
        return if (value != null) value!! else ""
    }

    companion object {

        @JvmField
        var factory: OverlayFactory<String> = object : OverlayFactory<String>() {
            override fun getOverlayClass(): Class<StringOverlay> {
                return StringOverlay::class.java
            }

            override fun _create(value: String?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): StringOverlay {
                return StringOverlay(value, parent, refMgr)
            }

            public override fun _create(
                json: JsonNode,
                parent: JsonOverlay<*>?,
                refMgr: ReferenceManager
            ): StringOverlay {
                return StringOverlay(json, parent, refMgr)
            }
        }

        fun builder(modelMember: JsonOverlay<*>?): Builder<String> {
            return Builder(factory, modelMember)
        }

        fun create(modelMember: JsonOverlay<*>?): JsonOverlay<String> {
            return builder(modelMember).build()
        }

        fun create(value: String, modelMember: JsonOverlay<*>?): JsonOverlay<String> {
            val result = create(modelMember)
            result._set(value)
            return result
        }
    }
}
