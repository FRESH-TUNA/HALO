package io.github.freshtuna.halo.util.queryBuilder

import io.github.freshtuna.halo.domain.variable.Variable
import io.github.freshtuna.halo.domain.variable.Variables
import io.github.freshtuna.halo.util.variable.strategy.DictRepresentationStrategy

/**
 * This builder offers query building feature
 *
 * ex) ?page=2&size=2&sort=asc
 */
class QueryBuilder private constructor() {
    companion object {
        fun builder(): QueryBuilder {
            return QueryBuilder()
        }
    }

    private val strategy = DictRepresentationStrategy()

    private var variables = Variables()

    fun addQuery(name: String, value: String): QueryBuilder {
        this.variables.add(Variable(name, value))
        return this
    }

    fun build(): String {
        val parseResult = StringBuilder("?")

        for (name in variables.findAllNames()) {
            for(token in variables.findAllByName(name))
                parseResult.append(strategy.parse(token)+"&")
        }

        parseResult.deleteCharAt(parseResult.length-1)
        return parseResult.toString()
    }
}
