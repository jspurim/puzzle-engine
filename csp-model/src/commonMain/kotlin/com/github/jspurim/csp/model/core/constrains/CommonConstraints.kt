package com.github.jspurim.csp.model.core.constrains

import com.github.jspurim.csp.model.core.Constraint
import com.github.jspurim.csp.model.core.Evaluation
import com.github.jspurim.csp.model.core.Variable

class NotEqualConstraint<T>(private val var1: Variable<T>, private val var2: Variable<T>) : Constraint {
    override fun isSatisfied(evaluation: Evaluation): Boolean {
        val val1 = evaluation[var1]
        val val2 = evaluation[var2]
        if (val1 == null) return true
        return val1 != val2
    }
}