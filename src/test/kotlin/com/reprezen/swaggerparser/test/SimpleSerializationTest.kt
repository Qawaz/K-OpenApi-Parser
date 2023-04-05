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

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.google.common.collect.Lists
import com.google.common.collect.Queues
import com.reprezen.jsonoverlay.JsonLoader
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.jsonoverlay.SerializationOptions
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.ovl3.OpenApi3Impl
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

    @Throws(Exception::class)
    private fun parseLocalModel(name: String): OpenApi3 {
        val url = SimpleSerializationTest::class.java.getResource("/models/$name.yaml")
        return OpenApiParser().parse(url)
    }

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
                val serialized = Overlay.toJson<OpenApi3>(model as OpenApi3Impl)
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
                    val tree: JsonNode = JsonLoader().load(url)
                    for (result in iterable(tree.elements())) {
                        val type = result["type"].asText()
                        val path = result["path"].asText()
                        val resultUrl = result["url"].asText()
                        if (type == "dir") {
                            dirs.add(URL(resultUrl))
                        } else if (type == "file" && (path.endsWith(".yaml") || path.endsWith(".json"))) {
                            val downloadUrl = result["download_url"].asText()
                            examples.add(arrayOf(Pair(URL(downloadUrl), result["name"].asText())))
                        }
                    }
                }
                return examples
            }
        }
    }

    class NonParameterizedTests {
        @Test
        @Throws(Exception::class)
        fun toJsonNoticesChanges() {
            val model = parseLocalModel("simpleTest")
            assertEquals("simple model", model.getInfo()?.getTitle())
            assertEquals("simple model", Overlay.of(model).toJson().at("/info/title").asText())
            // this changes the overlay value but does not refresh cached JSON -
            // just marks
            // it as out-of-date
            model.getInfo()?.setTitle("changed title")
            assertEquals("changed title", model.getInfo()?.getTitle())
            assertEquals("changed title", Overlay.of(model).toJson().at("/info/title").asText())
        }

        @Test
        @Throws(Exception::class)
        fun toJsonFollowsRefs() {
            val model = parseLocalModel("simpleTest")
            val xSchema = model.getSchema("X")!!
            assertEquals("#/components/schemas/Y", Overlay.of(xSchema).toJson().at("/properties/y/\$ref").asText())
            assertEquals(
                "integer",
                Overlay.of(xSchema).toJson(SerializationOptions.Option.FOLLOW_REFS).at("/properties/y/type").asText()
            )
        }
    }
}