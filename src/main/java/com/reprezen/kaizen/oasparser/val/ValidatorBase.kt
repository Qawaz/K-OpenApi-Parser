package com.reprezen.kaizen.oasparser.`val`

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.*
import com.reprezen.jsonoverlay.*
import com.reprezen.kaizen.oasparser.`val`.msg.Messages
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import java.net.URLStreamHandler
import java.util.*
import java.util.function.Consumer
import java.util.function.Function
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException
import java.util.stream.Collectors
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress

abstract class ValidatorBase<V> : Validator<V> {

    protected lateinit var value: Overlay<V>

    protected lateinit var results: ValidationResults

    override fun validate(value: Overlay<V>) {
        this.value = value
        results = ValidationContext.getValidationResults()
        checkJsonType(value, getAllowedJsonTypes(value), results)
        runValidations()
    }

    abstract fun runValidations()
    fun validateBooleanField(name: String?, required: Boolean): Overlay<Boolean>? {
        return validateField(name, required, Boolean::class.java, null)
    }

    fun validateStringField(name: String?, required: Boolean): Overlay<String>? {
        return validateStringField(name, required, null as Pattern?)
    }

    fun validateStringField(name: String?, required: Boolean, pattern: String?): Overlay<String>? {
        return validateStringField(name, required, Pattern.compile(pattern))
    }

    @SafeVarargs
    fun validateStringField(
        name: String?, required: Boolean, pattern: Pattern?,
        vararg otherChecks: Consumer<Overlay<String>>
    ): Overlay<String> {
        val field = validateField(name, required, String::class.java, null)
        checkMissing(field, required)
        if (field != null && field.isPresent) {
            pattern?.let { checkPattern(field, it) }
            for (otherCheck in otherChecks) {
                otherCheck.accept(field)
            }
        }
        return field
    }

    fun checkPattern(field: Overlay<String>, pattern: Pattern) {
        if (!pattern.matcher(field.get()).matches()) {
            results!!.addError(Messages.msg(BaseValidationMessages.PatternMatchFail, field.get()!!, pattern), field)
        }
    }

    fun validatePatternField(name: String?, required: Boolean): Overlay<String>? {
        return validateStringField(name, required, null, Consumer { field: Overlay<String> -> checkRegex(field) })
    }

    private fun checkRegex(field: Overlay<String>) {
        val regex = field.get()!!
        try {
            Pattern.compile(regex)
        } catch (e: PatternSyntaxException) {
            results!!.addWarning(Messages.msg(BaseValidationMessages.BadPattern, regex), field)
        }
    }

    fun validateUrlField(
        name: String?, required: Boolean, allowRelative: Boolean, allowVars: Boolean,
        pattern: String?
    ): Overlay<String>? {
        return validateUrlField(name, required, allowRelative, allowVars, Pattern.compile(pattern))
    }

    @JvmOverloads
    fun validateUrlField(
        name: String?, required: Boolean, allowRelative: Boolean, allowVars: Boolean,
        pattern: Pattern? = null as Pattern?
    ): Overlay<String> {
        return validateStringField(
            name,
            required,
            pattern,
            Consumer { field: Overlay<String> -> checkUrl(field, allowRelative, allowVars) })
    }

    private fun checkUrl(overlay: Overlay<String>, allowRelative: Boolean, allowVars: Boolean) {
        // TODO Q: Any help from spec in being able to validate URLs with vars? E.g is
        // our treatment here valid? We assume vars can only appear where simple text
        // can appear, so handling vars means relacing {.*} with "1" and testing for URL
        // validity. We use a digit instead of a letter because it covers vars in the
        // port, and elsewhere digits are always allowed where letters are.
        val origUrl = overlay.get()!!
        var url = origUrl
        var fake = false
        if (allowVars) {
            url = url.replace("\\{[^}]+\\}".toRegex(), "1")
            if (url.startsWith("1:")) {
                // "1" is not a valid scheme name, so we need to replace it with special scheme,
                // for which we provide a do-nothing protocol handler implementation
                url = FAKE_SCHEME + url.substring(1)
                fake = true
            }
        }
        try {
            URL(null, url, if (fake) fakeHandler else null)
        } catch (e: MalformedURLException) {
            try {
                val context = URL(null, FAKE_SCHEME + ":/", fakeHandler)
                URL(context, url)
                if (!allowRelative) {
                    results!!.addError(Messages.msg(BaseValidationMessages.NoRelUrl, origUrl, e.toString()), overlay)
                }
            } catch (e1: MalformedURLException) {
                results!!.addError(Messages.msg(BaseValidationMessages.BadUrl, origUrl, e.toString()), overlay)
            }
        }
    }

