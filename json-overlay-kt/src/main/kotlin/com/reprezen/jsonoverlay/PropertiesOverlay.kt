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
import java.util.*
import java.util.stream.Collectors

abstract class PropertiesOverlay<V> : JsonOverlay<V> {

    // retrieve property values from this map by property name
    private val children: MutableMap<String?, JsonOverlay<*>> = HashMap()

    // this queue sets ordering for serialization, so it matches parsed JSON
    private val childOrder: MutableList<PropertyLocator> = ArrayList()
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

    protected fun <T> _get(name: String?, cls: Class<T>?): T? {
        return _get(name, true, cls)
    }

    /* package */
    fun _getPropertyNames(): List<String> {
        return childOrder.stream().map { locator: PropertyLocator -> locator.name }.collect(Collectors.toList())
    }

    /* package */
    fun <T> _getOverlay(name: String?): JsonOverlay<*> {
        return children[name]!!
    }

    protected fun _isPresent(name: String?): Boolean {
        val overlay = children[name]
        return overlay != null && overlay.json != null
    }

    protected fun <T> _get(name: String?, elaborate: Boolean, cls: Class<T>?): T? {
        if (elaborate) {
            _ensureElaborated()
        }
        val overlay = children[name] as JsonOverlay<T>
        return overlay._get()
    }

    fun <T> _getOverlay(name: String?, cls: Class<T>?): JsonOverlay<T> {
        return children[name] as JsonOverlay<T>
    }

    protected fun <T> _setScalar(name: String?, `val`: T?, cls: Class<T>?) {
        val overlay = children[name] as JsonOverlay<T>
        overlay._set(`val`)
    }

    protected fun <T> _getList(name: String?, cls: Class<T>?): MutableList<T> {
        return _get(name, MutableList::class.java)!! as MutableList<T>
    }

    protected fun <T> _getList(
        name: String?,
        elaborate: Boolean,
        cls: Class<T>?
    ): MutableList<T> {
        return _get(name, elaborate, MutableList::class.java)!! as MutableList<T>
    }

    protected fun <T> _get(name: String?, index: Int, cls: Class<T>?): T {
        return _get(name, index, true, cls)
    }

    protected fun <T> _get(name: String?, index: Int, elaborate: Boolean, cls: Class<T>?): T {
        val overlay = children[name] as ListOverlay<T>
        return overlay[index]!!
    }

    protected fun <T> _setList(name: String?, listVal: MutableList<T>?, cls: Class<T>?) {
        val overlay = children[name] as ListOverlay<T>
        overlay._set(listVal)
    }

    protected fun <T> _set(name: String?, index: Int, `val`: T, cls: Class<T>?) {
        val overlay = children[name] as ListOverlay<T>
        overlay[index] = `val`
    }

    protected fun <T> _insert(name: String?, index: Int, `val`: T, cls: Class<T>?) {
        val overlay = children[name] as ListOverlay<T>
        overlay.insert(index, `val`)
    }

    protected fun <T> _add(name: String?, `val`: T, cls: Class<T>?) {
        val overlay = children[name] as ListOverlay<T>
        overlay.add(`val`)
    }

    protected fun <T> _remove(name: String?, index: Int, cls: Class<T>?) {
        val overlay = children[name] as ListOverlay<T>
        overlay.remove(index)
    }

    protected fun <T> _getMap(
        name: String?,
        cls: Class<T>?
    ): MutableMap<String, T> {
        return _get(name, MutableMap::class.java) as MutableMap<String, T>
    }

    protected fun <T> _getMap(
        name: String?,
        elaborate: Boolean,
        cls: Class<T>?
    ): MutableMap<String, T> {
        return _get(name, elaborate, MutableMap::class.java) as MutableMap<String, T>
    }

    protected fun <T> _get(name: String?, key: String, cls: Class<T>?): T? {
        return _get(name, key, true, cls)
    }

    protected fun <T> _get(name: String?, key: String, elaborate: Boolean, cls: Class<T>?): T? {
        val overlay = children[name] as MapOverlay<T>
        return overlay[key]
    }

