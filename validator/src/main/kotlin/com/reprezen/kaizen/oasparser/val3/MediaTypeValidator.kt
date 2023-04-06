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
import com.reprezen.kaizen.oasparser.model3.EncodingProperty
import com.reprezen.kaizen.oasparser.model3.Example
import com.reprezen.kaizen.oasparser.model3.MediaType
import com.reprezen.kaizen.oasparser.model3.Schema
import com.reprezen.kaizen.oasparser.ovl3.MediaTypeImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase
import com.reprezen.kaizen.oasparser.validate.ValidationResults
import com.reprezen.kaizen.oasparser.validate.msg.Messages.Companion.msg

class MediaTypeValidator : ObjectValidatorBase<MediaType>() {
    override fun runObjectValidations() {
        val mediaType = value.getOverlay() as MediaType
        // TODO Q: Should schema be required in media type?
        validateField<Schema>(MediaTypeImpl.F_schema, false, Schema::class.java, SchemaValidator())
        validateMapField<EncodingProperty>(
            MediaTypeImpl.F_encodingProperties,
            false,
            false,
            EncodingProperty::class.java,
            EncodingPropertyValidator()
        )
        checkEncodingPropsAreProps(mediaType, results)
        validateExtensions(mediaType.getExtensions())
        val examples: Overlay<MutableMap<String, Example>> = validateMapField<Example>(
            MediaTypeImpl.F_examples, false, false, Example::class.java,
            ExampleValidator()
        )
        val example: Overlay<Any> = validateField<Any>(MediaTypeImpl.F_example, false, Any::class.java, null)
        checkExampleExclusion(examples, example)
    }

    fun checkEncodingPropsAreProps(mediaType: MediaType, results: ValidationResults) {
        // TODO Q: do allOf, anyOf, oneOf schemas participate? what about
        // additionalProperties?
        val schema = mediaType.getSchema(false)
        if (schema != null && Overlay.of(schema).isElaborated) {
            val propNames: Set<String> = schema.getProperties().keys
            val encProps: Map<String, EncodingProperty> = mediaType.getEncodingProperties()
            for (encodingPropertyName in encProps.keys) {
                if (!propNames.contains(encodingPropertyName)) {
                    results.addError(
                        msg(OpenApi3Messages.EncPropNotSchemaProp, encodingPropertyName),
                        Overlay.of<EncodingProperty>(encProps, encodingPropertyName)!!
                    )
                }
            }
        }
    }

    fun checkExampleExclusion(examples: Overlay<MutableMap<String, Example>>?, example: Overlay<Any>?) {
        val examplesPresent = examples != null && examples.isPresent && Overlay.getMapOverlay(examples)!!.size() > 0
        val examplePresent = example != null && example.isPresent
        if (examplesPresent && examplePresent) {
            results.addError("ExmplExclusion|The 'example' and 'exmaples' properties may not both appear", value)
        }
    }
}