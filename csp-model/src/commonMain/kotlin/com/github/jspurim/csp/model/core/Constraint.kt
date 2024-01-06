package com.github.jspurim.csp.model.core

/**
 * A condition on a set of [Variable]s that needs to be satisfied for the [Evaluation] to be a solution to the
 * [Problem].
 */
interface Constraint {

    /**
     * Returns false if the provided evaluation violates the contraint.
     * Return true otherwise. Notice that an empty evaluation would always satisfy any constrain,
     * satisfying a constraint only means not violating it.
     */
    fun isSatisfied(evaluation: Evaluation) : Boolean
}

