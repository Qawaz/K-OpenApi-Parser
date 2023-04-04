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
package com.reprezen.jsonoverlay.parser

import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.core.io.IOContext
import com.fasterxml.jackson.core.util.BufferRecycler
import com.fasterxml.jackson.dataformat.yaml.YAMLParser
import com.reprezen.jsonoverlay.PositionInfo
import java.io.IOException
import java.io.Reader

class LocationRecorderYamlParser(
    ctxt: IOContext?, br: BufferRecycler?, parserFeatures: Int, formatFeatures: Int,
    codec: ObjectCodec?, reader: Reader?
) : YAMLParser(ctxt, br, parserFeatures, formatFeatures, codec, reader) {
    private val processor = LocationProcessor()
    val locations: Map<JsonPointer, PositionInfo>
        get() = processor.getLocations()

    @Throws(IOException::class)
    override fun nextToken(): JsonToken {
        val token = super.nextToken()
        val fieldName = if (token == JsonToken.FIELD_NAME) currentName else null
        val tokenStart = tokenLocation
        val tokenEnd = currentLocation
        processor.processTokenLocation(token, fieldName, tokenStart, tokenEnd)
        return token
    }
}
