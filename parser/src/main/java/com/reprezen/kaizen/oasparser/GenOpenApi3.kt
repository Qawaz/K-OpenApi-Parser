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

        "Callback.kt" to "kotlin/interface/callback.kate",
        "CallbackImpl.kt" to "kotlin/class/callback.kate",

        "Example.kt" to "kotlin/interface/example.kate",
        "ExampleImpl.kt" to "kotlin/class/example.kate",

        "Header.kt" to "kotlin/interface/header.kate",
        "HeaderImpl.kt" to "kotlin/class/header.kate",

        "LicenseImpl.kt" to "kotlin/class/license.kate",

        "Link.kt" to "kotlin/interface/link.kate",
        "LinkImpl.kt" to "kotlin/class/link.kate",

        "OpenApi3Impl.kt" to "kotlin/class/openapi3.kate",

        "Parameter.kt" to "kotlin/interface/parameter.kate",
        "ParameterImpl.kt" to "kotlin/class/parameter.kate",

        "Path.kt" to "kotlin/interface/path.kate",
        "PathImpl.kt" to "kotlin/class/path.kate",

        "RequestBody.kt" to "kotlin/interface/request_body.kate",
        "RequestBodyImpl.kt" to "kotlin/class/request_body.kate",

        "Response.kt" to "kotlin/interface/response.kate",
        "ResponseImpl.kt" to "kotlin/class/response.kate",

        "Schema.kt" to "kotlin/interface/schema.kate",
        "SchemaImpl.kt" to "kotlin/class/schema.kate",

        "SecurityParameterImpl.kt" to "kotlin/class/security_parameter.kate",

        "SecurityScheme.kt" to "kotlin/interface/security_scheme.kate",
        "SecuritySchemeImpl.kt" to "kotlin/class/security_scheme.kate",

    )

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        CodeGenerator.main(
            CodeGenerator.Options(
                topDir = "parser/src/main/java/com/reprezen/kaizen/oasparser",
                typeDataFile = "parser/src/main/java/com/reprezen/kaizen/oasparser/types3.yaml",
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