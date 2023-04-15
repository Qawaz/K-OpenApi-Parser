package com.reprezen.kaizen.oasparser.validate

import com.fasterxml.jackson.databind.node.*
import com.reprezen.jsonoverlay.*
import com.reprezen.kaizen.oasparser.validate.msg.Messages
import kotlinx.serialization.json.*
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import java.net.URLStreamHandler
import java.util.*
import java.util.function.Consumer
import java.util.function.Function
import java.util.regex.Pattern

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

    fun validateBooleanField(name: String, required: Boolean): Overlay<Boolean> {
        return validateField(name, required, null)
    }

    fun validateStringField(name: String, required: Boolean): Overlay<String> {
        return validateStringField(name, required, null as Pattern?)
    }

    fun validateStringField(name: String, required: Boolean, pattern: String?): Overlay<String> {
        return validateStringField(name, required, Pattern.compile(pattern))
    }

    @SafeVarargs
    fun validateStringField(
        name: String, required: Boolean, pattern: Pattern?,
        vararg otherChecks: Consumer<Overlay<String>>
    ): Overlay<String> {
        val field = validateField<String>(name, required, null)
        checkMissing(field, required)
        if (field.isPresent) {
            pattern?.let { checkPattern(field, it) }
            for (otherCheck in otherChecks) {
                otherCheck.accept(field)
            }
        }
        return field
    }

    fun checkPattern(field: Overlay<String>, pattern: Pattern) {
        if (!pattern.matcher(field.get()).matches()) {
            results.addError(Messages.msg(BaseValidationMessages.PatternMatchFail, field.get()!!, pattern), field)
        }
    }

    fun validatePatternField(name: String, required: Boolean): Overlay<String> {
        return validateStringField(name, required, null, Consumer { field: Overlay<String> -> checkRegex(field) })
    }

    private fun checkRegex(field: Overlay<String>) {
        val regex = field.get()!!
        try {
            Regex(regex)
        } catch (e: Exception) {
            results.addWarning(Messages.msg(BaseValidationMessages.BadPattern, regex, (e.message ?: "")), field)
        }
    }

    fun validateUrlField(
        name: String, required: Boolean, allowRelative: Boolean, allowVars: Boolean,
        pattern: String?
    ): Overlay<String> {
        return validateUrlField(name, required, allowRelative, allowVars, Pattern.compile(pattern))
    }

    @JvmOverloads
    fun validateUrlField(
        name: String, required: Boolean, allowRelative: Boolean, allowVars: Boolean,
        pattern: Pattern? = null
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
                    results.addError(Messages.msg(BaseValidationMessages.NoRelUrl, origUrl, e.toString()), overlay)
                }
            } catch (e1: MalformedURLException) {
                results.addError(Messages.msg(BaseValidationMessages.BadUrl, origUrl, e.toString()), overlay)
            }
        }
    }

    fun validateUrlField(name: String, required: Boolean, pattern: String?): Overlay<String> {
        return validateEmailField(name, required, Pattern.compile(pattern))
    }

    @JvmOverloads
    fun validateEmailField(name: String, required: Boolean, pattern: Pattern? = null): Overlay<String> {
        return validateStringField(
            name,
            required,
            pattern,
            Consumer { overlay: Overlay<String> -> checkEmail(overlay) })
    }

    private fun isEmailValid(email: String): Boolean {
        return Regex(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matches(email)
    }

    private fun checkEmail(overlay: Overlay<String>) {
        val email = overlay.get()!!
        if (!isEmailValid(email)) {
            results.addError(Messages.msg(BaseValidationMessages.BadEmail, email, "invalid email detected"), overlay)
        }
    }

    fun validatePositiveField(name: String, required: Boolean): Overlay<Number> {
        return validateNumericField(name, required, { x: Number -> NumericUtils.isPositive(x) }, "be positive")
    }

    fun validateNonNegativeField(name: String, required: Boolean): Overlay<Number> {
        return validateNumericField(name, required, { x: Number -> NumericUtils.isNonNegative(x) }, "not be positive")
    }

    fun validateNumericField(
        name: String,
        required: Boolean,
        test: Function<Number, Boolean>?,
        desc: String?
    ): Overlay<Number> {
        val field = validateField<Number>(name, required, null)
        checkMissing(field, required)
        if (field.isPresent == true && test != null) {
            val n = field.get()!!
            if (!test.apply(n)) {
                results.addError(Messages.msg(BaseValidationMessages.NumberConstraint, desc!!, n), field)
            }
        }
        return field
    }

    @SafeVarargs
    fun <F> validateField(
        name: String, required: Boolean, validator: Validator<F>?,
        vararg otherChecks: Consumer<Overlay<F>?>
    ): Overlay<F> {
        val propValue: PropertiesOverlay<F> = value.get() as PropertiesOverlay<F>
        val field = Overlay.of(propValue, name)
        if (required && field == null) throw IllegalStateException("field $name is required $propValue")
        checkJsonType(field, getAllowedJsonTypes(field!!), results)
        checkMissing(field, required)
        if (field.isPresent && validator != null) {
            validator.validate(field)
            for (otherCheck in otherChecks) {
                otherCheck.accept(field)
            }
        }
        return field
    }

    fun <X> validateListField(
        name: String, nonEmpty: Boolean, unique: Boolean,
        itemValidator: Validator<X>?
    ): Overlay<List<X>> {
        val list = Overlay.of(value.get() as PropertiesOverlay<V>, name) as Overlay<List<X>>
        validateList(list, nonEmpty, unique, itemValidator)
        return list
    }

    private fun <X> validateList(
        list: Overlay<List<X>>,
        nonEmpty: Boolean,
        unique: Boolean,
        itemValidator: Validator<X>?
    ) {
        if (itemValidator != null) ListValidator(itemValidator).validate(list)
        checkListNotEmpty(list, nonEmpty)
        checkListUnique(list, unique, nonEmpty = nonEmpty)
    }

    private fun <X> checkListNotEmpty(list: Overlay<List<X>>, nonEmpty: Boolean) {
        if (nonEmpty) {
            val listOverlay = Overlay.getListOverlay(list)
            if (!list.isPresent) {
                if (nonEmpty && listOverlay!!.size() == 0) {
                    results.addError(Messages.msg(BaseValidationMessages.EmptyList), list)
                }
            }
        }
    }

    private fun <X> checkListUnique(list: Overlay<List<X>>, unique: Boolean, nonEmpty: Boolean) {
        if (unique) {
            val listOverlay = Overlay.getListOverlay(list)
            if (nonEmpty && listOverlay == null) {
                throw IllegalStateException("list overlay is null while required non empty")
            }
            val seen: MutableSet<X> = HashSet()
            for (i in 0 until (listOverlay?.size() ?: 0)) {
                val item: X = listOverlay?.get(i)!!
                if (seen.contains(item)) {
                    results.addError(
                        Messages.msg(BaseValidationMessages.DuplicateValue, item!!, i),
                        Overlay.of<X>(listOverlay, i)
                    )
                } else {
                    seen.add(item)
                }
            }
        }
    }

    private fun <X> validateMap(
        map: Overlay<MutableMap<String, X>>,
        nonEmpty: Boolean, unique: Boolean,
        valueValidator: Validator<X>?
    ) {
        MapValidator(valueValidator, isNonEmpty = nonEmpty, isUnique = unique).validate(map)
    }

    fun <X> validateMapField(
        name: String, nonEmpty: Boolean, unique: Boolean,
        valueValidator: Validator<X>?
    ): Overlay<MutableMap<String, X>> {
        val map = Overlay.of(
            value.get() as PropertiesOverlay<V>,
            name
        ) as Overlay<MutableMap<String, X>>
        validateMap(map, nonEmpty, unique, valueValidator)
        return map
    }

    fun checkMissing(field: Overlay<*>?, required: Boolean) {
        if (required && (field == null || !field.isPresent)) {
            results.addError(Messages.msg(BaseValidationMessages.MissingField, field!!.pathInParent!!), value)
        }
    }

    @JvmOverloads
    fun validateExtensions(
        extensions: MutableMap<String, Any>,
        crumb: String? = null
    ): Overlay<MutableMap<String, Any>> {
        val mapOverlay = Overlay.of(extensions, parent = null)!!
        validateMap(mapOverlay, nonEmpty = false, unique = false, valueValidator = null)
        return mapOverlay
    }

    fun validateFormatField(name: String, required: Boolean, type: String?): Overlay<String> {
        val field = validateStringField(name, required)
        if (field.isPresent == true) {
            var normalType: String? = null
            when (field.get()) {
                "int32", "int64" -> normalType = "integer"
                "float", "double" -> normalType = "number"
                "byte", "binary", "date", "date-time", "password" -> normalType = "string"
            }
            if (normalType != null) {
                if (type == null || type != normalType) {
                    results.addWarning(
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
                results.addError(Messages.msg(BaseValidationMessages.WrongTypeValue, type, defaultValue!!), overlay)
            }
        }
    }

    fun checkJsonType(
        value: Overlay<*>?,
        verify: (JsonElement) -> Boolean,
        results: ValidationResults?
    ) {
        val json = value!!.overlay?.json
        if (json != null && json !is JsonNull) {
            if (verify(json)) {
                return
            }
            val allowed = allowedJsonTypes!!.map { "${it.key} verifies " + if (it.value(json)) "true" else "false" }
                .joinToString(",")
            results!!.addError(
                Messages.msg(
                    BaseValidationMessages.WrongTypeJson,
                    getJsonValueType(json.javaClass),
                    allowed
                ), value
            )
        }
    }

    private fun getJsonValueType(node: Class<out JsonElement?>): String {
        val type = node.simpleName
        return if (type.endsWith("Node")) type.substring(0, type.length - 4) else type
    }

    protected open fun getAllowedJsonTypes(value: Overlay<*>): (JsonElement) -> Boolean {
        if (allowedJsonTypes == null) {
            createAllowedJsonTypes()
        }
        if (value.overlay == null) {
            return { false }
        }
        return allowedJsonTypes!![if (value.overlay is PropertiesOverlay<*>) PropertiesOverlay::class.java else value.overlay!!.javaClass]!!
    }

    companion object {
        private const val FAKE_SCHEME = "oasparser.fake.scheme"
        private val fakeHandler: URLStreamHandler = object : URLStreamHandler() {
            @Throws(IOException::class)
            override fun openConnection(u: URL): URLConnection? {
                return null
            }
        }
        protected var allowedJsonTypes: Map<Class<*>, (JsonElement) -> Boolean>? = null

        private fun createAllowedJsonTypes() {
            val types: MutableMap<Class<*>, (JsonElement) -> Boolean> = HashMap()
            types[StringOverlay::class.java] = { it is JsonPrimitive && it.isString }
            types[BooleanOverlay::class.java] = { it is JsonPrimitive && it.booleanOrNull != null }
            types[IntegerOverlay::class.java] = { it is JsonPrimitive && it.intOrNull != null }
            types[NumberOverlay::class.java] = {
                it is JsonPrimitive && it.toValue()?.let { o -> o is Number } ?: false
            }
            types[PrimitiveOverlay::class.java] = {
                it is JsonPrimitive && it.toValue()?.let { o -> o is Number || o is String || o is Boolean } ?: false
            }
            types[ObjectOverlay::class.java] = { true }
            types[MapOverlay::class.java] = { it is JsonObject }
            types[ListOverlay::class.java] = { it is JsonArray }
            types[PropertiesOverlay::class.java] = { it is JsonObject }
            allowedJsonTypes = types
        }
    }
}