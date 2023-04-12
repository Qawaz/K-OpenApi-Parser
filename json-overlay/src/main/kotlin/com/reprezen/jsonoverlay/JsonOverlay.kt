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

@Suppress("FunctionName")
abstract class JsonOverlay<V> : IJsonOverlay<V> {

    @JvmField
    var value: V? = null

    @JvmField
    protected var parent: JsonOverlay<*>? = null

    @JvmField
    var json: JsonElement? = null

    @JvmField
    val refMgr: ReferenceManager

    @JvmField
    val factory: OverlayFactory<V>

    private val present: Boolean
        get() = (json != null || value != null)

    private var pathInParent: String? = null

    private var refOverlay: RefOverlay<V>? = null
    private var creatingRef: Reference? = null

    protected constructor(factory: OverlayFactory<V>, refMgr: ReferenceManager) {
        this.factory = factory
        this.refMgr = refMgr
    }

    protected fun load(value: V?, parent: JsonOverlay<*>?) {
        json = null
        this.value = value
        this.parent = parent
    }

    protected constructor(value: V?, parent: JsonOverlay<*>?, factory: OverlayFactory<V>, refMgr: ReferenceManager) {
        json = null
        this.value = value
        this.parent = parent
        this.factory = factory
        this.refMgr = refMgr
    }

    protected fun load(json: JsonElement, parent: JsonOverlay<*>?) {
        this.json = json
        if (Reference.isReferenceNode(json)) {
            refOverlay = RefOverlay(json, parent, factory, refMgr)
        } else {
            value = _fromJson(json)
        }
        this.parent = parent
    }

    protected constructor(
        json: JsonElement,
        parent: JsonOverlay<*>?,
        factory: OverlayFactory<V>,
        refMgr: ReferenceManager
    ) {
        this.json = json
        if (Reference.isReferenceNode(json)) {
            refOverlay = RefOverlay(json, parent, factory, refMgr)
        } else {
            value = _fromJson(json)
        }
        this.parent = parent
        this.factory = factory
        this.refMgr = refMgr
    }

    fun create(): JsonOverlay<V> {
        return builder().build()
    }

    fun builder(): Builder<V> {
        return Builder(_getFactory(), this)
    }

    /* package */ /* package */
    @JvmOverloads
    fun _get(elaborate: Boolean = true): V? {
        return if (_isValidRef()) {
            refOverlay!!._get(elaborate)
        } else if (_isReference()) {
            null
        } else {
            if (elaborate) {
                _ensureElaborated()
            }
            value
        }
    }

    /* package */
    fun _set(value: V?) {
        this.value = value
        refOverlay = null
    }

    /* package */
    fun _isReference(): Boolean {
        return refOverlay != null
    }

    private fun _isValidRef(): Boolean {
        return if (refOverlay != null) refOverlay!!._getReference().isValid else false
    }

    /* package */
    fun _getRefOverlay(): RefOverlay<V>? {
        return refOverlay
    }

    /* package */
    fun _getReference(): Reference? {
        return if (refOverlay != null) refOverlay!!._getReference() else null
    }

    /* package */
    fun _setReference(refOverlay: RefOverlay<V>?) {
        this.refOverlay = refOverlay
    }

    fun _getCreatingRef(): Reference? {
        return creatingRef
    }

    fun _setCreatingRef(creatingRef: Reference?) {
        this.creatingRef = creatingRef
    }

    /* package */
    fun _isPresent(): Boolean {
        return (if (_isValidRef()) refOverlay!!.overlay else this)?.present == true
    }

    /* package */ /* package */
    @JvmOverloads
    fun _getParent(followRef: Boolean = true): JsonOverlay<*>? {
        return (if (followRef && _isValidRef()) refOverlay!!.overlay else this)?.parent
    }

    /* package */
    fun _getRoot(): JsonOverlay<*>? {
        return if (_isValidRef()) {
            refOverlay!!.overlay?._getRoot()
        } else {
            var result: JsonOverlay<*>? = this
            while (result!!._getParent() != null) {
                result = result._getParent()
            }
            result
        }
    }

    /* package */
    fun _getModel(): JsonOverlay<*>? {
        return if (_isValidRef()) {
            refOverlay!!.overlay?._getModel()
        } else {
            var modelPart: JsonOverlay<*>? = if (_getModelType() != null) this else null
            var result: JsonOverlay<*>? = this
            while (result!!._getParent() != null) {
                result = result._getParent()
                modelPart = if (modelPart == null && result!!._getModelType() != null) result else null
            }
            if (modelPart != null && modelPart._getModelType()!!.isAssignableFrom(result.javaClass)) result else null
        }
    }

