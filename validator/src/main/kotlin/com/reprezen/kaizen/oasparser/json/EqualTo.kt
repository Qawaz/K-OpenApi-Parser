package com.reprezen.kaizen.oasparser.json

import kotlinx.serialization.json.*

fun JsonPrimitive.equalTo(other: JsonPrimitive, path: String): Result<String> {
    if ((this.isString && !other.isString) || (!this.isString && other.isString)) {
        return unComparableError(other, path)
    }
    return if (this.content == other.content) {
        successfulCompared(other, path)
    } else {
        Result.failure(Throwable("string element with path $path $content is not equal ${other.content}"))
    }
}

fun JsonArray.equalTo(other: JsonArray, path: String): Result<String> {
    if (this.size != other.size) {
        return Result.failure(Throwable("array with path $path has size $size which is not equal to ${other.size}"))
    }
    var i = 0
    for (element in this) {
        if (other.getOrNull(i) == null) {
            return Result.failure(Throwable("element in array with path $path/$i couldn't be found in other"))
        }
        val result = element.equalTo(other[i], "$path/$i")
        if (result.isFailure) return result
        i++
    }
    return successfulCompared(other, path)
}

fun JsonObject.equalTo(other: JsonObject, path: String): Result<String> {
    if (this.size != other.size) {
        return Result.failure(Throwable("object with path $path has size $size which is not equal to ${other.size}"))
    }
    for (entry in this) {
        val otherValue = other[entry.key]
            ?: return Result.failure(Throwable("element in object with path $path/${entry.key} couldn't be found in other"))
        val result = entry.value.equalTo(otherValue, path = "$path/${entry.key}")
        if (result.isFailure) return result
    }
    return successfulCompared(other, path)
}

private fun JsonElement.stringType(): String {
    return when (this) {
        is JsonArray -> "JsonArray(elementType : ${firstOrNull()?.stringType() ?: "Unknown"})"
        is JsonObject -> "JsonObject"
        is JsonPrimitive -> "JsonPrimitive(isString : ${this.isString})"
        JsonNull -> "JsonNull"
    }
}

private fun JsonElement.unComparableError(other: JsonElement, path: String): Result<String> {
    return Result.failure(Throwable("${stringType()} with path $path cannot be compared to ${other.stringType()}"))
}

private fun JsonElement.successfulCompared(other: JsonElement, path: String): Result<String> {
    return Result.success("${stringType()} with path $path is equal to ${other.stringType()}")
}

fun JsonElement.equalTo(other: JsonElement, path: String): Result<String> {
    when (this) {
        is JsonArray -> {
            if (other !is JsonArray) return unComparableError(other, path)
            return this.equalTo(other, path = path)
        }

        is JsonObject -> {
            if (other !is JsonObject) return unComparableError(other, path)
            return this.equalTo(other, path = path)
        }

        is JsonPrimitive -> {
            if (other !is JsonPrimitive) return unComparableError(other, path)
            return this.equalTo(other, path = path)
        }

        JsonNull -> {
            if (other !is JsonNull) return unComparableError(other, path)
            return successfulCompared(other, path)
        }
    }
}