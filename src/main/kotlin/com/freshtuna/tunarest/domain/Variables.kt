package com.freshtuna.tunarest.domain

import java.util.TreeMap

class Variables {
    private var variableListMap: MutableMap<String, MutableList<String>> = TreeMap()

    fun add(name: String, value: String) {

        if(!this.variableListMap.containsKey(name))
            this.variableListMap[name] = mutableListOf()

        this.variableListMap[name]?.add(value)
    }

    fun findValueBy(name: String): String {
        return variableListMap[name]!!.last()
    }

    fun findAllValuesBy(name: String): List<String> {
        return variableListMap[name]!!
    }

    fun findAllNames(): List<String> {
        return variableListMap.keys.toList()
    }
}
