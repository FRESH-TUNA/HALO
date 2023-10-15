package io.github.freshtuna.halo.util.linkBuilder

import io.github.freshtuna.halo.domain.Variables
import io.github.freshtuna.halo.util.queryBuilder.QueryBuilder
import io.github.freshtuna.halo.util.templateParser.TemplateParser

/**
 * This Tool helps to build link to resource
 *
 * It supports simple string parsing in HAL [RFC 6570]
 * ex) /users/{userId} -> /users/1
 *
 * Query String or Adding query supports
 * ex) ?a=1&b=2&c=3)
 */
class SimpleLinkBuilder(
    private val expander: TemplateParser
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

    /**
     * Set root path
     * Built link is started with root path
     */
    fun setRoot(root: String): SimpleLinkBuilder {
        this.root = root
        return this
    }

    /**
     * Set path
     * Built link = Root + path
     */
    fun setPath(path: String): SimpleLinkBuilder {
        return setTemplate(path)
    }

    /**
     * Set HAL template
     * Built result = Root + parsed template
     */
    fun setTemplate(template: String): SimpleLinkBuilder {
        this.template = template
        return this
    }

    /**
     * Set template argument
     *
     * ex) template: /users/{userId}
     *     method call: addArg("userId", "1)
     *     built result = /users/1
     */
    fun addArg(name: String, value: String): SimpleLinkBuilder {
        this.templateVars.add(name, value)
        return this
    }

    /**
     * Add Query
     *
     * ex) template: /users/{userId}
     *     method call: addArg("userId", "1)
     *     built result = /users/1
     */
    fun addQuery(name: String, value: String): SimpleLinkBuilder {
        this.queries = this.queries.addQuery(name, value)
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

        return StringBuilder(root)
            .append("/")
            .append(parsed)
            .append(this.queries.build())
            .toString()
    }
}
