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

import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.kaizen.oasparser.ovl3.LinkImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase
import com.reprezen.kaizen.oasparser.validate.ValidationResults
import com.reprezen.kaizen.oasparser.validate.msg.Messages.Companion.msg

class LinkValidator : ObjectValidatorBase<Link>() {
    override fun runObjectValidations() {
        // TODO: Validate operationRef value (why didn't they must make it a ref
        // object???!)
        val link = value.overlay as Link
        validateStringField(LinkImpl.F_description, false)
        val op = checkValidOperation(link)
        op?.let { checkParameters(link, it) }
        val requestBody: Overlay<Any> = validateField<Any>(LinkImpl.F_requestBody, false, null)
        checkRequestBody(requestBody)
        validateField<Server>(LinkImpl.F_server, false, ServerValidator())
        validateExtensions(link.getExtensions())
    }

    private fun checkValidOperation(link: Link): Operation? {
        val opId = link.getOperationId()
        val operationRef = link.getOperationRef()
        var op: Operation? = null
        if (opId == null && operationRef == null) {
            results.addError(msg(OpenApi3Messages.NoOpIdNoOpRefInLink), value)
        } else if (opId != null && operationRef != null) {
            results.addError(msg(OpenApi3Messages.OpIdAndOpRefInLink), value)
        }
        if (opId != null) {

            op = findOperationById((link as LinkImpl)._getRoot() as OpenApi3, opId)
            if (op == null) {
                results.addError(msg(OpenApi3Messages.OpIdNotFound, opId), value)
            }
        }
        val relativePath = getRelativePath(operationRef, results)
        if (relativePath != null) {
            op = findOperationByPath(Overlay.of(link).getModel<OpenApi3>()!!, relativePath, results)
            if (op == null) {
                results.addError(msg(OpenApi3Messages.OpPathNotFound, operationRef!!), value)
            }
        }
        return op
    }

    private fun checkParameters(link: Link, op: Operation) {
        // TODO Q: parameter name is not sufficient to identify param in
        // operation; will
        // allow if it's unique, warn if
        // it's not
        val opParamCounts = getParamNameCounts(op.getParameters())
        val params = link.getParameters()
        for (paramName in params.keys) {
            val count = opParamCounts[paramName]!!
            if (count == 0) {
                results.addError(msg(OpenApi3Messages.BadLinkParam, paramName), Overlay.of(params, paramName)!!)
            } else if (count > 1) {
                results.addWarning(msg(OpenApi3Messages.AmbigLinkParam, paramName), Overlay.of(params, paramName)!!)
            }
        }
    }

    private fun findOperationById(model: OpenApi3, operationId: String): Operation? {
        for (path in model.getPaths().values) {
            for (op in path.getOperations().values) {
                if (operationId == op.getOperationId()) {
                    return op
                }
            }
        }
        return null
    }

    private fun findOperationByPath(model: OpenApi3, relativePath: String, results: ValidationResults): Operation? {
        return model.getPath(relativePath)?.getGet()
    }

    private fun getRelativePath(operationRef: String?, results: ValidationResults): String? {
        // TODO Q: will braces be pct-encoded as required for URIs?
        if (operationRef != null) {
            results.addWarning(msg(OpenApi3Messages.OperationRefUnSupp), value)
        }
        return null
    }

    private fun getParamNameCounts(parameters: Collection<Parameter>): Map<String, Int> {
        val counts: MutableMap<String, Int> = HashMap()
        for (parameter in parameters) {
            val name = parameter.getName()!!
            if (counts.containsKey(name)) {
                counts[name] = 1 + counts[name]!!
            } else {
                counts[name] = 1
            }
        }
        return counts
    }

    private fun checkRequestBody(rbField: Overlay<Any>?) {
        if (rbField != null && rbField.isPresent && rbField.get() is String) {
            // TODO if this looks like it's meant to be an expression, check that it's a
            // valid one, and warn if not
        }
    }
}