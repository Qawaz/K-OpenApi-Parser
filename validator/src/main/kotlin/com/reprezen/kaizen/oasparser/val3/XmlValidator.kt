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

import com.reprezen.kaizen.oasparser.model3.Xml
import com.reprezen.kaizen.oasparser.ovl3.XmlImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class XmlValidator : ObjectValidatorBase<Xml>() {
    override fun runObjectValidations() {
        val xml: Xml = value.overlay as Xml
        validateStringField(XmlImpl.F_name, false)
        validateStringField(XmlImpl.F_prefix, false)
        validateBooleanField(XmlImpl.F_attribute, false)
        validateBooleanField(XmlImpl.F_wrapped, false)
        validateUrlField(XmlImpl.F_namespace, false, true, false)
        validateExtensions(xml.getExtensions())
    }
}