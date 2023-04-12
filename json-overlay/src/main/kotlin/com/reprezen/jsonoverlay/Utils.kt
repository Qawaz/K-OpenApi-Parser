package com.reprezen.jsonoverlay

import kotlinx.serialization.json.*

private const val MAX_INT_DIGITS = 10
private const val MAX_LONG_DIGITS = 19
private const val MAX_FLOAT_DIGITS = 7
private const val MAX_DOUBLE_DIGITS = 16

internal fun String.parseJsonLiteralAsNumber(): Number? {
    val content = this
    return if (content.contains('.')) {
        val bigDecimal = content.toBigDecimalOrNull() ?: return null
        val numDigits = bigDecimal.precision() - bigDecimal.scale()
        if (numDigits <= MAX_FLOAT_DIGITS) {
            bigDecimal.toFloat()
        } else if (numDigits <= MAX_DOUBLE_DIGITS) {
            bigDecimal.toDouble()
        } else {
            bigDecimal
        }
    } else {
        val bigInteger = content.toBigIntegerOrNull() ?: return null
        val numDigits = bigInteger.toString().length
        if (numDigits <= MAX_INT_DIGITS) {
            bigInteger.toInt()
        } else if (numDigits <= MAX_LONG_DIGITS) {
            bigInteger.toLong()
        } else {
            bigInteger
        }
    }
}

internal fun JsonPrimitive.toNumber(): Number? {
    return content.parseJsonLiteralAsNumber()
}

internal fun JsonElement.toValue(): Any? {
    return when (this) {
        is JsonPrimitive -> {
            if (contentOrNull == null) return null
            return (if (isString) content else null) ?: booleanOrNull ?: toNumber()
        }

        is JsonArray -> map { it.toValue() }
        is JsonObject -> mutableMapOf<String, Any?>().apply {
            for (item in this@toValue) {
                put(item.key, item.value.toValue())
            }
        }

        else -> null
    }
}

internal fun Any?.toJsonElement(): JsonElement {
    if (this == null) return JsonNull
    return when (this) {
        is String -> JsonPrimitive(this)
        is Number -> JsonPrimitive(this)
        is Boolean -> JsonPrimitive(this)
        is List<*> -> JsonArray(map { elem -> elem.toJsonElement() })
        is Map<*, *> -> JsonObject(mutableMapOf<String, JsonElement>().apply {
            for (x in this@toJsonElement) {
                put(x.key.toString(), x.value.toJsonElement())
            }
        })

        else -> {
            JsonNull
        }
    }
}