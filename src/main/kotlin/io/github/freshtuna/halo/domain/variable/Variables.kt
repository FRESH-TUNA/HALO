package io.github.freshtuna.halo.domain.variable

/**
 * This class manages variables used in parsing template.
 */
class Variables {
    private var variableListMap: MutableMap<String, MutableList<Variable>> = LinkedHashMap()
    fun add(variable: Variable) {
        if(!this.variableListMap.containsKey(variable.name))
            this.variableListMap[variable.name] = mutableListOf()

        this.variableListMap[variable.name]?.add(variable)
    }

    fun findByName(name: String): Variable? {
        if(variableListMap.containsKey(name))
            return variableListMap[name]!!.last()
        return null
    }

    fun findAllByName(name: String): List<Variable> {
        if(variableListMap.containsKey(name))
            return variableListMap[name]!!
        return emptyList()
    }

    fun findAllNames(): List<String> {
        return variableListMap.keys.toList()
    }
}
