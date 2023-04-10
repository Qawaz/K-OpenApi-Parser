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

class PositionInfo(val pointer: JsonPointer?, val start: PositionEndpoint, val end: PositionEndpoint) {

    var documentUrl: String? = null

    val line: Int
        // convenience methods when only start endpoint is of interest
        get() = start.line
    val column: Int
        get() = start.column

    override fun toString(): String {
        return String.format("%s[%s-%s]", documentUrl, start, end)
    }

    fun toString(startOnly: Boolean): String {
        return if (startOnly) String.format("%s[%s]", documentUrl, start) else toString()
    }

    class PositionEndpoint(val charOffset: Long, val line: Int, val column: Int) {

        override fun toString(): String {
            return String.format("%d:%d", line, column)
        }
    }
}
