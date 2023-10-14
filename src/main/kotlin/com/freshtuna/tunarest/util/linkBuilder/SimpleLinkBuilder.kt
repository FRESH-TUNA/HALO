package com.freshtuna.tunarest.util.linkBuilder

import com.freshtuna.tunarest.domain.Variables
import com.freshtuna.tunarest.util.queryBuilder.QueryBuilder
import com.freshtuna.tunarest.util.templateExpander.TemplateExpander

class SimpleLinkBuilder(
    private val expander: TemplateExpander
) {

    private var root = ""

    private var template = ""

    private var templateVars = Variables()

    private var queries = QueryBuilder.builder()

    companion object {
        fun builder(): SimpleLinkBuilder {
            return LinkBuilderFactory.createSimpleLinkBuilder()
        }
    }

    fun setRoot(root: String): SimpleLinkBuilder {
        this.root = root
        return this
    }

    fun setPath(path: String): SimpleLinkBuilder {
        return setTemplate(path)
    }

    fun setTemplate(template: String): SimpleLinkBuilder {
        this.template = template
        return this
    }

    fun addArg(name: String, value: String): SimpleLinkBuilder {
        this.templateVars.add(name, value)
        return this
    }

    fun addQuery(name: String, value: String): SimpleLinkBuilder {
        this.queries = this.queries.addQuery(name, value)
        return this
    }

    fun build() : String {

        var parsed = expander.expand(templateVars, template)

        if (parsed.startsWith("/"))
            parsed = parsed.substring(1)

        if (root.endsWith("/"))
            root = root.substring(0, root.length-1)

        return StringBuilder(root)
            .append("/")
            .append(parsed)
            .append(this.queries.build())
            .toString()
    }
}
