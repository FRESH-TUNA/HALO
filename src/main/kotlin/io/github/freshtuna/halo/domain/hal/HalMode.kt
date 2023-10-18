package io.github.freshtuna.halo.domain.hal

import io.github.freshtuna.halo.util.parser.hal.strategy.HalDictParingStrategy
import io.github.freshtuna.halo.domain.hal.strategy.HalParsingStretegy
import io.github.freshtuna.halo.util.parser.hal.strategy.HalValueParsingStrategy

enum class HalMode(
    val TEMPLATE_PREFIX: String = "",
    val SEPERATOR: String = ",",
    val TEMPLATE_REGEX: Regex,
    val STERTEGY: HalParsingStretegy
) {

    SIMPLE_STRING("", ",", Regex("\\{([-,:*\\w]+)\\}"), HalValueParsingStrategy()),
    FORM_STYLE_QUERY("?", "&", Regex("\\{\\?([-,:*\\w]+)\\}"), HalDictParingStrategy());
}
