package com.freshtuna.tunahal.domain

class Variables {
    private var variableListMap: MutableMap<String, MutableList<String>> = HashMap()

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
}
