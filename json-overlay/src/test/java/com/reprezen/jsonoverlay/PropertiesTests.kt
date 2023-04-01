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

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import com.google.common.collect.Queues
import org.junit.Assert
import org.junit.Test
import java.util.*

class PropertiesTests : Assert() {
    private val refMgr = ReferenceManager()
    private val LIST = Any()
    private val MAP = Any()
    private val ROOT_MAP = Any()
    private val END = Any()
    @Test
    fun testScalars() {
        val foo = createFooWithJson("hello")
        assertEquals("hello", foo.stringField)
        assertNull(foo.numField)
        foo.stringField = "bye"
        assertEquals("bye", foo.stringField)
        foo.numField = 10
        assertEquals(Integer.valueOf(10), foo.numField)
        foo.stringField = null
        assertNull(foo.stringField)
        assertSame(foo, foo._getRoot())
        val stringOverlay = foo._getOverlay("stringField", String::class.java)
        assertSame(foo, stringOverlay._getRoot())
        assertNull(Overlay.of(foo).getModel())
    }

    @Test
    fun testList() {
        val foo = createFooWithJson("hello", LIST, 1, 2, 3, END, 10)
        assertEquals(mutableListOf(1, 2, 3), foo.listField)
        checkPropertyNames(foo, "string", "list", "num")
    }

    @Test
    fun testMap() {
        val foo = createFooWithJson("hello", MAP, "a", 1, "b", 1, END, 10)
        assertEquals(mutableListOf("a", "b").associateWith { 1 }, foo.mapField)
        checkPropertyNames(foo, "string", "map", "num")
    }

    @Test
    fun testRootMap() {
        val foo = createFooWithJson("hello", ROOT_MAP, "x-a", 1, "x-b", 1, END, 10)
        assertEquals(mutableListOf("x-a", "x-b").associateWith { 1 }, foo.rootMap)
    }

    @Test
    fun testMixture() {
        val foo = createFooWithJson(
            10, LIST, 10, 20, 30, END, ROOT_MAP, "x-a", 1, END, "hello", MAP, "a", 1, "b", 1,
            END
        )
        assertEquals(Integer.valueOf(10), foo.numField)
        assertEquals("hello", foo.stringField)
        assertEquals(mutableListOf(10, 20, 30), foo.listField)
        assertEquals(mutableListOf("a", "b").associateWith { 1 }, foo.mapField)
        assertEquals(mutableListOf("x-a").associateWith { 1 }, foo.rootMap)
        checkPropertyNames(foo, "num", "list", "string", "map", "x-a")
        val copy = foo._copy() as Foo
        assertNotSame("Copy operation should create different object", foo, copy)
        assertEquals(foo, copy)
        for (name in foo._getPropertyNames()) {
            assertNotSame(
                "Copy operation should create copy of each property value",
                foo._getOverlay<Any>(name),
                copy._getOverlay<Any>(name)
            )
        }
        // foo2 has same content as foo, but numField comes last instead of
        // first
        val foo2 = createFooWithJson(
            LIST, 10, 20, 30, END, 10, ROOT_MAP, "x-a", 1, END, "hello", MAP, "a", 1, "b", 1,
            END
        )
        assertEquals(foo, foo2)
        assertFalse("Property order difference not detected", foo.equals(foo2, true))
    }

    @Test
    fun testSerializationOrder() {
        var foo = createFooWithJson("hello", 1)
        checkPropertyNames(foo, "string", "num")
        foo = createFooWithJson(1, "hello")
        checkPropertyNames(foo, "num", "string")
        foo = createFooWithJson("hello")
        checkPropertyNames(foo, "string")
        foo.numField = 10
        checkPropertyNames(foo, "string", "num")
        foo = createFooWithJson(1)
        checkPropertyNames(foo, "num")
        foo.stringField = "hello"
        checkPropertyNames(foo, "num", "string")
        foo = createFooWithJson()
        checkPropertyNames(foo)
    }