    fun validateUrlField(name: String?, required: Boolean, pattern: String?): Overlay<String>? {
        return validateEmailField(name, required, Pattern.compile(pattern))
    }

    @JvmOverloads
    fun validateEmailField(name: String?, required: Boolean, pattern: Pattern? = null as Pattern?): Overlay<String>? {
        return validateStringField(
            name,
            required,
            pattern,
            Consumer { overlay: Overlay<String> -> checkEmail(overlay) })
    }

    private fun checkEmail(overlay: Overlay<String>) {
        val email = overlay.get()!!
        try {
            val addr = InternetAddress()
            addr.address = email
            addr.validate()
        } catch (e: AddressException) {
            results!!.addError(Messages.msg(BaseValidationMessages.BadEmail, email, e.toString()), overlay)
        }
    }

    fun validatePositiveField(name: String?, required: Boolean): Overlay<Number>? {
        return validateNumericField(name, required, { x: Number -> NumericUtils.isPositive(x) }, "be positive")
    }

    fun validateNonNegativeField(name: String?, required: Boolean): Overlay<Number>? {
        return validateNumericField(name, required, { x: Number -> NumericUtils.isNonNegative(x) }, "not be positive")
    }

    fun validateNumericField(
        name: String?,
        required: Boolean,
        test: Function<Number, Boolean>?,
        desc: String?
    ): Overlay<Number>? {
        val field = validateField(name, required, Number::class.java, null)
        checkMissing(field, required)
        if (field != null && field.isPresent && test != null) {
            val n = field.get()!!
            if (!test.apply(n)) {
                results!!.addError(Messages.msg(BaseValidationMessages.NumberConstraint, desc!!, n), field)
            }
        }
        return field
    }

    @SafeVarargs
    fun <F> validateField(
        name: String?, required: Boolean, fieldClass: Class<F>?,
        validator: Validator<F>?, vararg otherChecks: Consumer<Overlay<F>?>
    ): Overlay<F> {
        val propValue: PropertiesOverlay<V> = value!!.get() as PropertiesOverlay<V>
        val field = Overlay.of(propValue, name, fieldClass)
        checkJsonType(field, getAllowedJsonTypes(field), results)
        checkMissing(field, required)
        if (field != null && field.isPresent && validator != null) {
            validator.validate(field)
            for (otherCheck in otherChecks) {
                otherCheck.accept(field)
            }
        }
        return field
    }

    fun <X> validateListField(
        name: String?, nonEmpty: Boolean, unique: Boolean, itemClass: Class<X>?,
        itemValidator: Validator<X>?
    ): Overlay<List<X>>? {
        val list = Overlay.of(
            value!!.get() as PropertiesOverlay<V>, name,
            MutableList::class.java
        ) as Any as Overlay<List<X>>
        validateList(list, nonEmpty, unique, itemValidator)
        return list
    }

    private fun <X> validateList(
        list: Overlay<List<X>>,
        nonEmpty: Boolean,
        unique: Boolean,
        itemValidator: Validator<X>?
    ) {
        ListValidator(itemValidator).validate(list)
        checkListNotEmpty(list, nonEmpty)
        checkListUnique(list, unique)
    }

