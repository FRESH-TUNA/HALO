package io.github.freshtuna.halo.domain.template.parser

import io.github.freshtuna.halo.domain.Variables

/**
 * This Interfaces helps to parse HAL Template [RFC 6570]
 */
interface TemplateParser {

    /**
     * Return parsed template with variables
     */
    fun parse(variables: Variables, template: String): String
}
