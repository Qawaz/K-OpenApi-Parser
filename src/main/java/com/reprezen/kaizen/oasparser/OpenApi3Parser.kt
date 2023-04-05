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
package com.reprezen.kaizen.oasparser

import com.fasterxml.jackson.databind.JsonNode
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import java.io.File
import java.net.URI
import java.net.URL

class OpenApi3Parser : OpenApiParser() {
    override fun parse(spec: String?, resolutionBase: URL?): OpenApi3 {
        return super.parse(spec, resolutionBase)
    }

    override fun parse(spec: String?, resolutionBase: URL?, validate: Boolean): OpenApi3 {
        return super.parse(spec, resolutionBase, validate)
    }

    @Throws(Exception::class)
    override fun parse(specFile: File): OpenApi3 {
        return super.parse(specFile)
    }

    @Throws(Exception::class)
    override fun parse(specFile: File, validate: Boolean): OpenApi3 {
        return super.parse(specFile, validate)
    }

    @Throws(Exception::class)
    override fun parse(uri: URI): OpenApi3 {
        return super.parse(uri)
    }

    @Throws(Exception::class)
    override fun parse(uri: URI, validate: Boolean): OpenApi3 {
        return super.parse(uri, validate)
    }

    @Throws(Exception::class)
    override fun parse(resolutionBase: URL?): OpenApi3 {
        return super.parse(resolutionBase)
    }

    @Throws(Exception::class)
    override fun parse(resolutionBase: URL?, validate: Boolean): OpenApi3 {
        return super.parse(resolutionBase, validate)
    }

    override fun parse(tree: JsonNode?, resolutionBase: URL?): OpenApi3 {
        return super.parse(tree, resolutionBase)
    }

    override fun parse(tree: JsonNode?, resolutionBase: URL?, validate: Boolean): OpenApi3 {
        return super.parse(tree, resolutionBase, validate)
    }

    override fun isVersion3(tree: JsonNode): Boolean {
        return true // if 'openapi' property is missing or incorrect, that will
        // show up as a
        // validation error
    }
}