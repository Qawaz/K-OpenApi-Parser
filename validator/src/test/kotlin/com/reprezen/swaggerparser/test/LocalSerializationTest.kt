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

import com.reprezen.jsonoverlay.*
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.model3.Schema
import com.reprezen.kaizen.oasparser.ovl3.OpenApi3Impl
import com.reprezen.kaizen.oasparser.ovl3.SchemaImpl
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert
import org.junit.Test
import java.util.*

class LocalSerializationTest : Assert() {

    private val isJson: Boolean get() = true
    private val extension: String get() = if (isJson) "json" else "yaml"
    private val folderName: String get() = if (isJson) "json" else "yaml"

    @Throws(Exception::class)
    private fun parseLocalModel(name: String): OpenApi3 {
        val url = LocalSerializationTest::class.java.getResource("/models/$folderName/$name.$extension")!!
        return OpenApiParser().parse(url)
    }

    @Test
    fun localModelCanBeParsed() {
        val model = parseLocalModel("invalidInput")
        assertEquals("description", model.getInfo()?.getDescription())
    }

    @Test
    fun testRefSchemaIssue() {
        val model = parseLocalModel("refSchemaIssue")
        val schema = model.getSchema("LogEntry")!! as SchemaImpl
        val properties = schema.getProperties() as MapOverlay

        // Checking using direct path
        assertTrue(schema.findByPath("properties/device_state")!!._isReference())

        // Checking using other path access methods
        assertTrue(schema._getValueOverlayByName("properties")!!.findByPath("device_state")!!._isReference())
        assertTrue(schema._getValueOverlayByPath("properties")!!.findByPath("device_state")!!._isReference())

        // Checking properties direct path
        assertTrue(properties.findByPath("device_state")!!._isReference())

        // Checking using other path access methods under properties
        assertTrue(properties._getValueOverlayByName("device_state")!!._isReference())
        assertTrue(properties._getValueOverlayByPath("device_state")!!._isReference())

        // Checking using properties overlay
        val propertiesOverlay = Overlay.of(properties)
        assertTrue(propertiesOverlay.isReference("device_state"))
        assertTrue(propertiesOverlay.find("device_state")!!._isReference())

        // Checking value is not a reference
        assertFalse((schema.getProperty("device_state") as SchemaImpl)._isReference())
    }

    @Test
    @Throws(Exception::class)
    fun toJsonNoticesChanges() {
        val model = parseLocalModel("simpleTest")
        assertEquals("simple model", model.getInfo()?.getTitle())
        assertEquals(
            "simple model",
            JsonPointer("/info/title").navigate((model as OpenApi3Impl)._toJson())!!.jsonPrimitive.content
        )
        // this changes the overlay value but does not refresh cached JSON -
        // just marks
        // it as out-of-date
        model.getInfo()?.setTitle("changed title")
        assertEquals("changed title", model.getInfo()?.getTitle())
        assertEquals("changed title", JsonPointer("/info/title").navigate(model._toJson())!!.jsonPrimitive.content)
    }

    @Test
    @Throws(Exception::class)
    fun toJsonFollowsRefs() {
        val model = parseLocalModel("simpleTest")
        val xSchema = model.getSchema("X")!!
        assertEquals(
            "#/components/schemas/Y",
            JsonPointer("/properties/y/\$ref").navigate((xSchema as SchemaImpl)._toJson())!!.jsonPrimitive.content
        )
        assertEquals(
            "integer",
            JsonPointer("/properties/y/type").navigate(xSchema._toJson(SerializationOptions.Option.FOLLOW_REFS))!!.jsonPrimitive.content
        )
    }
}