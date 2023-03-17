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

class LinksTest {
    @Test
    @Throws(Exception::class)
    fun testLinks() {
        val model = OpenApiParser().parse(Resources.getResource("models/linksTest.yaml"), false) as OpenApi3
        Assert.assertNotNull(model.getLink("PullRequestMerge").operationId)
        Assert.assertNotNull(model.getLink("PullRequestMerge").operationRef)
        Assert.assertNotNull(model.getLink("PullRequestMerge").server)
        Assert.assertEquals("http://localhost", model.getLink("PullRequestMerge").server.url)
        Assert.assertEquals("server", model.getLink("PullRequestMerge").server.description)
    }
}