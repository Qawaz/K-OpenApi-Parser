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

import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import java.util.*
import java.util.stream.Collectors

abstract class PropertiesOverlay<V> : JsonOverlay<V> {
    // retrieve property values from this map by property name
    private val children: MutableMap<String?, JsonOverlay<*>>? = HashMap()

    // this queue sets ordering for serialization, so it matches parsed JSON
    private val childOrder: MutableList<PropertyLocator>? = ArrayList()
    private var elaborated = false
    private var deferElaboration = false
    private var elaborationValue: V? = null

    protected constructor(
        json: JsonNode, parent: JsonOverlay<*>?, factory: OverlayFactory<V>?,
        refMgr: ReferenceManager?
    ) : super(json, parent, factory!!, refMgr!!) {
        deferElaboration = json.isMissingNode
    }

    protected constructor(
        value: V,
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
        return childOrder!!.stream().map { locator: PropertyLocator -> locator.name }.collect(Collectors.toList())
    }

    /* package */
    fun <T> _getOverlay(name: String?): JsonOverlay<*> {
        return children!![name]!!
    }

    protected fun _isPresent(name: String?): Boolean {
        val overlay = children!![name]
        return overlay != null && !overlay.json!!.isMissingNode
    }

    protected fun <T> _get(name: String?, elaborate: Boolean, cls: Class<T>?): T? {
        if (elaborate) {
            _ensureElaborated()
        }
        val overlay = children!![name] as JsonOverlay<T>
        return overlay._get()
    }

    public fun <T> _getOverlay(name: String?, cls: Class<T>?): JsonOverlay<T> {
        return children!![name] as JsonOverlay<T>
    }

    protected fun <T> _setScalar(name: String?, `val`: T?, cls: Class<T>?) {
        val overlay = children!![name] as JsonOverlay<T>
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
        val overlay = children!![name] as ListOverlay<T>
        return overlay[index]!!
    }

    protected fun <T> _setList(name: String?, listVal: MutableList<T>?, cls: Class<T>?) {
        val overlay = children!![name] as ListOverlay<T>
        overlay._set(listVal)
    }

    protected fun <T> _set(name: String?, index: Int, `val`: T, cls: Class<T>?) {
        val overlay = children!![name] as ListOverlay<T>
        overlay[index] = `val`
    }

    protected fun <T> _insert(name: String?, index: Int, `val`: T, cls: Class<T>?) {
        val overlay = children!![name] as ListOverlay<T>
        overlay.insert(index, `val`)
    }

    protected fun <T> _add(name: String?, `val`: T, cls: Class<T>?) {
        val overlay = children!![name] as ListOverlay<T>
        overlay.add(`val`)
    }

    protected fun <T> _remove(name: String?, index: Int, cls: Class<T>?) {
        val overlay = children!![name] as ListOverlay<T>
        overlay.remove(index)
    }

    protected fun <T> _getMap(
        name: String?,
        cls: Class<T>?
    ): Map<String, T> {
        return _get(name, MutableMap::class.java) as Map<String, T>
    }

    protected fun <T> _getMap(
        name: String?,
        elaborate: Boolean,
        cls: Class<T>?
    ): Map<String, T> {
        return _get(name, elaborate, MutableMap::class.java) as Map<String, T>
    }

    protected fun <T> _get(name: String?, key: String?, cls: Class<T>?): T {
        return _get(name, key, true, cls)
    }

    protected fun <T> _get(name: String?, key: String?, elaborate: Boolean, cls: Class<T>?): T {
        val overlay = children!![name] as MapOverlay<T>
        return overlay[key]
    }

    protected fun <T> _setMap(name: String?, mapVal: Map<String?, T>?, cls: Class<T>?) {
        val overlay = children!![name] as MapOverlay<T>
        overlay._set(mapVal)
    }

    protected fun <T> _set(name: String?, key: String?, `val`: T, cls: Class<T>?) {
        val overlay = children!![name] as MapOverlay<T>
        overlay[key] = `val`
    }

    protected fun <T> _remove(name: String?, key: String?, cls: Class<T>?) {
        val overlay = children!![name] as MapOverlay<T>
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
        children!!.clear()
        for ((key, value1) in overlay!!.children!!) {
            children[key] = value1._copy()
        }
        childOrder!!.clear()
        childOrder.addAll(overlay.childOrder!!)
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
        name: String, path: String, valueFactory: OverlayFactory<X>?,
        keyPattern: String?
    ): MapOverlay<X> {
        return _addChild(name, path, MapOverlay.getFactory(valueFactory, keyPattern)) as MapOverlay<X>
    }

    private fun <X> _addChild(name: String, path: String, factory: OverlayFactory<X>): JsonOverlay<X> {
        val pointer = JsonPointer.compile(if (path.isEmpty()) "" else "/$path")
        val childJson = json!!.at(pointer)
        val child = factory.create(childJson, this, refMgr)
        child._setPathInParent(path)
        val locator = PropertyLocator(name, path, json!!)
        childOrder!!.add(locator)
        children!![name] = child
        return child
    }

    override fun _findInternal(path: JsonPointer?): JsonOverlay<*>? {
        for (child in children!!.values) {
            if (matchesPath(child, path)) {
                val found = child._find(tailPath(child, path)!!)
                if (found != null) {
                    return found
                }
            }
        }
        return null
    }

