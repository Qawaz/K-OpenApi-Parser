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
package com.reprezen.jsonoverlay.gen

import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import com.reprezen.jsonoverlay.*
import com.wakaztahir.kate.InputSourceStream
import com.wakaztahir.kate.OutputDestinationStream
import com.wakaztahir.kate.RelativeResourceEmbeddingManager
import com.wakaztahir.kate.TemplateContext
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.annotation.Generated

abstract class TypeGenerator(
    private val dir: File,
    protected var intfPackage: String,
    protected var implPackage: String,
    protected var suffix: String,
    private val preserve: Boolean
) {
    private val requiredTypes: MutableSet<String> = HashSet()

    protected abstract fun getPackage(): String

    protected abstract fun getTypeDeclaration(file: File, type: TypeData.Type, suffix: String?): TypeDeclaration

    @Throws(IOException::class)
    fun generate(type: TypeData.Type) {
        val filename = String.format("%s%s.java", type.name, suffix)
        val javaFile = File(dir, filename)
        println("Generating " + javaFile.canonicalFile)
        generateWithJavaTemplate(javaFile, type)
    }

    private fun getCompilationUnitFor(javaFile: File, type: TypeData.Type): CompilationUnit {
        val declaration = getTypeDeclaration(javaFile, type, suffix)
        val gen = CompilationUnit(getPackage(), declaration)
        requireTypes(getImports(type))
        if (needIntfImports()) {
            gen.addImport("$intfPackage.*")
        }
        addGeneratedMembers(type, gen)
        requireTypes(Generated::class.java)
        resolveImports(type, gen)
        return gen
    }

    @Throws(IOException::class)
    protected fun generateToFile(javaFile: File, type: TypeData.Type) {
        val gen = getCompilationUnitFor(javaFile = javaFile, type = type)
        Files.writeString(javaFile.toPath(), gen.format())
    }

    @Throws(IOException::class)
    protected fun generateWithJavaTemplate(javaFile: File, type: TypeData.Type) {
        val gen = getCompilationUnitFor(javaFile = javaFile, type = type)
        val obj = gen.toMutableKTEObject()
        val resource = RelativeResourceEmbeddingManager("/java")
        val context = TemplateContext(
            stream = InputSourceStream(
                inputStream = resource.getStream(gen.type.javaTemplateResource),
                model = obj,
                embeddingManager = resource
            )
        )
        gen.toMutableKTEObject()
        val outputStream = javaFile.outputStream()
        context.generateTo(OutputDestinationStream(outputStream))
        outputStream.close()
    }

    protected abstract fun getImports(type: TypeData.Type): Collection<String>

    protected open fun needIntfImports(): Boolean {
        return false
    }

    protected fun requireTypes(vararg types: Class<*>) {
        requireTypes(Stream.of(*types).map { obj: Class<*> -> obj.simpleName }
            .collect(Collectors.toList()))
    }

    protected fun requireTypes(vararg types: String) {
        requireTypes(types.toList())
    }

    protected fun requireTypes(types: Collection<String>) {
        requiredTypes.addAll(
            types.stream().map { t: String -> if (t.contains("<")) t.substring(0, t.indexOf("<")) else t }
                .collect(Collectors.toList()))
    }

    private fun resolveImports(type: TypeData.Type, gen: CompilationUnit) {
        val importMap = type.typeData.imports
        val typeMap = type.typeData.typeMap
        for (requiredType in requiredTypes) {
            resolveImport(requiredType, typeMap, importMap)?.let { gen.addImport(it) }
        }
    }

    private fun resolveImport(
        type: String,
        typeMap: Map<String, TypeData.Type>,
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

    protected fun addGeneratedMembers(type: TypeData.Type, gen: CompilationUnit) {
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
        members.addAll(getOtherMembers(type))
        gen.addGeneratedMembers(members)
    }

    protected open fun skipField(field: TypeData.Field): Boolean {
        return false
    }

    //    private CompilationUnit tryParse(File file) {
    //        try {
    //            return JavaParser.parse(file);
    //        } catch (IOException e) {
    //            System.err.println("ABORTING AFTER PARTIAL GENERATION!");
    //            System.err.printf(
    //                    "Parsing of file %s failed; so generation cannot continue without destroying manual code.\n", file);
    //            System.err.println("Please restore generated code artifacts to a known good state before regenerating");
    //            System.err.println("Parse Error:");
    //            e.printStackTrace();
    //            System.exit(1);
    //            return null;
    //        }
    //    }
    //
    //    private void copyFileComment(SimpleJavaGenerator gen, CompilationUnit existing) {
    //        Optional<Comment> fileComment = existing.getComment();
    //        if (fileComment.isPresent()) {
    //            gen.setFileComment(fileComment.get().toString());
    //        }
    //    }
    //
    //    private void addManualMembers(SimpleJavaGenerator gen, CompilationUnit existing) {
    //        for (TypeDeclaration<?> type : existing.getTypes()) {
    //            for (BodyDeclaration<?> member : type.getMembers()) {
    //                if (member instanceof MethodDeclaration || member instanceof FieldDeclaration
    //                        || member instanceof ConstructorDeclaration) {
    //                    if (!isGenerated(member)) {
    //                        gen.addMember(new Member(member));
    //                    }
    //                }
    //            }
    //        }
    //    }
    //
    //    private boolean isGenerated(BodyDeclaration<?> node) {
    //        for (AnnotationExpr annotation : node.getAnnotations()) {
    //            if (annotation.getName().toString().equals("Generated")) {
    //                return true;
    //            }
    //        }
    //        return false;
    //    }
    protected open fun getConstructors(type: TypeData.Type): Members {
        return Members()
    }

    protected open fun getFieldMembers(field: TypeData.Field): Members {
        return Members()
    }

    protected open fun getFieldMethods(field: TypeData.Field): Members {
        return Members()
    }

    protected open fun getOtherMembers(type: TypeData.Type): Members {
        return Members()
    }

    class Members : ArrayList<ClassMember>() {
        fun addMember(code: String) {
            add(ClassMember(code = code))
        }
    }

    companion object {
        private val autoTypes = hashSetOf<String>(
            "String", "Object", "Boolean", "Integer", "Number"
        )

        private val knownTypes = getKnownTypes()
        private fun getKnownTypes(): Map<String, String> {
            val results: MutableMap<String, String> = HashMap()
            val overlays = listOf( //
                Generated::class.java,  //
                MutableList::class.java,  //
                MutableMap::class.java,  //
                Optional::class.java,  //
                Collectors::class.java,  //
                JsonNode::class.java,  //
                ObjectNode::class.java,  //
                JsonNodeFactory::class.java,  //
                JsonPointer::class.java,  //
                IJsonOverlay::class.java,  //
                JsonOverlay::class.java,  //
                IModelPart::class.java,  //
                PropertiesOverlay::class.java,  //
                OverlayFactory::class.java,  //
                Builder::class.java,  //
                ReferenceManager::class.java,  //
                StringOverlay::class.java,  //
                IntegerOverlay::class.java,  //
                NumberOverlay::class.java,  //
                BooleanOverlay::class.java,  //
                EnumOverlay::class.java,  //
                PrimitiveOverlay::class.java,  //
                ObjectOverlay::class.java,  //
                ListOverlay::class.java,  //
                MapOverlay::class.java
            ) //
            for (cls in overlays) {
                results[cls.simpleName] = cls.name.replace("\\$".toRegex(), ".")
            }
            return results
        }
    }
}
