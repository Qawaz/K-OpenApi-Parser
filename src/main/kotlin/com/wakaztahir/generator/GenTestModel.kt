/*********************************************************************
 * Copyright (c) 2023 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * WaqasTahir - Contributions after 2022
 * ModelSolv, Inc.
 * - initial API and implementation and/or initial documentation
 */
package com.wakaztahir.generator

object GenTestModel {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        CodeGenerator.main(
            CodeGenerator.Options(
                topDir = "json-overlay/src/test/kotlin/com/reprezen/jsonoverlay/model",
                typeDataFile = "src/main/kotlin/com/wakaztahir/generator/test_types.yaml",
                interfacePackage = "intf",
                interfaceDir = "intf",
                classDir = "impl",
                pkg = "com.reprezen.jsonoverlay.model",
                classPackage = "impl"
            )
        )
    }

}
