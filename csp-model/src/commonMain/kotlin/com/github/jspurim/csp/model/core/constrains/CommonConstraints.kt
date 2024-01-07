package com.github.jspurim.csp.model.core.constrains

import com.github.jspurim.csp.model.core.Constraint
import com.github.jspurim.csp.model.core.Evaluation
import com.github.jspurim.csp.model.core.Variable
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentHashSetOf

/**
 * A constraint prohibiting two variables from taking the same value.
 */
class NotEqualConstraint<T>(private val var1: Variable<T>, private val var2: Variable<T>) : Constraint {
    override fun isSatisfied(evaluation: Evaluation): Boolean {
        val val1 = evaluation[var1]
        val val2 = evaluation[var2]
        if (val1 == null) return true
        return val1 != val2
    }
}

/**
 * A constraint forcing the value of a variable to be in a specific set of allowed values.
 */
class ValueInConstraint<T>(private val variable: Variable<T>, private val allowedValues : ImmutableSet<T>) : Constraint {
    override fun isSatisfied(evaluation: Evaluation): Boolean {
        val value = evaluation[variable] ?: return true
        return value in allowedValues
    }
}

/**
 * Creates a [Constraint] forcing a given variable to take a specific value.
 */
fun <T> givenValueConstraint(variable: Variable<T>, value: T) = ValueInConstraint(variable, persistentHashSetOf(value))