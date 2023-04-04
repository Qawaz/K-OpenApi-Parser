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

import com.fasterxml.jackson.databind.JsonNode

abstract class OverlayFactory<V> {

    private val overlayClass: Class<out IJsonOverlay<in V>> = getOverlayClass()

    fun create(value: V?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): JsonOverlay<V> {
        val overlay = _create(value, parent, refMgr)
        overlay._elaborate(true)
        return overlay
    }

    fun create(json: JsonNode?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): JsonOverlay<V> {
        var overlay: JsonOverlay<V>?
        if (Reference.isReferenceNode(json)) {
            val reference = refMgr.getReference(json!!)
            val refOverlay = RefOverlay(reference, null, this, refMgr)
            overlay = refOverlay.overlay
            if (overlay == null) {
                overlay = _create(null as V?, parent, refMgr)
            }
            overlay = (overlay._getFactory() as OverlayFactory<V>?)!!._create(null as V?, parent, refMgr)
            overlay._setReference(refOverlay)
        } else {
            val existing = refMgr.registry.getOverlay(json!!, signature!!)
            if (existing != null) {
                overlay = existing as JsonOverlay<V>
                if (parent != null) {
                    overlay._setParent(parent)
                }
            } else {
                overlay = _create(json!!, parent, refMgr)
                overlay!!._setParent(parent)
                refMgr.registry.register(json, signature!!, overlay)
                if (!overlay._isElaborated()) {
                    overlay._elaborate(true)
                }
            }
        }
        return overlay
    }

    fun isCompatible(overlay: JsonOverlay<*>): Boolean {
        return overlayClass.isAssignableFrom(overlay.javaClass)
    }

    open val signature: String?
        get() = getOverlayClass().simpleName

    protected open val isExtendedType: Boolean
        protected get() = false

    abstract fun getOverlayClass(): Class<out JsonOverlay<in V>>

    protected abstract fun _create(value: V?, parent: JsonOverlay<*>?, refMgr: ReferenceManager): JsonOverlay<V>

    protected abstract fun _create(
        json: JsonNode,
        parent: JsonOverlay<*>?,
        refMgr: ReferenceManager
    ): JsonOverlay<V>
}
