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
package com.reprezen.swaggerparser.test

import com.reprezen.jsonoverlay.DocumentLoader
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.jsonoverlay.toValue
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.ovl3.OpenApi3Impl
import com.reprezen.swaggerparser.json.Walker
import com.reprezen.swaggerparser.json.walk
import kotlinx.serialization.json.JsonElement
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.net.URL
import java.util.*

/**
 * Tests basic parser operation by loading a swagger spec and then verifying
 * that all values can be obtained reliably from the model
 *
 * @author Andy Lowry
 */
@RunWith(Parameterized::class)
class BigParseTest(private val modelUrl: URL) : Assert() {

    @Test
    @Throws(Exception::class)
    fun testPrimitivesYieldSameValue() {
        val tree = DocumentLoader.Default.load(modelUrl)
        val model = OpenApiParser().parse(modelUrl) as OpenApi3Impl
        val walker = object : Walker {
            override fun walk(node: JsonElement, parent: JsonElement?, pointer: com.reprezen.jsonoverlay.JsonPointer) {
                val overlay = model.findByPointer(pointer)
                assertNotNull("No overlay object found for path: $pointer", overlay)
                val value = Overlay[overlay!!]
                val fromJson = node.toValue()
                val msg = String.format(
                    "Wrong overlay value for model '%s' and path '%s': expected '%s', got '%s'",
                    modelUrl.path,
                    pointer,
                    fromJson,
                    value
                )
                assertEquals(msg, fromJson, value)
            }
        }
        tree.walk(walker = walker, parent = null, pointer = com.reprezen.jsonoverlay.JsonPointer.Empty)
    }

    @Test
    fun testModelToString(){
        val model = OpenApiParser().parse(modelUrl)
        println(
            model.toString()
        )
    }

    companion object {

        val isJson: Boolean get() = true
        val resourceUrl: String get() = if (isJson) "/models/json/parseTest.json" else "/models/yaml/parseTest.yaml"

        @JvmStatic
        @Parameterized.Parameters
        fun resources(): Iterable<Array<URL>> {
            return listOf(arrayOf(object {}.javaClass.getResource(resourceUrl)!!))
        }
    }
}