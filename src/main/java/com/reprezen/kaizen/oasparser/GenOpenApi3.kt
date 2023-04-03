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

        "Callback.java" to "java/interface/callback.java.kate",
        "CallbackImpl.java" to "java/class/callback.java.kate",

        "Example.java" to "java/interface/example.java.kate",
        "ExampleImpl.java" to "java/class/example.java.kate",

        "Header.java" to "java/interface/header.java.kate",
        "HeaderImpl.java" to "java/class/header.java.kate",

        "LicenseImpl.java" to "java/class/license.java.kate",

        "Link.java" to "java/interface/link.java.kate",
        "LinkImpl.java" to "java/class/link.java.kate",

        "OpenApi3.java" to "java/interface/openapi3.java.kate",
        "OpenApi3Impl.java" to "java/class/openapi3.java.kate",

        "Parameter.java" to "java/interface/parameter.java.kate",
        "ParameterImpl.java" to "java/class/parameter.java.kate",

        "Path.java" to "java/interface/path.java.kate",
        "PathImpl.java" to "java/class/path.java.kate",

        "RequestBody.java" to "java/interface/request_body.java.kate",
        "RequestBodyImpl.java" to "java/class/request_body.java.kate",

        "Response.java" to "java/interface/response.java.kate",
        "ResponseImpl.java" to "java/class/response.java.kate",

        "Schema.java" to "java/interface/schema.java.kate",
        "SchemaImpl.java" to "java/class/schema.java.kate",

        "SecurityParameterImpl.java" to "java/class/security_parameter.java.kate",

        "SecurityScheme.java" to "java/interface/security_scheme.java.kate",
        "SecuritySchemeImpl.java" to "java/class/security_scheme.java.kate",

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