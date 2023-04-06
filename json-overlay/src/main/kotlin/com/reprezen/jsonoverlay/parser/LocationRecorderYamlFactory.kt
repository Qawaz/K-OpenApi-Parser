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

import com.fasterxml.jackson.core.io.IOContext
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLParser
import java.io.IOException
import java.io.InputStream
import java.io.Reader

class LocationRecorderYamlFactory : YAMLFactory() {
    @Throws(IOException::class)
    override fun _createParser(`in`: InputStream, ctxt: IOContext): YAMLParser {
        return LocationRecorderYamlParser(
            ctxt, _getBufferRecycler(), _parserFeatures, _yamlParserFeatures,
            _objectCodec, _createReader(`in`, null, ctxt)
        )
    }

    override fun _createParser(r: Reader, ctxt: IOContext): YAMLParser {
        return LocationRecorderYamlParser(
            ctxt, _getBufferRecycler(), _parserFeatures, _yamlParserFeatures,
            _objectCodec, r
        )
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
