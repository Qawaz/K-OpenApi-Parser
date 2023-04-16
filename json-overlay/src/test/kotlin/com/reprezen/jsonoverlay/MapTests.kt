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

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Assert
import org.junit.Test
import java.util.LinkedHashMap

class MapTests : Assert() {
    private val factory = MapOverlay.getFactory(IntegerOverlay.factory, null)
    private val refMgr = ReferenceManager()
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
        val obj = mutableMapOf<String,JsonElement>()
        for (i in 0..9) {
            obj[(a.code + i).toChar().toString()] = JsonPrimitive(i)
        }
        doChecks(factory.create(JsonObject(obj), null, refMgr) as MapOverlay<Int>)
    }

    private fun doChecks(overlay: MapOverlay<Int>) {
        // initial content: A=>0, B=>1, ..., C=>10
        assertEquals(10, overlay.size.toLong())
        checkKeys(overlay, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
        overlay.remove("A")
        overlay.remove("E")
        overlay.remove("J")
        // now B=>1, .. D=>4, F=>6, ..., I=>9
        assertEquals(7, overlay.size.toLong())
        checkKeys(overlay, "B", "C", "D", "F", "G", "H", "I")
        overlay["A"] = 0
        overlay["E"] = 4
        overlay["J"] = 9
        // now complete again, but A, E, and J are final keys
        assertEquals(10, overlay.size.toLong())
        checkKeys(overlay, "B", "C", "D", "F", "G", "H", "I", "A", "E", "J")
        assertSame(overlay, overlay._getRoot())
        val valueOverlay = overlay._getValueOverlayByName("B")
        assertSame(overlay, valueOverlay?._getRoot())
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
