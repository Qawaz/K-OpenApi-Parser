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

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Assert
import org.junit.Test

class ListTests : Assert() {

    private val data: MutableList<Int> = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    private val factory = ListOverlay.getFactory(IntegerOverlay.factory)
    private val refMgr = ReferenceManager()


    @Test
    fun testListFromValues() {
        doChecks(factory.create(data, null, refMgr) as ListOverlay<Int>)
    }

    @Test
    fun testListFromJson() {
        val json = mutableListOf<JsonElement>()
        for (i in data) {
            json.add(JsonPrimitive(i))
        }
        doChecks(factory.create(JsonArray(json), null, refMgr) as ListOverlay<Int>)
    }

    private fun doChecks(overlay: ListOverlay<Int>) {
        // initial content 0..9
        assertEquals(10, overlay.size().toLong())
        for (i in 0..9) {
            checkValueAt(overlay, i, i)
        }
        overlay.remove(0)
        overlay.remove(4)
        overlay.remove(7)
        try {
            overlay.remove(7)
            fail("Removal past end of list did not throw")
        } catch (e: IndexOutOfBoundsException) {
        }
        // now should be 1..4,6..8
        assertEquals(7, overlay.size().toLong())
        checkValueAt(overlay, 0, 1)
        checkValueAt(overlay, 4, 6)
        checkValueAt(overlay, 6, 8)
        overlay[0] = 0
        // now 0,2..4,6..8
        checkValueAt(overlay, 0, 0)
        overlay.insert(1, 1)
        // now 0..4,6..8
        checkValueAt(overlay, 1, 1)
        overlay.insert(5, 5)
        // now 0..8
        overlay.add(9)
        // now 0..9
        assertEquals(10, overlay.size().toLong())
        for (i in 0..9) {
            checkValueAt(overlay, i, i)
        }
        assertSame(overlay, overlay._getRoot())
        val itemOverlay = overlay._getValueOverlayByIndex(0)
        assertSame(overlay, itemOverlay?._getRoot())
        assertNull(Overlay.of(overlay).getModel())
    }

    private fun checkValueAt(overlay: ListOverlay<Int>, index: Int, value: Int) {
        assertEquals(Integer.valueOf(value), overlay[index])
    }
}
