package io.github.freshtuna.halo.util.variable.strategy

/**
 * This class manages singleton Variable Representation Strategy
 */
class VariableStrategyContext {
    companion object {
        val VALUE_ONLY = ValueOnlyRepresentationStrategy()
        val DICT_STYLE = DictRepresentationStrategy()
    }
}
