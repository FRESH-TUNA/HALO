package io.github.freshtuna.halo.domain.hal.token

import io.github.freshtuna.halo.domain.variable.Variable
import io.github.freshtuna.halo.domain.variable.Variables
import io.github.freshtuna.halo.domain.variable.VariableRepresentationStrategy

/**
 * This class represents token of HAL Template
 *
 * ex) member:34*, size, comment:33
 */
class HalToken(
    val name: String,
    val allowedCount: Int = UNLIMITED,
    val allowMulti: Boolean,
) {

    companion object {
        const val UNLIMITED = -1
    }

    fun parse(variables: Variables, strategy: VariableRepresentationStrategy, separator: String): String {

        val parseResult = StringBuilder()

        if (allowMulti)
            for (variable in variables.findAllByName(name))
                parseResult.append(parse(variable, strategy, separator))
        else {
            val variable = variables.findByName(name)?: Variable(name, "")
            parseResult.append(parse(variable, strategy, separator))
        }

        return parseResult.toString()
    }

    /**
     * helpers
     */
    private fun parse(variable: Variable, strategy: VariableRepresentationStrategy, separator: String): String {

        if(checkLimit(variable.value))
            return strategy.parse(variable)+separator
        return ""
    }

    private fun checkLimit(value: String): Boolean {
        return (allowedCount==UNLIMITED) or (value.length<=allowedCount)
    }
}
