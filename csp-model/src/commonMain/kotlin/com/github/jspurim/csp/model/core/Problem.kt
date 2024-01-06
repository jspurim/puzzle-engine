package com.github.jspurim.csp.model.core

import kotlinx.collections.immutable.ImmutableSet

/**
 * A constraint satisfaction problem definition.
 *
 * A [Problem] is characterized as set of [Variable]s (each with an associated [Domain]) plus a set of [Constraint]s on
 * the values of those variables.
 *
 * An assignment of values to variables is called an [Evaluation]. An evaluation that sets values for variables of a
 * problem without violating any constrain is a solution of the problem.
 */
class Problem(val variables: ImmutableSet<Variable<*>>, val constraints: ImmutableSet<Constraint>) {

    fun isSolution(evaluation: Evaluation) = isComplete(evaluation) && isConsistent(evaluation)

    fun isComplete(evaluation: Evaluation) = variables.all(evaluation::hasValue)
    fun isConsistent(evaluation: Evaluation) = constraints.all(evaluation::satisfies)
}