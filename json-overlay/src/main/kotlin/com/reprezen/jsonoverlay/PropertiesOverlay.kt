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

abstract class PropertiesOverlay<V> : JsonOverlay<V>, KeyValueOverlay {

    class FactoryMap(val path: String, val factory: OverlayFactory<*>) {

        val pointer = JsonPointer(path)

        val isSubMap: Boolean get() = path == "/" || path.isEmpty()

        override fun equals(other: Any?): Boolean {
            if (other !is FactoryMap) return false
            return other.pointer == this.pointer
        }

        override fun hashCode(): Int {
            var result = pointer.hashCode()
            result = 31 * result + factory.hashCode()
            return result
        }

    }

    private val factoryMap: MutableMap<String, FactoryMap> = mutableMapOf()
    private val overlays: MutableMap<FactoryMap, JsonOverlay<*>> = mutableMapOf()

    private var elaborated = false
    private var elaborationValue: V? = null

    protected constructor(
        json: JsonElement,
        parent: JsonOverlay<*>?,
        factory: OverlayFactory<V>,
        refMgr: ReferenceManager?
    ) : super(json, parent, factory, refMgr!!) {
        _elaborate()
    }

    protected constructor(
        value: V?,
        parent: JsonOverlay<*>?,
        factory: OverlayFactory<V>,
        refMgr: ReferenceManager?
    ) : super(value, parent, factory, refMgr!!) {
        _elaborate()
    }

    fun _getFactoryKeys(): List<String> {
        return factoryMap.keys.toList()
    }

    /* package */
    override fun _getPropertyNames(): List<String> {
        for (key in factoryMap.keys) _getValueOverlayByName(key)
        val keys = mutableListOf<String>()
        for (key in overlays.keys) if (!key.isSubMap) keys.add(key.path)
        for (subMap in getSubMaps()) keys.addAll(subMap.value._getPropertyNames())
        return keys
    }

    protected fun _isPresent(name: String?): Boolean {
        return _getValueOverlayByName(name!!) != null
    }

    // sub maps exist in a field as a property like
    // object[name] = the map {}
    // but they are actually just children of this map
    // meaning when keys are queries of this map, contain its keys and keys of al the sub maps
    private fun getSubMaps(): Map<FactoryMap, KeyValueOverlay> {
        val map = mutableMapOf<FactoryMap, KeyValueOverlay>()
        for (factory in factoryMap.values) {
            if (json != null && factory.isSubMap) {
                (createOverlay(
                    elem = json!!,
                    factoryMap = factory,
                    factory = factory.factory,
                ) as? KeyValueOverlay)?.let { map.put(factory, it) }
            }
        }
        return map
    }

    override fun _getPathOfChild(child: JsonOverlay<*>): String {
        for (factory in overlays) {
            if (factory.value == child) {
                return factory.key.path
            }
        }
        return "-1"//.also {
        //Throwable("returning child path -1 for child in properties overlay child : $child \n\n\n props : $this").printStackTrace()
        //}
    }

    protected fun <T> _get(name: String?): T? {
        factoryMap[name]?.let { factory ->
            return (overlays[factory] as? JsonOverlay<T>)?._get() ?: _getOverlay(
                factory,
                factory.factory as OverlayFactory<T>
            )?._get()
        }
        return null
    }

    private fun <T> createOverlay(
        elem: JsonElement,
        factoryMap: FactoryMap,
        factory: OverlayFactory<T>,
    ): JsonOverlay<T> {
        return factory.create(elem, this, refMgr).also { ov ->
            overlays[factoryMap] = ov
        }
    }

    private fun <T> createOverlay(
        value: T?,
        factoryMap: FactoryMap,
        factory: OverlayFactory<T>,
    ): JsonOverlay<T> {
        return factory.create(value, this, refMgr).also { ov ->
            overlays[factoryMap] = ov
        }
    }

