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

import com.reprezen.kaizen.oasparser.model3.MediaType
import com.reprezen.kaizen.oasparser.model3.RequestBody
import com.reprezen.kaizen.oasparser.ovl3.RequestBodyImpl
import com.reprezen.kaizen.oasparser.`val`.ObjectValidatorBase

class RequestBodyValidator : ObjectValidatorBase<RequestBody>() {
    override fun runObjectValidations() {
        val requestBody: RequestBody = value.getOverlay() as RequestBody
        validateStringField(RequestBodyImpl.F_description, false)
        validateBooleanField(RequestBodyImpl.F_required, false)
        validateMapField<MediaType>(
            RequestBodyImpl.F_contentMediaTypes,
            false,
            false,
            MediaType::class.java,
            MediaTypeValidator()
        )
        validateExtensions(requestBody.getExtensions())
    }
}