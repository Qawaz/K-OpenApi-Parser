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

    fun format(indentation: Int): String {
        var indented = ""
        repeat(indentation) { indented += "\t" }
        var formatted = ""
        if (comment != null) {
            formatted += "$indented// $comment\n"
        }
        if (annotations.size > 0) {
            formatted += annotations.joinToString("\n") { "$indented$it" }
            formatted += "\n"
        }
        formatted += indented + code.replace("\n", "\n$indented")
        return formatted
    }

}