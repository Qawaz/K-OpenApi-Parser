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
package com.reprezen.swaggerparser.test

import com.google.common.io.Resources
import com.reprezen.kaizen.oasparser.OpenApiParser
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class PathsTest {

    val isJson: Boolean get() = true

    private val pathsTestRes: String =
        "models/" + (if (isJson) "json" else "yaml") + "/pathsTest" + (if (isJson) ".json" else ".yaml")

    @Test
    @Throws(Exception::class)
    fun testGetPaths() {
        val model = OpenApiParser().parse(Resources.getResource(pathsTestRes))
        assertEquals(2, model.getPaths().size.toLong())
        assertTrue(model.hasPath("/"))
        assertTrue(model.hasPath("/v2"))
        assertEquals("/", model.getPath("/")?.getPathString())
        assertNotNull(model.getPath("/v2"))
        assertNotNull(model.getPath("/"))

        // TODO these two cause recursive errors
        assertEquals("/v2", model.getPath("/v2")?.getPathString())
    }
}