/*********************************************************************
 * Copyright (c) 2017 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * ModelSolv, Inc.
 * - initial API and implementation and/or initial documentation
 */
package com.wakaztahir.generator

import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import com.reprezen.jsonoverlay.*
import com.reprezen.jsonoverlay.parser.Generated
import com.wakaztahir.kate.InputSourceStream
import com.wakaztahir.kate.OutputDestinationStream
import com.wakaztahir.kate.RelativeResourceEmbeddingManager
import com.wakaztahir.kate.TemplateContext
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.*
import java.util.stream.Collectors
import kotlin.reflect.KClass

abstract class TypeGenerator(
    private val dir: File,
    protected var intfPackage: String,
    protected var implPackage: String,
    protected var suffix: String
) {
    private val requiredTypes: MutableSet<String> = HashSet()

    protected abstract fun getPackage(): String

    protected abstract fun getTypeDeclaration(type: KTypeData.Type, suffix: String?): TypeDeclaration

    fun getCompilationUnitFor(type: KTypeData.Type): CompilationUnit {
        val declaration = getTypeDeclaration(type, suffix)
        val gen = CompilationUnit(getPackage(), declaration)
        requireTypes(getImports(type))
        if (needIntfImports()) {
            gen.addImport("$intfPackage.*")
        }
        addGeneratedMembers(type, gen)
        this.requireTypes(Generated::class)
        resolveImports(type, gen)
        return gen
    }

    @Throws(IOException::class)
    protected fun generateWithTemplate(
        javaFile: File,
        gen: CompilationUnit,
        inputStream: InputStream,
        resource: RelativeResourceEmbeddingManager,
    ) {
        println("Generating " + javaFile.canonicalFile)
        val obj = gen.toMutableKATEObject()
        val context = TemplateContext(
            stream = InputSourceStream(
                inputStream = inputStream,
                model = obj,
                embeddingManager = resource
            )
        )
        val outputStream = javaFile.outputStream()
        context.generateTo(OutputDestinationStream(outputStream))
        outputStream.close()
    }

    fun getFileFor(type: KTypeData.Type): File {
        return File(dir, String.format("%s%s.kt", type.name, suffix))
    }

    @Throws(IOException::class)
    fun generate(
        gen: CompilationUnit,
        javaFile: File,
        resource: RelativeResourceEmbeddingManager,
        templatePath: String
    ) {
        generateWithTemplate(
            javaFile = javaFile,
            gen = gen,
            resource = resource,
            inputStream = resource.getStream(templatePath)
        )
    }

    protected abstract fun getImports(type: KTypeData.Type): Collection<String>

    protected open fun needIntfImports(): Boolean {
        return false
    }

    protected fun requireTypes(vararg types: KClass<*>) {
        requireTypes(types.toList().map { it.simpleName!! })
    }

    protected fun requireTypes(vararg types: String) {
        requireTypes(types.toList())
    }

    protected fun requireTypes(types: Collection<String>) {
        requiredTypes.addAll(
            types.stream().map { t: String -> if (t.contains("<")) t.substring(0, t.indexOf("<")) else t }
                .collect(Collectors.toList()))
    }

    private fun resolveImports(type: KTypeData.Type, gen: CompilationUnit) {
        val importMap = type.typeData.imports
        val typeMap = type.typeData.typeMap
        for (requiredType in requiredTypes) {
            resolveImport(requiredType, typeMap, importMap)?.let { gen.addImport(it) }
        }
    }

    private fun resolveImport(
        type: String,
        typeMap: Map<String, KTypeData.Type>,
        importMap: Map<String, String>
    ): String? {
        return if (importMap.containsKey(type)) {
            when (val imp = importMap[type]) {
                "_intf" -> {
                    "$intfPackage.$type"
                }

                "_impl" -> {
                    "$implPackage.$type"
                }

                else -> {
                    imp
                }
            }
        } else if (typeMap.containsKey(type)) {
            // interface type
            "$intfPackage.$type"
        } else if (suffix.isNotEmpty() && type.endsWith(suffix)
            && typeMap.containsKey(type.substring(0, type.length - suffix.length))
        ) {
            // impl type
            "$implPackage.$type"
        } else if (autoTypes.contains(type)) {
            null
        } else if (knownTypes.containsKey(type)) {
            knownTypes[type]
        } else {
            throw RuntimeException("Unable to resolve import for type: $type")
        }
    }

    protected fun addGeneratedMembers(type: KTypeData.Type, gen: CompilationUnit) {
        val members = Members()
        members.addAll(getConstructors(type))
        for (field in type.fields.values) {
            if (!skipField(field)) {
                members.addAll(getFieldMembers(field))
            }
        }
        for (field in type.fields.values) {
            if (!skipField(field)) {
                members.addAll(getFieldMethods(field))
            }
        }
        for (member in getCompanionMembers(type)) {
            gen.type.addCompanionMember(member)
        }
        members.addAll(getOtherMembers(type))
        gen.addGeneratedMembers(members)
    }

    protected open fun skipField(field: KTypeData.Field): Boolean {
        return false
    }

    protected open fun getConstructors(type: KTypeData.Type): Members {
        return Members()
    }

    protected open fun getFieldMembers(field: KTypeData.Field): Members {
        return Members()
    }

    protected open fun getFieldMethods(field: KTypeData.Field): Members {
        return Members()
    }

    protected open fun getCompanionMembers(type: KTypeData.Type): Members {
        return Members()
    }

    protected open fun getOtherMembers(type: KTypeData.Type): Members {
        return Members()
    }

    class Members : ArrayList<ClassMember>() {
        fun addMember(code: String) {
            add(ClassMember(code = code))
        }
    }

    companion object {
        private val autoTypes = hashSetOf<String>(
            "String", "Any", "Boolean", "Int", "Number"
        )

        private val knownTypes = getKnownTypes()
        private fun getKnownTypes(): Map<String, String> {
            val results: MutableMap<String, String> = HashMap()
            val overlays = listOf( //
                Generated::class,  //
                MutableList::class,  //
                MutableMap::class,  //
                Optional::class,  //
                Collectors::class,  //
                JsonNode::class,  //
                ObjectNode::class,  //
                JsonNodeFactory::class,  //
                JsonPointer::class,  //
                IJsonOverlay::class,  //
                JsonOverlay::class,  //
                IModelPart::class,  //
                PropertiesOverlay::class,  //
                OverlayFactory::class,  //
                Builder::class,  //
                ReferenceManager::class,  //
                StringOverlay::class,  //
                IntegerOverlay::class,  //
                NumberOverlay::class,  //
                BooleanOverlay::class,  //
                EnumOverlay::class,  //
                PrimitiveOverlay::class,  //
                ObjectOverlay::class,  //
                ListOverlay::class,  //
                MapOverlay::class
            ) //
            for (cls in overlays) {
                results[cls.simpleName!!] = cls.qualifiedName!!.replace("\\$".toRegex(), ".")
            }
            return results
        }
    }
}
