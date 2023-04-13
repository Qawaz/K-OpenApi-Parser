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

fun JsonElement.toValue(): Any? {
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

internal fun Any.toJsonOverlay(parent: JsonOverlay<*>?, refMgr: ReferenceManager): JsonOverlay<*> {
    return when (this) {
        is JsonOverlay<*> -> this
        is String -> StringOverlay.factory.create(value = this, parent = parent, refMgr = refMgr)
        is Number -> {
            when (this) {
                is Int -> IntegerOverlay.factory.create(value = this, parent = parent, refMgr = refMgr)
                else -> NumberOverlay.factory.create(value = this, parent = parent, refMgr = refMgr)
            }
        }

        is Boolean -> BooleanOverlay.factory.create(value = this, parent = parent, refMgr = refMgr)
        is MutableList<*> -> {
            ListOverlay.getFactory(
                itemFactory = firstOrNull()?.toJsonOverlay(parent = parent, refMgr = refMgr)?.factory
                    ?: ObjectOverlay.factory
            ).tryCreate(value = this, parent = parent, refMgr = refMgr)
        }

        is MutableMap<*, *> -> {
            MapOverlay.getFactory(
                valueFactory = values.firstOrNull()?.toJsonOverlay(parent = parent, refMgr = refMgr)?.factory
                    ?: ObjectOverlay.factory,
                keyPattern = null
            ).tryCreate(value = this, parent = parent, refMgr = refMgr)
        }

        else -> {
            ObjectOverlay.factory.create(value = this, parent = parent, refMgr = refMgr)
        }
    }
}