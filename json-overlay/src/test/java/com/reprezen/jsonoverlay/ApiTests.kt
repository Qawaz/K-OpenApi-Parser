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


import com.reprezen.jsonoverlay.model.TestModelParser
import com.reprezen.jsonoverlay.model.impl.TestModelImpl
import com.reprezen.jsonoverlay.model.intf.Color
import com.reprezen.jsonoverlay.model.intf.TestModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.util.*

class ApiTests : Assert() {

    lateinit var model: TestModel

    @Before
    @Throws(IOException::class)
    fun setup() {
        model = TestModelParser.parse(javaClass.getResource("/apiTestModel.yaml"))
    }

    @Test
    fun testScalarApi() {
        assertEquals("Model description", model.description)
        model.description = "Model Description"
        assertEquals("Model Description", model.description)
        assertEquals(Integer.valueOf(10), model.width)
        assertNull(model.height)
        model.height = 20
        assertEquals(Integer.valueOf(20), model.height)
        assertEquals(Color.GREEN, model.color)
        model.color = Color.BLUE
        assertEquals(Color.BLUE, model.color)
        model.color = null
        assertNull(model.color)
        assertEquals(mutableListOf("A", "B"), entryKeys)
    }

    @Test
    fun testListApi() {
        assertTrue(model.hasIntegers())
        checkIntegers(1, 2, 3, 4, 5)
        checkIntegersPaths()
        assertEquals(Integer.valueOf(1), model.getInteger(0))
        model.removeInteger(1)
        model.addInteger(6)
        model.setInteger(0, 100)
        model.insertInteger(1, 200)
        checkIntegers(100, 200, 3, 4, 5, 6)
        checkIntegersPaths()
        assertEquals("Title for item 1", model.getItem(0).title)
        assertEquals("Title for item 2", model.getItem(1).title)
    }

    @Test
    fun testMapApi() {
        assertTrue(model.hasNamedIntegers())
        assertTrue(model.hasNamedInteger("I"))
        assertFalse(model.hasNamedInteger("X"))
        assertEquals(Integer.valueOf(1), model.getNamedInteger("I"))
        checkNamedIntegerNames("I", "II", "III", "IV", "V")
        checkNamedIntegers(1, 2, 3, 4, 5)
        model.removeNamedInteger("I")
        model.setNamedInteger("X", 10)
        model.setNamedInteger("II", 22)
        checkNamedIntegerNames("II", "III", "IV", "V", "X")
        checkNamedIntegers(22, 3, 4, 5, 10)
        assertEquals("Title for entry A", model.getEntry("A").title)
        assertEquals("Title for entry B", model.getEntry("B").title)
    }

    @Test
    fun testPathInParent() {
        assertEquals("description", Overlay.of(model as TestModelImpl?, "description", String::class.java)?.pathInParent)
        assertEquals(
            "0", Overlay.of(
                model.items, 0
            )?.pathInParent
        )
        assertEquals(
            "A", Overlay.of(
                model.entries, "A"
            )?.pathInParent
        )
    }

    @Test
    fun testRoot() {
        assertSame(model, Overlay.of(model).root)
        assertSame(model, Overlay.of(model, "description", String::class.java)?.root)
        assertSame(model, Overlay.of(model, "integers", ListOverlay::class.java)?.root)
        assertSame(model, Overlay.of(model, "namedIntegers", MapOverlay::class.java)?.root)
        assertSame(
            model, Overlay.of(
                model.entries, "A"
            )?.root
        )
        assertSame(
            model, Overlay.of(
                model.items, 0
            )?.root
        )
        assertSame(model, Overlay.of(model).getModel())
        assertSame(model, Overlay.of(model, "description", String::class.java)?.getModel())
        assertSame(model, Overlay.of(model, "integers", ListOverlay::class.java)?.getModel())
        assertSame(model, Overlay.of(model, "namedIntegers", MapOverlay::class.java)?.getModel())
        assertSame(
            model, Overlay.of(
                model.entries, "A"
            )?.getModel()
        )
        assertSame(
            model, Overlay.of(
                model.items, 0
            )?.getModel()
        )
    }

