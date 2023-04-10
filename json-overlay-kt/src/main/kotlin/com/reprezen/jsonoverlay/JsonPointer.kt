package com.reprezen.jsonoverlay

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

class JsonPointer(val segments: List<String>) {

    constructor(path: String) : this(
        segments = path
            .removePrefix("/")
            .split("/")
            .map { segment ->
                segment
                    .replace("~1", "/")
                    .replace("~0", "~")
            }
    )

    fun navigate(json: JsonElement): JsonElement? {
        var current: JsonElement? = json
        for (segment in segments) {
            current = when (current) {
                is JsonObject -> current[segment]
                is JsonArray -> {
                    val index = segment.toIntOrNull()
                    if (index != null && index < current.size) {
                        current[index]
                    } else {
                        return null
                    }
                }

                else -> return null
            }
        }
        return current
    }
}