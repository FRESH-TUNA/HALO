package io.github.freshtuna.halo.util.parser

import io.github.freshtuna.halo.domain.Variables
import io.github.freshtuna.halo.domain.template.parser.TemplateParser

class TemplateParserDecorator(
    private val parser: TemplateParser,
    private val nextExpander: TemplateParser?
) : TemplateParser {

    constructor(parser: TemplateParser) : this(parser, null)

    override fun parse(variables: Variables, template: String): String {

        val parsed = parser.parse(variables, template)

        if(nextExpander != null)
            return nextExpander.parse(variables, parsed)

        return parsed
    }
}
