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
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import kotlin.collections.Collection
import kotlin.collections.HashMap
import kotlin.collections.MutableMap
import kotlin.collections.listOf
import kotlin.collections.mutableListOf
import kotlin.collections.set

class ScalarTests {

    @RunWith(Parameterized::class)
    class StringTests(value: String?) : ScalarTestBase<String>(StringOverlay.factory) {

        init {
            this.value = value
        }

        override fun toJson(value: String?): JsonElement {
            return if (value != null) JsonPrimitive(value) else JsonNull
        }

        companion object {
            @JvmStatic
            @get:Parameterized.Parameters
            val values: Collection<String>
                get() = listOf("hello", "")
        }
    }

    @RunWith(Parameterized::class)
    class BooleanTests(value: Boolean?) : ScalarTestBase<Boolean>(BooleanOverlay.factory) {
        init {
            this.value = value
        }

        override fun toJson(value: Boolean?): JsonElement {
            return if (value != null) JsonPrimitive(value) else JsonNull
        }

        companion object {
            @JvmStatic
            @get:Parameterized.Parameters
            val values: Collection<Boolean>
                get() = listOf(true, false)
        }
    }

    @RunWith(Parameterized::class)
    class IntegerTests(value: Int?) : ScalarTestBase<Int>(IntegerOverlay.factory) {

        init {
            this.value = value
        }

        override fun toJson(value: Int?): JsonElement {
            return if (value != null) JsonPrimitive(value) else JsonNull
        }

        companion object {
            @JvmStatic
            @get:Parameterized.Parameters
            val values: Collection<Int>
                get() = listOf(0, 1, -1, Int.MAX_VALUE, Int.MIN_VALUE)
        }
    }

    @RunWith(Parameterized::class)
    class NumberTests(value: Number?) : ScalarTestBase<Number>(NumberOverlay.factory) {
        init {
            this.value = value
        }

        override fun toJson(value: Number?): JsonElement {
            return numberToJson(value)
        }

        companion object {
            @JvmStatic
            @get:Parameterized.Parameters
            val values: Collection<Number>
                get() = listOf<Number>( //
                    BigDecimal.ZERO,
                    BigDecimal.ONE,
                    BigDecimal("-1"),
                    BigDecimal("4323433423423423423424234234234234.342342313434253432342412342342342232"),  //
                    BigInteger.ZERO,
                    BigInteger.ONE,
                    BigInteger("-1"),  //
                    BigInteger("312371230981234712398471234912873491283471293847129348712349821374129347823"),  //
                    0.0,
                    1.0,
                    -1.0,
                    Double.MAX_VALUE,
                    -Double.MAX_VALUE,
                    Double.MIN_VALUE,
                    -Double.MIN_VALUE,  //
                    0.0,
                    1.0,
                    -1.0,
                    Float.MAX_VALUE,
                    -Float.MAX_VALUE,
                    Float.MIN_VALUE,
                    -Float.MIN_VALUE,  //
                    0,
                    1,
                    -1,
                    Int.MAX_VALUE,
                    Int.MIN_VALUE,  //
                    0L,
                    1L,
                    -1L,
                    Long.MAX_VALUE,
                    Long.MIN_VALUE,
                    0.toShort(),
                    1.toShort(),
                    ((-1).toShort()),
                    Short.MAX_VALUE,
                    Short.MIN_VALUE
                )

            // broken out so can be reused in PrimitiveTests
            fun numberToJson(value: Number?): JsonElement {
                when (value) {
                    null -> {
                        return JsonNull
                    }
                    is BigDecimal -> {
                        return JsonPrimitive(value as BigDecimal?)
                    }

                    is BigInteger -> {
                        return JsonPrimitive(value as BigInteger?)
                    }

                    is Double -> {
                        return JsonPrimitive(value as Double?)
                    }

                    is Float -> {
                        return JsonPrimitive(value as Float?)
                    }

                    is Int -> {
                        return JsonPrimitive(value as Int?)
                    }

                    is Long -> {
                        return JsonPrimitive(value as Long?)
                    }

                    is Short -> {
                        return JsonPrimitive(value as Short?)
                    }

                    else -> throw IllegalArgumentException()
                }
            }
        }
    }

    @RunWith(Parameterized::class)
    class PrimitiveTests(value: Any?) : ScalarTestBase<Any>(PrimitiveOverlay.factory) {
        init {
            this.value = value
        }

