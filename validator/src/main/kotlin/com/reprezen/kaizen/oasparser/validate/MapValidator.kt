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

import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.validate.msg.Messages
import java.util.HashSet

class MapValidator<T>(
    private val valueValidator: Validator<T>?,
    private val isNonEmpty: Boolean,
    private val isUnique: Boolean
) : ValidatorBase<MutableMap<String, T>>() {
    
    override fun runValidations() {
        if (valueValidator != null) validateValues(valueValidator)
        checkMapNotEmpty(value, isNonEmpty)
        checkMapUnique(value, isUnique)
    }

    private fun <X> checkMapNotEmpty(list: Overlay<MutableMap<String, X>>, nonEmpty: Boolean) {
        if (nonEmpty) {
            val mapOverlay: MapOverlay<X> = Overlay.getMapOverlay(list)!!
            if (!list.isPresent) {
                if (mapOverlay.size() == 0) {
                    results.addError(Messages.msg(BaseValidationMessages.EmptyList), list)
                }
            }
        }
    }

    private fun <X> checkMapUnique(map: Overlay<MutableMap<String, X>>, unique: Boolean) {
        if (unique) {
            val mapOverlay: MapOverlay<X> = Overlay.getMapOverlay(map)!!
            val seen: MutableSet<X> = HashSet()
            for (key in mapOverlay.keySet()) {
                val value = mapOverlay[key]!!
                if (seen.contains(value)) {
                    results.addError(
                        Messages.msg(BaseValidationMessages.DuplicateValue, value, key),
                        Overlay.of<X>(mapOverlay, key)
                    )
                } else {
                    seen.add(value)
                }
            }
        }
    }

    private fun validateValues(valueValidator: Validator<T>) {
        val mapOverlay = Overlay.getMapOverlay(value) ?: return
        if (ValidationContext.visitIfUnvisited(mapOverlay)) {
            for (key in mapOverlay.keySet()) {
                val value = Overlay.of<T>(mapOverlay, key)
                valueValidator.validate(value)
            }
        }
    }

}