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

import com.reprezen.kaizen.oasparser.model3.License
import com.reprezen.kaizen.oasparser.ovl3.LicenseImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class LicenseValidator : ObjectValidatorBase<License>() {
    override fun runObjectValidations() {
        val license = value.getOverlay() as License
        validateStringField(LicenseImpl.F_name, true)
        validateUrlField(LicenseImpl.F_url, false, true, false)
        validateExtensions(license.getExtensions())
    }
}