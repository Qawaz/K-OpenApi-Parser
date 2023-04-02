package com.reprezen.jsonoverlay.gen

import com.wakaztahir.kate.model.StringValue
import com.wakaztahir.kate.model.model.KTEListImpl
import com.wakaztahir.kate.model.model.MutableKTEObject

class CompilationUnit(private var packageDec: String, val type: TypeDeclaration) {

    private val imports = mutableListOf<String>()

    fun addImport(import: String) {
        imports.add(import)
    }

    fun addGeneratedMembers(members: Collection<ClassMember>) {
        for (member in members) type.addMember(member.generated())
    }

    fun format(): String {
        var formatted = "package ${packageDec};"
        if (imports.size > 0) {
            formatted += "\n\n"
            formatted += imports.joinToString("\n") { "import $it;" }
        }
        formatted += "\n\n${type.format()}"
        return formatted
    }

    fun toMutableKTEObject(): MutableKTEObject {
        return type.toMutableKTEObject().apply {
            putValue("packageName", packageDec)
            putValue("imports",KTEListImpl(imports.map { StringValue(it) }))
        }
    }

}