package com.github.jspurim.csp.model.format

import com.github.jspurim.csp.model.arrangement.RectangularGrid
import com.github.jspurim.csp.model.core.Evaluation
import com.github.jspurim.csp.model.core.typeDomain
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class FormatTests : FreeSpec({
    "RectangularGrid.format generates correct text representation" - {
        val grid = RectangularGrid(2, 2, typeDomain<Int>())
        "empty evaluation" {
            val evaluation = Evaluation()
            val representation = grid.format(evaluation)
            representation shouldBe
                    """
                        -----
                        | | |
                        -----
                        | | |
                        -----
                        
                    """.trimIndent()
        }
        "evaluation with single char values" {
            val evaluation = Evaluation()
            evaluation[grid[0, 0]] = 1
            evaluation[grid[0, 1]] = 2
            evaluation[grid[1, 0]] = 3
            evaluation[grid[1, 1]] = 4
            val representation = grid.format(evaluation)
            representation shouldBe
                    """
                        -----
                        |1|2|
                        -----
                        |3|4|
                        -----
                        
                    """.trimIndent()
        }
        "evaluation with values with long string representations" {
            val evaluation = Evaluation()
            evaluation[grid[0, 0]] = 1
            evaluation[grid[0, 1]] = 20
            evaluation[grid[1, 0]] = 300
            evaluation[grid[1, 1]] = 4
            val representation = grid.format(evaluation)
            representation shouldBe
                    """
                        ---------
                        |  1| 20|
                        ---------
                        |300|  4|
                        ---------
                        
                    """.trimIndent()
        }
    }
})