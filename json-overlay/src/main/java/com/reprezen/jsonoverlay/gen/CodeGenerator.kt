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

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.wakaztahir.kate.RelativeResourceEmbeddingManager
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Paths
import kotlin.io.path.name

class CodeGenerator private constructor(private val opts: Options) {

    @Throws(IOException::class)
    private fun generate(typeData: KTypeData) {
        generateInterfaces(typeData)
        generateImpls(typeData)
    }

    @Throws(IOException::class)
    private fun generateInterfaces(typeData: KTypeData) {
        val intfDir = intfDir
        val intfPackage = intfPackage
        val implPackage = implPackage
        for (type in typeData.types) {
            if (type.isNoGen) {
                return
            }
            JavaInterfaceGenerator(intfDir, intfPackage, implPackage, "").apply {
                val cUnit = getCompilationUnitFor(type)
                val file = getFileFor(type)
                val templatePath = opts.getTemplatePath(file, cUnit)
                val resource = RelativeResourceEmbeddingManager(templatePath?.let {
                    RelativeResourceEmbeddingManager("/").relativeParentPath(it)
                } ?: "/kotlin")
                this.generate(
                    javaFile = file,
                    resource = resource,
                    gen = cUnit,
                    templatePath = templatePath?.let { Paths.get(it).name } ?: (cUnit.type.templateResource)
                )
            }
        }
    }

    @Throws(IOException::class)
    private fun generateImpls(typeData: KTypeData) {
        val implDir = implDir
        val intfPackage = intfPackage
        val implPackage = implPackage
        for (type in typeData.types) {
            if (type.isNoGen) {
                return
            }
            JavaImplGenerator(implDir, intfPackage, implPackage, opts.classSuffix).apply {
                val cUnit = getCompilationUnitFor(type)
                val file = getFileFor(type)
                val templatePath = opts.getTemplatePath(file, cUnit)
                val resource = RelativeResourceEmbeddingManager(templatePath?.let {
                    RelativeResourceEmbeddingManager("/").relativeParentPath(it)
                } ?: "/kotlin")
                this.generate(
                    javaFile = file,
                    resource = resource,
                    gen = cUnit,
                    templatePath = templatePath?.let { Paths.get(it).name } ?: (cUnit.type.templateResource)
                )
            }
        }
    }

    private val implDir: File = File(opts.topDir, opts.classDir.path)
    private val implPackage: String = opts.pkg + "." + opts.classPackage
    private val intfDir: File = File(opts.topDir, opts.interfaceDir.path)
    private val intfPackage: String = opts.pkg + "." + opts.interfacePackage

    data class Options(
        var topDir: File = File("."),
        var typeDataFile: File = File("type-data.yaml"),
        var interfacePackage: String = "",
        var interfaceDir: File = File("."),
        var classDir: File = File("impl"),
        var pkg: String = CodeGenerator::class.java.getPackage().name,
        var classPackage: String = "impl",
        var classSuffix: String = "Impl",
        val getTemplatePath: (File, CompilationUnit) -> String? = { _, _ -> null }
    ) {
        constructor(
            topDir: String = ".",
            typeDataFile: String = "type-data.yaml",
            interfacePackage: String = "",
            interfaceDir: String = ".",
            classDir: String = "impl",
            pkg: String = CodeGenerator::class.java.getPackage().name,
            classPackage: String = "impl",
            classSuffix: String = "Impl",
            getTemplatePath: (File, CompilationUnit) -> String? = { _, _ -> null }
        ) : this(
            topDir = File(topDir),
            typeDataFile = File(typeDataFile),
            interfacePackage = interfacePackage,
            interfaceDir = File(interfaceDir),
            classDir = File(classDir),
            pkg = pkg,
            classPackage = classPackage,
            classSuffix = classSuffix,
            getTemplatePath = getTemplatePath
        )
    }


    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(options: Options) {
            val parsedYaml = Yaml().load<Any>(FileInputStream(options.typeDataFile))
            val typeData = YAMLMapper().convertValue(parsedYaml, TypeData::class.java)
            typeData.init()
            CodeGenerator(options).generate(KTypeData(typeData))
        }
    }
}