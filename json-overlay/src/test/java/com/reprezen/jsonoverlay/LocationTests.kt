/*********************************************************************
 * Copyright (c) 2017 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * ModelSolv, Inc.
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

import com.fasterxml.jackson.core.JsonPointer
import com.reprezen.jsonoverlay.IntegerOverlay
import com.reprezen.jsonoverlay.StringOverlay
import com.reprezen.jsonoverlay.model.GenTestModel.loadResourceFileAsString
import com.reprezen.jsonoverlay.model.GenTestModel.loadResourceFileAsURL
import com.reprezen.jsonoverlay.model.TestModelParser
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class LocationTests {
    @Test
    @Throws(IOException::class)
    fun testYamlLocation() {
        val result = JsonLoader().loadWithLocations(
            loadResourceFileAsString("locationsTest.yaml")
        )
        val locations = result.right
        Assert.assertTrue(locations.containsKey(JsonPointer.compile("/map/a")))
        val region = locations[JsonPointer.compile("/map/a")]
        Assert.assertEquals(5, region!!.start.line.toLong())
        Assert.assertEquals(5, region.start.column.toLong())
        Assert.assertEquals(6, region.end.line.toLong())
        Assert.assertEquals(3, region.end.column.toLong())
    }

    @Test
    @Throws(IOException::class)
    fun testPositions() {
        val model = TestModelParser.parse(loadResourceFileAsURL("refTest.yaml"))
        checkPositions(model, 1, 1, 53, 35)
        run {
            val desc = Overlay.of(model, "description", StringOverlay::class.java)
            checkPositions(desc, 1, 14, 1, 28)
        }
        run {
            val scalars = Overlay.of(model.scalars)
            checkPositions(scalars, 3, 3, 53, 35)
        }
        run {
            val s2intRef = Overlay.of(model.getScalar("s2"), "intValue", IntegerOverlay::class.java)
            checkPositions(s2intRef, 15, 7, 16, 5)
        }
        run {
            val s2Int = Overlay.of(model.getScalar("s2"), "intValue", IntegerOverlay::class.java)
                .referenceOverlay
            checkPositions(s2Int, 5, 15, 5, 17)
        }
        run {
            val ext1Ref = Overlay.of(model.scalars, "ext1")
            checkPositions(ext1Ref, 49, 5, 50, 3)
        }
        run {
            val ext1Obj = Overlay.of(model.scalars, "ext1").referenceOverlay
            checkPositions(ext1Obj, 2, 3, 9, 1)
        }
    }

    private fun <V, T : IJsonOverlay<V>?> checkPositions(
        overlay: T, startLine: Int, startCol: Int, endLine: Int,
        endCol: Int
    ) {
        checkPositions(Overlay.of(overlay), startLine, startCol, endLine, endCol)
    }

    private fun checkPositions(overlay: Overlay<*>, startLine: Int, startCol: Int, endLine: Int, endCol: Int) {
        val pos = overlay.positionInfo
        Assert.assertTrue(pos.isPresent)
        Assert.assertEquals(startLine.toLong(), pos.get().line.toLong())
        Assert.assertEquals(startCol.toLong(), pos.get().column.toLong())
        Assert.assertEquals(endLine.toLong(), pos.get().end.line.toLong())
        Assert.assertEquals(endCol.toLong(), pos.get().end.column.toLong())
    }
}
