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
import com.reprezen.jsonoverlay.JsonLoader
import com.reprezen.jsonoverlay.ReferenceManager
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.ovl3.OpenApi3Impl
import java.io.File
import java.io.IOException
import java.net.MalformedURLException
import java.net.URI
import java.net.URL

open class OpenApiParser {

    open fun parse(spec: String?, resolutionBase: URL?): OpenApi<*> {
        return parse(spec, resolutionBase, true)
    }

    open fun parse(spec: String?, resolutionBase: URL?, validate: Boolean): OpenApi<*> {
        return try {
            val loader = JsonLoader()
            val tree = loader.loadString(resolutionBase, spec)
            parse(tree, resolutionBase, validate, loader)
        } catch (e: IOException) {
            throw OpenApiParserException("Failed to parse spec as JSON or YAML", e)
        }
    }

    @Throws(Exception::class)
    open fun parse(specFile: File): OpenApi<*> {
        return parse(specFile, true)
    }

    @Throws(Exception::class)
    open fun parse(specFile: File, validate: Boolean): OpenApi<*> {
        return try {
            parse(specFile.toURI().toURL(), validate)
        } catch (e: IOException) {
            throw OpenApiParserException("Failed to read spec from file", e)
        }
    }

    @Throws(Exception::class)
    open fun parse(uri: URI): OpenApi<*> {
        return parse(uri, true)
    }

    @Throws(Exception::class)
    open fun parse(uri: URI, validate: Boolean): OpenApi<*> {
        return try {
            parse(uri.toURL(), validate)
        } catch (e: MalformedURLException) {
            throw OpenApiParserException("Invalid URI for Swagger spec", e)
        }
    }

    @Throws(Exception::class)
    open fun parse(resolutionBase: URL?): OpenApi<*> {
        return parse(resolutionBase, true)
    }

    @Throws(Exception::class)
    open fun parse(resolutionBase: URL?, validate: Boolean): OpenApi<*> {
        val manager = ReferenceManager(resolutionBase)
        return parse(manager, validate)
    }

    open fun parse(tree: JsonNode?, resolutionBase: URL?): OpenApi<*> {
        return parse(tree, resolutionBase, true)
    }

    open fun parse(tree: JsonNode?, resolutionBase: URL?, validate: Boolean): OpenApi<*> {
        return parse(tree, resolutionBase, validate, null)
    }

    fun parse(tree: JsonNode?, resolutionBase: URL?, validate: Boolean, loader: JsonLoader?): OpenApi<*> {
        val manager = ReferenceManager(resolutionBase, tree, loader)
        return parse(manager, validate)
    }

    private fun parse(manager: ReferenceManager, validate: Boolean): OpenApi<*> {
        val tree: JsonNode
        return try {
            tree = manager.loadDoc()
            if (isVersion3(tree)) {
                val model = OpenApi3Impl.factory.create(tree, null, manager) as OpenApi3
                (model as OpenApi3Impl)._setCreatingRef(manager.docReference)
                if (validate) {
                    model.validate()
                }
                model
            } else {
                throw OpenApiParserException(
                    "Could not determine OpenApi version from model: no 'openapi' property"
                )
            }
        } catch (e: IOException) {
            throw OpenApiParserException("Failed to parse model", e)
        }
    }

    protected open fun isVersion3(tree: JsonNode): Boolean {
        val versionNode = tree.path("openapi")
        return versionNode.isTextual && versionNode.asText().startsWith("3.")
    }

    class OpenApiParserException : RuntimeException {
        constructor() : super()
        constructor(
            message: String?, cause: Throwable?, enableSuppression: Boolean,
            writableStackTrace: Boolean
        ) : super(message, cause, enableSuppression, writableStackTrace)

        constructor(message: String?, cause: Throwable?) : super(message, cause)
        constructor(message: String?) : super(message)
        constructor(cause: Throwable?) : super(cause)

        companion object {
            private const val serialVersionUID = 1L
        }
    }
}