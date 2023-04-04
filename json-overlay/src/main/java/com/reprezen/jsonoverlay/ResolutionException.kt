/*********************************************************************
 * Copyright (c) 2017 ModelSolv, Inc. and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * ModelSolv, Inc.
 * - initial API and implementation and/or initial documentation
 */
package com.reprezen.jsonoverlay

open class ResolutionException @JvmOverloads constructor(
    message: String?,
    val reference: Reference?,
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    constructor(ref: Reference?) : this(null, ref, null)
    constructor(ref: Reference?, cause: Throwable?) : this(null, ref, cause)

    class ReferenceCycleException(ref: Reference?, val detectedAt: Reference) :
        ResolutionException("This reference participates in a reference cycle", ref) {

        companion object {
            private const val serialVersionUID = 1L
        }
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
