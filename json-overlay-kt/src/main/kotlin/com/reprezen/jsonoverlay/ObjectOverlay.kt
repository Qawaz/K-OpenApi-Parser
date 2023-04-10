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

class ObjectOverlay : ScalarOverlay<Any> {

    private constructor(value: Any?, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        Companion.factory,
        refMgr
    ) {
        if (value != null && value is JsonElement) {
            load(value, parent)
        } else {
            load(value, parent)
        }
    }

    override fun _fromJson(json: JsonElement): Any? {
        return when (json) {
            is JsonArray -> json.jsonArray
            is JsonObject -> json.jsonObject
            is JsonPrimitive -> json.jsonPrimitive.toValue()
            JsonNull -> null
        }
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement? {
        return null
        //TODO
//        return if (value != null) mapper.convertValue(value, JsonNode::class.java) else _jsonMissing()
    }

    override fun _getFactory(): OverlayFactory<*> {
        return Companion.factory
    }

    companion object {

        @JvmField
        var factory: OverlayFactory<Any> = object : OverlayFactory<Any>() {
            override fun getOverlayClass(): Class<ObjectOverlay> {
                return ObjectOverlay::class.java
            }

            override fun _create(value: Any?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): ObjectOverlay {
                return ObjectOverlay(value, parent, refMgr)
            }

            override fun _create(json: JsonElement, parent: JsonOverlay<*>?, refMgr: ReferenceManager): ObjectOverlay {
                return ObjectOverlay(json, parent, refMgr)
            }
        }

        fun builder(modelMember: JsonOverlay<*>?): Builder<Any> {
            return Builder(factory, modelMember)
        }

        fun create(modelMember: JsonOverlay<*>?): JsonOverlay<Any> {
            return builder(modelMember).build()
        }

        fun create(value: Any?, modelMember: JsonOverlay<*>?): JsonOverlay<Any> {
            val result = create(modelMember)
            result._set(value)
            return result
        }
    }
}
