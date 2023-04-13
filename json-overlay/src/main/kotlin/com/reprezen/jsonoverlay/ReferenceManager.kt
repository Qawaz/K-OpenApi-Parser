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

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.IOException
import java.net.MalformedURLException
import java.net.URI
import java.net.URISyntaxException
import java.net.URL
import java.util.*

class ReferenceManager {

    var registry: ReferenceRegistry
        private set

    private var docUrl: URL?
    private var doc: JsonElement? = null


    @JvmOverloads
    constructor(rootUrl: URL? = null, loader: JsonLoader? = null) {
        registry = ReferenceRegistry(loader)
        docUrl = if (rootUrl != null) normalize(rootUrl, true) else null
        if (docUrl != null) {
            registry.registerManager(docUrl!!, this)
        }
    }

    constructor(rootUrl: URL?, preloadedDoc: JsonElement?, loader: JsonLoader?) : this(rootUrl, loader) {
        doc = preloadedDoc
    }

    private constructor(baseUrl: URL?, registry: ReferenceRegistry) {
        docUrl = baseUrl
        this.registry = registry
    }

    fun getManagerFor(url: URL?): ReferenceManager {
        val normalized = normalize(url, true)
        var manager = registry.getManager(normalized!!)
        if (manager == null) {
            manager = ReferenceManager(normalized, registry)
            registry.registerManager(normalized, manager)
        }
        return manager
    }

    fun getReference(refNode: JsonElement): Reference {
        return getReference(refNode.jsonObject["\$ref"]!!.jsonPrimitive.content)
    }

    fun getReference(refString: String): Reference {
        return try {
            val url = URL(docUrl, refString)
            val fragment = url.ref
            val manager = getManagerFor(url)
            if(refString.startsWith('#')){
                InternalReference(doc!!,refString, fragment, normalize(url, false).toString(), manager)
//                ReferenceImpl(refString, fragment, normalize(url, false).toString(), manager)
            }else {
                ReferenceImpl(refString, fragment, normalize(url, false).toString(), manager)
            }
        } catch (e: MalformedURLException) {
            ReferenceImpl(refString, ResolutionException(null, e), null)
        }
    }

    @Throws(IOException::class)
    fun loadDoc(): JsonElement {
        if (doc == null) {
            doc = registry.loadDoc(docUrl!!)
        }
        return doc!!
    }

    companion object {
        private fun normalize(url: URL?, noFrag: Boolean): URL? {
            return if (url != null) {
                var urlString = url.toString()
                val fragPos = urlString.indexOf("#")
                if (noFrag && fragPos >= 0) {
                    urlString = urlString.substring(0, fragPos)
                }
                try {
                    URI(urlString).normalize().toURL()
                } catch (e: MalformedURLException) {
                    // oh well, we tried...
                    url
                } catch (e: URISyntaxException) {
                    url
                }
            } else {
                null
            }
        }
    }
}
