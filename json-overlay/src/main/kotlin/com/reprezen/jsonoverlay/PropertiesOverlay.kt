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
        override fun equals(other: Any?): Boolean {
            if (other !is FactoryMap) return false
            return other.path == this.path && factory == other.factory
        }

        override fun hashCode(): Int {
            var result = path.hashCode()
            result = 31 * result + factory.hashCode()
            return result
        }
    }

    private val factoryMap: MutableMap<String, FactoryMap> = mutableMapOf()
    private val overlays: MutableMap<FactoryMap, JsonOverlay<*>> = mutableMapOf()

    private var elaborated = false
    private var deferElaboration = false
    private var elaborationValue: V? = null

    protected constructor(
        json: JsonElement,
        parent: JsonOverlay<*>?,
        factory: OverlayFactory<V>?,
        refMgr: ReferenceManager?
    ) : super(json, parent, factory!!, refMgr!!) {
        deferElaboration = false
    }

    protected constructor(
        value: V?,
        parent: JsonOverlay<*>?,
        factory: OverlayFactory<V>?,
        refMgr: ReferenceManager?
    ) : super(value, parent, factory!!, refMgr!!) {
        elaborationValue = value
    }

    fun _getFactoryKeys(): List<String> {
        return factoryMap.keys.toList()
    }

    /* package */
    override fun _getPropertyNames(): List<String> {
        for (key in factoryMap.keys) _getKeyValueOverlay(key)
        val keys = mutableListOf<String>()
        for (key in overlays.keys) if (key.path.isNotEmpty() && key.path != "/") keys.add(key.path)
        keys.addAll(getSubMapKeys())
        return keys
    }

    protected fun _isPresent(name: String?): Boolean {
        return _getKeyValueOverlay(name!!) != null
    }

    // sub maps exist in a field as a property like
    // object[name] = the map {}
    // but they are actually just children of this map
    // meaning when keys are queries of this map, contain its keys and keys of al the sub maps
    private fun getSubMaps(): List<KeyValueOverlay> {
        val list = mutableListOf<KeyValueOverlay>()
        for (factory in factoryMap.values) {
            if (factory.path.isEmpty() || factory.path == "/") {
                (createOverlay(
                    elem = json!!,
                    factoryMap = factory,
                    factory = factory.factory,
                ) as? KeyValueOverlay)?.let { list.add(it) }
            }
        }
        return list
    }

    private fun getSubMapKeys(): MutableList<String> {
        val keys = mutableListOf<String>()
        val subMaps = getSubMaps()
        for (map in subMaps) {
            keys.addAll(map._getPropertyNames())
        }
        return keys
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
            ov._setPathInParent(factoryMap.path)
            overlays[factoryMap] = ov
        }
    }

    private fun <T> createOverlay(
        value: T?,
        factoryMap: FactoryMap,
        factory: OverlayFactory<T>,
    ): JsonOverlay<T> {
        return factory.create(value, this, refMgr).also { ov ->
            ov._setPathInParent(factoryMap.path)
            overlays[factoryMap] = ov
        }
    }

    protected fun <T> _getOverlay(factoryMap: FactoryMap, factory: OverlayFactory<T>): JsonOverlay<T>? {
        val pointer = JsonPointer(factoryMap.path)
        val elem = pointer.navigate(json!!)
        return elem?.let { createOverlay(it, factoryMap, factory) }
    }

    override fun _getKeyValueOverlay(name: String): JsonOverlay<*>? {
        return factoryMap[name]?.let { factory ->
            _getOverlay(factory, factory.factory)
        }
    }

    fun <T> _getOverlay(name: String): JsonOverlay<T>? {
        return _getKeyValueOverlay(name) as? JsonOverlay<T>
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

    @Suppress("UNCHECKED_CAST")
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
        val overlay = _getKeyValueOverlay(name) as ListOverlay<*>
        overlay.remove(index)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> _getMap(name: String): MutableMap<String, T> {
        return _get(name)!!
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
        val overlay = _getKeyValueOverlay(name) as MapOverlay<*>
        overlay.remove(key)
    }

    override fun _elaborate(atCreation: Boolean) {
        if (atCreation && deferElaboration) {
            return
        }
        if (elaborationValue != null) {
            _elaborateValue()
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

    private fun _elaborateValue() {
        val overlay = elaborationValue as PropertiesOverlay<*>?
        factoryMap.clear()
        for ((key, value1) in overlay!!.factoryMap) {
            factoryMap[key] = value1
        }
        elaborationValue = null
    }

    override fun _isElaborated(): Boolean {
        return elaborated
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
        factoryMap[name] = FactoryMap(path, factory)
    }

    private fun getPointer(child: JsonOverlay<*>): JsonPointer {
        val path = child._getPathInParent()
        return JsonPointer(if (path.isNullOrEmpty()) "" else "/$path")
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
        val obj = overlays.mapKeys { it.key.path }
            .mapValues { it.value._toJson(options.minus(SerializationOptions.Option.KEEP_ONE_EMPTY)) }
        val result = _fixJson(JsonObject(obj))
        return if (options.isKeepThisEmpty || obj.isNotEmpty()) result else JsonNull
    }

    protected open fun _fixJson(json: JsonElement): JsonElement {
        return json
    }

    override fun equals(other: Any?): Boolean {
        if (!super.equals(other)) return false
        if (other is PropertiesOverlay<*>) {
            return if (this.factoryMap == other.factoryMap) {
                true
            } else {
                this.factoryMap == (other._getRefOverlay()?.overlay as PropertiesOverlay<*>).factoryMap
            }
        }
        return false
    }

    override fun hashCode(): Int {
        var hash = 7
        hash = 31 * hash + factoryMap.hashCode()
        return hash
    }

}
