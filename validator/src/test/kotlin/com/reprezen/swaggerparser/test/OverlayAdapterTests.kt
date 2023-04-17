package com.reprezen.swaggerparser.test

import com.reprezen.jsonoverlay.*
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.*
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
            model!!.getInfo() === Overlay<Info>(model!!.getInfo()!!).get()
        )
    }

    @Test
    fun testFieldAdapter() {

        // Testing retrieved value
        assertEquals(model!!.getOpenApi(), Overlay.of(model!! as PropertiesOverlay<*>, "openApi")?.get())

        // Testing values
        assertEquals("3.0.0", model!!.getOpenApi())

        val pathsMapOverlay = Overlay.of(model as PropertiesOverlay<*>, "paths")
        assertSame(model!!.getPath("/2.0/users/{username}"), pathsMapOverlay?.find("/2.0/users/{username}"))
    }

    @Test
    fun testMapAdapter() {
        val mapOverlay = Overlay.of(
            model as PropertiesOverlay<*>, "paths"
        ) as Overlay<MutableMap<String, Path>>
        val castMapOverlay: MapOverlay<Path> = Overlay.getMapOverlay<Path>(mapOverlay)!!
        assertTrue(castMapOverlay is MapOverlay<Path>)
        val path: Path = Overlay.of<Path>(castMapOverlay, "/2.0/users/{username}").get()!!
        assertTrue(model!!.getPath("/2.0/users/{username}") === path)
    }

    @Test
    fun testListAdapter() {
        val method = model!!.getPath("/2.0/repositories/{username}/{slug}")?.getGet()
        val listOverlay = Overlay.of(
            method as PropertiesOverlay<*>, "parameters"
        ) as Overlay<List<Parameter>>
        val castListOverlay: ListOverlay<Parameter> = Overlay.getListOverlay(listOverlay)!!
        assertTrue(castListOverlay is ListOverlay<*>)
        val param: Parameter = Overlay.of<Parameter>(castListOverlay, 1).get()!!
        assertTrue(method.getParameter(1) === param)
    }

    @Test
    fun testReferences() {
        // props reference
        val resp200 = model!!.getPath("/2.0/users/{username}")?.getGet()?.getResponse("200")!!
        assertFalse(Overlay.of(resp200).isReference("description"))
        assertTrue(Overlay.of(resp200.getContentMediaType("application/json")!!).isReference("schema"))

        assertEquals(
            "#/components/schemas/user",
            Overlay.of(resp200.getContentMediaType("application/json")!!).getReference("schema")!!.refString
        )

        // map reference
        assertFalse(Overlay.of(model!!, "schemas")!!.isReference("user"))
        val properties = model!!.getSchema("repository")?.getProperties()!!
        val propsOverlay = Overlay.of(properties)!!
        val mapOverlay = Overlay(properties as MapOverlay<Schema>)

        assertTrue(propsOverlay.isReference("owner"))
        assertEquals("#/components/schemas/user", propsOverlay.getReference("owner")!!.refString)

        assertTrue(mapOverlay.isReference("owner"))
        assertEquals("#/components/schemas/user", mapOverlay.getReference("owner")!!.refString)

        // list reference
        val params = model!!.getPath("/2.0/repositories/{username}/{slug}")?.getGet()?.getParameters()!!
        val paramsOverlay = Overlay.of(params)!!
        val listOverlay = Overlay(params as ListOverlay)

        assertFalse(paramsOverlay.isReference(0))
        assertEquals("slug", params[0].getName())
        assertTrue(paramsOverlay.isReference(1))
        assertEquals("#/components/parameters/username", paramsOverlay.getReference(1.toString())!!.refString)

        assertFalse(listOverlay.isReference(0))
        assertEquals("slug", params[0].getName())
        assertTrue(listOverlay.isReference(1))
        assertEquals("#/components/parameters/username", listOverlay.getReference(1.toString())!!.refString)

    }

    companion object {

        val isJson: Boolean = true

        @Throws(Exception::class)
        private fun parseLocalModel(name: String): OpenApi3 {
            val url =
                SimpleSerializationTest::class.java.getResource("/models/" + (if (isJson) "json" else "yaml") + "/" + name + (if (isJson) ".json" else ".yaml"))
            return OpenApiParser().parse(url)
        }
    }
}