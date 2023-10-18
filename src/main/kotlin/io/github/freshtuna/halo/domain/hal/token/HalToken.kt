package io.github.freshtuna.halo.domain.hal.token

import io.github.freshtuna.halo.domain.Variables
import io.github.freshtuna.halo.domain.hal.strategy.HalParsingStretegy

class HalToken(
    val name: String,
    val allowedCount: Int = -1,
    val allowMulti: Boolean,
) {

    fun parse(variables: Variables, stretegy: HalParsingStretegy, seperator: String): String {
        val parseResult = StringBuilder()

        if(allowMulti)
            for (value in variables.findAllValuesBy(name))
                parseResult.append(stretegy.parse(name, value)+seperator)
        else {
            val value = variables.findValueBy(name)
            parseResult.append(stretegy.parse(name, value)+seperator)
        }

        return parseResult.toString()
    }
}
