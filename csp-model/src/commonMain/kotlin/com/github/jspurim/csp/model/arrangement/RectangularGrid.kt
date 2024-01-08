package com.github.jspurim.csp.model.arrangement

import com.github.jspurim.csp.model.core.Domain
import com.github.jspurim.csp.model.core.Problem
import kotlinx.collections.immutable.immutableHashSetOf
import kotlinx.collections.immutable.persistentHashSetOf
import kotlinx.collections.immutable.persistentListOf

/**
 * A rectangular grid of [Variable]s.
 *
 * Each variable in the grid is identified by its row and column within the grid.
 * Optionally a grid identifier can be provided, allowing to have multiple grids of variables in the same [Problem].
 */
class RectangularGrid<D: Domain<T>, T>(val rows: Int, val cols: Int, domain: D, val gridId : Int = 0) {

    private val variables =
        Array(rows) { r -> Array(cols) { c -> Variable(gridId, r, c, domain) } }

    /**
     * A list of all variables in the grid.
     *
     * The variables will be orders first by row, then by column, in ascending order.
     * This translates to natural reading order on a matrix, with standard indexing (where top left is [0,0]).
     */
    val allVariables = persistentListOf(*variables.flatten().toTypedArray());

    /**
     * Returns the [Variable] in the specified position.
     */
    operator fun get(row : Int, col:Int) : Variable<D, T> {
        return variables[row][col]
    }

    /**
     * A [Variable][com.github.jspurim.csp.model.core.Variable] that lives within a [RectangularGrid].
     */
    data class Variable<VD : Domain<VT>, VT>(val gridId: Int, val row: Int, val col: Int, override val domain: VD) :
        com.github.jspurim.csp.model.core.Variable<VD, VT>
}
