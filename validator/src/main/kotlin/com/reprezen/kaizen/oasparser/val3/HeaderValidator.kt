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

import com.reprezen.kaizen.oasparser.model3.Example
import com.reprezen.kaizen.oasparser.model3.Header
import com.reprezen.kaizen.oasparser.model3.MediaType
import com.reprezen.kaizen.oasparser.model3.Schema
import com.reprezen.kaizen.oasparser.ovl3.HeaderImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase
import com.reprezen.kaizen.oasparser.validate.msg.Messages.Companion.msg

class HeaderValidator : ObjectValidatorBase<Header>() {
    override fun runObjectValidations() {
        val header = value.getOverlay() as Header
        validateStringField(HeaderImpl.F_description, false)
        validateBooleanField(HeaderImpl.F_deprecated, false)
        validateBooleanField(HeaderImpl.F_allowEmptyValue, false)
        validateBooleanField(HeaderImpl.F_explode, false)
        validateField<Any>(HeaderImpl.F_example, false, Any::class.java, null)
        validateMapField<Example>(HeaderImpl.F_examples, false, false, Example::class.java, ExampleValidator())
        validateStringField(HeaderImpl.F_style, false, Regexes.STYLE_REGEX)
        checkAllowReserved(header)
        // TODO Q: Should schema be required in header object?
        validateField<Schema>(HeaderImpl.F_schema, false, Schema::class.java, SchemaValidator())
        validateMapField<MediaType>(
            HeaderImpl.F_contentMediaTypes,
            false,
            false,
            MediaType::class.java,
            MediaTypeValidator()
        )
        validateExtensions(header.getExtensions())
        checkContentType()
        // TODO validate that location-related values (like style) are consistent with
        // header location
        // TODO warn if this appears on a request body whose media type is not multipart
    }

    private fun checkAllowReserved(header: Header) {
        // TODO Q: Shouldn't "allowReserved" be disallowed for headers, as are "name"
        // and "in"?
        if (header.isAllowReserved()) {
            results.addWarning(msg(OpenApi3Messages.NonQryAllowRsvd, value.pathInParent!!, "header"), value)
        }
    }

    private fun checkContentType() {
        if (value.pathInParent.equals("Content-Type", ignoreCase = true)) {
            results.addWarning(msg(OpenApi3Messages.IgnContType), value)
        }
    }
}