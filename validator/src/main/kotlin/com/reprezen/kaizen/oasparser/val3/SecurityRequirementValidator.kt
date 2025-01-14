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

import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.model3.SecurityParameter
import com.reprezen.kaizen.oasparser.model3.SecurityRequirement
import com.reprezen.kaizen.oasparser.ovl3.SecurityRequirementImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase
import com.reprezen.kaizen.oasparser.validate.msg.Messages.Companion.msg

class SecurityRequirementValidator : ObjectValidatorBase<SecurityRequirement>() {
    override fun runObjectValidations() {
        val requirements: Overlay<MutableMap<String, SecurityParameter>> = validateMapField<SecurityParameter>(
            SecurityRequirementImpl.F_requirements, false, false,
            SecurityParameterValidator()
        )
        checkAllSchemesDefined(requirements)
    }

    fun checkAllSchemesDefined(requirements: Overlay<MutableMap<String, SecurityParameter>>) {
        val model: OpenApi3 = value.getModel<OpenApi3>()!!
        val definedSchemes: Set<String> = model.getSecuritySchemes().keys
        val mapOverlay: MapOverlay<SecurityParameter> = Overlay.getMapOverlay(requirements)!!
        for (name in mapOverlay.keys) {
            if (!definedSchemes.contains(name)) {
                results.addError(
                    msg(OpenApi3Messages.UnkSecScheme, name),
                    Overlay.of<SecurityParameter>(mapOverlay, name)
                )
            }
        }
    }
}