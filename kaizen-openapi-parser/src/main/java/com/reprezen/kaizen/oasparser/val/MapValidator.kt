/*******************************************************************************
 * Copyright (c) 2017 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ModelSolv, Inc. - initial API and implementation and/or initial documentation
 */
package com.reprezen.kaizen.oasparser.`val`

import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.Overlay

class MapValidator<T>(private val valueValidator: Validator<T>?) : ValidatorBase<Map<String, T>>() {
    override fun runValidations() {
        val mapOverlay: MapOverlay<T> = Overlay.getMapOverlay(value)
        if (valueValidator != null) {
            for (key in mapOverlay.keySet()) {
                valueValidator.validate(Overlay.of<T>(mapOverlay, key))
            }
        }
    }
}