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

class MapValidator<T>(private val valueValidator: Validator<T>?) : ValidatorBase<MutableMap<String, T>>() {
    override fun runValidations() {
        val mapOverlay = Overlay.getMapOverlay(value) ?: return
        if (valueValidator != null) {
            for (key in mapOverlay.keySet()) {
                valueValidator.validate(Overlay.of<T>(mapOverlay, key))
            }
        }
    }
}