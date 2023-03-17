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

import com.reprezen.jsonoverlay.Overlay
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.kaizen.oasparser.ovl3.ParameterImpl
import com.reprezen.kaizen.oasparser.`val`.ObjectValidatorBase
import com.reprezen.kaizen.oasparser.`val`.msg.Messages.Companion.msg
import java.lang.Boolean
import kotlin.Any
import kotlin.String

class ParameterValidator : ObjectValidatorBase<Parameter>() {
    override fun runObjectValidations() {
        val parameter = value.getOverlay() as Parameter
        validateStringField(ParameterImpl.F_description, false)
        validateBooleanField(ParameterImpl.F_deprecated, false)
        validateBooleanField(ParameterImpl.F_allowEmptyValue, false)
        validateBooleanField(ParameterImpl.F_explode, false)
        val example: Overlay<Any> = validateField<Any>(ParameterImpl.F_example, false, Any::class.java, null)
        val examples: Overlay<Map<String, Example>>? = validateMapField<Example>(
            ParameterImpl.F_examples, false, false, Example::class.java,
            ExampleValidator()
        )
        checkExampleExclusion(examples, example)
        validateStringField(ParameterImpl.F_name, true)
        validateStringField(ParameterImpl.F_in, true, Regexes.PARAM_IN_REGEX)
        checkPathParam(parameter)
        checkRequired(parameter)
        validateStringField(ParameterImpl.F_style, false, Regexes.STYLE_REGEX)
        checkAllowReserved(parameter)
        // TODO Q: Should schema be required in parameter object?
        validateField<Schema>(ParameterImpl.F_schema, false, Schema::class.java, SchemaValidator())
        validateMapField<MediaType>(
            ParameterImpl.F_contentMediaTypes,
            false,
            false,
            MediaType::class.java,
            MediaTypeValidator()
        )
        validateExtensions(parameter.extensions)
    }

    private fun checkPathParam(parameter: Parameter) {
        if (parameter.getIn() != null && parameter.getIn() == "path" && parameter.name != null) {
            val path = getPathString(parameter)
            if (path != null) {
                if (!path.matches((".*\\{" + parameter.name + "\\}(.*)?").toRegex())) {
                    results.addError(msg(OpenApi3Messages.MissingPathTplt, parameter.name, path), value)
                }
            }
        }
    }

    private fun checkRequired(parameter: Parameter) {
        if ("path" == parameter.getIn()) {
            if (parameter.required !== Boolean.TRUE) {
                results.addError(msg(OpenApi3Messages.PathParamReq, parameter.name), value)
            }
        }
    }

    private fun checkAllowReserved(parameter: Parameter) {
        if (parameter.isAllowReserved && "query" != parameter.getIn()) {
            results.addWarning(msg(OpenApi3Messages.NonQryAllowRsvd, parameter.name, parameter.getIn()), value)
        }
    }

    private fun getPathString(parameter: Parameter): String? {
        var parent: PropertiesOverlay<*>? = Overlay.of(parameter).parentPropertiesOverlay
        while (parent != null && parent !is Path) {
            parent = Overlay.of(parent).getParentPropertiesOverlay()
        }
        return if (parent != null && parent is Path) Overlay.getPathInParent(parent) else null
    }

    fun checkExampleExclusion(examples: Overlay<Map<String, Example>>?, example: Overlay<Any>?) {
        val examplesPresent = examples != null && examples.isPresent && Overlay.getMapOverlay(examples).size() > 0
        val examplePresent = example != null && example.isPresent
        if (examplesPresent && examplePresent) {
            results.addError("ExmplExclusion|The 'example' and 'exmaples' properties may not both appear", value)
        }
    }
}