package io.github.freshtuna.halo.domain.uriTemplate

import io.github.freshtuna.halo.domain.variable.VariableRepresentationStrategy
import io.github.freshtuna.halo.util.variable.strategy.VariableStrategyContext

/**
 * This class used to provide information about URI template (RFC 6570)
 */
enum class UriTemplateMode(
    val TEMPLATE_PREFIX: String = "",
    val SEPERATOR: String = ",",
    val TEMPLATE_REGEX: Regex,
    val STERTEGY: VariableRepresentationStrategy
) {

    SIMPLE_STRING("", ",", Regex("\\{([-,:*\\w]+)\\}"), VariableStrategyContext.VALUE_ONLY),
    FORM_STYLE_QUERY("?", "&", Regex("\\{\\?([-,:*\\w]+)\\}"), VariableStrategyContext.DICT_STYLE);
}
