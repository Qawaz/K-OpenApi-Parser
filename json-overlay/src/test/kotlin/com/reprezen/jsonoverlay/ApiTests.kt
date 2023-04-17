/*********************************************************************
 * Copyright (c) 2023 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * WaqasTahir - Contributions after 2022
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

    lateinit var model: TestModelImpl

    private val isJson: Boolean get() = true
    private val resourcePath: String get() = if (isJson) "/json/apiTestModel.json" else "/yaml/apiTestModel.yaml"


    @Before
    @Throws(IOException::class)
    fun setup() {
        model = TestModelParser.parse(javaClass.getResource(resourcePath)) as TestModelImpl
    }

    @Test
    fun testScalarApi() {
        assertEquals("Model description", model.getDescription())
        model.setDescription("Model Description")
        assertEquals("Model Description", model.getDescription())
        assertEquals(Integer.valueOf(10), model.getWidth())
        assertNull(model.getHeight())
        model.setHeight(20)
        assertEquals(Integer.valueOf(20), model.getHeight())
        assertEquals(Color.GREEN, model.getColor())
        model.setColor(Color.BLUE)
        assertEquals(Color.BLUE, model.getColor())
        //TODO allow to set color to null
//        model.setColor(null)
//        assertNull(model.getColor())
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
        assertEquals("Title for item 1", model.getItem(0).getTitle())
        assertEquals("Title for item 2", model.getItem(1).getTitle())
    }

    @Test
    fun testFactorySignatures(){
        assertEquals(BooleanOverlay.factory.signature,BooleanOverlay::class.simpleName)
        assertEquals(IntegerOverlay.factory.signature,IntegerOverlay::class.simpleName)
        assertEquals(NumberOverlay.factory.signature,NumberOverlay::class.simpleName)
        assertEquals(ObjectOverlay.factory.signature,ObjectOverlay::class.simpleName)
        assertEquals(PrimitiveOverlay.factory.signature,PrimitiveOverlay::class.simpleName)
        assertEquals(StringOverlay.factory.signature,StringOverlay::class.simpleName)
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
        assertEquals("Title for entry A", model.getEntry("A")!!.getTitle())
        assertEquals("Title for entry B", model.getEntry("B")!!.getTitle())
    }

    @Test
    fun testPathInParent() {
        assertEquals("description", Overlay.of(model, "description")?.pathInParent)
        assertEquals("0", Overlay.of(model.getItems(), 0).pathInParent)
        assertEquals("A", Overlay.of(model.getEntries(), "A").pathInParent)
    }

    @Test
    fun testRoot() {
        assertSame(model, Overlay.of(model).root)
        assertSame(model, Overlay.of(model, "description")?.root)
        assertSame(model, Overlay.of(model, "integers")?.root)
        assertSame(model, Overlay.of(model, "namedIntegers")?.root)
        assertSame(model, Overlay.of(model.getEntries(), "A").root)
        assertSame(model, Overlay.of(model.getItems(), 0).root)
        assertSame(model, Overlay.of(model).getModel())
        assertSame(model, Overlay.of(model, "description")?.getModel())
        assertSame(model, Overlay.of(model, "integers")?.getModel())
        assertSame(model, Overlay.of(model, "namedIntegers")?.getModel())
        assertSame(model, Overlay.of(model.getEntries(), "A").getModel())
        assertSame(model, Overlay.of(model.getItems(), 0).getModel())
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
            Overlay.of(model.getEntries(), "A").propertyNames?.toSet()
        )
        assertEquals(
            hashSetOf("title"),
            Overlay.of(model.getItems(), 0).propertyNames?.toSet()
        )
    }

    @Test
    fun testScalarFind() {
        checkScalarFind("description", "/description")
        checkScalarFind("width", "/width")
        checkScalarFind("width", "/width")
        checkScalarFind("color", "/color")
    }

    @Test
    fun testMapAndFind() {

        // Testing overlay value
        assertSame(model.getNamedIntegers()["I"], model.findByPath("/namedIntegers/I")?._get())
        assertSame(model.getNamedIntegers()["II"], model.findByPath("/namedIntegers/II")?._get())

        // Testing actual value
        assertEquals(1, model.getNamedIntegers()["I"])
        assertEquals(2, model.getNamedIntegers()["II"])

        // Testing
        assertEquals(Overlay.of(model.getNamedIntegers(), "I").overlay, model.findByPath("/namedIntegers/I"))
        assertEquals(Overlay.of(model.getNamedIntegers(), "II").overlay, model.findByPath("/namedIntegers/II"))
        assertNotEquals(Overlay.of(model.getNamedIntegers(), "I").overlay, model.findByPath("/namedIntegers/II"))
    }

    @Test
    fun testItemsAndFind() {

        // Testing instances
        assertSame(Overlay.of(model.getItems(), 0).overlay, model.findByPath("/items/0"))
        assertSame(Overlay.of(model.getItems(), 1).overlay, model.findByPath("/items/1"))
        assertNotSame(Overlay.of(model.getItems(), 1).overlay, model.findByPath("/items/0"))

        // Testing overlay
        assertSame(model.getItems()[0], model.findByPath("/items/0"))
        assertSame(model.getItems()[1], model.findByPath("/items/1"))

        // Testing overlay value
        assertEquals(model.getItems()[0].getTitle(), model.findByPath("/items/0/title")?._get())
        assertEquals(model.getItems()[1].getTitle(), model.findByPath("/items/1/title")?._get())

        // Testing actual value
        assertEquals("Title for item 1", model.getItems()[0].getTitle())
        assertEquals("Title for item 2", model.getItems()[1].getTitle())
    }

    @Test
    fun testPathFromRoot() {
        assertEquals("/description", Overlay.of(model, "description")?.pathFromRoot)
        assertEquals("/width", Overlay.of(model, "width")?.pathFromRoot)
        assertEquals("/color", Overlay.of(model, "color")?.pathFromRoot)
        assertEquals(
            "/items/0", Overlay.of(
                model.getItems(), 0
            ).pathFromRoot
        )
        assertEquals("/items/0/title", Overlay.of(model.getItem(0), "title")?.pathFromRoot)
        assertEquals("/entries", Overlay.of(model.getEntries() as MapOverlay<*>).pathFromRoot)
        assertEquals(
            "/entries/A", Overlay.of(model.getEntries(), "A").pathFromRoot
        )
    }

    @Test
    fun testJsonRefs() {
        val url = javaClass.getResource(resourcePath).toString()
        assertEquals("$url#/description", Overlay.of(model, "description")?.jsonReference)
        assertEquals("$url#/width", Overlay.of(model, "width")?.jsonReference)
        assertEquals("$url#/color", Overlay.of(model, "color")?.jsonReference)
        assertEquals(
            "$url#/items/0", Overlay.of(
                model.getItems(), index = 0
            ).jsonReference
        )
        assertEquals("$url#/items/0/title", Overlay.of(model.getItem(0), "title")?.jsonReference)
        assertEquals(
            "$url#/entries", Overlay.of(
                model.getEntries()
            ).jsonReference
        )
        assertEquals(
            "$url#/entries/A", Overlay.of(
                model.getEntries(), "A"
            ).jsonReference
        )
    }

    private val entryKeys: List<String>
        private get() = model.getEntries().keys.stream().toList()

    private fun checkIntegers(vararg integers: Int) {
        assertEquals(integers.toList(), model.getIntegers())
    }

    private fun checkIntegersPaths() {
        for (i in model.getIntegers().indices) {
            assertEquals(
                i.toString(), Overlay.of(model.getIntegers(), i).pathInParent
            )
        }
    }

    private fun checkNamedIntegerNames(vararg names: String) {
        assertEquals(listOf(*names), model.getNamedIntegers().keys.stream().toList())
    }

    private fun checkNamedIntegers(vararg integers: Int) {
        assertEquals(integers.toList(), model.getNamedIntegers().values.stream().toList())
    }

    private fun checkScalarFind(field: String, path: String) {
//        println("${Overlay.of(model, field)?.overlay} to ${model.findByPath(path)}")
        assertSame(Overlay.of(model, field)?.overlay, Overlay.of(model).find(path))
    }
}
