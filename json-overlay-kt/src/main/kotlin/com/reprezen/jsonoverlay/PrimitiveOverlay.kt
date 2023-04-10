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

class PrimitiveOverlay private constructor(
    value: Any?,
    parent: JsonOverlay<*>?,
    refMgr: ReferenceManager
) : ScalarOverlay<Any>(Companion.factory, refMgr) {

    init {
        if (value != null && value is JsonElement) {
            load(value, parent)
        } else {
            load(value, parent)
        }
    }

    override fun _fromJson(json: JsonElement): Any? {
        if (json !is JsonPrimitive) return null
        return json.toValue()
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonElement? {
        return when (value) {
            null -> {
                _jsonMissing()
            }

            is String -> {
                _jsonScalar(value as String)
            }

            is BigDecimal -> {
                _jsonScalar(value as BigDecimal)
            }

            is BigInteger -> {
                _jsonScalar(value as BigInteger)
            }

            is Byte -> {
                _jsonScalar((value as Byte))
            }

            is Double -> {
                _jsonScalar((value as Double))
            }

            is Float -> {
                _jsonScalar((value as Float))
            }

            is Int -> {
                _jsonScalar((value as Int))
            }

            is Long -> {
                _jsonScalar((value as Long))
            }

            is Short -> {
                _jsonScalar((value as Short))
            }

            else -> {
                null
            }
        }
    }

    override fun _getFactory(): OverlayFactory<*> {
        return Companion.factory
    }

    companion object {
        @JvmField
        var factory: OverlayFactory<Any> = object : OverlayFactory<Any>() {
            override fun getOverlayClass(): Class<PrimitiveOverlay> {
                return PrimitiveOverlay::class.java
            }

            override fun _create(value: Any?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): PrimitiveOverlay {
                return PrimitiveOverlay(value, parent, refMgr)
            }

            public override fun _create(
                json: JsonElement,
                parent: JsonOverlay<*>?,
                refMgr: ReferenceManager
            ): PrimitiveOverlay {
                return PrimitiveOverlay(json, parent, refMgr)
            }
        }
    }
}