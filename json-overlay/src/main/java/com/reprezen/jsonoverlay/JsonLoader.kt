/*********************************************************************
 * Copyright (c) 2017 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * ModelSolv, Inc.
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.reprezen.jsonoverlay.parser.LocationRecorderYamlFactory
import com.reprezen.jsonoverlay.parser.LocationRecorderYamlParser
import java.io.IOException
import java.net.URL
import java.util.*
import java.util.regex.Pattern

class JsonLoader {

    private val cache: MutableMap<String, JsonNode> = HashMap()
    private val positions: MutableMap<String, Map<JsonPointer, PositionInfo>> = HashMap()

    @Throws(IOException::class)
    fun load(url: URL): JsonNode {
        val urlString = url.toString()
        if (cache.containsKey(urlString)) {
            return cache[urlString]!!
        }
        url.openStream().use { `in` ->
            Scanner(`in`, "UTF-8").use { scanner ->
                val json = scanner.useDelimiter("\\Z").next()
                return loadString(url, json)
            }
        }
    }

    @Throws(IOException::class, JsonProcessingException::class)
    fun loadString(url: URL?, json: String?): JsonNode {
        val result = loadWithLocations(json)
        if (url != null) {
            cache[url.toString()] = result.first
            positions[url.toString()] = result.second
        }
        return result.first
    }

    fun getPositionInfo(url: String, pointer: JsonPointer): Optional<PositionInfo> {
        return if (positions.containsKey(url)) {
            Optional.ofNullable(positions[url]!![pointer])
        } else {
            Optional.empty()
        }
    }

    @Throws(IOException::class)
    fun loadWithLocations(json: String?): kotlin.Pair<JsonNode, Map<JsonPointer, PositionInfo>> {
        val tree: JsonNode
        val regions: Map<JsonPointer, PositionInfo>
        val parser = yamlFactory.createParser(fixTabs(json)) as LocationRecorderYamlParser
        tree = yamlMapper.readTree(parser)
        regions = parser.locations
        return kotlin.Pair(tree, regions)
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

    companion object {
        private val yamlFactory = LocationRecorderYamlFactory()
        private val yamlMapper = ObjectMapper(yamlFactory)

        init {
            yamlMapper.setNodeFactory(MinSharingJsonNodeFactory.instance)
        }
    }
}
