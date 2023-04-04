package com.reprezen.swaggerparser.test

import com.reprezen.jsonoverlay.ListOverlay
import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.kaizen.oasparser.ovl3.PathImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OverlayAdapterTests : Assert() {
    private var model: OpenApi3? = null
    @Before
    @Throws(Exception::class)
    fun setup() {
        model = parseLocalModel("linksTest")
    }

    @Test
    fun testPropertiesAdapter() {
        assertTrue(model === Overlay.of(model!!).get())
        assertTrue(
            model!!.info === Overlay<Info>(
                model!!.info
            ).get()
        )
    }

    @Test
    fun testFieldAdapter() {
        assertEquals(model!!.openApi, Overlay.of(model, "openApi", String::class.java)?.get())
        val pathsMapOverlay= Overlay.of(model as PropertiesOverlay<*>, "paths", MutableMap::class.java).get()
        assertTrue(model!!.getPath("/2.0/users/{username}") === pathsMapOverlay?.get("/2.0/users/{username}"))
    }

    @Test
    fun testMapAdapter() {
        val mapOverlay = Overlay.of(
            model as PropertiesOverlay<*>, "paths",
            MutableMap::class.java
        ) as Overlay<MutableMap<String,Path>>
        val castMapOverlay: MapOverlay<Path> = Overlay.getMapOverlay<Path>(mapOverlay)!!
        assertTrue(castMapOverlay is MapOverlay<Path>)
        val path: Path = Overlay.of<Path>(castMapOverlay, "/2.0/users/{username}").get()!!
        assertTrue(model!!.getPath("/2.0/users/{username}") === path)
    }

    @Test
    fun testListAdapter() {
        val method = model!!.getPath("/2.0/repositories/{username}/{slug}").get
        val listOverlay = Overlay.of(
            method as PropertiesOverlay<*>, "parameters",
            MutableList::class.java
        ) as Any as Overlay<List<Parameter>>
        val castListOverlay: ListOverlay<Parameter> = Overlay.getListOverlay(listOverlay)!!
        assertTrue(castListOverlay is ListOverlay<*>)
        val param: Parameter = Overlay.of<Parameter>(castListOverlay, 1).get()!!
        assertTrue(method.getParameter(1) === param)
    }

    @Test
    fun testReferences() {
        // props reference
        val resp200 = model!!.getPath("/2.0/users/{username}").get.getResponse("200")
        assertFalse(Overlay.of(resp200).isReference("description"))
        assertTrue(Overlay.of(resp200.getContentMediaType("application/json")).isReference("schema"))
        assertEquals(
            "#/components/schemas/user",
            Overlay.of(resp200.getContentMediaType("application/json")).getReference("schema")!!.refString
        )

        // map reference
        assertFalse(Overlay.of(model, "schemas", Schema::class.java)!!.isReference("user"))
        assertTrue(
            Overlay.of(
                model!!.getSchema("repository").properties
            )!!.isReference("owner")
        )
        assertEquals(
            "#/components/schemas/user",
            Overlay.of(model!!.getSchema("repository").properties)!!.getReference("owner")!!.refString
        )

        // list reference
        val params = model!!.getPath("/2.0/repositories/{username}/{slug}").get.parameters
        assertFalse(Overlay.of(params)!!.isReference(1))
        assertTrue(Overlay.of(params)!!.isReference(0))
        assertEquals("#/components/parameters/username", Overlay.of(params)!!.getReference(0)!!.refString)
    }

    companion object {
        @Throws(Exception::class)
        private fun parseLocalModel(name: String): OpenApi3 {
            val url = SimpleSerializationTest::class.java.getResource("/models/$name.yaml")
            return OpenApiParser().parse(url) as OpenApi3
        }
    }
}