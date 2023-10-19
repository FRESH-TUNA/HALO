package io.github.freshtuna.halo.domain.variable

/**
 * This class represents representation (toString) strategy of variable
 */
interface VariableRepresentationStrategy {

    fun parse(variable: Variable): String
}
