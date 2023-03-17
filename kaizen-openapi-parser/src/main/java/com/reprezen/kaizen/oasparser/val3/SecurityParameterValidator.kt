package com.reprezen.kaizen.oasparser.val3

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.model3.SecurityParameter
import com.reprezen.kaizen.oasparser.ovl3.SecurityParameterImpl
import com.reprezen.kaizen.oasparser.`val`.ObjectValidatorBase
import java.util.*

class SecurityParameterValidator : ObjectValidatorBase<SecurityParameter>() {
    override fun runObjectValidations() {
        validateListField<String>(SecurityParameterImpl.F_parameters, false, false, String::class.java, null)
    }

    protected override fun getAllowedJsonTypes(value: Overlay<*>?): Collection<Class<out JsonNode?>> {
        // SecurityParameter is the odd case of a ParametersOverlay that is not formed
        // from an object. It's the value type of the security requirement map, and that
        // value comes in the form of a JSON array. The SecurityParameter type exposes
        // that type as a list valued property named "parameters". There's no object
        // containing that property.Hence we need to specify the expected JsonNode type
        return Arrays.asList<Class<out JsonNode?>>(ArrayNode::class.java)
    }
}