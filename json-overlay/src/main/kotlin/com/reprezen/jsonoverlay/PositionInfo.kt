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

import com.fasterxml.jackson.core.JsonLocation
import com.fasterxml.jackson.core.JsonPointer

class PositionInfo(val pointer: JsonPointer?, val start: PositionEndpoint, val end: PositionEndpoint) {

    /**
     * Sets the document URL for this instance.
     *
     * This is accessible within the package, so it can be used by the JsonOverlay
     * package that obtains instances on demand for associated overlay objects. The
     * document URL is not available to the JSON/YAML parser that actually create
     * the insstances, so it must be filled in later.
     *
     * @param documentUrl
     */
    /* package */ var documentUrl: String? = null

    /**
     * Records the position of a JSON value.
     *
     * Depending on the value type, this may be an intermediate value, including
     * only the position of the beginning of the value. This is the case for the
     * document as a whole in all cases, and also for any containers (objects or
     * arrays), whose end positions are detected later in the parse.
     *
     * @param pointer
     * JsonPointer uniquely identifying this value in the oveall document
     * @param start
     * position data for start of value
     * @param end
     * position data for end of value if known, else repeat of start data
     */
    constructor(pointer: JsonPointer?, start: JsonLocation, end: JsonLocation) : this(
        pointer,
        PositionEndpoint(start),
        PositionEndpoint(end)
    )

    /**
     * Records the position of a JSON value.
     *
     * This is used to update a provisional instance that was created when the end
     * of the value had not yet been detected. The start data is supplied in its
     * processed form, while the end data comes in the raw form provided by the
     * parser.
     *
     * @param pointer
     * JsonPointer uniquely identifying this value in the overall
     * document
     * @param start
     * position data for start of value, in PositionEndpoint form
     * @param end
     * position data for end of value, in raw JsonLocation form
     */
    constructor(pointer: JsonPointer?, start: PositionEndpoint, end: JsonLocation) : this(
        pointer,
        start,
        PositionEndpoint(end)
    )

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

        constructor(location: JsonLocation) : this(location.charOffset, location.lineNr, location.columnNr)

        override fun toString(): String {
            return String.format("%d:%d", line, column)
        }
    }
}
