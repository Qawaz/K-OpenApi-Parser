package com.reprezen.kaizen.oasparser

import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.val3.OpenApi3Validator
import com.reprezen.kaizen.oasparser.validate.ValidationContext
import com.reprezen.kaizen.oasparser.validate.ValidationResults

fun OpenApi3.validate(): ValidationResults {
    ValidationContext.open().use { context ->
        OpenApi3Validator().validate(Overlay.of(this))
        return ValidationContext.getValidationResults()
    }
}

fun OpenApi3.isValid(): Boolean {
    val results = ValidationContext.getNullableResults() ?: validate()
    return results.severity.lt(ValidationResults.Severity.ERROR)
}

fun OpenApi3.getValidationResults(): ValidationResults {
    return ValidationContext.getNullableResults() ?: validate()
}

fun OpenApi3.getValidationItems(): Collection<ValidationResults.ValidationItem> {
    return getValidationResults().getItems()
}