package com.wakaztahir.jsontoyaml

import com.charleskorn.kaml.*
import com.reprezen.jsonoverlay.DocumentLoader
import com.reprezen.jsonoverlay.toNumber
import kotlinx.serialization.json.*
import java.lang.Exception
import java.net.URL

interface YamlOrJson : DocumentLoader {

    override fun load(url: URL): JsonElement {
        return if (url.toString().endsWith(".yaml")) {
            url.openStream().use { Yaml.default.parseToYamlNode(it).toJsonElement() }
        } else {
            super.load(url)
        }
    }

    override fun load(spec: String): JsonElement {
        return try {
            super.load(spec)
        } catch (e: Exception) {
            Yaml.default.parseToYamlNode(spec).toJsonElement()
        }
    }

    companion object {
        val Default = object : YamlOrJson {}
    }

}

fun YamlList.toJsonArray(): JsonArray {
    return JsonArray(items.map { it.toJsonElement() })
}

fun YamlMap.toJsonObject(): JsonObject {
    return JsonObject(this.entries.mapKeys { it.key.content }.mapValues { it.value.toJsonElement() })
}

fun YamlNull.toJsonNull(): JsonNull = JsonNull

internal fun String.toBooleanJsonPrimitive(): JsonPrimitive? {
    return when {
        this.equals("true", ignoreCase = true) -> JsonPrimitive(true)
        this.equals("false", ignoreCase = true) -> JsonPrimitive(false)
        else -> null
    }
}

fun YamlScalar.toJsonPrimitive(): JsonPrimitive {
    return content.toBooleanJsonPrimitive() ?: (
        JsonPrimitive(content).let {
            it.toNumber()?.let { n ->
                JsonPrimitive(n)
            } ?: it
        })
}

fun YamlScalar.toNumberJsonPrimitive(): JsonPrimitive? {
    return JsonPrimitive(content).toNumber()?.let { JsonPrimitive(it) }
}

fun YamlScalar.toBooleanJsonPrimitive(): JsonPrimitive? {
    return when {
        content.equals("true", ignoreCase = true) -> JsonPrimitive(true)
        content.equals("false", ignoreCase = true) -> JsonPrimitive(false)
        else -> null
    }
}

fun YamlTaggedNode.toJsonElement(): JsonElement {
    return when (tag) {
        "str" -> (innerNode as? YamlScalar)?.let { JsonPrimitive(it.content) } ?: innerNode.toJsonElement()
        "float", "int" -> (innerNode as? YamlScalar)?.toNumberJsonPrimitive() ?: innerNode.toJsonElement()
        "bool" -> (innerNode as? YamlScalar)?.toBooleanJsonPrimitive() ?: innerNode.toJsonElement()
        "null" -> JsonNull
        else -> {
            println("UNKNOWN TAG $tag")
            innerNode.toJsonElement()
        }
    }
}

fun YamlNode.toJsonElement(): JsonElement {
    return when (this) {
        is YamlList -> toJsonArray()
        is YamlMap -> toJsonObject()
        is YamlNull -> toJsonNull()
        is YamlScalar -> toJsonPrimitive()
        is YamlTaggedNode -> toJsonElement()
    }
}