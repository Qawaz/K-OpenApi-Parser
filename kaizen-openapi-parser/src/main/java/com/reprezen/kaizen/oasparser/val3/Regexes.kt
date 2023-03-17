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
package com.reprezen.kaizen.oasparser.val3

import java.util.regex.Pattern

object Regexes {
    val PATH_REGEX = Pattern.compile("/.*")
    val EXT_REGEX = Pattern.compile("x-.+")
    val NOEXT_REGEX = Pattern.compile("(?!x-).*")
    val NAME_REGEX = Pattern.compile("[a-zA-Z0-9\\._-]+")
    val NOEXT_NAME_REGEX = Pattern.compile("(?!x-)[a-zA-Z0-9\\._-]+")
    val METHOD_REGEX = Pattern.compile("get|put|post|delete|options|head|patch|trace")
    val PARAM_IN_REGEX = Pattern.compile("path|query|header|cookie")
    val STYLE_REGEX = Pattern
        .compile("matrix|label|form|simple|spaceDelimited|pipeDelimited|deepObject")
    val RESPONSE_REGEX = Pattern.compile("default|(\\d[0-9X]{2})")
}