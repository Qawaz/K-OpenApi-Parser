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
package com.reprezen.kaizen.oasparser.val3

import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.model3.Example
import com.reprezen.kaizen.oasparser.ovl3.ExampleImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase
import com.reprezen.kaizen.oasparser.validate.msg.Messages.Companion.msg

class ExampleValidator : ObjectValidatorBase<Example>() {
    override fun runObjectValidations() {
        val example = value.getOverlay() as Example
        validateStringField(ExampleImpl.F_summary, false)
        validateStringField(ExampleImpl.F_description, false)
        val valueField: Overlay<Any> = validateField<Any>(ExampleImpl.F_value, false, Any::class.java, null)
        val externalField: Overlay<String> = validateUrlField(ExampleImpl.F_externalValue, false, true, false)
        validateExtensions(example.getExtensions())
        checkExactlyOneValue(valueField, externalField)
        // TODO check that a direct value is compatible with the containng parameter or
        // media type (not entirely clear how to approach this)
    }

    private fun checkExactlyOneValue(valueField: Overlay<Any>?, externalField: Overlay<String>?) {
        val valuePresent = (valueField != null) and valueField!!.isPresent
        val externalPresent = externalField != null && externalField.isPresent
        if (valuePresent && externalPresent) {
            results.addError(msg(OpenApi3Messages.ExmplTwoValues), value)
        } else if (!valuePresent && !externalPresent) {
            // the specification doesn't actually state that a value is required, but the
            // object seems pointless without one, so we'll go with a warning
            results.addWarning(msg(OpenApi3Messages.ExampleNoValue), value)
        }
    }
}