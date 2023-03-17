package com.reprezen.kaizen.oasparser.`val`

import java.util.*

class VisitedObjectRegistry {
    private val visitedObjects: MutableMap<Any, Any> = IdentityHashMap()
    fun visitIfUnvisited(o: Any): Boolean {
        return if (visitedObjects.containsKey(o)) {
            false
        } else {
            visitedObjects[o] = o
            true
        }
    }
}