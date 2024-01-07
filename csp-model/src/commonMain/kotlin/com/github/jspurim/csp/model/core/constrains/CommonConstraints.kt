package com.github.jspurim.csp.model.core.constrains

import com.github.jspurim.csp.model.core.Constraint
import com.github.jspurim.csp.model.core.Domain
import com.github.jspurim.csp.model.core.Evaluation
import com.github.jspurim.csp.model.core.Variable
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentHashSetOf

/**
 * A constraint prohibiting two variables from taking the same value.
 */
class NotEqualConstraint<D : Domain<T>, T>(private val var1: Variable<D, T>, private val var2: Variable<D, T>) :
    Constraint {
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
class ValueInConstraint<D : Domain<T>, T>(
    private val variable: Variable<D, T>,
    private val allowedValues: ImmutableSet<T>
) : Constraint {
    override fun isSatisfied(evaluation: Evaluation): Boolean {
        val value = evaluation[variable] ?: return true
        return value in allowedValues
    }
}

/**
 * Creates a [Constraint] forcing a given variable to take a specific value.
 */
fun <D : Domain<T>, T> givenValueConstraint(variable: Variable<D, T>, value: T) =
    ValueInConstraint(variable, persistentHashSetOf(value))