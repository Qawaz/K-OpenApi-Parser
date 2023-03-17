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

import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.model3.SecurityParameter
import com.reprezen.kaizen.oasparser.model3.SecurityRequirement
import com.reprezen.kaizen.oasparser.ovl3.SecurityRequirementImpl
import com.reprezen.kaizen.oasparser.`val`.ObjectValidatorBase
import com.reprezen.kaizen.oasparser.`val`.msg.Messages.Companion.msg

class SecurityRequirementValidator : ObjectValidatorBase<SecurityRequirement>() {
    override fun runObjectValidations() {
        val requirements: Overlay<Map<String, SecurityParameter>> = validateMapField<SecurityParameter>(
            SecurityRequirementImpl.F_requirements, false, false,
            SecurityParameter::class.java, SecurityParameterValidator()
        )
        checkAllSchemesDefined(requirements)
    }

    fun checkAllSchemesDefined(requirements: Overlay<Map<String, SecurityParameter>>) {
        val model: OpenApi3 = value.getModel<OpenApi3>()
        val definedSchemes: Set<String> = model.getSecuritySchemes().keys
        val mapOverlay: MapOverlay<SecurityParameter?> = Overlay.getMapOverlay(requirements)
        for (name in mapOverlay.keySet()) {
            if (!definedSchemes.contains(name)) {
                results.addError(
                    msg(OpenApi3Messages.UnkSecScheme, name),
                    Overlay.of<SecurityParameter>(mapOverlay, name)
                )
            }
        }
    }
}