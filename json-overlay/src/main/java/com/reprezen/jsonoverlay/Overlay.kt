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
import com.reprezen.jsonoverlay.PositionInfo.PositionEndpoint
import java.lang.reflect.InvocationTargetException
import java.util.*

class Overlay<V> {

    var overlay: JsonOverlay<V>
        private set

    constructor(overlay: JsonOverlay<V>) {
        this.overlay = overlay
    }

    constructor(overlay: IJsonOverlay<*>) {
        val castOverlay = overlay as JsonOverlay<V>
        this.overlay = castOverlay
    }

    constructor(map: MapOverlay<V>, key: String?) {
        overlay = map._getOverlay(key)
    }

    constructor(list: ListOverlay<V>, index: Int) {
        overlay = list._getOverlay(index)
    }

    constructor(props: PropertiesOverlay<*>, fieldName: String?) {
        val overlay = props._getOverlay<Any>(fieldName) as JsonOverlay<V>
        this.overlay = overlay
    }

    @JvmName("otherGetter")
    fun getOverlay() : JsonOverlay<V> {
        return overlay
    }

    fun get(): V? {
        return overlay._get()
    }

    fun find(path: JsonPointer?): JsonOverlay<*>? {
        return overlay._find(path!!)
    }

    fun find(path: String?): JsonOverlay<*>? {
        return overlay._find(path)
    }

    fun toJson(): JsonNode {
        return overlay._toJson()
    }

    fun toJson(options: SerializationOptions?): JsonNode {
        return overlay._toJson(options!!)
    }

    fun toJson(vararg options: SerializationOptions.Option): JsonNode {
        return overlay._toJson(*options)
    }

    val parsedJson: JsonNode?
        get() = overlay._getParsedJson()
    val isPresent: Boolean
        get() = overlay._isPresent()
    val isElaborated: Boolean
        get() = overlay._isElaborated()
    val parent: JsonOverlay<*>?
        get() = overlay._getParent()
    val parentPropertiesOverlay: PropertiesOverlay<*>?
        get() {
            var parent = parent
            while (parent != null) {
                parent = if (parent is PropertiesOverlay<*>) {
                    return parent
                } else {
                    getParent(parent)
                }
            }
            return null
        }
    val pathInParent: String?
        get() = overlay._getPathInParent()
    val root: JsonOverlay<*>?
        get() = overlay._getRoot()

    fun <Model> getModel(): Model? {
        return overlay._getModel() as Model?
    }

    val pathFromRoot: String?
        get() = overlay._getPathFromRoot()
    val jsonReference: String
        get() = overlay._getJsonReference()

    fun getJsonReference(forRef: Boolean): String {
        return overlay._getJsonReference(forRef)
    }

    val positionInfo: Optional<PositionInfo>?
        get() = overlay._getPositionInfo()
    val startPosition: Optional<PositionEndpoint>
        get() = overlay._getPositionInfo()!!
            .map { info: PositionInfo -> info.start }
    val propertyNames: List<String>?
        get() = if (overlay is PropertiesOverlay<*>) {
            (overlay as PropertiesOverlay<*>)._getPropertyNames()
        } else {
            null
        }

    fun isReference(key: String?): Boolean {
        return getReference(key) != null
    }

    fun isReference(index: Int): Boolean {
        return getReference(index) != null
    }

    val referenceOverlay: Overlay<V>?
        get() {
            val refOverlay = overlay._getRefOverlay()
            return if (refOverlay != null) Overlay(refOverlay.overlay!!) else null
        }

    fun getReference(key: String?): Reference? {
        return if (overlay is PropertiesOverlay<*>) {
            getPropertyReference(key)
        } else if (overlay is MapOverlay<*>) {
            getMapReference(key)
        } else {
            null
        }
    }

    fun getReference(index: Int): Reference? {
        return if (overlay is ListOverlay<*>) {
            getListReference(index)
        } else {
            null
        }
    }

