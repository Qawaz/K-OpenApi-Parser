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
import com.google.common.collect.Lists
import com.google.common.collect.Queues
import com.reprezen.jsonoverlay.JsonLoader
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.IOException
import java.net.URL
import java.util.*

@RunWith(Parameterized::class)
class ExamplesTest(
    val pair : Pair<URL,String>
) : Assert() {


    val exampleUrl: URL = pair.first

    val fileName: String = pair.second

    @Test
    @Throws(Exception::class)
    fun exampleCanBeParsed() {
        if (!exampleUrl.toString().contains("callback-example")) {
            val model = OpenApiParser().parse(exampleUrl)
            for (item in model.getValidationItems()) {
                println(item)
            }
            assertTrue("Example was not valid: $exampleUrl", model.isValid())
        }
    }

    companion object {
        private const val SPEC_REPO = "OAI/OpenAPI-specification"
        private const val EXAMPLES_BRANCH = "main"
        private const val EXAMPLES_ROOT = "examples/v3.0"

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {1}")
        @Throws(IOException::class)
        fun findExamples(): Iterable<Array<Pair<URL,String>>> {
            val examples: MutableCollection<Array<Pair<URL,String>>> = Lists.newArrayList()
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

        private fun <T> iterable(iterator: Iterator<T>): Iterable<T> {
            return object : Iterable<T> {
                override fun iterator(): Iterator<T> {
                    return iterator
                }
            }
        }
    }
}