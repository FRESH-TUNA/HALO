package io.github.freshtuna.halo.util.parser.hal.old

import io.github.freshtuna.halo.domain.Variables
import io.github.freshtuna.halo.domain.template.parser.TemplateParser

/**
 * This Class helps to parse Form Style Query in HAL Template [RFC 6570]
 *
 * ex) {?name}, {?page,sort,size}
 */
class HalFormStyleQueryParser(
    private val nextExpander: TemplateParser?
): TemplateParser {

    constructor() : this(null)

    private val TEMPLATE_QUERY_REGEX = Regex("\\{\\?([-,\\w]+)\\}")

    override fun parse(variables: Variables, template: String): String {

        val result = StringBuilder(template)

        for (match in TEMPLATE_QUERY_REGEX.findAll(template)) {
            val case = match.groups[1]?.value!!
            val range = match.range
            val parseResult = StringBuilder("?")

            for(key in case.split(",")) {
                for(token in variables.findAllValuesBy(key))
                    parseResult.append("$key=$token&")
            }

            if(parseResult.length == 1)
                continue

            parseResult.deleteCharAt(parseResult.length-1)
            result.replace(range.first, range.last+1, parseResult.toString())
        }

        if(nextExpander != null)
            return nextExpander.parse(variables, result.toString())

        return result.toString()
    }
}
