package com.reprezen.jsonoverlay

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
    }

}