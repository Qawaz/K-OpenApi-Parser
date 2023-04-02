package com.reprezen.jsonoverlay.gen

class CompilationUnit(private var packageDec: String, val type: TypeDeclaration) {

    private val imports = mutableListOf<String>()

    fun addImport(import: String) {
        imports.add(import)
    }

    fun addGeneratedMembers(members: Collection<ClassMember>) {
        for (member in members) type.addMember(member.generated())
    }

    fun format(): String {
        val indentation = 0
        var formatted = packageDec
        if (imports.size > 0) {
            formatted += "\n\n"
            formatted += imports.joinToString("\n") { "import $it;" }
        }
        formatted += "\n\n${type.format(indentation)}"
        return formatted
    }

}