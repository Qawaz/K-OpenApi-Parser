package com.reprezen.kaizen.oasparser.validate.msg

import java.util.*

interface Messages {
    // TODO: add a method to test a localization file for missing and invalid
    // property names (compare all the property keys to the enum value names). This
    // should be easily executed by a main method in the enum class. It would be
    // great to also flag localized messages whose list of placeholder positions
    // does not match that of the default message.
    fun getFormatString(): String

    val name : String

    fun getFormatString(locale: Locale): String {
        val localeStrings = getFormatStrings(locale)
        val formatString = localeStrings?.getProperty(name)
        return formatString ?: getFormatString()
    }

    fun getFormatStrings(locale: Locale): Properties? {
        return MessagesHelper.loadLocalizations(this.javaClass, locale)
    }

    fun msg(vararg args: Any): String {
        return MessagesHelper.format(Locale.getDefault(), this, *args)
    }

    fun msgNoLocale(vararg args: Any): String? {
        TODO("Not yet implemented")
    }

    fun msg(locale: Locale, vararg args: Any): String {
        return MessagesHelper.format(locale, this, *args)
    }

    companion object {
        fun msg(instance: Messages, vararg args: Any): String {
            return instance.msg(*args)
        }
    }
}