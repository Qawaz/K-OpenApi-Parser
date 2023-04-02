package com.reprezen.jsonoverlay.gen

class ClassMember(val code: String) {

    private var comment: String? = null
    private val annotations = mutableListOf<String>()

    fun generated(): ClassMember {
        val annotation = "@Generated(\"com.reprezen.jsonoverlay.gen.CodeGenerator\")"
        if (!annotations.contains(annotation)) {
            annotations.add(annotation)
        }
        return this
    }

    fun comment(comment: String?): ClassMember {
        this.comment = comment
        return this
    }

    fun override(): ClassMember {
        if (!annotations.contains("@Override")) {
            annotations.add("@Override")
        }
        return this
    }

    fun format(): String {
        var formatted = ""
        if (comment != null) {
            formatted += comment + "\n"
        }
        if (annotations.size > 0) {
            formatted += annotations.joinToString("\n")
            formatted += "\n"
        }
        formatted += code
        return formatted
    }

}