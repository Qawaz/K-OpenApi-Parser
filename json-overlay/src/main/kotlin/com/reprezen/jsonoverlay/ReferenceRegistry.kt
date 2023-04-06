/*********************************************************************
 * Copyright (c) 2017 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * ModelSolv, Inc.
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.databind.JsonNode
import java.io.IOException
import java.net.URL
import java.util.*

class ReferenceRegistry @JvmOverloads constructor(loader: JsonLoader? = null) {
    private val managers: MutableMap<String, ReferenceManager> = HashMap()
    private val loader: JsonLoader
    private val overlaysByRef: MutableMap<Pair<String, String>, JsonOverlay<*>> = HashMap()

    // can't use Pair here because we need to index by JsonNode identity, not
    // using
    // its equals impl
    private val overlaysByJson: MutableMap<JsonNode, MutableMap<String, JsonOverlay<*>>> = IdentityHashMap()

    init {
        this.loader = loader ?: JsonLoader()
    }

    fun getManager(baseUrl: URL): ReferenceManager? {
        return managers[baseUrl.toString()]
    }

    fun registerManager(baseUrl: URL, manager: ReferenceManager) {
        managers[baseUrl.toString()] = manager
    }

    @Throws(IOException::class)
    fun loadDoc(url: URL?): JsonNode {
        return loader.load(url!!)
    }

    fun getOverlay(normalizedRef: String, factorySig: String): JsonOverlay<*>? {
        return overlaysByRef[Pair(normalizedRef, factorySig)]
    }

    fun register(normalizedRef: String, factorySig: String, overlay: JsonOverlay<*>) {
        overlaysByRef[Pair(normalizedRef, factorySig)] = overlay
    }

    fun getOverlay(json: JsonNode, factorySig: String): JsonOverlay<*>? {
        val overlaysBySig: Map<String, JsonOverlay<*>>? = overlaysByJson[json]
        return overlaysBySig?.get(factorySig)
    }

    fun register(json: JsonNode, factorySig: String, overlay: JsonOverlay<*>) {
        // can't share boolean or nulls because they don't have a public
        // constructor,
        // and factory uses shared instances
        if (!json.isMissingNode && !json.isBoolean && !json.isNull) {
            if (!overlaysByJson.containsKey(json)) {
                overlaysByJson[json] = HashMap()
            }
            overlaysByJson[json]!![factorySig] = overlay
        }
    }

    fun getPositionInfo(docUrl: String?, pointer: JsonPointer?): Optional<PositionInfo> {
        return loader.getPositionInfo(docUrl!!, pointer!!)
    }
}