package com.github.jspurim.csp.solve

import com.github.jspurim.csp.model.core.Evaluation
import com.github.jspurim.csp.model.core.Problem

/**
 * A solver to find the solution for a specific csp problem.
 */
interface Solver<P : Problem> {

    /**
     * The problem this solver is trying to solve.
     */
    val problem : P

    /**
     * Attempt to find a solution for the problem.
     *
     * This method may fail if the problem is unsolvable, or the solver implementation is unable to find the solution.
     * The return type will contain an [Evaluation] representing the solution if it was found, or an exception
     * otherwise. Each implementation is free to define their own exceptions to provide more information about why the
     * solution was not found.
     */
    suspend fun findSolution() : Result<Evaluation>
}


