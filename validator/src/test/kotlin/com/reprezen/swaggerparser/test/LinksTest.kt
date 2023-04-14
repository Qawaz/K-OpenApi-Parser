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
import org.junit.Test

class LinksTest : Assert() {

    val isJson: Boolean get() = true

    private val linksTestRes: String =
        "models/" + (if (isJson) "json" else "yaml") + "/linksTest" + (if (isJson) ".json" else ".yaml")

    @Test
    @Throws(Exception::class)
    fun testLinks() {
        val model = OpenApiParser().parse(Resources.getResource(linksTestRes))
        assertEquals("mergePullRequest", model.getLink("PullRequestMerge")?.getOperationId())
        assertEquals(
            "#/paths/~12.0~1repositories~1%7Busername%7D~1%7Bslug%7D~1pullrequests~1%7Bpid%7D~1merge",
            model.getLink("PullRequestMerge")?.getOperationRef()
        )
        assertEquals("http://localhost", model.getLink("PullRequestMerge")?.getServer()?.getUrl())
        assertEquals("server", model.getLink("PullRequestMerge")?.getServer()?.getDescription())
    }
}