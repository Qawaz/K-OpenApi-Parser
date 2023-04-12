/*********************************************************************
 * Copyright (c) 2023 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * WaqasTahir - Contributions after 2022
 * ModelSolv, Inc.
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

import com.reprezen.jsonoverlay.ResolutionException.ReferenceCycleException
import kotlinx.serialization.json.*
import java.io.IOException

interface Reference {

    val refString: String

    val normalizedRef: String?

    val invalidReason: ResolutionException?

    val pointer: JsonPointer?

    val manager: ReferenceManager?

    val fragment: String?

    val isResolved: Boolean

    val isValid: Boolean
        get() = !isInvalid(true)

    val isInvalid: Boolean
        get() = isInvalid(true)

    fun isInvalid(resolve: Boolean): Boolean

    fun resolve(): Boolean

    fun getJson(): JsonElement?

    companion object {
        fun isReferenceNode(node: JsonElement): Boolean {
            return node is JsonObject && node.containsKey("\$ref") && node["\$ref"]?.let { (it is JsonPrimitive) && it.isString } == true
        }
    }
}

class InternalReference : Reference {

    override val refString: String

    override val normalizedRef: String?

    override val pointer: JsonPointer?

    override val manager: ReferenceManager

    private val root: JsonElement?

    override val fragment: String?

    override val isResolved: Boolean
        get() = true

    override fun isInvalid(resolve: Boolean): Boolean {
        return getJson() == null
    }

    override fun resolve(): Boolean {
        return getJson() != null
    }

    override var invalidReason: ResolutionException? = null
        private set

    constructor(
        root: JsonElement,
        refString: String,
        fragment: String?,
        normalizedRef: String?,
        manager: ReferenceManager
    ) {
        this.root = root
        this.refString = refString
        this.fragment = fragment
        this.normalizedRef = normalizedRef
        this.manager = manager
        pointer = JsonPointer(refString.removePrefix("#"))
    }

    private fun JsonElement.getRefString(): String? {
        return ((this as? JsonObject)?.get("\$ref") as? JsonPrimitive)?.contentOrNull
    }

    override fun getJson(): JsonElement? {
        val node = root?.let { pointer?.navigate(it) }
        if (node == null) {
            invalidReason = ResolutionException(this, Throwable("couldn't navigate to pointer $pointer"))
            return null
        }
        if (Reference.isReferenceNode(node)) {
//            println("Reference inside a reference $refString ${node.getRefString()}")
            return if (node.getRefString() == refString) {
                invalidReason = ReferenceCycleException(this, this)
                null
            } else {
                manager.getReference(node).getJson().also {
                    if(it == null){
                        println("reference node inside reference is null")
                    }
                }
            }
        }
        return node
    }

}

class ReferenceImpl : Reference {

    override var refString: String
        private set

    override var normalizedRef: String? = null
        private set

    override var pointer: JsonPointer? = null
        private set

    override var manager: ReferenceManager?
        private set

    private var json: JsonElement? = null

    private var valid: Boolean? = null

    override var invalidReason: ResolutionException? = null
        private set

    constructor(refString: String, fragment: String?, normalizedRef: String?, manager: ReferenceManager) {
        this.refString = refString
        this.normalizedRef = normalizedRef
        this.manager = manager
        try {
            pointer = if (fragment != null) JsonPointer(fragment) else null
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

    override val fragment: String
        get() = if (pointer != null) pointer.toString() else ""

    override val isResolved: Boolean
        get() = valid != null

    override fun isInvalid(resolve: Boolean): Boolean {
        if (resolve) resolve()
        return valid?.let { !it } ?: false
    }

    override fun getJson(): JsonElement? {
        resolve()
        return json
    }

    override fun resolve(): Boolean {
        val visited: MutableSet<String?> = HashSet()
        var current: Reference = this
        while (valid == null) {
            val normalized = current.normalizedRef
            if (visited.contains(normalized)) {
                return failResolve(null, ReferenceCycleException(this, current))
            } else {
                visited.add(normalized)
            }
            var currentJson: JsonElement? = try {
                current.manager?.loadDoc()
            } catch (e: IOException) {
                return failResolve("Failed to load referenced documnet", e)
            }
            currentJson = if (current.pointer != null) current.pointer!!.navigate(currentJson!!) else currentJson
            if (currentJson != null && Reference.isReferenceNode(currentJson)) {
                current = manager?.getReference(currentJson)!!
                if (current.isInvalid(false)) {
                    return failResolve("Invalid reference in reference chain", current.invalidReason)
                }
            } else {
                json = currentJson
                valid = true
                if (currentJson == null) {
                    failResolve("Referenced node not present in JSON document")
                }
            }
        }
        return (valid ?: false).also {
            if (!it) {
                println(pointer)
                invalidReason?.printStackTrace()
            }
        }
    }

    private fun failResolve(msg: String?, e: Exception? = null): Boolean {
        valid = false
        invalidReason = if (e is ResolutionException && msg == null) e else {
            ResolutionException(msg, this, e)
        }
        return false
    }

}
