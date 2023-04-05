/*******************************************************************************
 * Copyright (c) 2017 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ModelSolv, Inc. - initial API and implementation and/or initial documentation
 */
package com.reprezen.kaizen.oasparser.`val`

import java.math.BigDecimal
import java.math.BigInteger

object NumericUtils {
    fun isNumeric(obj: Number): Boolean {
        return NumericType.of(obj) != null
    }

    fun isIntegral(obj: Number): Boolean {
        return when (NumericType.of(obj)) {
            NumericType.BIG_INTEGER, NumericType.BYTE, NumericType.INTEGER, NumericType.LONG, NumericType.SHORT -> true
            else -> false
        }
    }

    fun <T : Number> gt(x: T, y: T): Boolean {
        return compare(x, y) > 0
    }

    fun <T : Number> ge(x: T, y: T): Boolean {
        return compare(x, y) >= 0
    }

    fun <T : Number> lt(x: T, y: T): Boolean {
        return compare(x, y) < 0
    }

    fun <T : Number> le(x: T, y: T): Boolean {
        return compare(x, y) <= 0
    }

    fun <T : Number> eq(x: T, y: T): Boolean {
        return compare(x, y) == 0
    }

    fun <T : Number> ne(x: T, y: T): Boolean {
        return compare(x, y) != 0
    }

    fun <T : Number> isPositive(x: T): Boolean {
        return x.toInt() > 0
    }

    fun <T : Number> isZero(x: T): Boolean {
        return x.toInt() == 0
    }

    fun <T : Number> isNegative(x: T): Boolean {
        return x.toInt() < 0
    }

    fun <T : Number> isNonNegative(x: T): Boolean {
        return x.toInt() >= 0
    }

    fun <T : Number> isnonZero(x: T): Boolean {
        return x.toInt() != 0
    }

    fun <T : Number> isNonPostive(x: T): Boolean {
        return x.toInt() <= 0
    }

    fun <T : Number> compare(x: T, y: T): Int {
        val type = NumericType.of(x)
        require(type == NumericType.of(y))
        return when (type) {
            NumericType.BIG_DECIMAL -> (x as BigDecimal).compareTo(y as BigDecimal)
            NumericType.BIG_INTEGER -> (x as BigInteger).compareTo(y as BigInteger)
            NumericType.BYTE -> (x as Byte).compareTo((y as Byte))
            NumericType.DOUBLE -> (x as Double).compareTo(
                (y as Double)
            )

            NumericType.FLOAT -> (x as Float).compareTo((y as Float))
            NumericType.INTEGER -> (x as Int).compareTo((y as Int))
            NumericType.LONG -> (x as Long).compareTo((y as Long))
            NumericType.SHORT -> (x as Short).compareTo((y as Short))
            else -> throw IllegalArgumentException()
        }
    }

    internal enum class NumericType(cls: Class<out Number>) {
        BIG_DECIMAL(BigDecimal::class.java),  //
        BIG_INTEGER(BigInteger::class.java),  //
        BYTE(Byte::class.java),  //
        DOUBLE(Double::class.java),  //
        FLOAT(Float::class.java),  //
        INTEGER(Int::class.java),  //
        LONG(Long::class.java),  //
        SHORT(Short::class.java);

        init {
            register(cls, this)
        }

        private fun register(cls: Class<out Number>, type: NumericType) {
            if (types == null) types = HashMap()
            types!![cls] = type
        }

        companion object {
            private var types: MutableMap<Class<out Number>, NumericType>? = null
            fun of(value: Number?): NumericType? {
                return if (value != null) types!!.get(value.javaClass) else null
            }
        }
    }
}