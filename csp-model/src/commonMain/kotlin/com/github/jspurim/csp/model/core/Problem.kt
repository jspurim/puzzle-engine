package com.github.jspurim.csp.model.core

import kotlinx.collections.immutable.ImmutableSet

/**
 * A constraint satisfaction problem definition.
 *
 * A [GeneralProblem] is characterized as set of [Variable]s (each with an associated [Domain]) plus a set of [Constraint]s on
 * the values of those variables.
 *
 * An assignment of values to variables is called an [Evaluation]. An evaluation that sets values for variables of a
 * problem without violating any constrain is a solution of the problem.
 */
interface Problem {
    val variables: ImmutableSet<Variable<*>>
    val constraints: ImmutableSet<Constraint>

    /**
     * Whether the given [Evaluation] is a solution for this problem.
     *
     * An evaluation is solution if and only if it is complete and consistent with respect to this problem.
     */
    fun isSolution(evaluation: Evaluation) = isComplete(evaluation) && isConsistent(evaluation)

    /**
     * Whether the given [Evaluation] sets values for every [Variable] in this problem.
     */
    fun isComplete(evaluation: Evaluation) = variables.all(evaluation::hasValue)

    /**
     * Whether the given [Evaluation] is consistent with the [Constraint]s of this problem.
     *
     * Notices that consistency only means that no constraint was violated, and does not imply that the evaluation is a
     * solutions. For example, the empty evaluation that sets no variables is trivially consistent with every problem.
     */
    fun isConsistent(evaluation: Evaluation) = constraints.all(evaluation::satisfies)
}

/**
 * A general [Problem] defined directly by its sets of [Variable]s and [Constraint]s.
 *
 * This is the most general type of problem possible, making no assumptions at all about any of the variables or
 * constrains.
 */
class GeneralProblem(
    override val variables: ImmutableSet<Variable<*>>,
    override val constraints: ImmutableSet<Constraint>
) : Problem


/**
 * A simple [Problem] that satisfies the following conditions:
 *
 * 1. All the variables are [FiniteVariable].
 * 2. All the variables are of the same type.
 *
 * Each variable is allowed to have a different [FiniteDomain].
 */
class SimpleFiniteProblem<T>(
    override val variables: ImmutableSet<FiniteVariable<T>>,
    override val constraints: ImmutableSet<Constraint>
) : Problem