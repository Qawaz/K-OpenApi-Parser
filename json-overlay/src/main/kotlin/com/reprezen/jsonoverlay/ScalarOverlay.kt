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

abstract class ScalarOverlay<V> : JsonOverlay<V> {

    protected constructor(
        factory: OverlayFactory<V>,
        refMgr: ReferenceManager
    ) : super(factory,refMgr)

    protected constructor(
        json: JsonNode,
        parent: JsonOverlay<*>?,
        factory: OverlayFactory<V>,
        refMgr: ReferenceManager
    ) : super(
        json, parent, factory, refMgr
    )

    protected constructor(
        value: V?,
        parent: JsonOverlay<*>?,
        factory: OverlayFactory<V>?,
        refMgr: ReferenceManager?
    ) : super(value, parent, factory!!, refMgr!!)

    public override fun _findInternal(path: JsonPointer?): JsonOverlay<*>? {
        return null
    }
}
