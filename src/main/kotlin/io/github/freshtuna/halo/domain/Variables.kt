package io.github.freshtuna.halo.domain

import java.util.TreeMap

class Variables {
    private var variableListMap: MutableMap<String, MutableList<String>> = LinkedHashMap()

    fun add(name: String, value: String) {

        if(!this.variableListMap.containsKey(name))
            this.variableListMap[name] = mutableListOf()

        this.variableListMap[name]?.add(value)
    }

    fun findValueBy(name: String): String? {
        if(variableListMap.containsKey(name))
            return variableListMap[name]!!.last()
        return null
    }

    fun findAllValuesBy(name: String): List<String> {
        if(variableListMap.containsKey(name))
            return variableListMap[name]!!
        return emptyList()
    }

    fun findAllNames(): List<String> {
        return variableListMap.keys.toList()
    }
}
