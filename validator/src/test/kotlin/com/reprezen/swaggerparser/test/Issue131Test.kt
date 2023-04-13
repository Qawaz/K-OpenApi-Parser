package com.reprezen.swaggerparser.test

import com.google.common.io.Resources
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.validate
import org.junit.Assert
import org.junit.Test

class Issue131Test : Assert() {


    val isJson : Boolean get() = true

    val issue131Res : String = "models/" + (if(isJson) "json" else "yaml") + "/issue131" + (if(isJson) ".json" else ".yaml")

    @Test
    @Throws(Exception::class)
    fun testSchemaRefs() {
        val model = OpenApiParser().parse(Resources.getResource(issue131Res)).also { it.validate() }
        assertEquals("SampleData", Overlay.of(model).find("/components/schemas/SampleData")!!._getPathInParent())
        assertEquals("MoreData", Overlay.of(model).find("/components/schemas/MoreData")!!._getPathInParent())
        val direct = model.getSchema("SampleData")
        val viaMoreData = model.getSchema("MoreData")?.getProperty("master")
        val viaPath = model.getPath("/sampledatamanagement/v1/sampledata/custom/{id}")?.getGet()?.getResponse("200")
            ?.getContentMediaType("application/json")?.getSchema()
        assertTrue(direct === viaMoreData)
        assertTrue(direct === viaPath)
    }
}