    private fun <X> checkListNotEmpty(list: Overlay<List<X>>, nonEmpty: Boolean) {
        if (nonEmpty) {
            val listOverlay= Overlay.getListOverlay(list)
            if (list != null && !list.isPresent) {
                if (nonEmpty && listOverlay!!.size() == 0) {
                    results!!.addError(Messages.msg(BaseValidationMessages.EmptyList), list)
                }
            }
        }
    }

    private fun <X> checkListUnique(list: Overlay<List<X>>, unique: Boolean) {
        if (unique) {
            val listOverlay: ListOverlay<X> = Overlay.getListOverlay(list)!!
            val seen: MutableSet<X> = HashSet()
            for (i in 0 until listOverlay.size()) {
                val item: X = listOverlay.get(i)
                if (seen.contains(item)) {
                    results!!.addError(
                        Messages.msg(BaseValidationMessages.DuplicateValue, item!!, i),
                        Overlay.of<X>(listOverlay, i)
                    )
                } else {
                    seen.add(item)
                }
            }
        }
    }

    fun <X> validateMapField(
        name: String?, nonEmpty: Boolean, unique: Boolean,
        valueClass: Class<X>?, valueValidator: Validator<X>?
    ): Overlay<Map<String, X>> {
        val map = Overlay.of(
            value!!.get() as PropertiesOverlay<V>,
            name, MutableMap::class.java
        ) as Any as Overlay<Map<String, X>>
        validateMap(map, nonEmpty, unique, valueValidator)
        return map
    }

    private fun <X> validateMap(
        map: Overlay<Map<String, X>>,
        nonEmpty: Boolean, unique: Boolean,
        valueValidator: Validator<X>?
    ) {
        MapValidator(valueValidator).validate(map!!)
        checkMapNotEmpty(map, nonEmpty)
        checkMapUnique(map, unique)
    }

    private fun <X> checkMapNotEmpty(list: Overlay<Map<String, X>>, nonEmpty: Boolean) {
        if (nonEmpty) {
            val mapOverlay: MapOverlay<X> = Overlay.getMapOverlay(list)!!
            if (list != null && !list.isPresent) {
                if (nonEmpty && mapOverlay.size() == 0) {
                    results!!.addError(Messages.msg(BaseValidationMessages.EmptyList), list)
                }
            }
        }
    }

    private fun <X> checkMapUnique(map: Overlay<Map<String, X>>, unique: Boolean) {
        if (unique) {
            val mapOverlay: MapOverlay<X> = Overlay.getMapOverlay(map)!!
            val seen: MutableSet<X> = HashSet()
            for (key in mapOverlay.keySet()) {
                val value: X = mapOverlay.get(key)
                if (seen.contains(value)) {
                    results!!.addError(
                        Messages.msg(BaseValidationMessages.DuplicateValue, value!!, key),
                        Overlay.of<X>(mapOverlay, key)
                    )
                } else {
                    seen.add(value)
                }
            }
        }
    }

    fun checkMissing(field: Overlay<*>?, required: Boolean) {
        if (required && (field == null || !field.isPresent)) {
            results!!.addError(Messages.msg(BaseValidationMessages.MissingField, field!!.pathInParent!!), value!!)
        }
    }

    @JvmOverloads
    fun validateExtensions(extensions: Map<String, Any>, crumb: String? = null): Overlay<Map<String, Any>> {
        val mapOverlay = Overlay.of(extensions)!!
        validateMap(mapOverlay, false, false, null)
        return mapOverlay
    }

    fun validateFormatField(name: String?, required: Boolean, type: String?): Overlay<String>? {
        val field = validateStringField(name, required)
        if (field != null && field.isPresent) {
            var normalType: String? = null
            when (field.get()) {
                "int32", "int64" -> normalType = "integer"
                "float", "double" -> normalType = "number"
                "byte", "binary", "date", "date-time", "password" -> normalType = "string"
            }
            if (normalType != null) {
                if (type == null || type != normalType) {
                    results!!.addWarning(
                        Messages.msg(BaseValidationMessages.WrongTypeFormat, field, type!!, normalType),
                        field
                    )
                }
            }
        }
        return field
    }

