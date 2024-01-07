package com.github.jspurim.csp.model.arrangement

import com.github.jspurim.csp.model.core.Domains
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldBe

class RectangularGridTests : FreeSpec({

    "2x2 rectangular grid" - {
        val grid = RectangularGrid(2, 2, Domains.SUDOKU_DIGITS)
        mapOf(0 to 0, 0 to 1, 1 to 0, 1 to 1).forEach {
            val r = it.key
            val c = it.value
            "variable in the ($r,$c) position" - {
                val variable = grid[r, c]
                "has correct coordinates" {
                    variable.row shouldBe r
                    variable.col shouldBe c
                }
            }
        }
        mapOf(2 to 0, 0 to 2, 0 to -1, -1 to 0).forEach {
            val r = it.key
            val c = it.value
            "throws IndexOutOfBoundsException when trying to access variable ($c,$r)" {
                shouldThrow<IndexOutOfBoundsException> { grid[r, c] }
            }
        }
    }

    "Two grids with different ids" - {
        val grid1 = RectangularGrid(2, 2, Domains.SUDOKU_DIGITS, 1)
        val grid2 = RectangularGrid(2, 2, Domains.SUDOKU_DIGITS, 2)
        "Variables in the same position are distinct" {
            val variable1 = grid1[0,0]
            val variable2 = grid2[0,0]
            variable1 shouldNotBeEqual variable2
        }

        "Variables from each grid have the correct grid id" {
            val variable1 = grid1[0,0]
            val variable2 = grid2[0,0]
            variable1.gridId shouldBe 1
            variable2.gridId shouldBe 2
        }
    }
})