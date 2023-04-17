package com.reprezen.jsonoverlay

interface KeyValueOverlay {

    fun _getPropertyNames(): List<String>

    fun _getValueOverlayByName(name: String): JsonOverlay<*>?

    fun _getValueOverlayByPath(segment: String): JsonOverlay<*>? {
        return _getValueOverlayByName(segment)
    }

    fun _getValueOverlayByIndex(index : Int) : JsonOverlay<*>? {
        return _getValueOverlayByPath(index.toString())
    }

    fun _findByPointer(path: JsonPointer): JsonOverlay<*>?

}