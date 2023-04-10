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
import java.math.BigDecimal
import java.math.BigInteger

class NumberOverlay : ScalarOverlay<Number> {

    private constructor(json: JsonElement, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        json,
        parent,
        Companion.factory,
        refMgr
    )

    private constructor(value: Number?, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
        value,
        parent,
        Companion.factory,
        refMgr
    )

    override fun _fromJson(json: JsonElement): Number? {
        if (json !is JsonPrimitive || json.contentOrNull == null) return null
        return if (json.content.contains('.')) {
            json.doubleOrNull
        } else {
            json.intOrNull ?: json.longOrNull
        }
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement {
        if (value == null) {
            return _jsonMissing()
        }
        val type = NumberType.of(value!!)
        return if (type == null) {
            throw IllegalArgumentException(
                "Numeric class " + value!!.javaClass.name + " is not representable as a JsonNode"
            )
        } else {
            when (type) {
                NumberType.BigDecimal -> _jsonScalar(value as BigDecimal)
                NumberType.BigInteger -> _jsonScalar(value as BigInteger)
                NumberType.Byte -> _jsonScalar((value as Byte))
                NumberType.Double -> _jsonScalar((value as Double))
                NumberType.Float -> _jsonScalar((value as Float))
                NumberType.Integer -> _jsonScalar((value as Int))
                NumberType.Long -> _jsonScalar((value as Long))
                NumberType.Short -> _jsonScalar((value as Short))
                else -> _jsonMissing()
            }
        }
    }

    override fun _getFactory(): OverlayFactory<*> {
        return Companion.factory
    }

    private enum class NumberType(private val cls: Class<out Number>) {
        BigDecimal(java.math.BigDecimal::class.java),

        //
        BigInteger(java.math.BigInteger::class.java),

        //
        Byte(java.lang.Byte::class.java),

        //
        Double(java.lang.Double::class.java),

        //
        Float(java.lang.Float::class.java),

        //
        Integer(java.lang.Integer::class.java),

        //
        Long(java.lang.Long::class.java),

        //
        Short(java.lang.Short::class.java);

        companion object {

            private var typeMap: MutableMap<Class<out Number>, NumberType>? = null

            fun <T : Number> of(obj: T): NumberType? {
                if (typeMap == null) {
                    buildTypeMap()
                }
                return typeMap!![obj.javaClass]
            }

            private fun buildTypeMap() {
                typeMap = HashMap()
                for (type in values()) {
                    typeMap!![type.cls] = type
                }
            }
        }
    }

    companion object {
        @JvmField
        var factory: OverlayFactory<Number> = object : OverlayFactory<Number>() {
            override fun getOverlayClass(): Class<NumberOverlay> {
                return NumberOverlay::class.java
            }

            override fun _create(value: Number?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): NumberOverlay {
                return NumberOverlay(value, parent, refMgr)
            }

            public override fun _create(
                json: JsonElement,
                parent: JsonOverlay<*>?,
                refMgr: ReferenceManager
            ): NumberOverlay {
                return NumberOverlay(json, parent, refMgr)
            }
        }

        fun builder(modelMember: JsonOverlay<*>?): Builder<Number> {
            return Builder(factory, modelMember)
        }

        fun create(modelMember: JsonOverlay<*>?): JsonOverlay<Number> {
            return builder(modelMember).build()
        }

        fun create(value: Number?, modelMember: JsonOverlay<*>?): JsonOverlay<Number> {
            val result = create(modelMember)
            result._set(value)
            return result
        }
    }
}