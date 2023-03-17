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

import com.reprezen.jsonoverlay.ListOverlay
import com.reprezen.jsonoverlay.Overlay

class ListValidator<T>(var itemValidator: Validator<T>?) : ValidatorBase<List<T>>() {
    override fun runValidations() {
        if (itemValidator != null) {
            val list: ListOverlay<T> = Overlay.getListOverlay(value)
            for (i in 0 until list.size()) {
                itemValidator!!.validate(Overlay.of<T>(list, i))
            }
        }
    }
}