/*********************************************************************
 * Copyright (c) 2023 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * WaqasTahir - Contributions after 2022
 * ModelSolv, Inc.
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import java.io.IOException
import java.net.URL
import java.util.*
import java.util.regex.Pattern

class JsonLoader {

    private val cache: MutableMap<String, JsonElement> = HashMap()
    private val positions: MutableMap<String, Map<JsonPointer, PositionInfo>> = HashMap()

    @Throws(IOException::class)
    fun load(url: URL): JsonElement {
        val urlString = url.toString()
        if (cache.containsKey(urlString)) {
            return cache[urlString]!!
        }
        url.openStream().use { input ->
            Scanner(input, "UTF-8").use { scanner ->
                val json = scanner.useDelimiter("\\Z").next()
                return loadString(url, json)
            }
        }
    }

    @Throws(IOException::class)
    fun loadString(url: URL?, json: String): JsonElement {
        return Json.encodeToJsonElement<String>(json)
    }

    fun getPositionInfo(url: String, pointer: JsonPointer): Optional<PositionInfo> {
        return if (positions.containsKey(url)) {
            Optional.ofNullable(positions[url]!![pointer])
        } else {
            Optional.empty()
        }
    }

    private fun fixTabs(json: String?): String {
        val initialTabs = Pattern.compile("^(\\t+)", Pattern.MULTILINE)
        val m = initialTabs.matcher(json)
        val sb = StringBuffer()
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).replace("\\t".toRegex(), " "))
        }
        m.appendTail(sb)
        return sb.toString()
    }

}
