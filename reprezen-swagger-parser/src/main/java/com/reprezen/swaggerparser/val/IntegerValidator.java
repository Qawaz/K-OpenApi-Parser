/*******************************************************************************
 *  Copyright (c) $(date) ModelSolv, Inc. and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *     ModelSolv, Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package com.reprezen.swaggerparser.val;

import static com.reprezen.swaggerparser.val.Messages.m;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import com.reprezen.swaggerparser.jsonoverlay.std.IntegerOverlay;

public class IntegerValidator extends Validator<IntegerOverlay> {

    public static IntegerValidator validator = new IntegerValidator();

    @Override
    public void validate(IntegerOverlay overlay, ValidationResults results) {
        JsonNode json = overlay.getJson();
        if (!json.isMissingNode() && !json.isInt()) {
            results.addError(m.msg("NotInteger|Value must be an integer", json.getNodeType()));
        }
    }

    @Override
    public Map<? extends Validator<?>, String> getBeforeValidators() {
        return ImmutableMap.of(OverlayValidator.validator, NO_CRUMB);
    }
}
