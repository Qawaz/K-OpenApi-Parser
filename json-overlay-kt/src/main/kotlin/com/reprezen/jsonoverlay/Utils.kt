package com.reprezen.jsonoverlay

import kotlinx.serialization.json.*

internal fun JsonPrimitive.toValue(): Any? {
    val json = this
    return (if (json.isString) json.contentOrNull else null) ?: json.booleanOrNull ?: run {
        if (json.contentOrNull?.contains('.') == true) {
            json.doubleOrNull
        } else {
            json.intOrNull ?: json.longOrNull
        }
    }
}

internal fun Any?.toJsonElement() : JsonElement {
    if(this == null) return JsonNull
    return when(this){
        is String -> JsonPrimitive(this)
        is Number -> JsonPrimitive(this)
        is Boolean -> JsonPrimitive(this)
        else -> {
            throw IllegalArgumentException("Cannot convert value $this class ${this::class.simpleName} to json element")
        }
    }
}