    protected fun <T> _getOverlay(factoryMap: FactoryMap, factory: OverlayFactory<T>): JsonOverlay<T>? {
        if (json == null) return null
        val pointer = JsonPointer(factoryMap.path)
        val elem = pointer.navigate(json!!)
        return elem?.let { createOverlay(it, factoryMap, factory) }
    }

    override fun <T> _traverseOverlaysOfSegment(segment: String, block: (JsonOverlay<*>) -> T?): T? {
        for (factory in factoryMap.values) {
            if (factory.pointer.segments.firstOrNull() == segment) {
                _getOverlay(factory, factory.factory)?.let(block)?.let { return it }
            }
        }
        for (subMap in getSubMaps()) {
            for (subMapKey in subMap.value._getPropertyNames()) {
                if (subMapKey == segment) {
//                    println("found $subMapKey , $segment")
                    subMap.value._getValueOverlayByPath(subMapKey)?.let(block)?.let { return it }
//                    println("NOT RETURNED")
                }
            }
        }
        return null
    }

    override fun _getValueOverlayByPath(segment: String): JsonOverlay<*>? {
        return _traverseOverlaysOfSegment(segment) { it }
    }

    override fun _findByPath(path: JsonPointer): JsonOverlay<*>? {
//        val debug = path == JsonPointer("/2.0/users/{username}")
//        if (debug) println("NAVIGATING $path")
        for (entry in factoryMap) {
            val remaining = path.minus(entry.value.pointer) ?: continue
            _getOverlay(entry.value, entry.value.factory)?.let {
//                if (debug) {
//                    println("FOUND ${path.minusEnd(remaining).toString().ifEmpty { "${it::class.qualifiedName}" }}")
//                }
                if (remaining.isEmpty()) {
//                    if (debug) println("FOUND $it")
                    return it
                } else if (it is KeyValueOverlay) {
//                    if (debug) println("FINDING $remaining IN ${it::class.qualifiedName}")
                    it.findByPointer(remaining)?.let { next -> return next }
//                    if (debug) println("FINDING /")
//                    if (remaining.segments.firstOrNull()?.startsWith("/") == true) {
//                        println("FINDING WITHOUT F-SLASH $remaining")
//                        it.findByPointer(
//                            JsonPointer(
//                                remaining.segments.first().removePrefix("/") + remaining.segments.drop(1)
//                            )
//                        )?.let { next -> return next }
//                    }
                }
            }
        }
        return null
//        return super._findByPath(path)
    }

    override fun toString(): String {
        var value = "{"
        for (factory in factoryMap.values) {
            if (factory.path != "/" && factory.path.isNotEmpty()) {
                value += "\n${factory.path} : " + _getOverlay(factory, factory.factory).toString()
                    .replace("\n", "\n\t")
            }
        }
        for (subMap in getSubMaps()) {
            for (subMapKey in subMap.value._getPropertyNames()) {
                value += "\n$subMapKey : ${subMap.value._getValueOverlayByPath(subMapKey)}"
            }
        }
        return "$value\n}"
    }

    override fun _getValueOverlayByName(name: String): JsonOverlay<*>? {
        return factoryMap[name]?.let { factory ->
            _getOverlay(factory, factory.factory)
        }
    }

    fun <T> _getOverlay(name: String): JsonOverlay<T>? {
        return _getValueOverlayByName(name) as? JsonOverlay<T>
    }

    protected fun <T> _setScalar(name: String, value: T?) {
        var overlay = _getOverlay<T>(name)
        if (overlay == null && value != null) {
            overlay = factoryMap[name]?.let {
                createOverlay(value, it, it.factory as OverlayFactory<T>)
            }
        }
        overlay?._set(value)
    }

