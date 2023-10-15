package io.github.freshtuna.halo.util.queryBuilder

import io.github.freshtuna.halo.domain.Variables

class QueryBuilder private constructor() {
    companion object {
        fun builder(): QueryBuilder {
            return QueryBuilder()
        }
    }

    private var variables = Variables()

    fun addQuery(name: String, value: String): QueryBuilder {
        this.variables.add(name, value)
        return this
    }

    fun build(): String {
        val parseResult = StringBuilder("?")

        for (name in variables.findAllNames()) {
            for(token in variables.findAllValuesBy(name))
                parseResult.append("$name=$token&")
        }

        parseResult.deleteCharAt(parseResult.length-1)
        return parseResult.toString()
    }
}