    protected fun <T> _setMap(name: String?, mapVal: MutableMap<String, T>?, cls: Class<T>?) {
        val overlay = children[name] as MapOverlay<T>
        overlay._set(mapVal)
    }

    protected fun <T> _set(name: String?, key: String, `val`: T, cls: Class<T>?) {
        val overlay = children[name] as MapOverlay<T>
        overlay[key] = `val`
    }

    protected fun <T> _remove(name: String?, key: String, cls: Class<T>?) {
        val overlay = children[name] as MapOverlay<T>
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
        Collections.sort(childOrder)
        elaborated = true
    }

    protected open fun _elaborateJson() {
        // need an implemenatation here because subclasses invoke method in
        // super, to
        // support type extensions
    }

    private fun _elaborateValue() {
        val overlay = elaborationValue as PropertiesOverlay<V>?
        children.clear()
        for ((key, value1) in overlay!!.children) {
            children[key] = value1._copy()
        }
        childOrder.clear()
        childOrder.addAll(overlay.childOrder)
        elaborationValue = null
    }

    override fun _isElaborated(): Boolean {
        return elaborated
    }

    protected fun <X> _createScalar(name: String, path: String, factory: OverlayFactory<X>): JsonOverlay<X> {
        return _addChild(name, path, factory)
    }

    protected fun <X> _createList(name: String, path: String, itemFactory: OverlayFactory<X>): ListOverlay<X> {
        return _addChild(name, path, ListOverlay.getFactory(itemFactory)) as ListOverlay<X>
    }

    protected fun <X> _createMap(
        name: String, path: String, valueFactory: OverlayFactory<X>,
        keyPattern: String?
    ): MapOverlay<X> {
        return _addChild(name, path, MapOverlay.getFactory(valueFactory, keyPattern)) as MapOverlay<X>
    }

    private fun <X> _addChild(name: String, path: String, factory: OverlayFactory<X>): JsonOverlay<X> {
        val pointer = JsonPointer(if (path.isEmpty()) "" else "/$path")
        val childJson = pointer.navigate(json!!)!!
        val child = factory.create(childJson, this, refMgr)
        child._setPathInParent(path)
        val locator = PropertyLocator(name, path, json!!)
        childOrder.add(locator)
        children[name] = child
        return child
    }

    override fun _findInternal(path: JsonPointer?): JsonOverlay<*>? {
        for (child in children.values) {
            if (matchesPath(child, path!!)) {
                val found = tailPath(child, path)!!.navigate(child.json!!)
                if (found != null) return child
            }
        }
        return null
    }

    private fun matchesPath(child: JsonOverlay<*>, path: JsonPointer): Boolean {
        var path = path
        var childPath = getPointer(child)
        while (childPath.navigate(json!!) == null) {
            if (childPath.segments.lastOrNull() != path.segments.lastOrNull()) {
                return false
            } else {
                path = JsonPointer(path.segments.drop(1))
                childPath = JsonPointer(childPath.segments.drop(1))
            }
        }
        return true
    }

    private fun tailPath(child: JsonOverlay<*>, path: JsonPointer): JsonPointer? {
        var path = path
        var childPath = getPointer(child)
        while (childPath.navigate(json!!) == null) {
            path = JsonPointer(path!!.segments.drop(1))
            childPath = JsonPointer(childPath.segments.drop(1))
        }
        return path
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
        var obj: JsonElement = _jsonMissing()
        for (child in childOrder) {
            val childJson = children[child.name]!!._toJson(options.minus(SerializationOptions.Option.KEEP_ONE_EMPTY))
            obj = _injectChild(obj, childJson, child.pointer)
        }
        val result = _fixJson(obj)
        return if (options.isKeepThisEmpty || childOrder.size > 0) result else _jsonMissing()
    }

