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

import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.kaizen.oasparser.ovl3.OpenApi3Impl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase

class OpenApi3Validator : ObjectValidatorBase<OpenApi3>() {
    override fun runObjectValidations() {
        val model: OpenApi3 = value.overlay as OpenApi3
        validateStringField(OpenApi3Impl.F_openApi, true, "3\\.\\d+(\\.\\d+.*)?")
        validateField<Info>(OpenApi3Impl.F_info, true, InfoValidator())
        validateListField<Server>(
            OpenApi3Impl.F_servers,
            nonEmpty = false,
            unique = false,
            itemValidator = ServerValidator()
        )
        validateMapField<Path>(
            OpenApi3Impl.F_paths, true,
            unique = false,
            valueValidator = PathValidator()
        )
        validateExtensions(model.getPathsExtensions())
        validateMapField<Schema>(
            OpenApi3Impl.F_schemas,
            nonEmpty = false,
            unique = false,
            valueValidator = SchemaValidator()
        )
        validateMapField<Response>(
            OpenApi3Impl.F_responses,
            nonEmpty = false,
            unique = false,
            valueValidator = ResponseValidator()
        )
        validateMapField<Parameter>(
            OpenApi3Impl.F_parameters,
            false,
            false,
            ParameterValidator()
        )
        validateMapField<Example>(
            OpenApi3Impl.F_examples,
            nonEmpty = false,
            unique = false,
            valueValidator = ExampleValidator()
        )
        validateMapField<RequestBody>(
            OpenApi3Impl.F_requestBodies,
            nonEmpty = false,
            unique = false,
            valueValidator = RequestBodyValidator()
        )
        validateMapField<Header>(
            OpenApi3Impl.F_headers,
            nonEmpty = false,
            unique = false,
            valueValidator = HeaderValidator()
        )
        validateMapField<SecurityScheme>(
            OpenApi3Impl.F_securitySchemes,
            nonEmpty = false,
            unique = false,
            valueValidator = SecuritySchemeValidator()
        )
        validateMapField<Link>(
            OpenApi3Impl.F_links,
            nonEmpty = false,
            unique = false,
            valueValidator = LinkValidator()
        )
        validateMapField<Callback>(
            OpenApi3Impl.F_callbacks,
            nonEmpty = false,
            unique = false,
            valueValidator = CallbackValidator()
        )
        validateExtensions(model.getComponentsExtensions())
        validateListField<SecurityRequirement>(
            OpenApi3Impl.F_securityRequirements,
            nonEmpty = false,
            unique = false,
            itemValidator = SecurityRequirementValidator()
        )
        validateListField<Tag>(
            OpenApi3Impl.F_tags,
            nonEmpty = false,
            unique = false,
            itemValidator = TagValidator()
        )
        validateField<ExternalDocs>(
            OpenApi3Impl.F_externalDocs,
            false,
            ExternalDocsValidator()
        )
        validateExtensions(model.getExtensions())
    }
}