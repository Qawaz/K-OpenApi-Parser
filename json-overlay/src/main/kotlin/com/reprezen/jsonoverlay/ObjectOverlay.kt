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

class ObjectOverlay : ScalarOverlay<Any>, KeyValueOverlay {

    private constructor(parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        parent = parent,
        factory = Companion.factory,
        refMgr = refMgr
    )

    private constructor(json: JsonElement, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        json = json,
        parent = parent,
        factory = Companion.factory,
        refMgr = refMgr
    )

    private constructor(value: Any, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        value = value,
        parent = parent,
        Companion.factory,
        refMgr
    )

    override fun _fromJson(json: JsonElement): Any? {
        return json.toValue()
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement {
        return value.toJsonElement()
    }

    companion object {

        @JvmField
        val factory: OverlayFactory<Any> = object : OverlayFactory<Any>() {

            override val signature: String?
                get() = ObjectOverlay::class.simpleName

            override fun _create(value: Any?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): ObjectOverlay {
                return if (value != null) ObjectOverlay(value, parent, refMgr) else ObjectOverlay(parent, refMgr)
            }

            override fun _create(json: JsonElement, parent: JsonOverlay<*>?, refMgr: ReferenceManager): ObjectOverlay {
                return ObjectOverlay(json, parent, refMgr)
            }
        }

        fun builder(modelMember: JsonOverlay<*>): Builder<Any> {
            return Builder(factory, modelMember)
        }

        fun create(modelMember: JsonOverlay<*>): JsonOverlay<Any> {
            return builder(modelMember).build()
        }

        fun create(value: Any?, modelMember: JsonOverlay<*>): JsonOverlay<Any> {
            val result = create(modelMember)
            result._set(value)
            return result
        }
    }

    private val emptyPropertyNames by lazy { emptyList<String>() }

    @Suppress("UNCHECKED_CAST")
    private val Any.valueMap: Map<String, *>? get() = (this as? Map<String, *>)

    override fun _getPropertyNames(): List<String> {
        if (json != null && json is JsonObject) (json as JsonObject).keys.toList()
        _get()?.valueMap?.keys?.toList()?.let { return it }
        return emptyPropertyNames
    }

    private fun valueOfKey(key: String): JsonOverlay<*>? {
        if (json != null && json is JsonObject) {
            (json as JsonObject)[key]?.toJsonOverlay(parent = this, refMgr = refMgr)?.let { return it }
        }
        _get()?.valueMap?.getOrDefault(key, null)?.toJsonOverlay(parent = this, refMgr = refMgr)?.let { return it }
        return null
    }

    override fun _findByPath(path: JsonPointer): JsonOverlay<*>? {
        path.segments.firstOrNull()?.let { valueOfKey(it) }?.let { ov ->
            if (path.segments.size == 1) return ov
            return ov.findByPointer(JsonPointer(path.segments.drop(1)))
        }
        return null
    }

    override fun _getValueOverlayByName(name: String): JsonOverlay<*>? {
        valueOfKey(name)?.let { return it }
        return null
    }

}
