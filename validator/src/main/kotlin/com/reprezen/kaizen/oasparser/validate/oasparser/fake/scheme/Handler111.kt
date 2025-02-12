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
package com.reprezen.kaizen.oasparser.validate.oasparser.fake.scheme

import java.io.IOException
import java.net.URL
import java.net.URLConnection
import java.net.URLStreamHandler

class Handler : URLStreamHandler() {
    @Throws(IOException::class)
    override fun openConnection(u: URL): URLConnection? {
        return null
    }
}