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

import com.reprezen.jsonoverlay.JsonOverlay.Companion._jsonObject

class Builder<V>(private val factory: OverlayFactory<*>, private val modelMember: JsonOverlay<*>?) {
    fun build(): JsonOverlay<V> {
        val refMgr =
            modelMember?.refMgr ?: ReferenceManager()
        return factory.create(_jsonObject(), null, refMgr) as JsonOverlay<V>
    }
}
