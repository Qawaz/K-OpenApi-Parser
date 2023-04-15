package com.reprezen.swaggerparser.json

import com.reprezen.jsonoverlay.JsonPointer
import kotlinx.serialization.json.*

fun JsonArray.walk(walker: Walker, parent: JsonElement?, pointer: JsonPointer) {
    var i = 0
    for (element in this) {
        element.walk(walker = walker, parent = this, pointer = JsonPointer(pointer.segments + "$i"))
        i++
    }
}

fun JsonObject.walk(walker: Walker, parent: JsonElement?, pointer: JsonPointer) {
    for (entry in this) {
        entry.value.walk(walker = walker, parent = this, pointer = JsonPointer(pointer.segments + entry.key))
    }
}

interface Walker {
    fun walk(node: JsonElement, parent: JsonElement?, pointer: JsonPointer)
}

fun JsonElement.walk(walker: Walker, parent: JsonElement?, pointer: JsonPointer) {
    when (this) {
        is JsonArray -> {
            this.walk(walker = walker, parent = parent, pointer = pointer)
        }

        is JsonObject -> {
            this.walk(walker = walker, parent = parent, pointer = pointer)
        }

        is JsonPrimitive -> {
            walker.walk(node = this, parent = parent, pointer = pointer)
        }

        JsonNull -> {
            walker.walk(node = this, parent = parent, pointer = pointer)
        }
    }
}