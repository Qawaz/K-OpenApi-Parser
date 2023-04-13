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
package com.reprezen.kaizen.oasparser.validate

import com.reprezen.jsonoverlay.Overlay

class ValidationResults {
    enum class Severity {
        NONE, INFO, WARNING, ERROR;

        fun lt(other: Severity?): Boolean {
            return this.compareTo(other!!) < 0
        }

        fun le(other: Severity?): Boolean {
            return this.compareTo(other!!) <= 0
        }

        fun gt(other: Severity?): Boolean {
            return this.compareTo(other!!) > 0
        }

        fun ge(other: Severity?): Boolean {
            return this.compareTo(other!!) >= 0
        }

        companion object {
            val MAX_SEVERITY = ERROR
        }
    }

    private val items: MutableList<ValidationItem> = ArrayList()
    fun <V> addInfo(msg: String, context: Overlay<V>) {
        items.add(ValidationItem(Severity.INFO, msg, context))
    }

    fun addWarning(msg: String, context: Overlay<*>) {
        items.add(ValidationItem(Severity.WARNING, msg, context))
    }

    fun addError(msg: String, context: Overlay<*>) {
        items.add(ValidationItem(Severity.ERROR, msg, context))
    }

    fun add(results: ValidationResults) {
        items.addAll(results.getItems())
    }

    fun getItems(): Collection<ValidationItem> {
        return items
    }

    val severity: Severity
        get() {
            var severity = Severity.NONE
            for (item in items) {
                if (item.severity.gt(severity)) {
                    severity = item.severity
                    if (severity == Severity.MAX_SEVERITY) {
                        break
                    }
                }
            }
            return severity
        }

    class ValidationItem(val severity: Severity, val msg: String, context: Overlay<*>)
}