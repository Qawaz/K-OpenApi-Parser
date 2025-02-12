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

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.kaizen.oasparser.ovl3.OperationImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class OperationValidator : ObjectValidatorBase<Operation>() {
    override fun runObjectValidations() {
        val operation = value.overlay as Operation
        validateListField<String>(OperationImpl.F_tags, false, false, null)
        validateStringField(OperationImpl.F_summary, false)
        validateStringField(OperationImpl.F_description, false)
        validateField<ExternalDocs>(
            OperationImpl.F_externalDocs,
            false,
            ExternalDocsValidator()
        )
        // TODO Q: Not marked as required in spec, but spec says they all must be unique
        // within the API. Seems like it should be required.
        validateStringField(OperationImpl.F_operationId, false)
        validateListField<Parameter>(
            OperationImpl.F_parameters,
            false,
            false,
            ParameterValidator()
        )
        validateField<RequestBody>(OperationImpl.F_requestBody, false, RequestBodyValidator())
        validateMapField<Response>(OperationImpl.F_responses, true, false, ResponseValidator())
        validateMapField<Callback>(OperationImpl.F_callbacks, false, false, CallbackValidator())
        validateListField<SecurityRequirement>(
            OperationImpl.F_securityRequirements, false, false, SecurityRequirementValidator()
        )
        validateListField<Server>(OperationImpl.F_servers, false, false, ServerValidator())
        validateExtensions(operation.getExtensions())
    }
}