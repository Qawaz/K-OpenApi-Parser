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

    private constructor(
        json: JsonElement,
        parent: JsonOverlay<*>?,
        factory: OverlayFactory<MutableList<V>>,
        refMgr: ReferenceManager
    ) : super(json, parent, factory, refMgr) {
        itemFactory = (factory as ListOverlayFactory<V>).itemFactory
        _elaborate()
    }

    private constructor(
        value: MutableList<V>,
        parent: JsonOverlay<*>?, factory: OverlayFactory<MutableList<V>>,
        refMgr: ReferenceManager
    ) : super(value, parent, factory, refMgr) {
        itemFactory = (factory as ListOverlayFactory<V>).itemFactory
        _elaborate()
    }

    override fun _fromJson(json: JsonElement): MutableList<V> {
        return object : ArrayList<V>() {
            @get:Suppress("unused")
            val overlay: ListOverlay<V>
                get() = this@ListOverlay
        }
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement {
        if (overlays.isEmpty() && !options.isKeepThisEmpty) return JsonNull
        return _jsonArray(overlays.map { it._toJson(options.plus(SerializationOptions.Option.KEEP_ONE_EMPTY)) })
    }

    fun _elaborate() {
        if (json != null && json is JsonArray) {
            fillWithJson(json!! as JsonArray)
        } else {
            fillWithValues()
        }
    }

    private fun fillWithJson(json: JsonArray) {
        value!!.clear()
        overlays.clear()
        for (item in json.jsonArray) {
            val itemOverlay = itemFactory.create(item, this, refMgr)
            overlays.add(itemOverlay)
            value!!.add(itemOverlay._get()!!)
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

    operator fun set(index: Int, itemValue: V) {
        value!!.set(index, itemValue)
        overlays[index] = itemOverlayFor(itemValue)
    }

    fun add(itemValue: V) {
        value!!.add(itemValue)
        overlays.add(itemOverlayFor(itemValue))
    }

    fun insert(index: Int, itemValue: V) {
        value!!.add(index, itemValue)
        overlays.add(index, itemOverlayFor(itemValue))
    }

    fun remove(index: Int) {
        value!!.removeAt(index)
        overlays.removeAt(index)
    }

    fun size(): Int {
        return overlays.size
    }

    override fun _getPropertyNames(): List<String> {
        return mutableListOf<String>().apply {
            for (i in 0 until size) add(i.toString())
        }
    }

    override fun _getValueOverlayByName(name: String): JsonOverlay<V>? {
        return name.toIntOrNull()?.let { overlays[it] }
    }

    override fun _findByPath(path: JsonPointer): JsonOverlay<*>? {
        val index = path.segments.firstOrNull()?.toIntOrNull()
        if (index != null) {
            _getValueOverlayByIndex(index)?.let {
                if (path.segments.size == 1) return it
                return it.findByPointer(JsonPointer(path.segments.drop(1)))
            }
        }
        return null
    }

    override fun _getValueOverlayByIndex(index: Int): JsonOverlay<V>? {
        return overlays.getOrNull(index)
    }

    override fun hashCode(): Int {
        return overlays.hashCode()
    }

    override fun _getPathOfChild(child: JsonOverlay<*>): String {
        return (child as? JsonOverlay<V>)?.let { overlays.indexOf(it).toString() } ?: "-1".also {
            Throwable("returning child path -1 for child in list overlay child : $child \n\n\nprops : $this").printStackTrace()
        }
    }

    private class ListOverlayFactory<V>(val itemFactory: OverlayFactory<V>) : OverlayFactory<MutableList<V>>() {

        override val signature: String
            get() = String.format("list[%s]", itemFactory.signature)

        override fun _create(
            value: MutableList<V>?,
            parent: JsonOverlay<*>?,
            refMgr: ReferenceManager
        ): JsonOverlay<MutableList<V>> {
            return ListOverlay(value ?: mutableListOf<V>(), parent, this, refMgr)
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
