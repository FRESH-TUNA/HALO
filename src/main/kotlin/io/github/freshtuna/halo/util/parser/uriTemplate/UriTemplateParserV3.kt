package io.github.freshtuna.halo.util.parser.uriTemplate

import io.github.freshtuna.halo.domain.variable.Variables
import io.github.freshtuna.halo.domain.uriTemplate.UriTemplateMode
import io.github.freshtuna.halo.domain.uriTemplate.token.UriTemplateTokenizer

import io.github.freshtuna.halo.domain.template.parser.TemplateParser

/**
 * This class parse UriTemplate (RFC 6570)
 */
class UriTemplateParserV3(
    private val uriTemplateMode: UriTemplateMode,
    private val tokenizer: UriTemplateTokenizer
) : TemplateParser {

    override fun parse(variables: Variables, template: String): String {

        return uriTemplateMode.TEMPLATE_REGEX.replace(template) { match ->

            val tokenString = match.groups[1]?.value!!

            val parsed = StringBuilder(uriTemplateMode.TEMPLATE_PREFIX)

            for(atomic in tokenizer.tokenize(tokenString))
                parsed.append(atomic.parse(variables, uriTemplateMode.STERTEGY, uriTemplateMode.SEPERATOR))

            parsed.deleteCharAt(parsed.length-1)
        }
    }
}
