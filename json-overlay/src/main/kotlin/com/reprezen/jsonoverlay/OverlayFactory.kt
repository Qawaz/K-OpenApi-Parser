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

abstract class OverlayFactory<V> {

    fun create(value: V?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): JsonOverlay<V> {
        return _create(value, parent, refMgr)
    }

    fun tryCreate(value: Any, parent: JsonOverlay<*>?, refMgr: ReferenceManager): JsonOverlay<*> {
        @Suppress("UNCHECKED_CAST")
        return _create(value as? V, parent = parent, refMgr = refMgr)
    }

    fun create(json: JsonElement, parent: JsonOverlay<*>?, refMgr: ReferenceManager): JsonOverlay<V> {
        var overlay: JsonOverlay<V>?
        if (Reference.isReferenceNode(json)) {
            val reference = refMgr.getReference(json)
            val refOverlay = RefOverlay(reference, null, this, refMgr)
            overlay = refOverlay.overlay
            if (overlay == null) {
                overlay = _create(null as V?, parent, refMgr)
            }
            overlay = (overlay.factory)._create(null as V?, parent, refMgr)
            overlay._setReference(refOverlay)
        } else {
            val existing = refMgr.registry.getOverlay(json, signature!!)
            if (existing != null) {
                overlay = existing as JsonOverlay<V>
                if (parent != null) {
                    overlay._setParent(parent)
                }
            } else {
                overlay = _create(json, parent, refMgr)
                overlay._setParent(parent)
                refMgr.registry.register(json, signature!!, overlay)
//                overlay._elaborate()
            }
        }
        return overlay
    }

    open val signature: String?
        get() = getOverlayClass().simpleName

    protected open val isExtendedType: Boolean
        protected get() = false

    abstract fun getOverlayClass(): Class<out JsonOverlay<in V>>

    protected abstract fun _create(value: V?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): JsonOverlay<V>

    protected abstract fun _create(
        json: JsonElement,
        parent: JsonOverlay<*>?,
        refMgr: ReferenceManager
    ): JsonOverlay<V>
}
