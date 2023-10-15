package io.github.freshtuna.halo.util.templateParser

import io.github.freshtuna.halo.domain.Variables

/**
 * This Class helps to parse Simple String parsing in HAL Template [RFC 6570]
 *
 * ex) {name}, {id}
 */
class HalSimpleStringParser(
    private val nextExpander: TemplateParser?
) : TemplateParser {

    constructor() : this(null)

    private val TEMPLATE_SIMPLE_REGEX = Regex("\\{([-,\\w]+)\\}")

    override fun parse(variables: Variables, template: String): String {

        val result = StringBuilder(template)

        for (match in TEMPLATE_SIMPLE_REGEX.findAll(result)) {

            val name = match.groups[1]?.value!!
            val range = match.range

            val value = variables.findValueBy(name)

            if(value.isNullOrBlank())
                throw RuntimeException("key: $name is not found")

            result.replace(range.first, range.last+1, value)
        }

        if(nextExpander != null)
            return nextExpander.parse(variables, result.toString())

        return result.toString()
    }
}
