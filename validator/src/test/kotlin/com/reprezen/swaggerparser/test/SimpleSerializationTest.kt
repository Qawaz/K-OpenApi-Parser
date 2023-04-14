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

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.google.common.collect.Lists
import com.google.common.collect.Queues
import com.reprezen.jsonoverlay.JsonLoader
import com.reprezen.jsonoverlay.JsonPointer
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.jsonoverlay.SerializationOptions
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.ovl3.OpenApi3Impl
import com.reprezen.kaizen.oasparser.ovl3.SchemaImpl
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import java.io.IOException
import java.net.URL
import java.util.*

@RunWith(Enclosed::class)
object SimpleSerializationTest : Assert() {

    private const val SPEC_REPO = "OAI/OpenAPI-Specification"
    private const val EXAMPLES_BRANCH = "main"
    private const val EXAMPLES_ROOT = "examples/v3.0"
    private val mapper = ObjectMapper()
    private val yamlMapper: ObjectMapper = YAMLMapper()

    private fun <T> iterable(iterator: Iterator<T>): Iterable<T> {
        return object : Iterable<T> {
            override fun iterator(): Iterator<T> {
                return iterator
            }
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedTests(val pair: Pair<URL, String>) : Assert() {

        var exampleUrl: URL = pair.first
        var fileName: String = pair.second

        @Test
        @Throws(Exception::class)
        fun serializeExample() {
            if (!exampleUrl.toString().contains("callback-example")) {
                val model = OpenApiParser().parse(exampleUrl)
                val serialized = (model as OpenApi3Impl)._toJson()
                val expected = yamlMapper.readTree(exampleUrl)
                JSONAssert.assertEquals(
                    mapper.writeValueAsString(expected), mapper.writeValueAsString(serialized),
                    JSONCompareMode.STRICT
                )
            }
        }

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "{index}: {1}")
            @Throws(IOException::class)
            fun findExamples(): Collection<Array<Pair<URL, String>>> {
                val examples: MutableCollection<Array<Pair<URL, String>>> = Lists.newArrayList()
                val dirs: Deque<URL> = Queues.newArrayDeque()
                val auth = if (System.getenv("GITHUB_AUTH") != null) System.getenv("GITHUB_AUTH") + "@" else ""
                val request = String.format(
                    "https://%sapi.github.com/repos/%s/contents/%s?ref=%s", auth, SPEC_REPO,
                    EXAMPLES_ROOT, EXAMPLES_BRANCH
                )
                dirs.add(URL(request))
                while (!dirs.isEmpty()) {
                    val url = dirs.remove()
                    val tree = JsonLoader().load(url) as JsonArray
                    for (resultItem in tree) {
                        val result = resultItem as JsonObject
                        val type = result["type"]!!.jsonPrimitive.content
                        val path = result["path"]!!.jsonPrimitive.content
                        val resultUrl = result["url"]!!.jsonPrimitive.content
                        if (type == "dir") {
                            dirs.add(URL(resultUrl))
                        } else if (type == "file" && (path.endsWith(".yaml") || path.endsWith(".json"))) {
                            val downloadUrl = result["download_url"]!!.jsonPrimitive.content
                            examples.add(arrayOf(Pair(URL(downloadUrl), result["name"]!!.jsonPrimitive.content)))
                        }
                    }
                }
                return examples
            }
        }
    }

}