package com.reprezen.kaizen.oasparser.val3

import com.reprezen.kaizen.oasparser.OpenApi3Parser
import com.reprezen.kaizen.oasparser.`val`.msg.Messages
import org.junit.Assert
import org.junit.Test

class ParameterValidatorTest {
    @Test
    @Throws(Exception::class)
    fun shouldFail_OnPathParamNotRequired() {
        val model = OpenApi3Parser().parse(
            javaClass.getResource("/models/params/pathParamNotRequired.yaml"),
            true
        )
        Assert.assertEquals(1, model.validationItems.size.toLong())
        Assert.assertEquals(
            Messages.msg(OpenApi3Messages.PathParamReq, "id"),
            model.validationItems.stream().findFirst().get().msg
        )
        Assert.assertFalse(model.isValid)
    }

    @Test
    @Throws(Exception::class)
    fun shouldFail_OnInvalidParam() {
        val model = OpenApi3Parser().parse(javaClass.getResource("/models/params/invalidParam.yaml"), true)
        Assert.assertEquals(1, model.validationItems.size.toLong())
        Assert.assertEquals(
            Messages.msg(OpenApi3Messages.MissingPathTplt, "id", "/"),
            model.validationItems.stream().findFirst().get().msg
        )
        Assert.assertFalse(model.isValid)
    }

    @Test
    @Throws(Exception::class)
    fun shouldPass_OnValidParam() {
        val model = OpenApi3Parser().parse(javaClass.getResource("/models/params/validParam.yaml"), true)
        Assert.assertEquals(0, model.validationItems.size.toLong())
        Assert.assertTrue(model.isValid)
    }

    @Test
    @Throws(Exception::class)
    fun shouldAllow_HybridPathParam() {
        val model = OpenApi3Parser().parse(javaClass.getResource("/models/params/hybridParam.yaml"), true)
        Assert.assertEquals(0, model.validationItems.size.toLong())
        Assert.assertTrue(model.isValid)
    }

    @Test
    @Throws(Exception::class)
    fun shouldAllow_MultiHybridPathParam() {
        val model = OpenApi3Parser().parse(
            javaClass.getResource("/models/params/multiHybridParam.yaml"),
            true
        )
        Assert.assertEquals(0, model.validationItems.size.toLong())
        Assert.assertTrue(model.isValid)
    }
}