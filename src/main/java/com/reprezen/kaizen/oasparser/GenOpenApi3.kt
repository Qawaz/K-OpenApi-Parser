/*******************************************************************************
 * Copyright (c) 2017 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ModelSolv, Inc. - initial API and implementation and/or initial documentation
 */
package com.reprezen.kaizen.oasparser

import com.reprezen.jsonoverlay.gen.CodeGenerator

object GenOpenApi3 {

    private val overrideTemplates = mapOf<String, String>(
        "Path.java" to "java/interface/path.java.kate",
        "Callback.java" to "java/interface/callback.java.kate",
        "PathImpl.java" to "java/class/path.java.kate",
        "CallbackImpl.java" to "java/class/callback.java.kate"
    )

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        CodeGenerator.main(
            CodeGenerator.Options(
                topDir = "src/main/java/com/reprezen/kaizen/oasparser",
                typeDataFile = "src/main/java/com/reprezen/kaizen/oasparser/types3.yaml",
                interfacePackage = "model3",
                interfaceDir = "model3",
                classDir = "ovl3",
                pkg = "com.reprezen.kaizen.oasparser",
                classPackage = "ovl3",
                getTemplatePath = { file, _ ->
                    overrideTemplates[file.name]
                }
            )
        )
    }

}