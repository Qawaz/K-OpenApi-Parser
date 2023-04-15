package com.reprezen.jsonoverlay

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromStream
import java.net.URL

interface DocumentLoader {

    @OptIn(ExperimentalSerializationApi::class)
    fun load(url: URL): JsonElement {
        return url.openStream().use { Json.decodeFromStream<JsonElement>(it) }
    }

    fun load(spec: String): JsonElement {
        return Json.parseToJsonElement(spec)
    }

    companion object {
        val Default = object : DocumentLoader {}
    }

}