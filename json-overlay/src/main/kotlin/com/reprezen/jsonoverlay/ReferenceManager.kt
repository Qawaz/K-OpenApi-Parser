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

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.*
import java.io.IOException
import java.net.MalformedURLException
import java.net.URI
import java.net.URISyntaxException
import java.net.URL
import java.util.*

class ReferenceManager(
    rootUrl: URL? = null,
    preloadedDoc: JsonElement? = null,
    registry: ReferenceRegistry = ReferenceRegistry(),
    val loader : DocumentLoader = DocumentLoader.Default
) {

    var registry: ReferenceRegistry = registry
        private set

    private var docUrl: URL?
    private var doc: JsonElement? = preloadedDoc

    init {
        docUrl = if (rootUrl != null) normalize(rootUrl, true) else null
        if (docUrl != null) {
            registry.registerManager(docUrl!!, this)
        }
    }

    fun getManagerFor(url: URL): ReferenceManager {
        val normalized = normalize(url, true)
        var manager = registry.getManager(normalized!!)
        if (manager == null) {
            manager = ReferenceManager(url,null, registry)
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
            val manager = getManagerFor(url)
            if (refString.startsWith('#')) {
                InternalReference(doc!!, refString, docUrl, manager)
//                ReferenceImpl(refString, fragment, normalize(url, false).toString(), manager)
            } else {
                ReferenceImpl(refString, docUrl,url.ref, manager)
            }
        } catch (e: MalformedURLException) {
            ReferenceImpl(refString, docUrl, ResolutionException(null, e), null)
        }
    }

    @Throws(IOException::class)
    fun loadDoc(): JsonElement {
        if (doc == null) {
            doc = loader.load(docUrl!!)
        }
        return doc!!
    }

    companion object {
        internal fun normalize(url: URL, noFrag: Boolean): URL? {
            var urlString = url.toString()
            if (noFrag) {
                val fragPos = urlString.indexOf("#")
                if (fragPos >= 0) {
                    urlString = urlString.substring(0, fragPos)
                }
            }
            return try {
                URI(urlString).normalize().toURL()
            } catch (e: MalformedURLException) {
                // oh well, we tried...
                url
            } catch (e: URISyntaxException) {
                url
            }

        }
    }
}