        override fun toJson(value: Any?): JsonElement {
            return when (value) {
                null -> {
                    JsonNull
                }
                is Number -> {
                    NumberTests.numberToJson(value as Number?)
                }

                is String -> {
                    JsonPrimitive(value as String?)
                }

                else -> {
                    throw IllegalArgumentException()
                }
            }
        }

        companion object {
            @JvmStatic
            @get:Parameterized.Parameters
            val values: Collection<Any>
                get() = listOf<Any>( //
                    "hello",
                    "",  //
                    BigDecimal.ZERO,
                    BigDecimal.ONE,
                    BigDecimal("-1"),
                    BigDecimal("4323433423423423423424234234234234.342342313434253432342412342342342232"),  //
                    BigInteger.ZERO,
                    BigInteger.ONE,
                    BigInteger("-1"),  //
                    BigInteger("312371230981234712398471234912873491283471293847129348712349821374129347823"),  //
                    0.0,
                    1.0,
                    -1.0,
                    Double.MAX_VALUE,
                    -Double.MAX_VALUE,
                    Double.MIN_VALUE,
                    -Double.MIN_VALUE,  //
                    0.0,
                    1.0,
                    -1.0,
                    Float.MAX_VALUE,
                    -Float.MAX_VALUE,
                    Float.MIN_VALUE,
                    -Float.MIN_VALUE,  //
                    0,
                    1,
                    -1,
                    Int.MAX_VALUE,
                    Int.MIN_VALUE,  //
                    0L,
                    1L,
                    -1L,
                    Long.MAX_VALUE,
                    Long.MIN_VALUE,
                    0.toShort(),
                    1.toShort(),
                    ((-1).toShort()),
                    Short.MAX_VALUE,
                    Short.MIN_VALUE
                )
        }
    }

    @RunWith(Parameterized::class)
    class ObjectTests(value: Any?) : ScalarTestBase<Any>(ObjectOverlay.factory) {
        init {
            this.value = value
        }

        override fun toJson(value: Any?): JsonElement {
            return value.toJsonElement()
        }

        override fun testWithWrongJson() {
            // there's no "wrong json" for this overlay, so this test is a no-op
        }

        companion object {
            @JvmStatic
            @get:Parameterized.Parameters
            val values: Collection<Any>
                get() {
                    val map: MutableMap<String, Any?> = HashMap()
                    map["x"] = 1
                    map["y"] = null
                    map["z"] = mutableListOf("a", "b", "c")
                    return listOf(
                        "foo", 1, 1.0, mutableListOf(0, 1, 2),
                        listOf(3, "blah", mutableListOf(1, 2, 3)), map
                    )
                }
        }
    }

    enum class XEnum {
        A,
        B,
        C
    }

    @RunWith(Parameterized::class)
    class EnumTests(value: XEnum?) : ScalarTestBase<XEnum>(XEnumOverlay.factory) {
        init {
            this.value = value
        }

        override fun toJson(value: XEnum?): JsonElement {
            return if (value != null) JsonPrimitive(value.name) else JsonNull
        }

        class XEnumOverlay : EnumOverlay<XEnum> {

            constructor(json: JsonElement, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
                json,
                parent,
                Companion.factory,
                refMgr
            )

            constructor(value: XEnum?, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
                value,
                parent,
                Companion.factory,
                refMgr
            )

            override fun getEnumValue(value: String): XEnum {
                return XEnum.valueOf(value)
            }

            override fun _getFactory(): OverlayFactory<*> {
                return Companion.factory
            }

            companion object {

                var factory: OverlayFactory<XEnum> = object : OverlayFactory<XEnum>() {

                    override fun getOverlayClass(): Class<out JsonOverlay<in XEnum>> {
                        return XEnumOverlay::class.java
                    }

                    override fun _create(
                        value: XEnum?,
                        parent: JsonOverlay<*>?,
                        refMgr: ReferenceManager
                    ): JsonOverlay<XEnum> {
                        return XEnumOverlay(value, parent, refMgr)
                    }

                    public override fun _create(
                        json: JsonElement,
                        parent: JsonOverlay<*>?,
                        refMgr: ReferenceManager
                    ): JsonOverlay<XEnum> {
                        return XEnumOverlay(json, parent, refMgr)
                    }
                }
            }
        }

        companion object {
            @JvmStatic
            @get:Parameterized.Parameters
            val values: Collection<XEnum>
                get() = XEnum.values().toList()
        }
    }
}
