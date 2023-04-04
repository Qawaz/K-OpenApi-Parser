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

import com.fasterxml.jackson.databind.JsonNode
import java.math.BigDecimal
import java.math.BigInteger

class PrimitiveOverlay private constructor(
    isJson: Boolean,
    value: Any?,
    parent: JsonOverlay<*>?,
    refMgr: ReferenceManager
) : ScalarOverlay<Any>(Companion.factory, refMgr) {

    init {
        if (isJson && value != null && value is JsonNode) {
            load(value, parent)
        } else {
            load(value, parent)
        }
    }

    override fun _fromJson(json: JsonNode): Any? {
        return if (json.isTextual) {
            json.textValue()
        } else if (json.isNumber) {
            json.numberValue()
        } else if (json.isBoolean) {
            json.booleanValue()
        } else {
            null
        }
    }

    override fun _toJsonInternal(options: SerializationOptions): JsonNode? {
        return if (value == null) {
            _jsonMissing()
        } else if (value is String) {
            _jsonScalar(value as String)
        } else if (value is BigDecimal) {
            _jsonScalar(value as BigDecimal)
        } else if (value is BigInteger) {
            _jsonScalar(value as BigInteger)
        } else if (value is Byte) {
            _jsonScalar((value as Byte))
        } else if (value is Double) {
            _jsonScalar((value as Double))
        } else if (value is Float) {
            _jsonScalar((value as Float))
        } else if (value is Int) {
            _jsonScalar((value as Int))
        } else if (value is Long) {
            _jsonScalar((value as Long))
        } else if (value is Short) {
            _jsonScalar((value as Short))
        } else {
            null
        }
    }

    override fun _getFactory(): OverlayFactory<Any> {
        return Companion.factory
    }

    companion object {
        @JvmField
        var factory: OverlayFactory<Any> = object : OverlayFactory<Any>() {
            override fun getOverlayClass(): Class<PrimitiveOverlay> {
                return PrimitiveOverlay::class.java
            }

            override fun _create(value: Any?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): PrimitiveOverlay {
                return PrimitiveOverlay(isJson = false, value, parent, refMgr)
            }

            public override fun _create(
                json: JsonNode,
                parent: JsonOverlay<*>?,
                refMgr: ReferenceManager
            ): PrimitiveOverlay {
                return PrimitiveOverlay(isJson = true, json, parent, refMgr)
            }
        }
    }
}
