package com.reprezen.kaizen.oasparser.val3

import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.model3.SecurityParameter
import com.reprezen.kaizen.oasparser.ovl3.SecurityParameterImpl
import com.reprezen.kaizen.oasparser.validate.ObjectValidatorBase
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

class SecurityParameterValidator : ObjectValidatorBase<SecurityParameter>() {
    override fun runObjectValidations() {
        validateListField<String>(SecurityParameterImpl.F_parameters, false, false, null)
    }

    override fun getAllowedJsonTypes(value: Overlay<*>): (JsonElement)->Boolean {
        // SecurityParameter is the odd case of a ParametersOverlay that is not formed
        // from an object. It's the value type of the security requirement map, and that
        // value comes in the form of a JSON array. The SecurityParameter type exposes
        // that type as a list valued property named "parameters". There's no object
        // containing that property.Hence we need to specify the expected JsonNode type
        return { it is JsonArray }
//        return Arrays.asList<Class<out JsonNode?>>(ArrayNode::class.java)
    }
}