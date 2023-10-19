package io.github.freshtuna.halo.util.linkBuilder

import io.github.freshtuna.halo.domain.variable.Variables
import io.github.freshtuna.halo.domain.template.parser.TemplateParser
import io.github.freshtuna.halo.domain.variable.Variable

/**
 * This Tool helps to parse UriTemplate and build link to resource [RFC 6570]
 */
class UriTemplateLinkBuilder(
    private val expander: TemplateParser
) {

    private var root = ""

    private var template = ""

    private var templateVars = Variables()

    companion object {
        fun builder(): UriTemplateLinkBuilder {
            return LinkBuilderFactory.createLinkBuilder()
        }
    }

    /**
     * Set root path
     * Built link is started with root path
     */
    fun setRoot(baseUrl: String): UriTemplateLinkBuilder {
        this.root = baseUrl
        return this
    }

    /**
     * Set HAL Template
     */
    fun setTemplate(template: String): UriTemplateLinkBuilder {
        this.template = template
        return this
    }

    /**
     * Add variable to UriTemplate
     */
    fun addVariable(name: String, value: String): UriTemplateLinkBuilder {
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
