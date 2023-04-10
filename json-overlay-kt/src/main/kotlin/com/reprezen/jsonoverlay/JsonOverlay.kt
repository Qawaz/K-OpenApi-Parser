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

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.*
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

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
    private var positionInfo: Optional<PositionInfo>? = null

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
    fun _copy(): JsonOverlay<V> {
        return factory.create(_get(), null, refMgr)
    }

    /* package */
    fun _isReference(): Boolean {
        return refOverlay != null
    }

    private fun _isValidRef(): Boolean {
        return if (refOverlay != null) refOverlay!!._getReference().isValid() else false
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
//    fun _find(path: JsonPointer): JsonOverlay<*>? {
//        return if (path.matches()) thisOrRefTarget() else _findInternal(path)
//    }

    /* package */
//    fun _find(path: String?): JsonOverlay<*>? {
//        return _find(JsonPointer.compile(path))
//    }

    protected abstract fun _findInternal(path: JsonPointer?): JsonOverlay<*>?

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
        if (_isReference() && refOverlay!!._getReference().isValid() && !forRef) {
            return refOverlay!!.overlay!!._getJsonReference(false)
        }
        return if (parent != null) {
            val ref = parent!!._getJsonReference()
            ref + (if (ref.contains("#")) "" else "#") + "/" + pathInParent
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
            _toJsonInternal(options)!!
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

    fun _find(path : String?) : JsonOverlay<*>? {
        return _find(path?.let { JsonPointer(it) })
    }

    fun _find(path : JsonPointer?): JsonOverlay<*>? {
        //TODO
        return null
    }

    /* package */
    fun _getPathInParent(): String? {
        return pathInParent
    }

    /* package */
    fun _setPathInParent(pathInParent: String?) {
        this.pathInParent = pathInParent
    }

    /* package */
    fun _getPositionInfo(): Optional<PositionInfo>? {
        if (positionInfo == null) {
            val ptr = JsonPointer(_getPathFromRoot()!!)
            positionInfo = refMgr.getPositionInfo(ptr)
            positionInfo!!.ifPresent { info: PositionInfo -> info.documentUrl = _getDocumentUrl(true) }
        }
        return positionInfo
    }

    abstract fun _getFactory(): OverlayFactory<*>

    override fun toString(): String {
        return _toJson().toString()
    }

    override fun equals(obj: Any?): Boolean {
        return if (obj is JsonOverlay<*>) {
            if (value != null) value == obj.value else obj.value == null
        } else {
            false // obj is null or not an overlay object
        }
    }

    override fun hashCode(): Int {
        return if (value != null) value.hashCode() else 0
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

        @JvmStatic
        protected fun _jsonScalar(s: String): JsonPrimitive {
            return JsonPrimitive(s)
        }

        @JvmStatic
        protected fun _jsonScalar(n: Int): JsonPrimitive {
            return JsonPrimitive(n)
        }

        @JvmStatic
        protected fun _jsonScalar(n: Long): JsonPrimitive {
            return JsonPrimitive(n)
        }

        @JvmStatic
        protected fun _jsonScalar(n: Short): JsonPrimitive {
            return JsonPrimitive(n)
        }

        @JvmStatic
        protected fun _jsonScalar(n: Byte): JsonPrimitive {
            return JsonPrimitive(n)
        }

        @JvmStatic
        protected fun _jsonScalar(n: Double): JsonPrimitive {
            return JsonPrimitive(n)
        }

        @JvmStatic
        protected fun _jsonScalar(n: Float): JsonPrimitive {
            return JsonPrimitive(n)
        }

        @JvmStatic
        protected fun _jsonScalar(n: BigInteger): JsonPrimitive {
            return JsonPrimitive(n)
        }

        @JvmStatic
        protected fun _jsonScalar(n: BigDecimal): JsonPrimitive {
            return JsonPrimitive(n)
        }

        @JvmStatic
        protected fun _jsonBoolean(b: Boolean): JsonPrimitive {
            return JsonPrimitive(b)
        }

        @JvmStatic
        protected fun _jsonMissing(): JsonNull {
            return JsonNull
        }

    }
}