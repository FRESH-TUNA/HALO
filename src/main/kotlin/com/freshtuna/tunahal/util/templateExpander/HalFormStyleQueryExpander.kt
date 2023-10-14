package com.freshtuna.tunahal.util.templateExpander

import com.freshtuna.tunahal.domain.Variables

class HalFormStyleQueryExpander(
    private val nextExpander: TemplateExpander?
): TemplateExpander {

    constructor() : this(null)

    private val TEMPLATE_QUERY_REGEX = Regex("\\{\\?([-,\\w]+)\\}")

    override fun expand(variables: Variables, template: String): String {

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
            return nextExpander.expand(variables, result.toString())

        return result.toString()
    }
}
