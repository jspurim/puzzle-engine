package com.github.jspurim.csp.solve

import com.github.jspurim.csp.model.arrangement.RectangularGrid
import com.github.jspurim.csp.model.core.Domains
import com.github.jspurim.csp.model.core.ExplicitValuesDomain
import com.github.jspurim.csp.model.core.SimpleFiniteProblem
import com.github.jspurim.csp.model.core.constrains.NotEqualConstraint
import com.github.jspurim.csp.model.core.constrains.givenValueConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.collections.immutable.persistentHashSetOf

class BruteForceSolverTests : FreeSpec({

    "Finds solution on simple problem" {
        val grid = RectangularGrid(2, 2, Domains.BINARY_DIGITS)
        val constraints = persistentHashSetOf(
            // Top left digit should be a one
            givenValueConstraint(grid[0, 0], 1),
            // Standard 2x2 sudoku constraints
            NotEqualConstraint(grid[0, 0], grid[0, 1]),
            NotEqualConstraint(grid[1, 0], grid[1, 1]),
            NotEqualConstraint(grid[0, 0], grid[1, 0]),
            NotEqualConstraint(grid[0, 1], grid[1, 1])
        )
        val problem = SimpleFiniteProblem(
            persistentHashSetOf(*(grid.allVariables.toTypedArray())),
            constraints
        )
        val solver = BruteForceSolver(problem)

        val solution = solver.findSolution().getOrThrow()
        
        solution[grid[0,0]] shouldBe 1
        solution[grid[1,1]] shouldBe 1
        solution[grid[0,1]] shouldBe 0
        solution[grid[1,0]] shouldBe 0
        problem.isSolution(solution) shouldBe true
    }

})