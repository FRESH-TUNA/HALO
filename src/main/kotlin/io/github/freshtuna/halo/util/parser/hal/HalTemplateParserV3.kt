package io.github.freshtuna.halo.util.parser.hal

import io.github.freshtuna.halo.domain.variable.Variables
import io.github.freshtuna.halo.domain.hal.HalMode
import io.github.freshtuna.halo.domain.hal.token.HalTokenizer

import io.github.freshtuna.halo.domain.template.parser.TemplateParser

class HalTemplateParserV3(
    private val halMode: HalMode,
    private val tokenizer: HalTokenizer
) : TemplateParser {

    override fun parse(variables: Variables, template: String): String {

        return halMode.TEMPLATE_REGEX.replace(template) { match ->

            val tokenString = match.groups[1]?.value!!

            val parsed = StringBuilder(halMode.TEMPLATE_PREFIX)

            for(atomic in tokenizer.tokenize(tokenString))
                parsed.append(atomic.parse(variables, halMode.STERTEGY, halMode.SEPERATOR))

            parsed.deleteCharAt(parsed.length-1)
        }
    }
}
