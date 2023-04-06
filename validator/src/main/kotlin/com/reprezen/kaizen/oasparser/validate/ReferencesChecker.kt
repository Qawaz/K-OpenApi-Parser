/*******************************************************************************
 * Copyright (c) 2023 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * WaqasTahir - Contributions after 2022
 * ModelSolv, Inc. - initial API and implementation and/or initial documentation
 */
package com.reprezen.kaizen.oasparser.validate

import com.reprezen.jsonoverlay.*
import com.reprezen.kaizen.oasparser.validate.msg.Messages

object ReferencesChecker {
    fun checkReferences(list: ListOverlay<*>, results: ValidationResults) {
        val listAdapter: Overlay<*> = Overlay.of(list)
        for (i in 0 until list.size()) {
            if (listAdapter.isReference(i)) {
                checkReference(listAdapter.getReference(i)!!, results, Overlay.of(list, i))
            }
        }
    }

    fun checkReferences(map: MapOverlay<*>, results: ValidationResults) {
        val mapAdapter: Overlay<*> = Overlay.of(map)
        for (key in map.keySet()) {
            if (mapAdapter.isReference(key)) {
                checkReference(mapAdapter.getReference(key)!!, results, Overlay.of(map, key))
            }
        }
    }

    fun checkReferences(props: PropertiesOverlay<*>, results: ValidationResults) {
        if (props._isElaborated()) {
            val propsAdapter: Overlay<*> = Overlay.of(props)
            for (name in propsAdapter.propertyNames!!) {
                if (propsAdapter.isReference(name)) {
                    checkReference(propsAdapter.getReference(name)!!, results, Overlay.of(props, name, Any::class.java))
                }
            }
        }
    }

    fun checkReference(ref: Reference, results: ValidationResults, context: Overlay<*>?) {
        if (ref.isInvalid) {
            results.addError(
                Messages.Companion.msg(BaseValidationMessages.BadRef, ref.refString, ref.invalidReason!!),
                context!!
            )
        }
    }
}