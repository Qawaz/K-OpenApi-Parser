package com.reprezen.jsonoverlay

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Assert
import org.junit.Test

class JsonPointerTests : Assert() {

    @Test
    fun testJsonPointerMinus() {
        val pointer = JsonPointer("/components/schemas/SomeData")
        val other = JsonPointer("/components/schemas")
        assertEquals("SomeData", pointer.minus(other).toString())
        assertNull(other.minus(pointer))
        assertEquals(JsonPointer.Empty, pointer.minus(pointer))
        assertEquals(JsonPointer.Empty, other.minus(other))
        assertEquals(
            "0/variables/x-foo",
            JsonPointer("servers/0/variables/x-foo").minus(JsonPointer("servers")).toString()
        )
        assertEquals("components/schemas", other.minus(JsonPointer("/")).toString())
    }

    @Test
    fun testModifyJsonObject() {
        val obj = JsonObject(mapOf())
        assertEquals("{\"name\":\"one\"}", obj.withValue("name", JsonPrimitive("one")).toString())
        assertEquals(
            "{\"child\":{\"name\":\"two\"}}",
            obj.withValue("child", JsonObject(mapOf("name" to JsonPrimitive("two")))).toString()
        )
        assertEquals("{\"age\":0}", obj.withValue("age", JsonPrimitive(0)).toString())
        assertEquals("{\"child\":{\"age\":1}}", obj.withValue(JsonPointer("child/age"), JsonPrimitive(1)).toString())
        assertEquals(
            "{\"child\":{\"child\":{\"child\":{\"child\":{\"age\":2}}}}}",
            obj.withValue(JsonPointer("child/child/child/child/age"), JsonPrimitive(2)).toString()
        )
        val modified = JsonObject(mapOf("child" to JsonPrimitive(2)))
        assertEquals(
            "{\"child\":3}",
            modified.withValue(JsonPointer("child"), JsonPrimitive(3)).toString()
        )
        val nested = JsonObject(mapOf("modified" to modified))
        assertEquals(
            "{\"modified\":{\"child\":4}}",
            nested.withValue(JsonPointer("modified/child"), JsonPrimitive(4)).toString()
        )
    }

}