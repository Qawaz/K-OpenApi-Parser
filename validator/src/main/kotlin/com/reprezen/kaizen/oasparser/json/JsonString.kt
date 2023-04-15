package com.reprezen.kaizen.oasparser.json

import kotlinx.serialization.json.*

internal fun JsonElement.toIndentedString(indent: String = ""): String {
    return when (this) {
        is JsonArray -> {
            joinToString(prefix = "[\n", postfix = "\n${indent.removePrefix("\t")}]", separator = ",\n") {
                indent + it.toIndentedString(indent + '\t')
            }
        }

        is JsonObject -> {
            entries.joinToString(
                separator = ",\n",
                prefix = "{\n",
                postfix = "\n${indent.removePrefix("\t")}}",
                transform = { (k, v) ->
                    buildString {
                        append(indent + '"' + k + '"')
                        append(" : ")
                        append(v.toIndentedString(indent + '\t'))
                    }
                }
            )
        }

        is JsonPrimitive -> {
            toString()
        }

        JsonNull -> toString()
    }
}