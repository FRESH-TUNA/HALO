package com.freshtuna.tunarest.util.templateExpander

import com.freshtuna.tunarest.domain.Variables

class HalSimpleStringExpander(
    private val nextExpander: TemplateExpander?
) : TemplateExpander {

    constructor() : this(null)

    private val TEMPLATE_SIMPLE_REGEX = Regex("\\{([-,\\w]+)\\}")

    override fun expand(variables: Variables, template: String): String {

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
            return nextExpander.expand(variables, result.toString())

        return result.toString()
    }
}
