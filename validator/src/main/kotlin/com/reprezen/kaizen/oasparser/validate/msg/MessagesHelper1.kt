package com.reprezen.kaizen.oasparser.validate.msg

import java.io.IOException
import java.util.*
import java.util.function.Consumer
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.collections.HashMap

object MessagesHelper {

    private val placeholderPattern = Pattern.compile("%(\\d+)|%\\{(\\d+)\\}")

    /**
     * Interpolate arguments into the message format string for the given message.
     *
     *
     * If a non-null locale is provided, an attempt is made to load a properties
     * file for the Message class of which this message is an instance, and the
     * property named by this message's name is used as the format string. If that
     * fails, or if the locale is null, the message object's default format string
     * is used.
     *
     *
     * The format string contains embedded place holders indicating where arguments
     * are to be substituted. Each place holder takes the form `%n` or
     * `%{n}`, where `n` is an integer from 1 to the number of
     * arguments. The second form allows interpolation at a site immediately
     * followed by other digits.
     *
     *
     * To prevent a percent sign from triggering interpolation, double it:
     * `%%`. For example, `"%%1 %%{2}"` will always produce
     * `"%1 %{2}"`.
     *
     *
     * The result is obtained by replacing each placeholder with the corresponding
     * argument's [.toString] value (except that null arguments are rendered
     * as empty strings).
     *
     * @param locale  locale to use
     * @param message message to format
     * @param args    values
     * @return message with interpolated arguments
     */
    fun format(locale: Locale, message: Messages, vararg args: Any): String {
        val formatString = message.getFormatString(locale)
        val sortArgs = sortArgs(formatString, args.toList().toTypedArray())
        val printfString = Stream.of(*formatString.split("%%".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray())
            .map { part: String? -> placeholderPattern.matcher(part).replaceAll("%s") } //
            .collect(Collectors.joining("%%"))
        return String.format(printfString, *sortArgs)
    }

    private fun sortArgs(format: String, args: Array<Any?>): Array<Any> {
        val sites = findInterpolationSites(format)
        sites.forEach(Consumer { site: Int ->
            if (site < 1 || site > args.size) {
                val msg = String.format("Interpolation position must be from 1 to %d: %%%d", args.size, site)
                throw IndexOutOfBoundsException(msg)
            }
        })
        return sites.stream().map { i: Int -> if (args[i - 1] != null) args[i - 1] else "" }.toArray()
    }

    private fun findInterpolationSites(format: String): List<Int> {
        val sites: MutableList<Int> = ArrayList()
        val parts = format.split("%%".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (part in parts) {
            val m = placeholderPattern.matcher(part)
            while (m.find()) {
                val braces = m.group(1) == null
                val digits = m.group(if (braces) 2 else 1)
                val index = if (digits.isEmpty()) 0 else Integer.valueOf(digits)
                sites.add(index)
            }
        }
        return sites
    }

    private val localizationsByClass: HashMap<Class<*>, HashMap<String, Properties?>> = HashMap()
    fun loadLocalizations(msgClass: Class<out Messages?>, locale: Locale): Properties? {
        if (!localizationsByClass.containsKey(msgClass)) {
            localizationsByClass[msgClass] = HashMap()
        }
        val localizations: MutableMap<String, Properties?> = localizationsByClass[msgClass]!!
        // try locale-specified variant tag first, then fall back to just the language
        // code
        for (tag in listOf(locale.toLanguageTag(), locale.language)) {
            if (!localizations.containsKey(tag)) {
                val resource = msgClass.getResource(String.format("localizations/%s/messages.properties", tag))
                var props: Properties? = null
                if (resource != null) {
                    props = Properties()
                    try {
                        resource.openStream().use { `in` -> props!!.load(`in`) }
                    } catch (e: IOException) {
                        props = null
                    }
                }
                // if no props loaded, set null value in localizations so we don't keep trying
                localizations[tag] = props
            }
            val result = localizations[tag]
            if (result != null) {
                return result
            }
        }
        return null
    }
}