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

import com.google.common.collect.Lists
import com.google.common.collect.Queues
import com.reprezen.jsonoverlay.DocumentLoader
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.ovl3.OpenApi3Impl
import com.reprezen.swaggerparser.json.equalTo
import com.reprezen.swaggerparser.json.toIndentedString
import com.wakaztahir.jsontoyaml.YamlOrJson
import kotlinx.serialization.json.*
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.IOException
import java.net.URL
import java.util.*

@RunWith(Enclosed::class)
object SimpleSerializationTest : Assert() {

    private const val SPEC_REPO = "OAI/OpenAPI-Specification"
    private const val EXAMPLES_BRANCH = "main"
    private const val EXAMPLES_ROOT = "examples/v3.0"

    @RunWith(Parameterized::class)
    class ParameterizedTests(val pair: Pair<URL, String>) : Assert() {

        var exampleUrl: URL = pair.first
        var fileName: String = pair.second

        @Test
        @Throws(Exception::class)
        fun serializeExample() {
            if (!exampleUrl.toString().contains("callback-example")) {
                val expected = YamlOrJson.Default.load(exampleUrl)
                val model = OpenApiParser(loader = YamlOrJson.Default).parse(expected, exampleUrl)
                val serialized = (model as OpenApi3Impl)._toJson()
                // TODO PropertiesOverlay defies order of json returned at the moment
                val result = expected.equalTo(serialized, "", checkOrder = false)
                result.exceptionOrNull()?.let {
                    it.printStackTrace()
                    assertEquals(expected.toIndentedString(), serialized.toIndentedString())
                }
                assertTrue(result.isSuccess)
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
                    val tree = DocumentLoader.Default.load(url) as JsonArray
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