    protected fun <T> _getList(name: String): MutableList<T> {
        return _get(name)!!
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> _get(name: String, index: Int): T {
        val overlay = _getOverlay<T>(name) as ListOverlay<T>
        return overlay[index]!!
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> _setList(name: String, listVal: MutableList<T>?) {
        val overlay = _getOverlay<T>(name) as ListOverlay<T>
        overlay._set(listVal)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> _set(name: String, index: Int, value: T) {
        val overlay = _getOverlay<T>(name) as ListOverlay<T>
        overlay[index] = value
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> _insert(name: String, index: Int, value: T) {
        val overlay = _getOverlay<T>(name) as ListOverlay<T>
        overlay.insert(index, value)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> _add(name: String, value: T) {
        val overlay = _getOverlay<T>(name) as ListOverlay<T>
        overlay.add(value)
    }

    protected fun _remove(name: String, index: Int) {
        val overlay = _getValueOverlayByName(name) as ListOverlay<*>
        overlay.remove(index)
    }

    protected fun <T> _getMap(name: String): MutableMap<String, T> {
        return _get(name) ?: mutableMapOf()
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> _get(name: String, key: String): T? {
        val overlay = _getOverlay<T>(name) as MapOverlay<T>
        return overlay[key]
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> _setMap(name: String, mapVal: MutableMap<String, T>?) {
        val overlay = _getOverlay<T>(name) as MapOverlay<T>
        overlay._set(mapVal)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> _set(name: String, key: String, value: T) {
        val overlay = _getOverlay<T>(name) as MapOverlay<in T>
        overlay[key] = value
    }

    protected fun _remove(name: String, key: String) {
        val overlay = _getValueOverlayByName(name) as MapOverlay<*>
        overlay.remove(key)
    }

    fun _elaborate() {
        if (elaborationValue != null) {
            (value as? PropertiesOverlay<*>)?.let { _elaborateValue(it) }
        } else {
            _elaborateJson()
        }
        elaborated = true
    }

    protected open fun _elaborateJson() {
        // need an implemenatation here because subclasses invoke method in
        // super, to
        // support type extensions
    }

    private fun _elaborateValue(value: PropertiesOverlay<*>) {
        factoryMap.clear()
        for ((key, value1) in value.factoryMap) {
            factoryMap[key] = value1
        }
        elaborationValue = null
    }

    protected fun <X> _createScalar(name: String, path: String, factory: OverlayFactory<X>) {
        return _addChild(name, path, factory)
    }

    protected fun <X> _createList(name: String, path: String, itemFactory: OverlayFactory<X>) {
        return _addChild(name, path, ListOverlay.getFactory(itemFactory))
    }

    protected fun <X> _createMap(
        name: String, path: String,
        valueFactory: OverlayFactory<X>,
        keyPattern: String?
    ) {
        return _addChild(name, path, MapOverlay.getFactory(valueFactory, keyPattern))
    }

    private fun <X> _addChild(name: String, path: String, factory: OverlayFactory<X>) {
        val map = FactoryMap(path, factory)
        factoryMap[name] = map
    }

    private fun getPointer(child: JsonOverlay<*>): JsonPointer {
        val path = child._getPathInParent()
        return JsonPointer(if (path.isEmpty()) "" else "/$path")
    }

    public override fun _fromJson(json: JsonElement): V? {
        // parsing of the json node is expected to be done in the constructor of
        // the
        // subclass, so nothing is done here. But we do establish this object as
        // its own
        // value.
        return this as V
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement {
        for (key in factoryMap.keys) _getValueOverlayByName(key)
        val obj = overlays.mapKeys { it.key.path }
            .mapValues { it.value._toJson(options.minus(SerializationOptions.Option.KEEP_ONE_EMPTY)) }
        val result = _fixJson(JsonObject(obj))
        return if (options.isKeepThisEmpty || obj.isNotEmpty()) result else JsonNull
    }

    protected open fun _fixJson(json: JsonElement): JsonElement {
        return json
    }

    override fun hashCode(): Int {
        var hash = 7
        hash = 31 * hash + factoryMap.hashCode()
        return hash
    }

}
