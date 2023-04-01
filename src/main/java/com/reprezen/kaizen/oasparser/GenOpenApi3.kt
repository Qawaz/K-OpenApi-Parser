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
            arrayOf( //
                "-t", "src/main/java/com/reprezen/kaizen/oasparser/types3.yaml",  //
                "-p", "com.reprezen.kaizen.oasparser",  //
                "-d", "src/main/java/com/reprezen/kaizen/oasparser",  //
                "-i", "kmodel3",  //
                "-I", "kmodel3",  //
                "-c", "kovl3",  //
                "-C", "kovl3"
            )
        )
    }
}