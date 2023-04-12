package com.reprezen.jsonoverlay.model

import com.reprezen.jsonoverlay.parseJsonLiteralAsNumber
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.math.BigDecimal

class NumberTests {

    @Test
    fun testToInt() {
        assertEquals(0, "0".parseJsonLiteralAsNumber())
        assertEquals(1, "1".parseJsonLiteralAsNumber())
        assertEquals(-1, "-1".parseJsonLiteralAsNumber())
        assertEquals(123, "123".parseJsonLiteralAsNumber())
        assertEquals(-123, "-123".parseJsonLiteralAsNumber())
        assertEquals(2147483647, "2147483647".parseJsonLiteralAsNumber())
        assertNull("".parseJsonLiteralAsNumber())
        assertNull("c".parseJsonLiteralAsNumber())
    }

    @Test
    fun testToLong() {
        assertEquals(9223372036854775807L, "9223372036854775807".parseJsonLiteralAsNumber())
        assertNull("".parseJsonLiteralAsNumber())
        assertNull("abc".parseJsonLiteralAsNumber())
        assertEquals(-2147483648L, "-2147483648".parseJsonLiteralAsNumber())
    }

    @Test
    fun testToFloat() {
        assertEquals(3.1415927f, "3.1415927".parseJsonLiteralAsNumber())
        assertEquals(-3.1415927f, "-3.1415927".parseJsonLiteralAsNumber())
        assertNull("".parseJsonLiteralAsNumber())
        assertNull("abc".parseJsonLiteralAsNumber())
        assertEquals(12345678.12345678,"12345678.12345678".parseJsonLiteralAsNumber())
    }

}