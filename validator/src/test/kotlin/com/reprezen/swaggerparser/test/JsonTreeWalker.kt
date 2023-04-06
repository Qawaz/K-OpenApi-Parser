/*******************************************************************************
 * Copyright (c) 2023 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * WaqasTahir - Contributions after 2022
 * ModelSolv, Inc. - initial API and implementation and/or initial documentation
 */
package com.reprezen.swaggerparser.test

import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import java.util.function.Predicate

class JsonTreeWalker(private val nodeFilter: Predicate<JsonNode>, private val walkMethod: WalkMethod) {
    private fun walk(node: JsonNode, path: JsonPointer) {
        if (nodeFilter.test(node)) {
            visit(node, path)
        }
        walkChildren(node, path)
    }

    private fun visit(node: JsonNode, path: JsonPointer) {
        walkMethod.run(node, path)
    }

    private fun walkChildren(node: JsonNode, path: JsonPointer) {
        if (node is ObjectNode) {
            for ((key, value) in iterable(node.fields())) {
                walk(value, childPointer(path, key))
            }
        } else if (node is ArrayNode) {
            for (i in 0 until node.size()) {
                walk(node[i], childPointer(path, i))
            }
        }
    }

    private fun childPointer(path: JsonPointer, childKey: String): JsonPointer {
        return JsonPointer.compile(path.toString() + "/" + quote(childKey))
    }

    private fun childPointer(path: JsonPointer, index: Int): JsonPointer {
        return childPointer(path, Integer.toString(index))
    }

    fun quote(key: String): String {
        return key.replace("~".toRegex(), "~0").replace("/".toRegex(), "~1")
    }

    interface WalkMethod {
        fun run(node: JsonNode?, path: JsonPointer?)
    }

    private fun <T> iterable(iterator: Iterator<T>): Iterable<T> {
        return object : Iterable<T> {
            override fun iterator(): Iterator<T> {
                return iterator
            }
        }
    }

    companion object {
        @JvmStatic
        fun walkTree(tree: JsonNode, nodeFilter: Predicate<JsonNode>?, walkMethod: WalkMethod) {
            var nodeFilter = nodeFilter
            if (nodeFilter == null) {
                nodeFilter = Predicate { node: JsonNode? -> true }
            }
            JsonTreeWalker(nodeFilter, walkMethod).walk(tree, JsonPointer.compile(""))
        }
    }
}