package com.github.jspurim.csp.model.core

/**
 * A [Variable] that can take any value withing a given [Domain].
 *
 * This interface represents the variable itself, and not the value associated with that variable.
 * Such values are represented via "evaluations" which are maps from variables to concrete values.
 * Since variables are used as keys on collections, implementations should be data classes when possible.
 */
interface Variable<T>{
    val domain : Domain<T>
    val id : VariableId
}

interface VariableId

class SimpleVariable<T> constructor(variableId : Int, override val domain: Domain<T>) : Variable<T> {

    private data class SimpleVariableKey<T>(val variableId: Int, val domain: Domain<T>) : VariableId
    override val id : VariableId = SimpleVariableKey(variableId, domain)
}