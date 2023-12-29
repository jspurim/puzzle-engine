package com.github.jspurim.csp.model.core

/**
 * A [Variable] that can take any value withing a given [Domain].
 *
 * This interface represents the variable itself, and not the value associated with that variable.
 * Such values are represented via "evaluations" which are maps from variables to concrete values.
 * Since variables are used as keys on collections, implementations should be data classes when possible.
 */
interface Variable<T> {
    val domain : Domain<T>
}


data class SimpleVariable<T> constructor(val variableId : Int, override val domain: Domain<T>) : Variable<T>
