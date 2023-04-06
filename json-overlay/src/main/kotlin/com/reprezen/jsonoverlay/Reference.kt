/*********************************************************************
 * Copyright (c) 2023 WaqasTahir, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * WaqasTahir
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.databind.JsonNode
import com.reprezen.jsonoverlay.ResolutionException.ReferenceCycleException
import java.io.IOException

class Reference {
    var refString: String
        private set
    var normalizedRef: String? = null
        private set
    private var pointer: JsonPointer? = null
    var manager: ReferenceManager?
        private set
    private var json: JsonNode? = null
    private var valid: Boolean? = null
    var invalidReason: ResolutionException? = null
        private set

    constructor(refString: String, fragment: String?, normalizedRef: String?, manager: ReferenceManager) {
        this.refString = refString
        this.normalizedRef = normalizedRef
        this.manager = manager
        try {
            pointer = if (fragment != null) JsonPointer.compile(fragment) else null
        } catch (e: IllegalArgumentException) {
            valid = false
            invalidReason = ResolutionException("Invalid JSON pointer in JSON reference", this, e)
        }
    }

    constructor(refString: String, invalidReason: ResolutionException?, manager: ReferenceManager?) {
        this.refString = refString
        this.invalidReason = invalidReason
        this.manager = manager
    }

    val fragment: String
        get() = if (pointer != null) pointer.toString() else ""

    fun isValid(): Boolean {
        return isValid(true)
    }

    fun isValid(resolve: Boolean): Boolean {
        if (resolve) {
            resolve()
        }
        return isResolved && valid!!
    }

    val isInvalid: Boolean
        get() = isInvalid(true)

    fun isInvalid(resolve: Boolean): Boolean {
        if (resolve) {
            resolve()
        }
        return isResolved && !valid!!
    }

    val isResolved: Boolean
        get() = valid != null

    fun getJson(): JsonNode? {
        resolve()
        return json
    }

    fun resolve(): Boolean {
        val visited: MutableSet<String?> = HashSet()
        var current = this
        while (valid == null) {
            val normalized = current.normalizedRef
            if (visited.contains(normalized)) {
                return failResolve(null, ReferenceCycleException(this, current))
            } else {
                visited.add(normalized)
            }
            var currentJson: JsonNode? = null
            currentJson = try {
                current.manager?.loadDoc()
            } catch (e: IOException) {
                return failResolve("Failed to load referenced documnet", e)
            }
            currentJson = if (current.pointer != null) currentJson!!.at(current.pointer) else currentJson
            if (isReferenceNode(currentJson)) {
                current = manager?.getReference(currentJson!!)!!
                if (current.isInvalid(false)) {
                    return failResolve("Invalid reference in reference chain", current.invalidReason)
                }
            } else {
                json = currentJson
                if (json!!.isMissingNode) {
                    failResolve("Referenced node not present in JSON document")
                }
                valid = true
            }
        }
        return isValid(false)
    }

    private fun failResolve(msg: String?, e: Exception? = null): Boolean {
        valid = false
        if (e is ResolutionException && msg == null) {
            invalidReason = e
        } else {
            invalidReason = ResolutionException(msg, this, e)
        }
        return false
    }

    companion object {
        fun isReferenceNode(node: JsonNode?): Boolean {
            return node!!.isObject && node.has("\$ref") && node["\$ref"].isTextual
        }
    }
}
