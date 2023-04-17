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

class ListOverlay<V> private constructor(
    list: MutableList<V>,
    json: JsonElement?,
    parent: JsonOverlay<*>?,
    private val itemFactory: OverlayFactory<V>,
    factory: OverlayFactory<MutableList<V>>,
    refMgr: ReferenceManager
) : JsonOverlay<MutableList<V>>(
    parent = parent,
    factory = factory,
    refMgr = refMgr
), KeyValueOverlay, List<V> by list, MutableList<V> {

    private val overlays: MutableList<JsonOverlay<V>> = ArrayList()

    override val size: Int
        get() = overlays.size

    init {
        this.value = list
        this.json = json
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

    override fun _getPropertyNames(): List<String> {
        return mutableListOf<String>().apply {
            for (i in 0 until size) add(i.toString())
        }
    }

    override fun _getValueOverlayByName(name: String): JsonOverlay<V>? {
        return name.toIntOrNull()?.let { overlays[it] }
    }

    override fun findByPointer(path: JsonPointer): JsonOverlay<*>? {
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

    override fun _getPathOfChild(child: JsonOverlay<*>): String {
        return (child as? JsonOverlay<V>)?.let { overlays.indexOf(it).toString() } ?: "-1".also {
            Throwable("returning child path -1 for child in list overlay child : $child \n\n\nprops : $this").printStackTrace()
        }
    }

    // ----- Mutable List Functions

    override operator fun get(index: Int): V {
        return overlays[index]._get()!!
    }

    override fun removeAll(elements: Collection<V>): Boolean {
        for (index in elements.map { value!!.indexOf(it) }) if (index > -1) overlays.removeAt(index)
        return value!!.removeAll(elements)
    }

    override fun remove(element: V): Boolean {
        val index = value!!.indexOf(element)
        if (index > -1) {
            overlays.removeAt(index)
            value!!.removeAt(index)
        }
        return true
    }

    override fun addAll(elements: Collection<V>): Boolean {
        value!!.addAll(elements)
        return overlays.addAll(elements.map { itemOverlayFor(it) })
    }

    override fun addAll(index: Int, elements: Collection<V>): Boolean {
        value!!.addAll(index, elements)
        return overlays.addAll(index, elements.map { itemOverlayFor(it) })
    }

    override operator fun set(index: Int, element: V): V {
        val previous = value!!.getOrNull(index)
        value!![index] = element
        overlays[index] = itemOverlayFor(element)
        return previous ?: element
    }

    override fun add(element: V): Boolean {
        value!!.add(element)
        overlays.add(itemOverlayFor(element))
        return true
    }

    override fun add(index: Int, element: V) {
        value!!.add(index, element)
        overlays.add(index, itemOverlayFor(element))
    }

    override fun removeAt(index: Int): V {
        overlays.removeAt(index)
        return value!!.removeAt(index)
    }

    override fun retainAll(elements: Collection<V>): Boolean {
        return value!!.retainAll(elements)
    }

    override fun clear() {
        overlays.clear()
        value!!.clear()
    }

    override fun iterator(): MutableIterator<V> {
        return value!!.iterator()
    }

    override fun listIterator(): MutableListIterator<V> {
        return value!!.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<V> {
        return value!!.listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<V> {
        return value!!.subList(fromIndex, toIndex)
    }

    private class ListOverlayFactory<V>(val itemFactory: OverlayFactory<V>) : OverlayFactory<MutableList<V>>() {

        override val signature: String
            get() = String.format("list[%s]", itemFactory.signature)

        override fun _create(
            value: MutableList<V>?,
            parent: JsonOverlay<*>?,
            refMgr: ReferenceManager
        ): JsonOverlay<MutableList<V>> {
            return ListOverlay(list = value ?: mutableListOf<V>(), json = null, parent, itemFactory, this, refMgr)
        }

        override fun _create(
            json: JsonElement,
            parent: JsonOverlay<*>?,
            refMgr: ReferenceManager
        ): JsonOverlay<MutableList<V>> {
            return ListOverlay(list = mutableListOf<V>(), json = json, parent, itemFactory, this, refMgr)
        }
    }

    companion object {
        fun <V> getFactory(itemFactory: OverlayFactory<V>): OverlayFactory<MutableList<V>> {
            return ListOverlayFactory(itemFactory)
        }
    }
}
