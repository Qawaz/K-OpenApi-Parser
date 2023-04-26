package com.wakaztahir.generator

import com.wakaztahir.kate.model.BooleanValue
import com.wakaztahir.kate.model.KATEType
import com.wakaztahir.kate.model.StringValue
import com.wakaztahir.kate.model.model.KATEListImpl
import com.wakaztahir.kate.model.model.MutableKATEObject

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
        return MutableKATEObject {
            insertValue("name", name)
            insertValue("extends", KATEListImpl(extended.map { StringValue(it) }, itemType = KATEType.String))
            insertValue("implements", KATEListImpl(implemented.map { StringValue(it) }, itemType = KATEType.String))
            insertValue("isPublic", BooleanValue(isPublic))
            insertValue("ClassMembers", members.joinToString("\n\n") { it.format(1) })
            insertValue("CompanionMembers", companionMembers.joinToString("\n\n") { it.format(2) })
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
        return MutableKATEObject {
            insertValue("name", name)
            insertValue("isPublic", BooleanValue(isPublic))
            insertValue("EnumEntries", "\t" + entries.joinToString(",\n\t"))
            insertValue("ClassMembers", members.joinToString("\n\n") { it.format(1) })
            insertValue("CompanionMembers", companionMembers.joinToString("\n\n") { it.format(2) })
        }
    }

}