    fun checkDefault(overlay: Overlay<*>?, type: String?) {
        if (overlay != null && overlay.isPresent && type != null) {
            val defaultValue = overlay.get()
            var ok = false
            when (type) {
                "string" -> ok = defaultValue is String
                "number" -> ok = NumericUtils.isNumeric(defaultValue as Number)
                "integer" -> ok = NumericUtils.isIntegral(defaultValue as Number)
                "boolean" -> ok = defaultValue is Boolean
                "object" -> ok = defaultValue is Map<*, *>
                "array" -> ok = defaultValue is List<*>
            }
            if (!ok) {
                results!!.addError(Messages.msg(BaseValidationMessages.WrongTypeValue, type, defaultValue!!), overlay)
            }
        }
    }

    fun checkJsonType(
        value: Overlay<*>?, allowedJsonTypes: Collection<Class<out JsonNode?>>,
        results: ValidationResults?
    ) {
        val json = value!!.parsedJson
        if (json != null && !json.isMissingNode) {
            for (type in allowedJsonTypes) {
                if (type.isAssignableFrom(json.javaClass)) {
                    return
                }
            }
            val allowed = allowedJsonTypes.stream().map { type: Class<out JsonNode?> -> getJsonValueType(type) }
                .collect(Collectors.joining(", "))
            results!!.addError(
                Messages.msg(
                    BaseValidationMessages.WrongTypeJson,
                    getJsonValueType(json.javaClass),
                    allowed
                ), value
            )
        }
    }

    private fun getJsonValueType(node: Class<out JsonNode?>): String {
        val type = node.simpleName
        return if (type.endsWith("Node")) type.substring(0, type.length - 4) else type
    }

    protected open fun getAllowedJsonTypes(value: Overlay<*>?): Collection<Class<out JsonNode>> {
        if (allowedJsonTypes == null) {
            createAllowedJsonTypes()
        }
        val overlay: JsonOverlay<*> = value!!.overlay
        return allowedJsonTypes!![if (overlay is PropertiesOverlay<*>) PropertiesOverlay::class.java else overlay.javaClass]!!
    }

    companion object {
        private const val FAKE_SCHEME = "oasparser.fake.scheme"
        private val fakeHandler: URLStreamHandler = object : URLStreamHandler() {
            @Throws(IOException::class)
            override fun openConnection(u: URL): URLConnection? {
                return null
            }
        }
        protected var allowedJsonTypes: Map<Class<*>, List<Class<out JsonNode>>>? = null
        private fun createAllowedJsonTypes() {
            val types: MutableMap<Class<*>, List<Class<out JsonNode>>> = HashMap()
            types[StringOverlay::class.java] = listOf<Class<out JsonNode>>(
                TextNode::class.java
            )
            types[BooleanOverlay::class.java] = listOf<Class<out JsonNode>>(
                BooleanNode::class.java
            )
            types[IntegerOverlay::class.java] = listOf<Class<out JsonNode>>(
                IntNode::class.java, ShortNode::class.java, BigIntegerNode::class.java
            )
            types[NumberOverlay::class.java] = listOf<Class<out JsonNode>>(
                NumericNode::class.java
            )
            types[PrimitiveOverlay::class.java] = listOf<Class<out JsonNode>>(
                TextNode::class.java, NumericNode::class.java, BooleanNode::class.java
            )
            types[ObjectOverlay::class.java] = listOf<Class<out JsonNode>>(
                JsonNode::class.java
            )
            types[MapOverlay::class.java] = listOf<Class<out JsonNode>>(
                ObjectNode::class.java
            )
            types[ListOverlay::class.java] = listOf<Class<out JsonNode>>(
                ArrayNode::class.java
            )
            types[PropertiesOverlay::class.java] = listOf<Class<out JsonNode>>(
                ObjectNode::class.java
            )
            allowedJsonTypes = types
        }
    }
}