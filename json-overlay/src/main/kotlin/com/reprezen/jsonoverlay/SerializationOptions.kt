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

import java.util.*

class SerializationOptions {
    enum class Option {
        KEEP_EMPTY,
        KEEP_ONE_EMPTY,
        FOLLOW_REFS
    }

    private val options: Set<Option>

    constructor(vararg options: Option) {
        this.options = HashSet(listOf(*options))
    }

    constructor(options: Collection<Option>?) {
        this.options = HashSet(options)
    }

    operator fun plus(addOptions: Collection<Option>?): SerializationOptions {
        val newOptions: MutableSet<Option> = HashSet(
            options
        )
        newOptions.addAll(addOptions!!)
        return SerializationOptions(newOptions)
    }

    fun plus(vararg addOptions: Option): SerializationOptions {
        return plus(listOf(*addOptions))
    }

    operator fun minus(removeOptions: Collection<Option>?): SerializationOptions {
        val newOptions: MutableSet<Option> = HashSet(
            options
        )
        newOptions.removeAll(removeOptions!!)
        return SerializationOptions(newOptions)
    }

    fun minus(vararg removeOptions: Option): SerializationOptions {
        return minus(listOf(*removeOptions))
    }

    val isKeepEmpty: Boolean
        get() = options.contains(Option.KEEP_EMPTY)
    val isKeepOneEmpty: Boolean
        get() = options.contains(Option.KEEP_ONE_EMPTY)
    val isKeepThisEmpty: Boolean
        get() = isKeepEmpty || isKeepOneEmpty
    val isFollowRefs: Boolean
        get() = options.contains(Option.FOLLOW_REFS)

    companion object {
        val EMPTY = SerializationOptions()
    }
}