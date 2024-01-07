package com.github.jspurim.csp.solve

import com.github.jspurim.csp.model.core.Evaluation
import com.github.jspurim.csp.model.core.SimpleFiniteProblem

class BruteForceSolver<T>(override val problem : SimpleFiniteProblem<T>) : Solver<SimpleFiniteProblem<T>> {

    override suspend fun findSolution(): Result<Evaluation> {
        TODO("Not yet implemented")
    }
}