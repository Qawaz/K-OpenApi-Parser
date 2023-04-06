/*********************************************************************
 * Copyright (c) 2023 WaqasTahir, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * WaqasTahir
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

import com.fasterxml.jackson.databind.node.*
import java.math.BigDecimal
import java.math.BigInteger

class MinSharingJsonNodeFactory : JsonNodeFactory() {

    override fun numberNode(v: Byte): NumericNode {
        return IntNode(v.toInt())
    }

//    override fun numberNode(value: Byte?): ValueNode? {
//        return if (value != null) IntNode(value.toInt()) else nullNode()
//    }

    override fun numberNode(v: Short): NumericNode {
        return ShortNode(v)
    }

//    override fun numberNode(value: Short?): ValueNode? {
//        return if (value != null) ShortNode(value) else nullNode()
//    }

    override fun numberNode(v: Int): NumericNode {
        return IntNode(v)
    }

//    override fun numberNode(value: Int?): ValueNode? {
//        return if (value != null) IntNode(value) else nullNode()
//    }

    override fun numberNode(v: Long): NumericNode {
        return LongNode(v)
    }

//    override fun numberNode(value: Long?): ValueNode? {
//        return if (value != null) LongNode(value) else nullNode()
//    }

    override fun numberNode(v: BigInteger?): NumericNode? {
        return if (v != null) BigIntegerNode(v) else null
    }
//
    override fun numberNode(v: Float): NumericNode {
        return FloatNode(v)
    }

//    override fun numberNode(value: Float?): ValueNode? {
//        return if (value != null) FloatNode(value) else nullNode()
//    }

    override fun numberNode(v: Double): NumericNode {
        return DoubleNode(v)
    }

//    override fun numberNode(value: Double?): ValueNode? {
//        return if (value != null) DoubleNode(value) else nullNode()
//    }

    override fun numberNode(v: BigDecimal?): DecimalNode? {
        return if (v != null) DecimalNode(v) else null
    }

    override fun textNode(text: String?): TextNode? {
        return if (text != null) TextNode(text) else null
    }

    companion object {
        private const val serialVersionUID = 1L
        var instance = MinSharingJsonNodeFactory()
    }
}
