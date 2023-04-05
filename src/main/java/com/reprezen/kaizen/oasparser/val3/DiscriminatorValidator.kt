package com.reprezen.kaizen.oasparser.val3

import com.reprezen.kaizen.oasparser.model3.Discriminator
import com.reprezen.kaizen.oasparser.ovl3.DiscriminatorImpl
import com.reprezen.kaizen.oasparser.validate.ValidatorBase

class DiscriminatorValidator : ValidatorBase<Discriminator>() {
    override fun runValidations() {
        validateStringField(DiscriminatorImpl.F_propertyName, true)
        validateMapField<String>("mappings", false, false, String::class.java, null)
    }
}