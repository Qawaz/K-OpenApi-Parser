package com.reprezen.jsonoverlay

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

class JsonPointer(val segments: List<String>) {

    constructor(path: String) : this(
        segments = if (path.isEmpty() || path == "/") emptyList() else path
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

    override fun toString(): String {
        return segments.joinToString("/")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JsonPointer) return false
        return segments == other.segments
    }

    fun isEmpty() : Boolean = segments.isEmpty()

    fun minus(pointer: JsonPointer): JsonPointer? {
        if (pointer.segments.size > segments.size) return null
        var index = 0
        while (index < pointer.segments.size) {
            if (segments[index] == pointer.segments[index]) {
                index++
            } else {
                return null
            }
        }
        if (index == segments.size) return Empty
        return JsonPointer(segments.drop(index))
    }

    override fun hashCode(): Int {
        return segments.hashCode()
    }

    companion object {
        val Empty: JsonPointer = JsonPointer(emptyList())
    }

}