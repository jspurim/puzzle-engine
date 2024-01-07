package com.github.jspurim.csp.model.core

import com.github.jspurim.csp.model.core.constrains.NotEqualConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import io.kotest.matchers.shouldBe
import kotlinx.collections.immutable.persistentHashSetOf

class ProblemTests : FreeSpec({


    "Simple example problem:" - {
        val variableA = SimpleFiniteVariable(1, Domains.BINARY_DIGITS)
        val variableB = SimpleFiniteVariable(2, Domains.BINARY_DIGITS)
        val constraint = NotEqualConstraint(variableA, variableB)

        "Defined as a general problem" - {
            val problem = GeneralProblem(persistentHashSetOf(variableA, variableB), persistentHashSetOf(constraint))
            simpleFiniteProblemTests(problem, variableA, variableB)
        }

        "Defined as a simple finite problem" - {
            val problem = SimpleFiniteProblem(persistentHashSetOf(variableA, variableB), persistentHashSetOf(constraint))
            simpleFiniteProblemTests(problem, variableA, variableB)
        }

    }

    "GeneralProblem" -{
        "Allows variables of multiple types" {
            val variableA = SimpleVariable(1, Domains.BINARY_DIGITS)
            val variableB = SimpleVariable(2, typeDomain<Char>())
            val problem = GeneralProblem(persistentHashSetOf(variableA, variableB), persistentHashSetOf())
        }
    }

})

private suspend fun FreeSpecContainerScope.simpleFiniteProblemTests(
    problem: Problem,
    variableA: Variable<Int>,
    variableB: Variable<Int>
) {
    "Empty evaluation" {
        val emptyEvaluation = Evaluation()

        problem.isConsistent(emptyEvaluation) shouldBe true
        problem.isComplete(emptyEvaluation) shouldBe false
        problem.isSolution(emptyEvaluation) shouldBe false
    }

    "Contradictory evaluation" {
        val evaluation = Evaluation()
        evaluation[variableA] = 1
        evaluation[variableB] = 1
        problem.isConsistent(evaluation) shouldBe false
        problem.isComplete(evaluation) shouldBe true
        problem.isSolution(evaluation) shouldBe false
    }

    "Partially consistent evaluation" {
        val evaluation = Evaluation()
        evaluation[variableA] = 1
        problem.isConsistent(evaluation) shouldBe true
        problem.isComplete(evaluation) shouldBe false
        problem.isSolution(evaluation) shouldBe false
    }

    "Complete and consistent set evaluation" {
        val evaluation = Evaluation()
        evaluation[variableA] = 1
        evaluation[variableB] = 0
        problem.isConsistent(evaluation) shouldBe true
        problem.isComplete(evaluation) shouldBe true
        problem.isSolution(evaluation) shouldBe true
    }
}