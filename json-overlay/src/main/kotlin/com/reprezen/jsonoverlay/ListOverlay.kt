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

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.jsonArray

class ListOverlay<V> : JsonOverlay<MutableList<V>>, KeyValueOverlay {


    private val itemFactory: OverlayFactory<V>

    private val overlays: MutableList<JsonOverlay<V>> = ArrayList()

    private var elaborated = false

    private constructor(
        json: JsonElement,
        parent: JsonOverlay<*>?,
        factory: OverlayFactory<MutableList<V>>,
        refMgr: ReferenceManager
    ) : super(json, parent, factory, refMgr) {
        itemFactory = (factory as ListOverlayFactory<V>).itemFactory
    }

    private constructor(
        value: List<V>?,
        parent: JsonOverlay<*>?, factory: OverlayFactory<MutableList<V>>,
        refMgr: ReferenceManager
    ) : super(ArrayList<V>(value ?: listOf()), parent, factory, refMgr) {
        itemFactory = (factory as ListOverlayFactory<V>).itemFactory
    }

    override fun _fromJson(json: JsonElement): MutableList<V> {
        return object : ArrayList<V>() {
            private val serialVersionUID = 1L

            @get:Suppress("unused")
            val overlay: ListOverlay<V>
                get() = this@ListOverlay
        }
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement {
        if (overlays.isEmpty() && !options.isKeepThisEmpty) return JsonNull
        return _jsonArray(overlays.map { it._toJson(options.plus(SerializationOptions.Option.KEEP_ONE_EMPTY)) })
    }

    override fun _isElaborated(): Boolean {
        return elaborated
    }

    override fun _elaborate(atCreation: Boolean) {
        if (json != null) {
            fillWithJson()
        } else {
            fillWithValues()
        }
        _setChildParentPaths()
        elaborated = true
    }

    private fun fillWithJson() {
        value!!.clear()
        overlays.clear()
        if (json !is JsonArray) return
        for (item in json!!.jsonArray) {
            val itemOverlay = itemFactory.create(item, this, refMgr)
            overlays.add(itemOverlay)
            value!!.add(itemOverlay._get(false)!!)
        }
    }

    private fun fillWithValues() {
        overlays.clear()
        for (itemValue in value!!) {
            overlays.add(itemOverlayFor(itemValue))
        }
    }

    private fun itemOverlayFor(itemValue: V): JsonOverlay<V> {
        return itemFactory.create(itemValue, this, refMgr)
    }

    operator fun get(index: Int): V? {
        return overlays.getOrNull(index)?._get()
    }

    /* package */
    fun _getOverlay(index: Int): JsonOverlay<V> {
        return overlays[index]
    }

    operator fun set(index: Int, itemValue: V) {
        value!!.set(index, itemValue)
        overlays[index] = itemOverlayFor(itemValue)
        _setChildParentPath(index)
    }

    fun add(itemValue: V) {
        value!!.add(itemValue)
        overlays.add(itemOverlayFor(itemValue))
        _setChildParentPath(overlays.size - 1)
    }

    fun insert(index: Int, itemValue: V) {
        value!!.add(index, itemValue)
        overlays.add(index, itemOverlayFor(itemValue))
        _setChildParentPaths(index, overlays.size)
    }

    fun remove(index: Int) {
        value!!.removeAt(index)
        overlays.removeAt(index)
        _setChildParentPaths(index, overlays.size)
    }

    fun size(): Int {
        return overlays.size
    }

    private fun _setChildParentPath(index: Int) {
        _setChildParentPaths(index, index + 1)
    }

    private fun _setChildParentPaths(from: Int = 0, to: Int = overlays.size) {
        for (i in from until to) {
            overlays[i]._setPathInParent(i.toString())
        }
    }

    override fun _getPropertyNames(): List<String> {
        return mutableListOf<String>().apply {
            for (i in 0 until size) add(i.toString())
        }
    }

    override fun _getKeyValueOverlay(name: String): JsonOverlay<*>? {
        return name.toIntOrNull()?.let { overlays[it] }
    }

    override fun equals(other: Any?): Boolean {
        if (other is ListOverlay<*>) {
            return overlays == other.overlays
        }
        return false
    }

    override fun hashCode(): Int {
        return overlays.hashCode()
    }

    override fun _getFactory(): OverlayFactory<*> {
        return factory
    }

    private class ListOverlayFactory<V>(val itemFactory: OverlayFactory<V>) : OverlayFactory<MutableList<V>>() {

        override val signature: String
            get() = String.format("list[%s]", itemFactory.signature)

        override fun getOverlayClass(): Class<out JsonOverlay<in MutableList<V>>?> {
            val overlayClass: Class<*> = ListOverlay::class.java
            return overlayClass as Class<out JsonOverlay<MutableList<V>>?>
        }

        override fun _create(
            value: MutableList<V>?,
            parent: JsonOverlay<*>?,
            refMgr: ReferenceManager
        ): JsonOverlay<MutableList<V>> {
            return ListOverlay(value, parent, this, refMgr)
        }

        override fun _create(
            json: JsonElement,
            parent: JsonOverlay<*>?,
            refMgr: ReferenceManager
        ): JsonOverlay<MutableList<V>> {
            return ListOverlay<V>(json, parent, this, refMgr)
        }
    }

    companion object {
        fun <V> getFactory(itemFactory: OverlayFactory<V>): OverlayFactory<MutableList<V>> {
            return ListOverlayFactory(itemFactory)
        }
    }
}
