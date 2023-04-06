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

import com.fasterxml.jackson.databind.JsonNode

class RefOverlay<V> {

    private var reference: Reference
    private var parent: JsonOverlay<*>?
    private var target: JsonOverlay<V>? = null
    private var factory: OverlayFactory<V>
    private var refMgr: ReferenceManager

    constructor(json: JsonNode, parent: JsonOverlay<*>?, factory: OverlayFactory<V>, refMgr: ReferenceManager) {
        reference = refMgr.getReference(json)
        this.parent = parent // parent of reference, not parent of referent
        this.factory = factory
        this.refMgr = refMgr
    }

    constructor(reference: Reference, parent: JsonOverlay<*>?, factory: OverlayFactory<V>, refMgr: ReferenceManager) {
        this.reference = reference
        this.parent = parent
        this.factory = factory
        this.refMgr = refMgr
    }

    val overlay: JsonOverlay<V>?
        /* package */
        get() {
            if (!reference.isResolved) {
                reference.resolve()
            }
            if (target == null && reference.isValid()) {
                val registry = refMgr.registry
                run {
                    val castTarget = registry.getOverlay(
                        reference.normalizedRef!!,
                        factory.signature!!
                    ) as JsonOverlay<V>?
                    this.target = castTarget
                }
                if (target == null) {
                    val castTarget = registry.getOverlay(
                        reference.getJson()!!,
                        factory.signature!!
                    ) as JsonOverlay<V>?
                    target = castTarget
                }
                if (target == null) {
                    target = factory.create(reference.getJson()!!, null, reference.manager!!)
                    target!!._setCreatingRef(reference)
                    refMgr.registry.register(reference.normalizedRef!!, factory.signature!!, target!!)
                }
            }
            return target
        }

    fun _get(elaborate: Boolean): V? {
        overlay
        return if (target != null) target!!._get(elaborate) else null
    }

    fun _getReference(): Reference {
        return reference
    }

    fun _getParent(): JsonOverlay<*>? {
        return parent
    }
}
