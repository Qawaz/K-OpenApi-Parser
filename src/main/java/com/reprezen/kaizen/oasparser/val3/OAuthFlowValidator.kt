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

import com.reprezen.kaizen.oasparser.model3.OAuthFlow
import com.reprezen.kaizen.oasparser.ovl3.OAuthFlowImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class OAuthFlowValidator : ObjectValidatorBase<OAuthFlow>() {
    override fun runObjectValidations() {
        val oauthFlow: OAuthFlow = value.getOverlay() as OAuthFlow
        validateUrlField(OAuthFlowImpl.F_authorizationUrl, true, true, false)
        validateUrlField(OAuthFlowImpl.F_tokenUrl, true, true, false)
        validateUrlField(OAuthFlowImpl.F_refreshUrl, false, true, false)
        validateMapField<String>(OAuthFlowImpl.F_scopes, true, false, String::class.java, null)
        validateExtensions(oauthFlow.getExtensions())
    }
}