    private fun _injectChild(node: JsonElement, child: JsonElement, pointer: JsonPointer?): JsonElement {
        var currentElement: JsonElement = node
        if (pointer?.navigate(currentElement) != null) {
            if (currentElement is JsonNull) {
                return child
            } else if (currentElement is JsonObject && child is JsonObject) {
                for ((key, value) in child) {
                    currentElement = JsonObject(currentElement.jsonObject.toMutableMap().apply {
                        this[key] = _injectChild(this[key] ?: JsonNull, value, JsonPointer(pointer.segments.drop(1)))
                    })
                }
                return currentElement
            } else {
                throw IllegalArgumentException("Cannot inject child into non-object node")
            }
        } else {
            if (currentElement !is JsonObject && currentElement !is JsonNull) {
                throw IllegalArgumentException("Cannot add property name to non-object node")
            }
            val name = pointer?.segments?.lastOrNull() ?: throw IllegalArgumentException("Pointer is null or empty")
            currentElement = currentElement as? JsonObject ?: JsonObject(emptyMap())
            currentElement = JsonObject(currentElement.jsonObject.toMutableMap().apply {
                this[name] = _injectChild(this[name] ?: JsonNull, child, JsonPointer(pointer!!.segments.drop(1)))
            })
            return currentElement
        }
    }

    protected open fun _fixJson(json: JsonElement): JsonElement {
        return json
    }

    override fun equals(other: Any?): Boolean {
        return equals(other, false)
    }

    fun equals(other: Any?, sameOrder: Boolean): Boolean {
        if (other != null && javaClass == other.javaClass) {
            val otherPO = other as PropertiesOverlay<*>
            if (elaborated != otherPO.elaborated) {
                return false
            }
            if (children != otherPO.children) {
                return false
            }
            return if (sameOrder) {
                childOrder == otherPO.childOrder
            } else {
                true
            }
        }
        return false
    }

    override fun hashCode(): Int {
        var hash = 7
        hash = 31 * hash + children.hashCode()
        hash = 31 * hash + childOrder.hashCode()
        return hash
    }

    protected class PropertyLocator(val name: String, path: String, json: JsonElement) : Comparable<PropertyLocator?> {

        val pointer: JsonPointer?
        private val vector: List<Int>?

        init {
            pointer = JsonPointer(if (path.isEmpty()) "" else "/$path")
            vector = computeVector(pointer, json)
        }

        private fun computeVector(pointer: JsonPointer?, json: JsonElement): List<Int>? {
            var pointer = pointer
            var currentJson = json
            val result: MutableList<Int> = mutableListOf()
            // we only consider object nodes and continue until our pointer is fully consumed
            while (currentJson is JsonObject && pointer!!.segments.isNotEmpty()) {
                val key = pointer.segments.first()
                val index = currentJson.keys.indexOf(key)
                if (index == -1) {
                    // no match at current level, so child is not present - exclude from ordering
                    return null
                }
                result.add(index)
                currentJson = currentJson.getValue(key)
                pointer = JsonPointer(pointer.segments.drop(1))
            }
            // empty vector means the path was empty and matched the root json object
            return if (result.isEmpty()) null else result
        }

        override operator fun compareTo(other: PropertyLocator?): Int {
            if (other == null) return -1
            return if (vector == null) {
                if (other.vector == null) name.compareTo(other.name) else 1
            } else if (other.vector == null) {
                -1
            } else {
                var cmp = 0
                // first component where paths differ determines relative
                // ordering
                var i = 0
                while (cmp == 0 && i < vector.size && i < other.vector.size) {
                    cmp = vector[i] - other.vector[i]
                    i++
                }
                cmp
            }
        }

        override fun hashCode(): Int {
            val prime = 31
            var result = 1
            result = prime * result + name.hashCode()
            result = prime * result + (pointer?.hashCode() ?: 0)
            result = prime * result + (vector?.hashCode() ?: 0)
            return result
        }

        override fun equals(obj: Any?): Boolean {
            if (this === obj) return true
            if (obj == null) return false
            if (javaClass != obj.javaClass) return false
            val other = obj as PropertyLocator
            if (name == null) {
                if (other.name != null) return false
            } else if (name != other.name) return false
            if (pointer == null) {
                if (other.pointer != null) return false
            } else if (pointer != other.pointer) return false
            if (vector == null) {
                if (other.vector != null) return false
            } else if (vector != other.vector) return false
            return true
        }

        override fun toString(): String {
            return String.format("Loc[%s]=%s", name, vector)
        }
    }
}
