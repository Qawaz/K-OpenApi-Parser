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
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        CodeGenerator.main(
            CodeGenerator.Options(
                typeDataFile = "src/main/java/com/reprezen/kaizen/oasparser/types3.yaml",
                pkg = "com.reprezen.kaizen.oasparser",
                topDir = "src/main/java/com/reprezen/kaizen/oasparser",
                interfaceDir = "model3",
                interfacePackage = "model3",
                classDir = "ovl3",
                classPackage = "ovl3"
            )
        )
    }
}