    private fun getPropertyReference(name: String?): Reference? {
        val propsOverlay = overlay as PropertiesOverlay<V>
        return getReference(propsOverlay._getOverlay<Any>(name))
    }

    private fun getMapReference(key: String?): Reference? {
        val mapOverlay = overlay as MapOverlay<V>
        return getReference(mapOverlay._getOverlay(key))
    }

    private fun getListReference(index: Int): Reference? {
        val listOverlay = overlay as ListOverlay<V>
        return getReference(listOverlay._getOverlay(index))
    }

    private fun getReference(overlay: JsonOverlay<*>?): Reference? {
        return overlay?._getReference()
    }

    override fun toString(): String {
        return overlay.toString()
    }

    companion object {
        fun <V> of(overlay: JsonOverlay<V>): Overlay<V> {
            return Overlay(overlay)
        }

        fun <V> of(overlay: IJsonOverlay<V>): Overlay<V> {
            return Overlay(overlay)
        }

        fun <V> of(overlay: MapOverlay<V>): Overlay<Map<String, V>> {
            return Overlay(overlay)
        }

        fun <V> of(map: Map<String, V>): Overlay<Map<String, V>>? {
            val overlay = getSidebandOverlay(map)
            return if (overlay != null) Overlay(overlay) else null
        }

        fun <V> of(overlay: ListOverlay<V>): Overlay<List<V>> {
            return Overlay(overlay)
        }

        fun <V> of(list: List<V>): Overlay<List<V>>? {
            val overlay = getSidebandOverlay(list)
            return if (overlay != null) Overlay(overlay) else null
        }

        fun <V> of(map: MapOverlay<V>, key: String?): Overlay<V> {
            return Overlay(map, key)
        }

        fun <V> of(map: Map<String, V>, key: String?): Overlay<V>? {
            val mapOverlay = of(map)
            return if (mapOverlay != null) of(mapOverlay.overlay as MapOverlay<V>, key) else null
        }

        fun <V> of(list: ListOverlay<V>, index: Int): Overlay<V> {
            return Overlay(list, index)
        }

        fun <V> of(list: List<V>, index: Int): Overlay<V>? {
            val listOverlay = of(list)
            return if (listOverlay != null) of(listOverlay.overlay as ListOverlay<V>, index) else null
        }

        fun <X> of(props: PropertiesOverlay<*>, fieldName: String?, type: Class<X>?): Overlay<X> {
            return Overlay(props, fieldName)
        }

        fun <X> of(props: IJsonOverlay<*>?, fieldName: String?, type: Class<X>?): Overlay<X>? {
            return if (props is PropertiesOverlay<*>) {
                val castProps = props as PropertiesOverlay<X>
                of(castProps, fieldName, type)
            } else {
                null
            }
        }

        operator fun <V> get(overlay: JsonOverlay<V>): V? {
            return overlay._get()
        }

        fun <X> getListOverlay(overlay: Overlay<List<X>>): ListOverlay<X>? {
            return if (overlay.overlay is ListOverlay<*>) {
                getSidebandOverlay(overlay.get()!!)
            } else {
                null
            }
        }

        fun <X> getMapOverlay(overlay: Overlay<Map<String, X>>): MapOverlay<X>? {
            return if (overlay.overlay is MapOverlay<*>) {
                getSidebandOverlay(overlay.get()!!)
            } else {
                null
            }
        }

        fun getPropertiesOverlay(overlay: Overlay<PropertiesOverlay<*>?>): PropertiesOverlay<*>? {
            return if (overlay.overlay is PropertiesOverlay<*>) {
                overlay.overlay as PropertiesOverlay<*>
            } else {
                null
            }
        }

        fun find(overlay: JsonOverlay<*>, path: JsonPointer?): JsonOverlay<*>? {
            return overlay._find(path!!)
        }

        fun <V, OV : JsonOverlay<V>?> find(overlay: OV, path: String?): JsonOverlay<*>? {
            return overlay!!._find(path)
        }

        fun <V> toJson(overlay: JsonOverlay<V>): JsonNode {
            return overlay._toJson()
        }

        fun <V> toJson(overlay: JsonOverlay<V>, options: SerializationOptions?): JsonNode {
            return overlay._toJson(options!!)
        }

        fun <V> toJson(overlay: JsonOverlay<V>, vararg options: SerializationOptions.Option): JsonNode {
            return overlay._toJson(*options)
        }

        fun getParsedJson(overlay: JsonOverlay<*>): JsonNode? {
            return overlay._getParsedJson()
        }

        fun <V> isPresent(overlay: JsonOverlay<V>): Boolean {
            return overlay._isPresent()
        }

        fun <V> isElaborated(overlay: JsonOverlay<V>): Boolean {
            return overlay._isElaborated()
        }

        fun <V> getParent(overlay: JsonOverlay<V>): JsonOverlay<*>? {
            return overlay._getParent()
        }

        fun <V> getParentPropertiesOverlay(overlay: JsonOverlay<V>): PropertiesOverlay<*>? {
            return Overlay(overlay).parentPropertiesOverlay
        }

        fun getPathInParent(overlay: JsonOverlay<*>): String? {
            return overlay._getPathInParent()
        }

        fun getRoot(overlay: JsonOverlay<*>): JsonOverlay<*>? {
            return overlay._getRoot()
        }

        fun <Model, V> getModel(overlay: JsonOverlay<V>): Model {
            return Overlay(overlay).getModel()!!
        }

        fun getPathFromFromRoot(overlay: JsonOverlay<*>): String? {
            return overlay._getPathFromRoot()
        }

        fun getJsonReference(overlay: JsonOverlay<*>): String {
            return overlay._getJsonReference()
        }

        fun getJsonReference(overlay: JsonOverlay<*>, forRef: Boolean): String {
            return overlay._getJsonReference(forRef)
        }

        fun getPositionInfo(overlay: JsonOverlay<*>): Optional<PositionInfo>? {
            return overlay._getPositionInfo()
        }

        fun getStartPosition(overlay: JsonOverlay<*>): Optional<PositionEndpoint> {
            return overlay._getPositionInfo()!!
                .map { info: PositionInfo -> info.start }
        }

        fun <V> getPropertyNames(overlay: JsonOverlay<V>): List<String>? {
            return Overlay(overlay).propertyNames
        }

        fun <V> isReference(overlay: JsonOverlay<V>, key: String?): Boolean {
            return Overlay(overlay).isReference(key)
        }

        fun <V> isReference(overlay: JsonOverlay<V>, index: Int): Boolean {
            return Overlay(overlay).getReference(index) != null
        }

        fun <V> getReferenceOverlay(overlay: JsonOverlay<V>): Overlay<V>? {
            return of(overlay).referenceOverlay
        }

        fun <V> getReference(overlay: JsonOverlay<V>, key: String?): Reference? {
            return Overlay(overlay).getReference(key)
        }

        fun <V> getReference(overlay: JsonOverlay<V>, index: Int): Reference? {
            return Overlay(overlay).getReference(index)
        }

        private fun <X> getSidebandOverlay(list: List<X>): ListOverlay<X>? {
            return try {
                list.javaClass.getMethod("getOverlay").invoke(list) as ListOverlay<X>
            } catch (e: IllegalAccessException) {
                null
            } catch (e: IllegalArgumentException) {
                null
            } catch (e: InvocationTargetException) {
                null
            } catch (e: NoSuchMethodException) {
                null
            } catch (e: SecurityException) {
                null
            }
        }

        private fun <X> getSidebandOverlay(map: Map<String, X>): MapOverlay<X>? {
            return try {
                map.javaClass.getMethod("getOverlay").invoke(map) as MapOverlay<X>
            } catch (e: IllegalAccessException) {
                null
            } catch (e: IllegalArgumentException) {
                null
            } catch (e: InvocationTargetException) {
                null
            } catch (e: NoSuchMethodException) {
                null
            } catch (e: SecurityException) {
                null
            }
        }
    }
}
