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

import com.google.common.collect.ImmutableMap
import com.google.common.io.Resources
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import org.junit.Assert
import org.junit.Test

class ExampleTest {
    private val exampleFixture: Map<String, Any> = ImmutableMap.of<String, Any>( //
        "foo", "bar",  //
        "person", ImmutableMap.of<String, Any>( //
            "name", "Paul"
        )
    )

    @Test
    fun testExample() {
        val example = model!!.getPath("/v2") //
            ?.getOperation("get") //
            ?.getResponse("203") //
            ?.getContentMediaType("application/json") //
            ?.getExample()
        Assert.assertEquals(exampleFixture, example)
    }

    @Test
    fun testExamples() {
        val examples = model!!.getPath("/v2") //
            ?.getOperation("get") //
            ?.getResponse("200") //
            ?.getContentMediaType("application/json") //
            ?.getExamples()
        Assert.assertEquals(1, examples?.size)
        Assert.assertTrue(examples?.containsKey("foo") ?: false)
        val example = examples?.get("foo")
        Assert.assertEquals(exampleFixture, example!!.getValue())
        Assert.assertEquals("First Example", example.getSummary())
        Assert.assertEquals("An Example", example.getDescription())
    }

    @Test
    fun testValidate() {
        Assert.assertTrue(model!!.isValid())
    }

    companion object {
        private var model: OpenApi3? = OpenApiParser().parse(Resources.getResource("models/examplesTest.yaml"), true) as OpenApi3
    }
}