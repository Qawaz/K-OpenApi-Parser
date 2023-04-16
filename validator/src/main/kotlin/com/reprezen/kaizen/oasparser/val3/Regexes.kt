/*******************************************************************************
 * Copyright (c) 2023 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * WaqasTahir - Contributions after 2022
 * ModelSolv, Inc. - initial API and implementation and/or initial documentation
 */
package com.reprezen.kaizen.oasparser.val3

object Regexes {
    val PATH_REGEX = Regex("/.*")
    val EXT_REGEX = Regex("x-.+")
    val NOEXT_REGEX = Regex("(?!x-).*")
    val NAME_REGEX = Regex("[a-zA-Z0-9\\._-]+")
    val NOEXT_NAME_REGEX = Regex("(?!x-)[a-zA-Z0-9\\._-]+")
    val METHOD_REGEX = Regex("get|put|post|delete|options|head|patch|trace")
    val PARAM_IN_REGEX = Regex("path|query|header|cookie")
    val STYLE_REGEX = Regex("matrix|label|form|simple|spaceDelimited|pipeDelimited|deepObject")
    val RESPONSE_REGEX = Regex("default|(\\d[0-9X]{2})")
}