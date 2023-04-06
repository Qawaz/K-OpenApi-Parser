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
        val model: OpenApi3 = value.getOverlay() as OpenApi3
        validateStringField(OpenApi3Impl.F_openApi, true, "3\\.\\d+(\\.\\d+.*)?")
        validateField<Info>(OpenApi3Impl.F_info, true, Info::class.java, InfoValidator())
        validateListField<Server>(OpenApi3Impl.F_servers, false, false, Server::class.java, ServerValidator())
        validateMapField<Path>(OpenApi3Impl.F_paths, true, false, Path::class.java, PathValidator())
        validateExtensions(model.getPathsExtensions())
        validateMapField<Schema>(OpenApi3Impl.F_schemas, false, false, Schema::class.java, SchemaValidator())
        validateMapField<Response>(OpenApi3Impl.F_responses, false, false, Response::class.java, ResponseValidator())
        validateMapField<Parameter>(
            OpenApi3Impl.F_parameters,
            false,
            false,
            Parameter::class.java,
            ParameterValidator()
        )
        validateMapField<Example>(OpenApi3Impl.F_examples, false, false, Example::class.java, ExampleValidator())
        validateMapField<RequestBody>(
            OpenApi3Impl.F_requestBodies,
            false,
            false,
            RequestBody::class.java,
            RequestBodyValidator()
        )
        validateMapField<Header>(OpenApi3Impl.F_headers, false, false, Header::class.java, HeaderValidator())
        validateMapField<SecurityScheme>(
            OpenApi3Impl.F_securitySchemes,
            false,
            false,
            SecurityScheme::class.java,
            SecuritySchemeValidator()
        )
        validateMapField<Link>(OpenApi3Impl.F_links, false, false, Link::class.java, LinkValidator())
        validateMapField<Callback>(OpenApi3Impl.F_callbacks, false, false, Callback::class.java, CallbackValidator())
        validateExtensions(model.getComponentsExtensions())
        validateListField<SecurityRequirement>(
            OpenApi3Impl.F_securityRequirements, false, false, SecurityRequirement::class.java,
            SecurityRequirementValidator()
        )
        validateListField<Tag>(OpenApi3Impl.F_tags, false, false, Tag::class.java, TagValidator())
        validateField<ExternalDocs>(
            OpenApi3Impl.F_externalDocs,
            false,
            ExternalDocs::class.java,
            ExternalDocsValidator()
        )
        validateExtensions(model.getExtensions())
    }
}