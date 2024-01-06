package com.github.jspurim.csp.model.core

/**
 * A mapping from [Variable]s to concrete values.
 *
 * An evaluation allows setting values for multiple variables of different types.
 */
class Evaluation {

    private val data: MutableMap<Any, Any?> = mutableMapOf()

    /**
     * Set the value of the provided variable.
     */
    operator fun <T> set(variable: Variable<T>, value: T): Unit {
        if (!variable.domain.isValidValue(value)) {
            throw IllegalArgumentException("The value $value is not in the domain of variable $variable, which is ${variable.domain}.")
        }
        data[variable] = value
    }

    /**
     * Retrieve the value of the provided variable.
     */
    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(variable: Variable<T>): T? {
        return data[variable] as T?
    }

    /**
     * Indicates whether the specified variable has a value set on this evaluation.
     */
    fun hasValue(variable: Variable<*>) = data.containsKey(variable)
}