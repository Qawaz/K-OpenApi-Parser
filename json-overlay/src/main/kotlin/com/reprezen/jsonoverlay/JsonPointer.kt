package com.reprezen.jsonoverlay

import kotlinx.serialization.json.*

class JsonPointer(val segments: List<String>, val isPrefixedWithFSlash: Boolean = false) {

    constructor(path: String) : this(
        segments = if (path.isEmpty() || path == "/") emptyList() else path
            .removePrefix("/")
            .split("/")
            .map { segment ->
                segment
                    .replace("~1", "/")
                    .replace("~0", "~")
            },
        isPrefixedWithFSlash = path.getOrNull(0) == '/'
    )

    fun navigate(json: JsonElement): JsonElement? {
        var current: JsonElement = json
        for (segment in segments) current = navigate(current, segment) ?: return null
        return current
    }

    fun navigateToMutable(elements: MutableMap<String, JsonElement>): MutableMap<String, JsonElement> {
        var current = elements
        for (segment in segments) {
            current = ((current[segment] as? JsonObject)?.toMutableMap() ?: mutableMapOf()).also {
                current[segment] = JsonObject(it)
            }
        }
        return current
    }

    override fun toString(): String {
        return segments.joinToString("/").let { if (isPrefixedWithFSlash) "/$it" else it }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JsonPointer) return false
        return segments == other.segments
    }

    fun isEmpty(): Boolean = segments.isEmpty()

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

    fun minusEnd(pointer: JsonPointer): JsonPointer? {
        if (pointer.segments.size > segments.size) return null
        var index = pointer.segments.size - 1
        while (index > 0) {
            if (segments[index] == pointer.segments[index]) {
                index--
            } else {
                return null
            }
        }
        if (index == -1) return Empty
        return JsonPointer(segments.dropLast(index + 1))
    }

    override fun hashCode(): Int {
        return segments.hashCode()
    }

    companion object {

        val Empty: JsonPointer = JsonPointer(emptyList())

        fun navigate(element: JsonElement, segment: String): JsonElement? = with(element) {
            return when (this) {
                is JsonArray -> {
                    val index = segment.toIntOrNull()
                    if (index != null && index < size) {
                        this[index]
                    } else {
                        null
                    }
                }

                is JsonObject -> this[segment]
                else -> null
            }
        }

        private fun String.toPathSegments(): MutableList<String> {
            val segments = mutableListOf<String>()
            val splits = removePrefix("/").split("/")
            for (split in splits) {
                if (split.contains("~1")) {
                    if (split.startsWith("~1")) segments.add("/")
                    val endsWith = split.endsWith("~1")
                    segments.add(split.removePrefix("~1").removeSuffix("~1").replace("~1", "/"))
                    if (endsWith) segments.add("~1")
                } else {
                    segments.add(split.replace("~0", "~"))
                }
            }
            return segments
        }

    }

}