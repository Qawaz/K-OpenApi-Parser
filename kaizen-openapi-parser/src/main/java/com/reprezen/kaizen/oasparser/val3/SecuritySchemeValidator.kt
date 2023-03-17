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
import com.reprezen.kaizen.oasparser.model3.SecurityScheme
import com.reprezen.kaizen.oasparser.ovl3.SecuritySchemeImpl
import com.reprezen.kaizen.oasparser.`val`.ObjectValidatorBase


class SecuritySchemeValidator : ObjectValidatorBase<SecurityScheme>() {
    override fun runObjectValidations() {
        val securityScheme: SecurityScheme = value.getOverlay() as SecurityScheme
        validateStringField(SecuritySchemeImpl.F_description, false)
        validateStringField(SecuritySchemeImpl.F_type, true, "apiKey|http|oauth2|openIdConnect")
        val type: String = securityScheme.getType()
        // TODO should these type-specific fields be disallowed for non-applicable
        // types? (At least a warning)
        if (type != null) {
            when (type) {
                "http" -> {
                    validateStringField(SecuritySchemeImpl.F_scheme, true)
                    validateStringField(SecuritySchemeImpl.F_bearerFormat, false)
                }

                "apiKey" -> {
                    validateStringField(SecuritySchemeImpl.F_name, true)
                    validateStringField(SecuritySchemeImpl.F_in, true, "query|header|cookie")
                }

                "oauth2" -> {
                    val oauthFlowValidator = OAuthFlowValidator()
                    validateField(
                        SecuritySchemeImpl.F_implicitOAuthFlow,
                        false,
                        OAuthFlow::class.java,
                        oauthFlowValidator
                    )
                    validateField<OAuthFlow>(
                        SecuritySchemeImpl.F_passwordOAuthFlow,
                        false,
                        OAuthFlow::class.java,
                        oauthFlowValidator
                    )
                    validateField<OAuthFlow>(
                        SecuritySchemeImpl.F_clientCredentialsOAuthFlow,
                        false,
                        OAuthFlow::class.java,
                        oauthFlowValidator
                    )
                    validateField<OAuthFlow>(
                        SecuritySchemeImpl.F_authorizationCodeOAuthFlow,
                        false,
                        OAuthFlow::class.java,
                        oauthFlowValidator
                    )
                }

                "openIdConnect" -> validateUrlField(SecuritySchemeImpl.F_openIdConnectUrl, true, true, false)
            }
        }
        validateExtensions(securityScheme.getExtensions())
    }
}