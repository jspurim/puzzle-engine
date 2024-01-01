package com.github.jspurim.csp.model.core

class Evaluation {

    private val data : MutableMap<VariableId, Any?> = mutableMapOf()

    fun <T> setValue(variable : Variable<T>, value : T) {
        data[variable.id] = value
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getValue(variable: Variable<T>) : T? {
        return data[variable.id] as T?
    }
}