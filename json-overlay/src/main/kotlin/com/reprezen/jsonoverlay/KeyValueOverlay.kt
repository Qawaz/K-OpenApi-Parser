package com.reprezen.jsonoverlay

interface KeyValueOverlay {

    fun _getPropertyNames(): List<String>

    fun _getKeyValueOverlay(name: String): JsonOverlay<*>?

    fun _findByPath(path: String): JsonOverlay<*>? {
        return _findByPath(JsonPointer(path))
    }

    fun _findByPath(path: JsonPointer): JsonOverlay<*>? {
        if (path.segments.isEmpty()) return this as JsonOverlay<*>
        if (path.segments.size == 1) return _getKeyValueOverlay(path.segments.first())
        var currentProps: KeyValueOverlay = this
//        println("Traversing begins")
        for (segment in path.segments.dropLast(1)) {
//            println("Traversing $segment")
            currentProps = currentProps._getKeyValueOverlay(segment).also {
//                println("Got : ${it!!::class.qualifiedName}")
            } as? KeyValueOverlay ?: return null
        }
//        println("Traversing last ${path.segments.lastOrNull()}")
        return currentProps._getKeyValueOverlay(path.segments.lastOrNull() ?: return null)
    }

}