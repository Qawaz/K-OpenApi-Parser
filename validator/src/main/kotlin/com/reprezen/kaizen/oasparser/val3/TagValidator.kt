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

import com.reprezen.kaizen.oasparser.model3.ExternalDocs
import com.reprezen.kaizen.oasparser.model3.Tag
import com.reprezen.kaizen.oasparser.ovl3.TagImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class TagValidator : ObjectValidatorBase<Tag>() {
    override fun runObjectValidations() {
        val tag = value.getOverlay() as Tag
        validateStringField(TagImpl.F_name, true)
        validateStringField(TagImpl.F_description, false)
        validateField<ExternalDocs>(TagImpl.F_externalDocs, false, ExternalDocs::class.java, ExternalDocsValidator())
        validateExtensions(tag.getExtensions())
    }
}