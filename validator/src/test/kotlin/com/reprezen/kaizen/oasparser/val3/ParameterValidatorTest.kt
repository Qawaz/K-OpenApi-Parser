package com.reprezen.kaizen.oasparser.val3

import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.getValidationItems
import com.reprezen.kaizen.oasparser.isValid
import com.reprezen.kaizen.oasparser.validate
import com.reprezen.kaizen.oasparser.validate.msg.Messages
import org.junit.Assert
import org.junit.Test

class ParameterValidatorTest {

    val isJson : Boolean get() = true

    private fun path(fileName : String) = "/models/" + (if (isJson) "json" else "yaml") + "/params/" + fileName + (if(isJson) ".json" else ".yaml")
    private val hybridParamRes : String get() = path("hybridParam")
    private val invalidParamRes : String get() = path("invalidParam")
    private val multiHybridParamRes : String get() = path("multiHybridParam")
    private val pathParamNotRequiredRes : String get() = path("pathParamNotRequired")
    private val validParamRes : String get() = path("validParam")

    @Test
    @Throws(Exception::class)
    fun shouldFail_OnPathParamNotRequired() {
        val model = OpenApiParser().parse(javaClass.getResource(pathParamNotRequiredRes)).also { it.validate() }
        Assert.assertEquals(1, model.getValidationItems().size.toLong())
        Assert.assertEquals(
            Messages.msg(OpenApi3Messages.PathParamReq, "id"),
            model.getValidationItems().stream().findFirst().get().msg
        )
        Assert.assertFalse(model.isValid())
    }

    @Test
    @Throws(Exception::class)
    fun shouldFail_OnInvalidParam() {
        val model = OpenApiParser().parse(javaClass.getResource(invalidParamRes)).also { it.validate() }
        Assert.assertEquals(1, model.getValidationItems().size)
        Assert.assertEquals(
            Messages.msg(OpenApi3Messages.MissingPathTplt, "id", "/"),
            model.getValidationItems().stream().findFirst().get().msg
        )
        Assert.assertFalse(model.isValid())
    }

    @Test
    @Throws(Exception::class)
    fun shouldPass_OnValidParam() {
        val model = OpenApiParser().parse(javaClass.getResource(validParamRes)).also { it.validate() }
        Assert.assertEquals(0, model.getValidationItems().size.toLong())
        Assert.assertTrue(model.isValid())
    }

    @Test
    @Throws(Exception::class)
    fun shouldAllow_HybridPathParam() {
        val model = OpenApiParser().parse(javaClass.getResource(hybridParamRes)).also { it.validate() }
        Assert.assertEquals(0, model.getValidationItems().size.toLong())
        Assert.assertTrue(model.isValid())
    }

    @Test
    @Throws(Exception::class)
    fun shouldAllow_MultiHybridPathParam() {
        val model = OpenApiParser().parse(javaClass.getResource(multiHybridParamRes)).also { it.validate() }
        Assert.assertEquals(0, model.getValidationItems().size.toLong())
        Assert.assertTrue(model.isValid())
    }
}