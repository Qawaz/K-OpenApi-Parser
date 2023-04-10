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
import kotlinx.serialization.json.jsonObject
import java.util.regex.Pattern

class MapOverlay<V> : JsonOverlay<MutableMap<String, V>> {

    private val valueFactory: OverlayFactory<V>
    private val keyPattern: Pattern?
    private val overlays: MutableMap<String, JsonOverlay<V>> = LinkedHashMap()
    private var elaborated = false

    private constructor(
        json: JsonElement,
        parent: JsonOverlay<*>?, factory: OverlayFactory<MutableMap<String, V>>,
        refMgr: ReferenceManager
    ) : super(json, parent, factory, refMgr) {
        val mapOverlayFactory = factory as MapOverlayFactory<V>
        valueFactory = mapOverlayFactory.valueFactory
        val keyPattern = mapOverlayFactory.keyPattern
        this.keyPattern = if (keyPattern != null) Pattern.compile(keyPattern) else null
    }

    private constructor(
        value: MutableMap<String, V>?, parent: JsonOverlay<*>?, factory: OverlayFactory<MutableMap<String, V>>,
        refMgr: ReferenceManager
    ) : super(LinkedHashMap<String, V>(value), parent, factory, refMgr) {
        val mapOverlayFactory = factory as MapOverlayFactory<V>
        valueFactory = mapOverlayFactory.valueFactory
        val keyPattern = mapOverlayFactory.keyPattern
        this.keyPattern = if (keyPattern != null) Pattern.compile(keyPattern) else null
    }

    override fun _findInternal(path: JsonPointer?): JsonOverlay<*>? {
        return null
        //TODO
//        val key = path!!.matchingProperty
//        return if (overlays.containsKey(key)) overlays[key]!!._find(path.tail()) else null
    }

    override fun _fromJson(json: JsonElement): MutableMap<String, V> {
        return object : LinkedHashMap<String, V>() {
            private val serialVersionUID = 1L

            @get:Suppress("unused")
            val overlay: MapOverlay<V>
                get() = this@MapOverlay
        }
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement {
        if (overlays.isEmpty() && !options.isKeepThisEmpty) return _jsonMissing()
        val objMap = mutableMapOf<String, JsonElement>()
        for ((key, value1) in overlays) {
            objMap[key] = value1._toJson(options.minus(SerializationOptions.Option.KEEP_ONE_EMPTY))
        }
        return _jsonObject(objMap)
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
        elaborated = true
    }

    private fun fillWithJson() {
        value!!.clear()
        overlays.clear()
        for (item in json!!.jsonObject) {
            if (keyPattern == null || keyPattern.matcher(item.key).matches()) {
                val valOverlay = valueFactory.create(item.value, this, refMgr)
                overlays[item.key] = valOverlay
                valOverlay._setPathInParent(item.key)
                valOverlay._get(false)?.let { value!!.put(item.key, it) }
            }
        }
    }

    private fun fillWithValues() {
        overlays.clear()
        for ((key, value1) in value!!) {
            val valOverlay = valueOverlayFor(value1)
            overlays[key] = valOverlay
            valOverlay._setPathInParent(key)
        }
    }

    private fun valueOverlayFor(`val`: V): JsonOverlay<V> {
        return valueFactory.create(`val`, this, refMgr)
    }

    operator fun get(key: String): V? {
        val valOverlay = overlays[key]
        return valOverlay?._get()
    }

    /* package */
    fun _getOverlay(key: String): JsonOverlay<V> {
        return overlays[key]!!
    }

    fun keySet(): Set<String> {
        return value!!.keys
    }

    operator fun set(key: String, `val`: V) {
        value!!.put(key, `val`)
        overlays[key] = valueOverlayFor(`val`)
    }

    fun remove(key: String) {
        value!!.remove(key)
        overlays.remove(key)
    }

    fun size(): Int {
        return overlays.size
    }

    override fun equals(obj: Any?): Boolean {
        return equals(obj, false)
    }

    fun equals(obj: Any?, sameOrder: Boolean): Boolean {
        if (obj is MapOverlay<*>) {
            val castObj = obj
            return overlays == castObj.overlays && (!sameOrder || checkOrder(castObj))
        }
        return false
    }

    private fun checkOrder(other: MapOverlay<*>): Boolean {
        val myKeys: Iterator<String> = overlays.keys.iterator()
        val theirKeys: Iterator<String> = other.overlays.keys.iterator()
        while (myKeys.hasNext() && theirKeys.hasNext()) {
            if (myKeys.next() != theirKeys.next()) {
                return false
            }
        }
        return !myKeys.hasNext() && !theirKeys.hasNext()
    }

    override fun hashCode(): Int {
        return overlays.hashCode()
    }

    override fun _getFactory(): OverlayFactory<*> {
        return factory
    }

    private class MapOverlayFactory<V>(val valueFactory: OverlayFactory<V>, val keyPattern: String?) :
        OverlayFactory<MutableMap<String, V>>() {

        override val signature: String
            get() = String.format("map[%s|%s]", valueFactory.signature, keyPattern ?: "*")

        override fun getOverlayClass(): Class<out JsonOverlay<in Map<String, V>>?> {
            val overlayClass: Class<*> = MapOverlay::class.java
            return overlayClass as Class<out JsonOverlay<Map<String, V>>?>
        }

        override fun _create(
            value: MutableMap<String, V>?,
            parent: JsonOverlay<*>?,
            refMgr: ReferenceManager
        ): JsonOverlay<MutableMap<String, V>> {
            return MapOverlay<V>(value, parent, this, refMgr)
        }

        override fun _create(
            json: JsonElement,
            parent: JsonOverlay<*>?,
            refMgr: ReferenceManager
        ): JsonOverlay<MutableMap<String, V>> {
            return MapOverlay<V>(json, parent, this, refMgr)
        }
    }

    companion object {
        fun <V> getFactory(
            valueFactory: OverlayFactory<V>,
            keyPattern: String?
        ): OverlayFactory<MutableMap<String, V>> {
            return MapOverlayFactory(valueFactory, keyPattern)
        }
    }
}