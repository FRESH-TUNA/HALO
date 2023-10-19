package io.github.freshtuna.halo.domain.template.parser

import io.github.freshtuna.halo.domain.variable.Variables

/**
 * This Interfaces helps to template
 */
interface TemplateParser {

    /**
     * Return parsed template with variables
     */
    fun parse(variables: Variables, template: String): String
}