    protected open fun _getModelType(): Class<*>? {
        return if (_isValidRef()) refOverlay!!.overlay?._getModelType() else null
    }

    /* package */
    fun _getPathFromRoot(): String? {
        return if (parent != null) {
            if (pathInParent!!.isEmpty()) {
                parent!!._getPathFromRoot()
            } else {
                val parentPath = parent!!._getPathFromRoot()
                if (parentPath != null) parentPath + "/" + encodePointerPart(pathInParent) else null
            }
        } else if (creatingRef != null) {
            creatingRef!!.fragment
        } else {
            null
        }
    }

    private fun encodePointerPart(part: String?): String {
        // TODO fix this bogus special case
        return if (part!!.startsWith("components/")) {
            part
        } else part.replace("~".toRegex(), "~0").replace("/".toRegex(), "~1")
    }

    /* package */ /* package */
    @JvmOverloads
    fun _getJsonReference(forRef: Boolean = false): String {
        if (creatingRef != null) {
            return creatingRef!!.normalizedRef!!
        }
        if (_isReference() && refOverlay!!._getReference().isValid && !forRef) {
            return refOverlay!!.overlay!!._getJsonReference(false)
        }
        return if (parent != null) {
            val ref = parent!!._getJsonReference()
            ref + (if (ref.contains("#")) "" else "#") + "/" + pathInParent!!
        } else {
            "#"
        }
    }

    /* package */
    fun _getDocumentUrl(forRef: Boolean): String? {
        val jsonRef = _getJsonReference(forRef)
        val docUrl = if (jsonRef.contains("#")) jsonRef.substring(0, jsonRef.indexOf("#")) else jsonRef
        return docUrl.ifEmpty { null }
    }

    protected abstract fun _fromJson(json: JsonElement): V?

    fun _setParent(parent: JsonOverlay<*>?) {
        this.parent = parent
    }

    /* package */ /* package */
    @JvmOverloads
    fun _toJson(options: SerializationOptions = SerializationOptions.EMPTY): JsonElement {
        return if (_isReference()) {
            if (!options.isFollowRefs || refOverlay!!._getReference().isInvalid) {
                _jsonObject(mapOf("\$ref" to JsonPrimitive(refOverlay!!._getReference().refString)))
            } else {
                refOverlay!!.overlay!!._toJson(options)
            }
        } else {
            _toJsonInternal(options) ?: JsonNull
        }
    }

    /* package */
    fun _toJson(vararg options: SerializationOptions.Option): JsonElement {
        return _toJson(SerializationOptions(*options))
    }

    protected abstract fun _toJsonInternal(options: SerializationOptions): JsonElement?

    /* package */
    fun _getParsedJson(): JsonElement? {
        return json
    }

    private fun thisOrRefTarget(): JsonOverlay<V> {
        return if (refOverlay == null || refOverlay!!._getReference().isInvalid) {
            this
        } else {
            refOverlay!!.overlay!!
        }
    }

    open fun _elaborate(atCreation: Boolean) {
        // most types of overlay don't need to do any elaboration
    }

    open fun _isElaborated(): Boolean {
        return true
    }

    protected fun _ensureElaborated() {
        if (!_isElaborated() && refOverlay == null) {
            _elaborate(false)
        }
    }

    /* package */
    fun _getPathInParent(): String? {
        return pathInParent
    }

    /* package */
    fun _setPathInParent(pathInParent: String?) {
        this.pathInParent = pathInParent
    }

    abstract fun _getFactory(): OverlayFactory<*>


    override fun hashCode(): Int {
        return if (value != null) value.hashCode() else 0
    }

    private fun compareValueAndJson(other: JsonOverlay<*>): Boolean {
        if (value != null && value == other.value) return true
        return json != null && json == other.json
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JsonOverlay<*>) return false
        return if (compareValueAndJson(other)) {
            true
        } else {
            if (_isReference() && !other._isReference()) {
                _getRefOverlay()?.overlay?.let { other.compareValueAndJson(it) } ?: false
            } else if (!_isReference() && other._isReference()) {
                other._getRefOverlay()?.overlay?.let { compareValueAndJson(it) } ?: false
            } else {
                false
            }
        }
    }

    companion object {

        @JvmStatic
        fun _jsonObject(content: Map<String, JsonElement>): JsonObject {
            return JsonObject(content)
        }

        @JvmStatic
        protected fun _jsonArray(content: List<JsonElement>): JsonArray {
            return JsonArray(content)
        }

    }
}