    @Test
    fun testPropertyNames() {
        val foo = createFooWithJson()
        assertEquals(
            HashSet(mutableListOf("stringField", "numField", "listField", "mapField", "rootMap")),
            Overlay.of(foo).propertyNames.toSet()
        )
    }

    private fun checkPropertyNames(foo: Foo, vararg expected: String) {
        assertArrayEquals(expected, foo._toJson().fieldNames().asSequence().toList().toTypedArray())
    }

    private fun createFooWithJson(vararg values: Any): Foo {
        val queue: Deque<Any> = Queues.newArrayDeque(Arrays.asList(*values))
        val json = jfac.objectNode()
        while (!queue.isEmpty()) {
            val value = queue.removeFirst()
            if (value is String) {
                json.put("string", value)
            } else if (value is Int) {
                json.put("num", value)
            } else if (value === LIST) {
                json["list"] = gatherList(queue)
            } else if (value === MAP) {
                json["map"] = gatherMap(queue)
            } else if (value === ROOT_MAP) {
                json.setAll(gatherMap(queue))
            }
        }
        return Foo.factory.create(json, null, refMgr) as Foo
    }

    private fun gatherList(queue: Deque<Any>): ArrayNode {
        val array = jfac.arrayNode()
        while (!queue.isEmpty()) {
            val value = queue.removeFirst()
            if (value === END) {
                break
            }
            array.add(value as Int)
        }
        return array
    }

    private fun gatherMap(queue: Deque<Any>): ObjectNode {
        val map = jfac.objectNode()
        while (!queue.isEmpty()) {
            val key = queue.removeFirst()
            if (key === END) {
                break
            }
            val value = queue.removeFirst() as Int
            map.put(key as String, value)
        }
        return map
    }

    class Foo : PropertiesOverlay<Foo> {
        private constructor(json: JsonNode, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
            json,
            parent,
            Companion.factory,
            refMgr
        )

        private constructor(value: Foo, parent: JsonOverlay<*>?, refMgr: ReferenceManager) : super(
            value,
            parent,
            Companion.factory,
            refMgr
        )

        override fun _elaborateJson() {
            _createScalar("stringField", "string", StringOverlay.factory)
            _createScalar("numField", "num", IntegerOverlay.factory)
            _createList("listField", "list", IntegerOverlay.factory)
            _createMap("mapField", "map", IntegerOverlay.factory, null)
            _createMap("rootMap", "", IntegerOverlay.factory, "x-.*")
        }

        var stringField: String?
            get() = _get("stringField", String::class.java)
            set(value) {
                _setScalar("stringField", value, String::class.java)
            }
        var numField: Int?
            get() = _get("numField", Int::class.java)
            set(value) {
                _setScalar("numField", value, Int::class.java)
            }
        val listField: List<Int>
            get() = _getList("listField", Int::class.java)
        val mapField: Map<String, Int>
            get() = _getMap("mapField", Int::class.java)
        val rootMap: Map<String, Int>
            get() = _getMap("rootMap", Int::class.java)

        override fun _getFactory(): OverlayFactory<Foo> {
            return Companion.factory
        }

        companion object {
            var factory: OverlayFactory<Foo> = object : OverlayFactory<Foo>() {

                override fun getOverlayClass(): Class<out JsonOverlay<in Foo?>> {
                    return Foo::class.java
                }

                protected override fun _create(
                    value: Foo,
                    parent: JsonOverlay<*>?,
                    refMgr: ReferenceManager
                ): JsonOverlay<Foo> {
                    return Foo(value, parent, refMgr)
                }

                override fun _create(
                    json: JsonNode,
                    parent: JsonOverlay<*>?,
                    refMgr: ReferenceManager
                ): JsonOverlay<Foo?> {
                    return Foo(json, parent, refMgr)
                }
            }
        }
    }

    companion object {
        private val jfac = JsonNodeFactory.instance
    }
}
