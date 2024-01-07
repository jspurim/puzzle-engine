package com.github.jspurim.csp.model.format

import com.github.jspurim.csp.model.arrangement.RectangularGrid
import com.github.jspurim.csp.model.core.Domain
import com.github.jspurim.csp.model.core.Evaluation

fun RectangularGrid<*, *>.format(evaluation: Evaluation, unsetValueString: String = " "): String {
    val valueStrings = Array(rows) { r ->
        Array(cols) { c ->
            val variable = this[r, c]
            val value = evaluation[variable]
            value?.toString() ?: unsetValueString
        }
    }

    val cellSize = valueStrings.flatten().map { it.length }.max()
    val hrule = "-".repeat(cols + 1 + (cellSize * cols)) +"\n"
    return valueStrings.map { row ->
        row.map { it.padStart(cellSize, padChar = ' ') }
            .joinToString("|", prefix = "|", postfix = "|\n")
    }.joinToString(separator = hrule, prefix = hrule, postfix = hrule)
}