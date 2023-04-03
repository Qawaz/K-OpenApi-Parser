package com.reprezen.jsonoverlay.gen

import com.wakaztahir.kate.model.StringValue
import com.wakaztahir.kate.model.model.KATEListImpl
import com.wakaztahir.kate.model.model.MutableKATEObject

class CompilationUnit(private var packageDec: String, val type: TypeDeclaration) {

    private val imports = mutableListOf<String>()

    fun addImport(import: String) {
        imports.add(import)
    }

    fun addGeneratedMembers(members: Collection<ClassMember>) {
        for (member in members) type.addMember(member.generated())
    }

    fun toMutableKATEObject(): MutableKATEObject {
        return type.toMutableKTEObject().apply {
            putValue("packageName", packageDec)
            putValue("imports", KATEListImpl(imports.map { StringValue(it) }))
        }
    }

}