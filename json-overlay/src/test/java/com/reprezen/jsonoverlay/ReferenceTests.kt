/*********************************************************************
 * Copyright (c) 2017 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * ModelSolv, Inc.
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

import com.reprezen.jsonoverlay.ResolutionException.ReferenceCycleException
import com.reprezen.jsonoverlay.model.TestModelParser
import com.reprezen.jsonoverlay.model.intf.Color
import com.reprezen.jsonoverlay.model.intf.Scalars
import com.reprezen.jsonoverlay.model.intf.TestModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ReferenceTests : Assert() {
    private var model: TestModel? = null

    @Before
    @Throws(IOException::class)
    fun setup() {
        model = TestModelParser.parse(ReferenceTests::class.java.getResource("/refTest.yaml"))
    }

    @Test
    fun testRefAccess() {
        assertEquals("Reference Test", model!!.description)
        checkScalarsValues(model!!.getScalar("s1"), sharedRoot = true, sharedValues = true)
        checkScalarsValues(model!!.getScalar("s2"), sharedRoot = false, sharedValues = true)
        checkScalarsValues(model!!.getScalar("s3"), sharedRoot = true, sharedValues = true)
        checkScalarsValues(model!!.getScalar("s4"), sharedRoot = true, sharedValues = true)
        checkScalarsValues(model!!.getScalar("s5"), sharedRoot = false, sharedValues = true)
        checkScalarsValues(model!!.getScalar("ext1"), sharedRoot = false, sharedValues = false)
        checkScalarsValues(model!!.getScalar("ext2"), sharedRoot = false, sharedValues = false)
        checkScalarsValues(model!!.getScalar("ext3"), sharedRoot = true, sharedValues = true)
    }

    private fun checkScalarsValues(s: Scalars, sharedRoot: Boolean, sharedValues: Boolean) {
        assertEquals("hello", s.stringValue)
        assertEquals(Integer.valueOf(10), s.intValue)
        assertEquals(3.1, s.numberValue)
        assertEquals(java.lang.Boolean.TRUE, s.boolValue)
        assertEquals(mutableListOf(1, 2, 3), s.objValue)
        assertEquals("abcde", s.primValue)
        assertEquals(Color.BLUE, s.colorValue)
        checkScalarsSharing(s, sharedRoot, sharedValues)
    }

    private fun checkScalarsSharing(s: Scalars, sharedRoot: Boolean, sharedValues: Boolean) {
        val s1 = model!!.getScalar("s1")
        if (sharedRoot) {
            assertSame("Scalars objects should be shared", s1, s)
        } else if (sharedValues) {
            assertSame("String value should be shared", s1.stringValue, s.stringValue)
        }
    }

    @Test
    fun checkRecursion() {
        assertSame(model!!.getScalar("s6").embeddedScalars, model!!.getScalar("s5"))
        assertSame(model!!.getScalar("s7").embeddedScalars, model!!.getScalar("s7"))
        assertSame(model!!.getScalar("s8a").embeddedScalars, model!!.getScalar("s8b"))
        assertSame(model!!.getScalar("s8b").embeddedScalars, model!!.getScalar("s8a"))
        assertSame(model!!.getScalar("ext1"), model!!.getScalar("ext2"))
    }

    @Test
    fun checkBadRefs() {
        assertNull(model!!.getScalar("badPointer"))
        checkBadRef(Overlay.of(model!!.scalars).getReference("badPointer"))
        checkBadRef(Overlay.of(model!!.scalars).getReference("cycle"))
        assertTrue(
            Overlay.of(model!!.scalars).getReference("cycle")
                .invalidReason is ReferenceCycleException
        )
    }

    private fun checkBadRef(ref: Reference) {
        assertTrue(ref.isInvalid)
        assertTrue(ref.invalidReason is ResolutionException)
    }

    @Test
    fun testRoots() {
        assertSame(model, Overlay.of(model).root)
        assertSame(model, Overlay.of(model!!.getScalar("s1")).root)
        val ext1 = model!!.getScalar("ext1")
        assertSame(ext1, Overlay.of(ext1).root)
        assertSame(model, Overlay.of(model!!.getScalar("s3")).root)
        assertSame(model, Overlay.of(model).getModel())
        assertSame(model, Overlay.of(model!!.getScalar("s1")).getModel())
        assertNull(Overlay.of(ext1).getModel())
        assertSame(model, Overlay.of(model!!.getScalar("s3")).getModel())
    }

    @Test
    fun testFind() {
        assertSame(model!!.getScalar("s1"), Overlay.of(model).find("/scalars/s1"))
        assertSame(model!!.getScalar("s3"), Overlay.of(model).find("/scalars/s1"))
        assertSame(model!!.getScalar("s3"), Overlay.of(model).find("/scalars/s3"))
        assertSame(model!!.getScalar("ext1"), Overlay.of(model).find("/scalars/ext1"))
        assertSame(model!!.getScalar("ext2"), Overlay.of(model).find("/scalars/ext1"))
        assertSame(model!!.getScalar("ext3"), Overlay.of(model).find("/scalars/s1"))
    }

    @Test
    fun testJsonRefs() {
        val url = javaClass.getResource("/refTest.yaml")!!.toString()
        val ext = javaClass.getResource("/external.yaml")!!.toString()
        assertEquals(url, Overlay.of(model).jsonReference)
        assertEquals("$url#/scalars/s1", Overlay.of(model!!.scalars, "s1").jsonReference)
        assertEquals(
            "$url#/scalars/s1/stringValue",
            Overlay.of(model!!.getScalar("s1"), "stringValue", String::class.java).jsonReference
        )
        assertEquals(
            "$url#/scalars/s1/stringValue",
            Overlay.of(model!!.getScalar("s2"), "stringValue", String::class.java).jsonReference
        )
        assertEquals("$url#/scalars/s1", Overlay.of(model!!.getScalar("s3")).jsonReference)
        assertEquals("$ext#/scalar1", Overlay.of(model!!.getScalar("ext1")).jsonReference)
        assertEquals("$ext#/scalar1", Overlay.of(model!!.getScalar("ext2")).jsonReference)
        assertEquals("$url#/scalars/s1", Overlay.of(model!!.getScalar("ext3")).jsonReference)
    }
}
