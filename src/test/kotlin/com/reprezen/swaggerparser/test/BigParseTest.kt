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
package com.reprezen.swaggerparser.test

import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.google.common.io.Resources
import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.yaml.snakeyaml.Yaml
import java.net.URL
import java.util.*
import java.util.function.Predicate

/**
 * Tests basic parser operation by loading a swagger spec and then verifying
 * that all values can be obtained reliably from the model
 *
 * @author Andy Lowry
 */
@RunWith(Parameterized::class)
class BigParseTest(
    private val modelUrl: URL
) : Assert() {
    //    @JvmField
//    @Parameterized.Parameter
//    var modelUrl: URL? = null
    @Test
    @Throws(Exception::class)
    fun test() {
        val parsedYaml = Yaml().load<Any>(modelUrl.openStream())
        val tree = YAMLMapper().convertValue(parsedYaml, JsonNode::class.java)
        val model = OpenApiParser().parse(modelUrl, false) as OpenApi3
        val valueNodePredicate = Predicate { obj: JsonNode -> obj.isValueNode }
        val valueChecker = object : JsonTreeWalker.WalkMethod {
            override fun run(node: JsonNode?, path: JsonPointer?) {
                require(node != null && path != null)
                val overlay = Overlay.find(model as JsonOverlay<*>, path)
                assertNotNull("No overlay object found for path: $path", overlay)
                val value = Overlay.get(overlay!!)
                val fromJson = getValue(node)
                val msg = String.format(
                    "Wrong overlay value for model '%s' and path '%s': expected '%s', got '%s'",
                    modelUrl.path,
                    path,
                    fromJson,
                    value
                )
                assertEquals(msg, fromJson, value)
            }
        }
        JsonTreeWalker.walkTree(tree, valueNodePredicate, valueChecker)
    }

    private fun getValue(node: JsonNode): Any? {
        return if (node.isNumber) {
            node.numberValue()
        } else if (node.isTextual) {
            node.textValue()
        } else if (node.isBoolean) {
            node.booleanValue()
        } else if (node.isNull) {
            null
        } else {
            throw IllegalArgumentException("Non-value JSON node got through value node filter")
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun resources(): Iterable<Array<URL>> {
            return listOf(
                *arrayOf<Array<URL>>(
                    arrayOf<URL>(
                        object {}.javaClass.getResource("/models/parseTest.yaml")!!
                    )
                )
            )
        }
    }
}