package io.github.freshtuna.halo.domain.hal.token

import io.github.freshtuna.halo.domain.Variables
import io.github.freshtuna.halo.domain.hal.strategy.HalParsingStretegy

class HalToken(
    val name: String,
    val allowedCount: Int = UNLIMITED,
    val allowMulti: Boolean,
) {

    companion object {
        const val UNLIMITED = -1
    }

    fun parse(variables: Variables, stretegy: HalParsingStretegy, seperator: String): String {

        val parseResult = StringBuilder()

        if (allowMulti)
            for (value in variables.findAllValuesBy(name))
                parseResult.append(parse(name, value, stretegy, seperator))
        else
            parseResult.append(parse(name, variables.findValueBy(name), stretegy, seperator))

        return parseResult.toString()
    }

    /**
     * helpers
     */
    private fun parse(name: String, value: String?, strategy: HalParsingStretegy, separator: String): String {
        if(checkLimit(value.orEmpty()))
            return strategy.parse(name, value)+separator
        return ""
    }

    private fun checkLimit(value: String): Boolean {
        return (allowedCount==UNLIMITED) or (value.length<=allowedCount)
    }
}
