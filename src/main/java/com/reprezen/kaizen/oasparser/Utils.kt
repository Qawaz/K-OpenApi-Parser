package com.reprezen.kaizen.oasparser

import com.reprezen.jsonoverlay.MapOverlay
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.model3.Path
import com.reprezen.kaizen.oasparser.`val`.ValidationContext
import com.reprezen.kaizen.oasparser.`val`.ValidationResults
import com.reprezen.kaizen.oasparser.val3.OpenApi3Validator

fun Overlay<Path>.getPathString(): String? {
    return if (parent is MapOverlay<*>) pathInParent else null
}

fun Path.getPathString(): String? {
    val overlay: Overlay<Path> = Overlay.of(this)
    return overlay.getPathString()
}

fun OpenApi3.validate(): ValidationResults {
    ValidationContext.open().use { context ->
        val validationResults = ValidationContext.getValidationResults()
        OpenApi3Validator().validate(Overlay.of(this))
        return validationResults
    }
}

fun ValidationResults.isValid(): Boolean {
    return this.severity.lt(ValidationResults.Severity.ERROR)
}

fun OpenApi3.getValidationItems(): Collection<ValidationResults.ValidationItem?>? {
    TODO()
//    return validate()!!.getItems()
}
