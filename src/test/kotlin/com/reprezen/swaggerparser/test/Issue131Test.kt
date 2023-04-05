package com.reprezen.swaggerparser.test

import com.google.common.io.Resources
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import org.junit.Assert
import org.junit.Test

class Issue131Test : Assert() {
    @Test
    @Throws(Exception::class)
    fun testSchemaRefs() {
        val model = OpenApiParser().parse(Resources.getResource("models/issue131.yaml"), true)
        assertEquals("SampleData", Overlay.getPathInParent(Overlay.of(model).find("/components/schemas/SampleData")!!))
        assertEquals("MoreData", Overlay.getPathInParent(Overlay.of(model).find("/components/schemas/MoreData")!!))
        val direct = model.getSchema("SampleData")
        val viaMoreData = model.getSchema("MoreData")?.getProperty("master")
        val viaPath = model.getPath("/sampledatamanagement/v1/sampledata/custom/{id}")?.getGet()?.getResponse("200")
            ?.getContentMediaType("application/json")?.getSchema()
        assertTrue(direct === viaMoreData)
        assertTrue(direct === viaPath)
    }
}