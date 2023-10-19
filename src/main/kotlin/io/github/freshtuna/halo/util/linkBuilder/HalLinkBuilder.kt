package io.github.freshtuna.halo.util.linkBuilder

import io.github.freshtuna.halo.domain.variable.Variables
import io.github.freshtuna.halo.domain.template.parser.TemplateParser
import io.github.freshtuna.halo.domain.variable.Variable

/**
 * This Tool helps to parse HAL Template and build link to resource [RFC 6570]
 */
class HalLinkBuilder(
    private val expander: TemplateParser
) {

    private var root = ""

    private var template = ""

    private var templateVars = Variables()

    companion object {
        fun builder(): HalLinkBuilder {
            return LinkBuilderFactory.createLinkBuilder()
        }
    }

    /**
     * Set root path
     * Built link is started with root path
     */
    fun setRoot(baseUrl: String): HalLinkBuilder {
        this.root = baseUrl
        return this
    }

    /**
     * Set HAL Template
     */
    fun setTemplate(template: String): HalLinkBuilder {
        this.template = template
        return this
    }

    /**
     * Add variable to HAL template
     */
    fun addVariable(name: String, value: String): HalLinkBuilder {
        this.templateVars.add(Variable(name, value))
        return this
    }

    /**
     * Build link to resource
     */
    fun build() : String {

        var parsed = expander.parse(templateVars, template)

        if (parsed.startsWith("/"))
            parsed = parsed.substring(1)

        if (root.endsWith("/"))
            root = root.substring(0, root.length-1)

        return "$root/$parsed"
    }
}
