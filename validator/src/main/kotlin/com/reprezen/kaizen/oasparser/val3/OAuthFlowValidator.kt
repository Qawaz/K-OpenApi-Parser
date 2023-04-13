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

import com.reprezen.kaizen.oasparser.model3.OAuthFlow
import com.reprezen.kaizen.oasparser.ovl3.OAuthFlowImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class OAuthFlowValidator : ObjectValidatorBase<OAuthFlow>() {
    override fun runObjectValidations() {
        val oauthFlow: OAuthFlow = value.overlay as OAuthFlow
        validateUrlField(OAuthFlowImpl.F_authorizationUrl, required = true, allowRelative = true, allowVars = false)
        validateUrlField(OAuthFlowImpl.F_tokenUrl, true, allowRelative = true, allowVars = false)
        validateUrlField(OAuthFlowImpl.F_refreshUrl, required = false, allowRelative = true, allowVars = false)
        validateMapField<String>(
            OAuthFlowImpl.F_scopes,
            nonEmpty = true,
            unique = false,
            valueValidator = null
        )
        validateExtensions(oauthFlow.getExtensions())
    }
}