    private fun matchesPath(child: JsonOverlay<*>, path: JsonPointer?): Boolean {
        var path = path
        var childPath = getPointer(child)
        while (!childPath.matches()) {
            if (!childPath.matchesProperty(path!!.matchingProperty)) {
                return false
            } else {
                path = path.tail()
                childPath = childPath.tail()
            }
        }
        return true
    }

    private fun tailPath(child: JsonOverlay<*>, path: JsonPointer?): JsonPointer? {
        var path = path
        var childPath = getPointer(child)
        while (!childPath.matches()) {
            path = path!!.tail()
            childPath = childPath.tail()
        }
        return path
    }

    private fun getPointer(child: JsonOverlay<*>): JsonPointer {
        val path = child._getPathInParent()
        return JsonPointer.compile(if (path == null || path.isEmpty()) "" else "/$path")
    }

    public override fun _fromJson(json: JsonNode): V? {
        // parsing of the json node is expected to be done in the constructor of
        // the
        // subclass, so nothing is done here. But we do establish this object as
        // its own
        // value.
        return this as V
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonNode {
        var obj: JsonNode = _jsonMissing()
        for (child in childOrder!!) {
            val childJson = children!![child.name]!!._toJson(options.minus(SerializationOptions.Option.KEEP_ONE_EMPTY))
            if (!childJson.isMissingNode) {
                obj = _injectChild(obj, childJson, child.pointer)
            }
        }
        val result = _fixJson(obj)
        return if (result.size() > 0 || options.isKeepThisEmpty) result else _jsonMissing()
    }

    private fun _injectChild(node: JsonNode, child: JsonNode, pointer: JsonPointer?): JsonNode {
        var node = node
        return if (pointer!!.matches()) {
            // inject into current node, which means:
            // * If current is missing, return child
            // * If current and child are both objects, merge child into current
            // * Otherwise error
            if (node.isMissingNode) {
                child
            } else if (node.isObject && child.isObject) {
                (node as ObjectNode).setAll(child as ObjectNode)
                node
            } else {
                throw IllegalArgumentException()
            }
        } else if (node.isObject || node.isMissingNode) {
            val name = pointer.matchingProperty
            val childNode = _injectChild(node.path(name), child, pointer.tail())
            if (!childNode.isMissingNode) {
                node = if (node.isObject) node else _jsonObject()
                (node as ObjectNode)[name] = childNode
            }
            node
        } else {
            // can't add a property name to a non-object
            throw IllegalArgumentException()
        }
    }

    protected open fun _fixJson(json: JsonNode): JsonNode {
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
            if (if (children != null) children != otherPO.children else otherPO.children != null) {
                return false
            }
            return if (sameOrder) {
                if (childOrder != null) childOrder == otherPO.childOrder else otherPO.childOrder == null
            } else {
                true
            }
        }
        return false
    }

    override fun hashCode(): Int {
        var hash = 7
        hash = 31 * hash + (children?.hashCode() ?: 0)
        hash = 31 * hash + (childOrder?.hashCode() ?: 0)
        return hash
    }

    protected class PropertyLocator(val name: String, path: String, json: JsonNode) : Comparable<PropertyLocator?> {

        val pointer: JsonPointer?
        private val vector: List<Int>?

        init {
            pointer = JsonPointer.compile(if (path.isEmpty()) "" else "/$path")
            vector = computeVector(pointer, json)
        }

        private fun computeVector(pointer: JsonPointer?, json: JsonNode): List<Int>? {
            // the "position vector" computed by this method is key to keeping our children
            // sorted by the order in which they appeared in a parsed JSON object. The
            // vector list contains the index, in each level of object nesting, of the
            // property on the path to each child's value. The ordering of these vectors
            // thus represents the order in which the properties appeared in the parsed
            // JSON, with missing properties arbitrarily ordered at the end. Root-level maps
            // are also ordered at the end. They are either partial, in which case their
            // actual members may be scattered among other properties and we don't try to
            // maintain that ordering, or they represent the entire root object, in which
            // case the ordering is irrelevant.
            var pointer = pointer
            var currentJson = json
            val result: MutableList<Int> = ArrayList()
            // we only consider object nodes and continue until our pointer is
            // fully
            // consumed
            while (currentJson is ObjectNode && !pointer!!.matches()) {
                val key = pointer.matchingProperty
                var found = false
                var i = 0
                val iter = currentJson.fieldNames()
                while (iter.hasNext()) {
                    if (key == iter.next()) {
                        found = true
                        result.add(i)
                        currentJson = currentJson[key]
                        pointer = pointer!!.tail()
                        break
                    }
                    i += 1
                }
                if (!found) {
                    // no match at current level, so child is not present -
                    // exclude from ordering
                    return null
                }
            }
            // empty vector means the path was empty and matched the root json
            // object. This
            // occurs only with maps, which are excluded from ordering.
            return if (result.isEmpty()) null else result
        }

        override operator fun compareTo(other: PropertyLocator?): Int {
            if(other == null) return -1
            return if (vector == null) {
                if (other.vector == null) name!!.compareTo(other.name!!) else 1
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
            result = prime * result + (name?.hashCode() ?: 0)
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
