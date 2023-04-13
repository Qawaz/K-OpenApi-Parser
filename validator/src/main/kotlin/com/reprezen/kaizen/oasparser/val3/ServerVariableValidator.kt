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

import com.reprezen.kaizen.oasparser.model3.ServerVariable
import com.reprezen.kaizen.oasparser.ovl3.ServerVariableImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class ServerVariableValidator : ObjectValidatorBase<ServerVariable>() {
    override fun runObjectValidations() {
        val `var`: ServerVariable = value.overlay as ServerVariable
        validateStringField(ServerVariableImpl.F_description, false)
        validateListField<String>(ServerVariableImpl.F_enumValues, false, false, null)
        // TODO Q: What the heck is does the description in the spec mean???
        validateField<String>(ServerVariableImpl.F_defaultValue, true, null)
        validateExtensions(`var`.getExtensions())
    }
}