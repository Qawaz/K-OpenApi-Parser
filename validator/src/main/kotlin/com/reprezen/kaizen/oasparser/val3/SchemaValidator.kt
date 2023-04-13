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
import com.reprezen.kaizen.oasparser.model3.Discriminator
import com.reprezen.kaizen.oasparser.model3.ExternalDocs
import com.reprezen.kaizen.oasparser.model3.Schema
import com.reprezen.kaizen.oasparser.model3.Xml
import com.reprezen.kaizen.oasparser.ovl3.SchemaImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase
import com.reprezen.kaizen.oasparser.validate.msg.Messages.Companion.msg
import java.util.function.Consumer

class SchemaValidator : ObjectValidatorBase<Schema>() {
    override fun runObjectValidations() {
        val schema = value.overlay as Schema
        validateStringField(SchemaImpl.F_title, false)
        validateStringField(SchemaImpl.F_description, false)
        validateNumericField(SchemaImpl.F_maximum, false, null, null)
        validateBooleanField(SchemaImpl.F_exclusiveMaximum, false)
        validateNumericField(SchemaImpl.F_minimum, false, null, null)
        validateBooleanField(SchemaImpl.F_exclusiveMinimum, false)
        validateBooleanField(SchemaImpl.F_uniqueItems, false)
        validateBooleanField(SchemaImpl.F_nullable, false)
        validateField<Any>(SchemaImpl.F_example, false, Any::class.java, null)
        validateBooleanField(SchemaImpl.F_deprecated, false)
        validatePositiveField(SchemaImpl.F_multipleOf, false)
        validateNonNegativeField(SchemaImpl.F_maxLength, false)
        validateNonNegativeField(SchemaImpl.F_minLength, false)
        validatePatternField(SchemaImpl.F_pattern, false)
        validateNonNegativeField(SchemaImpl.F_maxItems, false)
        validateNonNegativeField(SchemaImpl.F_minItems, false)
        validateNonNegativeField(SchemaImpl.F_maxProperties, false)
        validateNonNegativeField(SchemaImpl.F_minProperties, false)
        validateListField<String>(SchemaImpl.F_requiredFields, false, true, null)
        validateListField<Any>(SchemaImpl.F_enums, false, true, null)
        validateStringField(SchemaImpl.F_type, false, "boolean|object|array|number|integer|string")
        run {
            val schemaValidator = SchemaValidator()
            validateListField<Schema>(SchemaImpl.F_allOfSchemas, false, false, schemaValidator)
            validateListField<Schema>(SchemaImpl.F_oneOfSchemas, false, false, schemaValidator)
            validateListField<Schema>(SchemaImpl.F_anyOfSchemas, false, false, schemaValidator)
            validateField<Schema>(SchemaImpl.F_notSchema, false, Schema::class.java, schemaValidator)
            validateField<Schema>(SchemaImpl.F_itemsSchema, false, Schema::class.java, schemaValidator)
            validateMapField<Schema>(SchemaImpl.F_properties, false, false, Schema::class.java, schemaValidator)
        }
        validateFormatField(SchemaImpl.F_format, false, schema.getType())
        validateField<Any>(
            SchemaImpl.F_defaultValue,
            false,
            Any::class.java,
            null,
            Consumer { field: Overlay<Any>? -> checkDefault(field, schema.getType()) })
        validateField<Discriminator>(
            SchemaImpl.F_discriminator,
            false,
            Discriminator::class.java,
            DiscriminatorValidator()
        )
        checkReadWrite(schema)
        validateField<Xml>(SchemaImpl.F_xml, false, Xml::class.java, XmlValidator())
        validateField<ExternalDocs>(SchemaImpl.F_externalDocs, false, ExternalDocs::class.java, ExternalDocsValidator())
        validateExtensions(schema.getExtensions())
    }

    private fun checkReadWrite(schema: Schema) {
        if (schema.isReadOnly() && schema.isWriteOnly()) {
            results.addError(msg(OpenApi3Messages.ROnlyAndWOnly), value)
        }
    }
}