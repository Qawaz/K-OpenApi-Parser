package com.reprezen.jsonoverlay.gen

interface TypeDeclaration {
    val name: String
    fun addMember(member: ClassMember)
    fun format(indentation: Int): String
}

class ClassOrInterfaceDeclaration(override val name: String, val isInterface: Boolean, val isPublic: Boolean) :
    TypeDeclaration {

    val extended = mutableListOf<String>()
    val implemented = mutableListOf<String>()
    val members = mutableListOf<ClassMember>()

    fun addExtendedType(type: String) {
        extended.add(type)
    }

    fun addImplementedType(type: String) {
        this.implemented.add(type)
    }

    override fun addMember(member: ClassMember) {
        members.add(member)
    }

    override fun format(indentation: Int): String {
        var indented = ""
        repeat(indentation) { indented += "\t" }
        var formatted = if (isPublic) "public " else ""
        formatted += if (isInterface) "interface " else "class "
        formatted += "$name "
        if (extended.size > 0) {
            formatted += "extends " + extended.joinToString(", ") + " "
        }
        if (implemented.size > 0 && !isInterface) {
            formatted += "implements " + implemented.joinToString(",") + " "
        }
        formatted += "{\n\n"
        formatted += members.joinToString("\n\n") { it.format(indentation + 1) }
        formatted += "\n}\n"
        return formatted
    }

}

class EnumDeclaration(override val name: String, val isPublic: Boolean) : TypeDeclaration {

    private val entries = mutableListOf<String>()
    private val members = mutableListOf<ClassMember>()

    fun addEntry(entryName: String) {
        entries.add(entryName)
    }

    override fun addMember(member: ClassMember) {
        members.add(member)
    }

    override fun format(indentation: Int): String {
        var formatted = if (isPublic) "public enum " else "enum "
        formatted += "$name "
        formatted += "{\n\n"
        formatted += entries.joinToString("\n\t")
        formatted += ";\n\n"
        formatted += members.joinToString("\n") { it.format(indentation + 1) }
        formatted += "\n\n}"
        return formatted
    }
}