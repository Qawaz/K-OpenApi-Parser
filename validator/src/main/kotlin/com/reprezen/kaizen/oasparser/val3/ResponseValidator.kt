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

import com.reprezen.kaizen.oasparser.model3.Header
import com.reprezen.kaizen.oasparser.model3.Link
import com.reprezen.kaizen.oasparser.model3.MediaType
import com.reprezen.kaizen.oasparser.model3.Response
import com.reprezen.kaizen.oasparser.ovl3.ResponseImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class ResponseValidator : ObjectValidatorBase<Response>() {
    override fun runObjectValidations() {
        val response = value.overlay as Response
        validateStringField(ResponseImpl.F_description, false)
        validateMapField<Header>(ResponseImpl.F_headers, false, false, HeaderValidator())
        validateMapField<MediaType>(
            ResponseImpl.F_contentMediaTypes,
            false,
            false,
            MediaTypeValidator()
        )
        validateMapField<Link>(ResponseImpl.F_links, false, false, LinkValidator())
        validateExtensions(response.getExtensions())
    }
}