/*******************************************************************************
 * Copyright (c) 2023 WaqasTahir, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * WaqasTahir - initial API and implementation and/or initial documentation
 */
package com.reprezen.kaizen.oasparser.val3

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.kaizen.oasparser.ovl3.OperationImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class OperationValidator : ObjectValidatorBase<Operation>() {
    override fun runObjectValidations() {
        val operation = value.getOverlay() as Operation
        validateListField<String>(OperationImpl.F_tags, false, false, String::class.java, null)
        validateStringField(OperationImpl.F_summary, false)
        validateStringField(OperationImpl.F_description, false)
        validateField<ExternalDocs>(
            OperationImpl.F_externalDocs,
            false,
            ExternalDocs::class.java,
            ExternalDocsValidator()
        )
        // TODO Q: Not marked as required in spec, but spec says they all must be unique
        // within the API. Seems like it should be required.
        validateStringField(OperationImpl.F_operationId, false)
        validateListField<Parameter>(
            OperationImpl.F_parameters,
            false,
            false,
            Parameter::class.java,
            ParameterValidator()
        )
        validateField<RequestBody>(OperationImpl.F_requestBody, false, RequestBody::class.java, RequestBodyValidator())
        validateMapField<Response>(OperationImpl.F_responses, true, false, Response::class.java, ResponseValidator())
        validateMapField<Callback>(OperationImpl.F_callbacks, false, false, Callback::class.java, CallbackValidator())
        validateListField<SecurityRequirement>(
            OperationImpl.F_securityRequirements, false, false, SecurityRequirement::class.java,
            SecurityRequirementValidator()
        )
        validateListField<Server>(OperationImpl.F_servers, false, false, Server::class.java, ServerValidator())
        validateExtensions(operation.getExtensions())
    }
}