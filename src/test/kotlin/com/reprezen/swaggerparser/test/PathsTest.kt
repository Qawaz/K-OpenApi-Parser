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
package com.reprezen.swaggerparser.test

import com.google.common.io.Resources
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import org.junit.Assert
import org.junit.Test

class PathsTest {
    @Test
    @Throws(Exception::class)
    fun testGetPaths() {
        val model = OpenApiParser().parse(Resources.getResource("models/pathsTest.yaml"), false) as OpenApi3
        Assert.assertEquals(2, model.paths.size.toLong())
        Assert.assertTrue(model.hasPath("/"))
        Assert.assertTrue(model.hasPath("/v2"))
        Assert.assertEquals("/", model.getPath("/").pathString)
        Assert.assertEquals("/v2", model.getPath("/v2").pathString)
        Assert.assertNotNull(model.getPath("/"))
        Assert.assertNotNull(model.getPath("/v2"))
    }
}