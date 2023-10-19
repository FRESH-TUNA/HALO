package io.github.freshtuna.halo.domain.variable

/**
 * This class represents variable used in parsing template.
 */
class Variable(
    val name: String,
    val value: String
) {
    fun toString(strategy: VariableRepresentationStrategy)
        = strategy.parse(this)
}
