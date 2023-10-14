package com.freshtuna.tunarest.util.linkBuilder

import com.freshtuna.tunarest.domain.Variables
import com.freshtuna.tunarest.util.templateExpander.TemplateExpander

class HalLinkBuilder(
    private val expander: TemplateExpander
) {

    private var root = "/"

    private var template = ""

    private var templateVars = Variables()

    companion object {
        fun builder(): HalLinkBuilder {
            return LinkBuilderFactory.createLinkBuilder()
        }
    }

    fun setRoot(baseUrl: String): HalLinkBuilder {
        this.root = baseUrl
        return this
    }

    fun setTemplate(template: String): HalLinkBuilder {
        this.template = template
        return this
    }

    fun addVariable(name: String, value: String): HalLinkBuilder {
        this.templateVars.add(name, value)
        return this
    }

    fun build() : String {
        return StringBuilder(root)
            .append(expander.expand(templateVars, template))
            .toString()
    }
}
