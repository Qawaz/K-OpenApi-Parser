package com.wakaztahir.jsontoyaml

import com.charleskorn.kaml.*
import com.reprezen.jsonoverlay.JsonPointer
import kotlinx.serialization.json.*

private val UnknownLocation = Location(line = 0, column = 0)

fun JsonObject.toYamlMap(yamlPath: YamlPath): YamlMap {
    return YamlMap(
        mapKeys {
            YamlScalar(
                it.key,
                YamlPath(
                    yamlPath.segments + YamlPathSegment.MapElementKey(
                        key = it.key,
                        location = UnknownLocation
                    )
                )
            )
        }.mapValues {
            it.value.toYamlNode(
                YamlPath(
                    yamlPath.segments + YamlPathSegment.MapElementValue(
                        location = UnknownLocation
                    )
                )
            )
        },
        path = yamlPath
    )
}

fun JsonArray.toYamlList(yamlPath: YamlPath): YamlList {
    return YamlList(
        mapIndexed { index, jsonElement ->
            jsonElement.toYamlNode(
                YamlPath(
                    yamlPath.segments + YamlPathSegment.ListEntry(
                        index = index,
                        UnknownLocation
                    )
                )
            )
        },
        path = yamlPath
    )
}

fun JsonPrimitive.toYamlScalar(yamlPath: YamlPath): YamlScalar {
    return YamlScalar(
        content = content,
        yamlPath
    )
}

fun JsonElement.toYamlNode(yamlPath: YamlPath): YamlNode {
    return when (this) {
        is JsonArray -> toYamlList(yamlPath)
        is JsonObject -> toYamlMap(yamlPath)
        is JsonPrimitive -> toYamlScalar(yamlPath)
        JsonNull -> YamlNull(yamlPath)
    }
}