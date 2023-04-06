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
package com.reprezen.jsonoverlay.parser

import com.fasterxml.jackson.core.JsonLocation
import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.core.JsonToken
import com.reprezen.jsonoverlay.PositionInfo
import java.util.*

class LocationProcessor internal constructor() {
    private val locations: MutableMap<JsonPointer, PositionInfo> = HashMap()
    private val contextStack: Deque<Context> = ArrayDeque()
    private var context // current context, never on the stack
            : Context

    init {
        context = RootContext()
    }

    fun getLocations(): Map<JsonPointer, PositionInfo> {
        return Collections.unmodifiableMap(locations)
    }

    fun processTokenLocation(token: JsonToken?, name: String?, tokenStart: JsonLocation?, tokenEnd: JsonLocation) {
        when (token) {
            JsonToken.FIELD_NAME -> context.setNextProp(name)
            JsonToken.START_OBJECT -> {
                context.startValue()
                contextStack.push(context)
                context = ObjectContext(context, tokenStart)
            }

            JsonToken.END_OBJECT -> {
                recordInfo(context, tokenEnd)
                check(context is ObjectContext)
                context = contextStack.pop()
            }

            JsonToken.START_ARRAY -> {
                context.startValue()
                contextStack.push(context)
                context = ArrayContext(context, tokenStart)
            }

            JsonToken.END_ARRAY -> {
                recordInfo(context, tokenEnd)
                check(context is ArrayContext)
                context = contextStack.pop()
            }

            JsonToken.NOT_AVAILABLE, JsonToken.VALUE_EMBEDDED_OBJECT -> throw IllegalStateException()
            else -> {
                context.startValue()
                recordInfo(context.childPointer, tokenStart, tokenEnd)
            }
        }
    }

    private fun recordInfo(context: Context, end: JsonLocation) {
        recordInfo(context.pointer, context.start, end)
    }

    private fun recordInfo(ptr: JsonPointer, start: JsonLocation?, end: JsonLocation) {
        val pos = PositionInfo(ptr, start!!, end)
        locations[ptr] = pos
    }

    private abstract class Context(var pointer: JsonPointer, var start: JsonLocation?, var end: JsonLocation?) {
        abstract val childPointer: JsonPointer
        abstract fun startValue()
        abstract fun setNextProp(name: String?)
    }

    private class RootContext : Context(JsonPointer.compile(""), null, null) {
        override val childPointer: JsonPointer
            get() = pointer

        override fun startValue() {}
        override fun setNextProp(name: String?) {
            throw IllegalStateException()
        }
    }

    private class ObjectContext(parent: Context, start: JsonLocation?) :
        Context(parent.childPointer, start, null) {
        private var currentProp: String? = null
        private var nextProp: String? = null
        override val childPointer: JsonPointer
            get() = if (currentProp != null) {
                val child = JsonPointer.compile(
                    "/" + encode(
                        currentProp!!
                    )
                )
                pointer.append(child)
            } else {
                throw IllegalStateException()
            }

        private fun encode(s: String): String {
            return s.replace("~".toRegex(), "~0").replace("/".toRegex(), "~1")
        }

        override fun startValue() {
            if (nextProp != null) {
                currentProp = nextProp
                nextProp = null
            } else {
                throw IllegalStateException()
            }
        }

        override fun setNextProp(name: String?) {
            if (nextProp == null) {
                nextProp = name
            } else {
                throw IllegalStateException()
            }
        }
    }

    private class ArrayContext(parent: Context, start: JsonLocation?) : Context(parent.childPointer, start, null) {
        private var currentIndex = -1
        override val childPointer: JsonPointer
            get() = if (currentIndex >= 0) {
                pointer.append(JsonPointer.compile("/$currentIndex"))
            } else {
                throw IllegalStateException()
            }

        override fun startValue() {
            currentIndex += 1
        }

        override fun setNextProp(name: String?) {
            throw IllegalStateException()
        }
    }
}