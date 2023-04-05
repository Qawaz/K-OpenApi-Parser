package com.reprezen.jsonoverlay.gen

import com.wakaztahir.kate.model.BooleanValue
import com.wakaztahir.kate.model.StringValue
import com.wakaztahir.kate.model.model.KATEListImpl
import com.wakaztahir.kate.model.model.MutableKATEObject
import com.wakaztahir.kate.model.model.MutableKTEObject

interface TypeDeclaration {

    val name: String

    val templateResource: String

    fun addMember(member: ClassMember)

    fun addCompanionMember(member: ClassMember)

    fun toMutableKTEObject(): MutableKATEObject

}

class ClassOrInterfaceDeclaration(
    override val name: String,
    val isInterface: Boolean,
    val isPublic: Boolean
) : TypeDeclaration {

    override val templateResource: String
        get() = if (isInterface) "interface.kate" else "implementation.kate"

    val extended = mutableListOf<String>()
    val implemented = mutableListOf<String>()
    val members = mutableListOf<ClassMember>()
    val companionMembers = mutableListOf<ClassMember>()

    fun addExtendedType(type: String) {
        extended.add(type)
    }

    fun addImplementedType(type: String) {
        this.implemented.add(type)
    }

    override fun addMember(member: ClassMember) {
        members.add(member)
    }

    override fun addCompanionMember(member: ClassMember) {
        companionMembers.add(member)
    }

    override fun toMutableKTEObject(): MutableKATEObject {
        return MutableKTEObject {
            putValue("name", name)
            putValue("extends", KATEListImpl(extended.map { StringValue(it) }))
            putValue("implements", KATEListImpl(implemented.map { StringValue(it) }))
            putValue("isPublic", BooleanValue(isPublic))
            putValue("ClassMembers", members.joinToString("\n\n") { it.format(1) })
            putValue("CompanionMembers", companionMembers.joinToString("\n\n") { it.format(2) })
        }
    }

}

class EnumDeclaration(override val name: String, val isPublic: Boolean) : TypeDeclaration {

    override val templateResource: String
        get() = "enum.kate"

    private val entries = mutableListOf<String>()
    private val members = mutableListOf<ClassMember>()
    val companionMembers = mutableListOf<ClassMember>()

    fun addEntry(entryName: String) {
        entries.add(entryName)
    }

    override fun addMember(member: ClassMember) {
        members.add(member)
    }

    override fun addCompanionMember(member: ClassMember) {
        companionMembers.add(member)
    }

    override fun toMutableKTEObject(): MutableKATEObject {
        return MutableKTEObject {
            putValue("name", name)
            putValue("isPublic", BooleanValue(isPublic))
            putValue("EnumEntries", "\t" + entries.joinToString(",\n\t"))
            putValue("ClassMembers", members.joinToString("\n\n") { it.format(1) })
            putValue("CompanionMembers", companionMembers.joinToString("\n\n") { it.format(2) })
        }
    }

}