    @Test
    fun testPropNames() {
        assertEquals(
            hashSetOf(
                "description", "width", "height", "entries", "items", "integers", "namedIntegers",
                "color", "scalars"
            ), Overlay.of(model).propertyNames?.toSet()
        )
        assertEquals(
            hashSetOf("title"),
            Overlay.of(
                model.entries, "A"
            )?.propertyNames?.toSet()
        )
        assertEquals(
            hashSetOf("title"),
            Overlay.of(
                model.items, 0
            )?.propertyNames?.toSet()
        )
    }

    @Test
    fun testFind() {
        checkScalarFind("description", String::class.java, "/description")
        checkScalarFind("width", Int::class.java, "/width")
        checkScalarFind("width", Int::class.java, "/width")
        checkScalarFind("color", Color::class.java, "/color")
        assertSame(Overlay.of(model.items, 0)?.overlay, Overlay.of(model).find("/items/0"))
        assertSame(Overlay.of(model.items, 1)?.overlay, Overlay.of(model).find("/items/1"))
        assertNotSame(
            Overlay.of(
                model.items, 1
            )?.overlay, Overlay.of(model).find("/items/0")
        )
        assertSame(Overlay.of(model.namedIntegers, "I")?.overlay, Overlay.of(model).find("/namedIntegers/I"))
        assertSame(Overlay.of(model.namedIntegers, "II")?.overlay, Overlay.of(model).find("/namedIntegers/II"))
        assertNotSame(Overlay.of(model.namedIntegers, "I")?.overlay, Overlay.of(model).find("/namedIntegers/II"))
    }

    @Test
    fun testPathFromRoot() {
        assertEquals("/description", Overlay.of(model, "description", String::class.java)?.pathFromRoot)
        assertEquals("/width", Overlay.of(model, "width", Int::class.java)?.pathFromRoot)
        assertEquals("/color", Overlay.of(model, "color", Color::class.java)?.pathFromRoot)
        assertEquals(
            "/items/0", Overlay.of(
                model.items, 0
            )?.pathFromRoot
        )
        assertEquals("/items/0/title", Overlay.of(model.getItem(0), "title", String::class.java)?.pathFromRoot)
        assertEquals(
            "/entries", Overlay.of(
                model.entries
            )?.pathFromRoot
        )
        assertEquals(
            "/entries/A", Overlay.of(
                model.entries, "A"
            )?.pathFromRoot
        )
    }

    @Test
    fun testJsonRefs() {
        val url = javaClass.getResource("/apiTestModel.yaml").toString()
        assertEquals("$url#/description", Overlay.of(model, "description", String::class.java)?.jsonReference)
        assertEquals("$url#/width", Overlay.of(model, "width", Int::class.java)?.jsonReference)
        assertEquals("$url#/color", Overlay.of(model, "color", Color::class.java)?.jsonReference)
        assertEquals(
            "$url#/items/0", Overlay.of(
                model.items, 0
            )?.jsonReference
        )
        assertEquals("$url#/items/0/title", Overlay.of(model.getItem(0), "title", String::class.java)?.jsonReference)
        assertEquals(
            "$url#/entries", Overlay.of(
                model.entries
            )?.jsonReference
        )
        assertEquals(
            "$url#/entries/A", Overlay.of(
                model.entries, "A"
            )?.jsonReference
        )
    }

    private val entryKeys: List<String>
        private get() = model.entries.keys.stream().toList()

    private fun checkIntegers(vararg integers: Int) {
        assertEquals(integers.toList(), model.integers)
    }

    private fun checkIntegersPaths() {
        for (i in model.integers.indices) {
            assertEquals(
                i.toString(), Overlay.of(model.integers, i)?.pathInParent
            )
        }
    }

    private fun checkNamedIntegerNames(vararg names: String) {
        assertEquals(listOf(*names), model.namedIntegers.keys.stream().toList())
    }

    private fun checkNamedIntegers(vararg integers: Int) {
        assertEquals(integers.toList(), model.namedIntegers.values.stream().toList())
    }

    private fun <V> checkScalarFind(field: String, fieldType: Class<V>, path: String) {
        assertSame(Overlay.of(model, field, fieldType)?.overlay, Overlay.of(model).find(path))
    }
}
