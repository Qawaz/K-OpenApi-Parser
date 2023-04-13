/*******************************************************************************
 * Copyright (c) 2023 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * WaqasTahir - Contributions after 2022
 * ModelSolv, Inc. - initial API and implementation and/or initial documentation
 */
package com.reprezen.kaizen.oasparser

import com.reprezen.jsonoverlay.JsonLoader
import com.reprezen.jsonoverlay.ReferenceManager
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.ovl3.OpenApi3Impl
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import java.io.File
import java.io.IOException
import java.net.MalformedURLException
import java.net.URI
import java.net.URL

open class OpenApiParser {

    open fun parse(spec: String, resolutionBase: URL?): OpenApi3 {
        return parse(spec, resolutionBase, true)
    }

    open fun parse(spec: String, resolutionBase: URL?, validate: Boolean): OpenApi3 {
        return try {
            val loader = JsonLoader()
            val tree = loader.loadString(resolutionBase, spec)
            parse(tree, resolutionBase, loader)
        } catch (e: IOException) {
            throw OpenApiParserException("Failed to parse spec as JSON or YAML", e)
        }
    }

//    @Throws(Exception::class)
//    open fun parse(specFile: File): OpenApi3 {
//        return parse(specFile, true)
//    }

    @Throws(Exception::class)
    open fun parse(specFile: File): OpenApi3 {
        return try {
            parse(specFile.toURI().toURL())
        } catch (e: IOException) {
            throw OpenApiParserException("Failed to read spec from file", e)
        }
    }

//    @Throws(Exception::class)
//    open fun parse(uri: URI): OpenApi3 {
//        return parse(uri, true)
//    }

    @Throws(Exception::class)
    open fun parse(uri: URI): OpenApi3 {
        return try {
            parse(uri.toURL())
        } catch (e: MalformedURLException) {
            throw OpenApiParserException("Invalid URI for Swagger spec", e)
        }
    }

//    @Throws(Exception::class)
//    open fun parse(resolutionBase: URL?): OpenApi3 {
//        return parse(resolutionBase, true)
//    }

    @Throws(Exception::class)
    open fun parse(resolutionBase: URL?): OpenApi3 {
        val manager = ReferenceManager(resolutionBase)
        return parse(manager)
    }

    open fun parse(tree: JsonElement?, resolutionBase: URL?): OpenApi3 {
        return parse(tree, resolutionBase, true)
    }

    open fun parse(tree: JsonElement?, resolutionBase: URL?, validate: Boolean): OpenApi3 {
        return parse(tree, resolutionBase, null)
    }

    fun parse(tree: JsonElement?, resolutionBase: URL?, loader: JsonLoader?): OpenApi3 {
        val manager = ReferenceManager(resolutionBase, tree, loader)
        return parse(manager)
    }

    private fun parse(manager: ReferenceManager): OpenApi3 {
        val tree: JsonElement
        return try {
            tree = manager.loadDoc()
            if (isVersion3(tree)) {
                val model = OpenApi3Impl.factory.create(tree, null, manager) as OpenApi3
                // TODO
//                (model as OpenApi3Impl)._setCreatingRef(manager.getReference(tree))
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

    protected open fun isVersion3(tree: JsonElement): Boolean {
        if (tree is JsonObject) {
            val primitive = tree["openapi"] as? JsonPrimitive
            if (primitive?.isString == true) {
                return primitive.contentOrNull?.startsWith("3.") == true
            }
        }
        return false
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