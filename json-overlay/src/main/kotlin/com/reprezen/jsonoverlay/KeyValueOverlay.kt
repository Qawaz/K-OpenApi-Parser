package com.reprezen.jsonoverlay

interface KeyValueOverlay {

    fun _getPropertyNames(): List<String>

    fun _getKeyValueOverlayByName(name: String): JsonOverlay<*>?

    fun _getValueOverlayByPath(segment: String): JsonOverlay<*>? {
        return _getKeyValueOverlayByName(segment)
    }

    fun <T> _traverseOverlaysOfSegment(segment: String, block: (JsonOverlay<*>) -> T?): T? {
        _getValueOverlayByPath(segment)?.let(block)?.let { return it }
        return null
    }

    fun _findByPath(path: String): JsonOverlay<*>? {
        return _findByPath(JsonPointer(path))
    }

    fun _findByIndex(index : Int) : JsonOverlay<*>? {
        return _findByPath(index.toString())
    }

    fun _findByPath(path: JsonPointer): JsonOverlay<*>? {
        if (path.segments.isEmpty()) return this as JsonOverlay<*>
        if (path.segments.size == 1) return _traverseOverlaysOfSegment(path.segments.first()) { it }
        var currentProps: KeyValueOverlay = this
        println("Traversing begins")
        for (segment in path.segments.dropLast(1)) {
            println("Traversing $segment")
            currentProps = (currentProps._traverseOverlaysOfSegment(segment){ it as? KeyValueOverlay } ?: return null).also {
                println("Got : ${it::class.qualifiedName}")
            }
        }
        println("Traversing last ${path.segments.lastOrNull()}")
        return currentProps._traverseOverlaysOfSegment(path.segments.last()){ it }//.also { println("FOUND2 $it") }
    }

}