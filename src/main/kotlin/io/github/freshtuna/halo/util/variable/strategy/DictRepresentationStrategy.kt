package io.github.freshtuna.halo.util.variable.strategy

import io.github.freshtuna.halo.domain.variable.Variable
import io.github.freshtuna.halo.domain.variable.VariableRepresentationStrategy


class DictRepresentationStrategy : VariableRepresentationStrategy {

    override fun parse(variable: Variable): String {
        return "${variable.name}=${variable.value}"
    }
}
