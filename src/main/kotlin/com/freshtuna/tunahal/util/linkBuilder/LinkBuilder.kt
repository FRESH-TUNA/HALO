package com.freshtuna.tunahal.util.linkBuilder

import com.freshtuna.tunahal.domain.Variables
import com.freshtuna.tunahal.util.templateExpander.TemplateExpander

class LinkBuilder(
    private val expander: TemplateExpander
) {

    private var baseUrl = "/"

    private var template = ""

    private var variables = Variables()

    companion object {
        fun builder(): LinkBuilder {
            return LinkBuilderFactory.createLinkBuilder()
        }
    }

    fun setBaseUrl(baseUrl: String): LinkBuilder {
        this.baseUrl = baseUrl
        return this
    }

    fun setTemplate(template: String): LinkBuilder {
        this.template = template
        return this
    }

    fun setPath(template: String): LinkBuilder {
        return setTemplate(template)
    }

    fun addVariable(name: String, value: String): LinkBuilder {
        this.variables.add(name, value)
        return this
    }

    fun addQuery(name: String, value: String): LinkBuilder {
        return addVariable(name, value)
    }

    fun addArgument(name: String, value: String): LinkBuilder {
        return addVariable(name, value)
    }

    fun build() : String {
        return StringBuilder(baseUrl).append(expander.expand(variables, template)).toString()
    }
}
