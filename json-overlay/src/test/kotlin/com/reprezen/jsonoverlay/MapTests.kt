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

import com.fasterxml.jackson.databind.node.JsonNodeFactory
import org.junit.Assert
import org.junit.Test
import java.util.LinkedHashMap

class MapTests : Assert() {
    private val factory = MapOverlay.getFactory(IntegerOverlay.factory, null)
    private val refMgr = ReferenceManager()
    private val jfac = JsonNodeFactory.instance
    private val a = 'A'
    @Test
    fun testMapFromValues() {
        val map: MutableMap<String, Int> = LinkedHashMap()
        for (i in 0..9) {
            map[(a.code + i).toChar().toString()] = i
        }
        doChecks(factory.create(map, null, refMgr) as MapOverlay<Int>)
    }

    @Test
    fun testMapFromJson() {
        val obj = jfac.objectNode()
        for (i in 0..9) {
            obj[Character.toString((a.code + i).toChar())] = jfac.numberNode(i)
        }
        doChecks(factory.create(obj, null, refMgr) as MapOverlay<Int>)
    }

    private fun doChecks(overlay: MapOverlay<Int>) {
        // initial content: A=>0, B=>1, ..., C=>10
        assertEquals(10, overlay.size().toLong())
        checkKeys(overlay, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
        overlay.remove("A")
        overlay.remove("E")
        overlay.remove("J")
        // now B=>1, .. D=>4, F=>6, ..., I=>9
        assertEquals(7, overlay.size().toLong())
        checkKeys(overlay, "B", "C", "D", "F", "G", "H", "I")
        overlay["A"] = 0
        overlay["E"] = 4
        overlay["J"] = 9
        // now complete again, but A, E, and J are final keys
        assertEquals(10, overlay.size().toLong())
        checkKeys(overlay, "B", "C", "D", "F", "G", "H", "I", "A", "E", "J")
        val copy = overlay._copy() as MapOverlay<Int>
        assertNotSame("Copy operation should yield different object", overlay, copy)
        assertEquals(overlay, copy)
        for (key in overlay.value!!.keys) {
            assertNotSame(
                "Copy operation should create copy of each map entry",
                overlay._getOverlay(key),
                copy._getOverlay(key)
            )
        }
        copy.remove("B")
        copy["B"] = 1
        assertEquals(overlay, copy)
        assertFalse("Key order difference not detected", overlay.equals(copy, true))
        assertSame(overlay, overlay._getRoot())
        val valueOverlay = overlay._getOverlay("B")
        assertSame(overlay, valueOverlay._getRoot())
        assertNull(Overlay.of(overlay).getModel())
    }

    private fun checkKeys(overlay: MapOverlay<Int>, vararg keys: String) {
        var i = 0
        for (key in overlay._get()!!.keys) {
            assertEquals(keys[i++], key)
            assertEquals(Integer.valueOf(key[0].code - a.code), overlay._get()!![key])
        }
    }
}
