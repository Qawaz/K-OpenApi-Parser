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
import kotlinx.serialization.json.JsonNull
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

    constructor(map: MapOverlay<V>, key: String) {
        overlay = map._getOverlay(key)
    }

    constructor(list: ListOverlay<V>, index: Int) {
        overlay = list._getOverlay(index)
    }

    constructor(props: PropertiesOverlay<*>, fieldName: String) {
        val overlay = props._getKeyValueOverlay(fieldName) as JsonOverlay<V>
        this.overlay = overlay
    }

    fun get(): V? {
        return overlay._get()
    }

    fun find(path: String): JsonOverlay<*>? {
        if (overlay is KeyValueOverlay) {
            return (overlay as KeyValueOverlay)._findByPath(path)
        }
        return null
    }

    val parent: JsonOverlay<*>?
        get() = overlay._getParent()
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

    val propertyNames: List<String>?
        get() = if (overlay is PropertiesOverlay<*>) {
            (overlay as PropertiesOverlay<*>)._getFactoryKeys()
        } else {
            null
        }

    fun getReference(key: String): Reference? {
        return when (overlay) {
            is PropertiesOverlay<*> -> {
                getPropertyReference(key)
            }

            is MapOverlay<*> -> {
                getMapReference(key)
            }

            else -> {
                null
            }
        }
    }

    private fun getPropertyReference(name: String): Reference? {
        val propsOverlay = overlay as PropertiesOverlay<V>
        return getReference(propsOverlay._getOverlay<Any>(name))
    }

    private fun getMapReference(key: String): Reference? {
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

        fun <V> of(overlay: MapOverlay<V>): Overlay<MutableMap<String, V>> {
            return Overlay(overlay)
        }

        fun <V> of(map: Map<String, V>): Overlay<MutableMap<String, V>>? {
            val overlay = getSidebandOverlay(map)
            return if (overlay != null) Overlay(overlay) else null
        }

        fun <V> of(overlay: ListOverlay<V>): Overlay<MutableList<V>> {
            return Overlay(overlay)
        }

        fun <V> of(list: List<V>): Overlay<MutableList<V>>? {
            val overlay = getSidebandOverlay(list)
            return if (overlay != null) Overlay(overlay) else null
        }

        fun <V> of(map: Map<String, V>, key: String): Overlay<V>? {
            val mapOverlay = of(map)
            return if (mapOverlay != null) Overlay(mapOverlay.overlay as MapOverlay<V>, key) else null
        }

        fun <V> of(list: List<V>, index: Int): Overlay<V>? {
            val listOverlay = of(list)
            return if (listOverlay != null) Overlay(listOverlay.overlay as ListOverlay<V>, index) else null
        }

        fun <X> of(props: PropertiesOverlay<*>, fieldName: String, type: Class<X>?): Overlay<X> {
            return Overlay(props, fieldName)
        }

        fun <X> of(props: IJsonOverlay<*>, fieldName: String, type: Class<X>?): Overlay<X>? {
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

        fun <V> getParent(overlay: JsonOverlay<V>): JsonOverlay<*>? {
            return overlay._getParent()
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
