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
package com.reprezen.kaizen.oasparser.val3

import com.reprezen.kaizen.oasparser.model3.Server
import com.reprezen.kaizen.oasparser.model3.ServerVariable
import com.reprezen.kaizen.oasparser.ovl3.ServerImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class ServerValidator : ObjectValidatorBase<Server>() {
    override fun runObjectValidations() {
        val server = value.getOverlay() as Server
        validateStringField(ServerImpl.F_description, false)
        validateUrlField(ServerImpl.F_url, true, true, true)
        validateMapField<ServerVariable>(
            ServerImpl.F_serverVariables,
            false,
            false,
            ServerVariable::class.java,
            ServerVariableValidator()
        )
        validateExtensions(server.getExtensions())
    }
}