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

import com.reprezen.kaizen.oasparser.model3.Contact
import com.reprezen.kaizen.oasparser.model3.Info
import com.reprezen.kaizen.oasparser.model3.License
import com.reprezen.kaizen.oasparser.ovl3.InfoImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class InfoValidator : ObjectValidatorBase<Info>() {
    override fun runObjectValidations() {
        val info = value.getOverlay() as Info
        validateStringField(InfoImpl.F_title, true)
        validateStringField(InfoImpl.F_description, false)
        validateUrlField(InfoImpl.F_termsOfService, false, true, false)
        validateField<Contact>(InfoImpl.F_contact, false, Contact::class.java, ContactValidator())
        validateField<License>(InfoImpl.F_license, false, License::class.java, LicenseValidator())
        validateStringField(InfoImpl.F_version, true)
        validateExtensions(info.getExtensions())
    }
}