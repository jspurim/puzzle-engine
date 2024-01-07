package com.github.jspurim.csp.model.core.constraints

import com.github.jspurim.csp.model.core.Domains
import com.github.jspurim.csp.model.core.Evaluation
import com.github.jspurim.csp.model.core.SimpleFiniteVariable
import com.github.jspurim.csp.model.core.constrains.NotEqualConstraint
import com.github.jspurim.csp.model.core.constrains.ValueInConstraint
import com.github.jspurim.csp.model.core.constrains.givenValueConstraint
import com.github.jspurim.csp.model.core.simpleIntVariable
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.collections.immutable.persistentHashSetOf

class NotEqualConstraintTests : FreeSpec({

    "A not equal to B constraint:" - {
        val varA = simpleIntVariable(1)
        val varB = simpleIntVariable(2)
        val varC = simpleIntVariable(3)
        val abNotEqualConstrant = NotEqualConstraint(varA, varB)

        "Empty evaluation satisfies constraint" {
            val evaluation = Evaluation()
            abNotEqualConstrant.isSatisfied(evaluation) shouldBe true
        }

        "Setting both variables to the same value violates the constraint" {
            val evaluation = Evaluation()
            evaluation[varA] = 42
            evaluation[varB] = 42
            abNotEqualConstrant.isSatisfied(evaluation) shouldBe false
        }

        "Setting both variables to different values satisfies the constraint" {
            val evaluation = Evaluation()
            evaluation[varA] = 42
            evaluation[varB] = 43
            abNotEqualConstrant.isSatisfied(evaluation) shouldBe true
        }

        "Setting only variable A satisfies the constraint" {
            val evaluation = Evaluation()
            evaluation[varA] = 42
            abNotEqualConstrant.isSatisfied(evaluation) shouldBe true
        }

        "Setting only variable B satisfies the constraint" {
            val evaluation = Evaluation()
            evaluation[varB] = 42
            abNotEqualConstrant.isSatisfied(evaluation) shouldBe true
        }

        "Setting an unrelated variable C to the same value as A satisfies the constraint" {
            val evaluation = Evaluation()
            evaluation[varA] = 42
            evaluation[varC] = 42
            abNotEqualConstrant.isSatisfied(evaluation) shouldBe true
        }

        "Setting an unrelated variable C to the same value as B satisfies the constraint" {
            val evaluation = Evaluation()
            evaluation[varB] = 42
            evaluation[varC] = 42
            abNotEqualConstrant.isSatisfied(evaluation) shouldBe true
        }
    }
})

class ValueInConstraintTests : FreeSpec({
    val variable = SimpleFiniteVariable(1, Domains.SUDOKU_DIGITS)
    "Constraint retraining value to be either 2, 3, 5, or 7" - {
        val valueMustBePrime = ValueInConstraint(variable, persistentHashSetOf(2,3,5,7))
        listOf(2,3,5,7).forEach {
            "Should accept $it as value" {
                val evaluation = Evaluation()
                evaluation[variable] = it
                valueMustBePrime.isSatisfied(evaluation) shouldBe true
            }
        }

        listOf(1,4,6,8,9).forEach {
            "Should reject $it as value" {
                val evaluation = Evaluation()
                evaluation[variable] = it
                valueMustBePrime.isSatisfied(evaluation) shouldBe false
            }
        }
    }

    "Constraint forcing variable to be specifically 5" - {
        val valueMustBeFive = givenValueConstraint(variable, 5)
        "Should accept 5 as value" {
            val evaluation = Evaluation()
            evaluation[variable] = 5
            valueMustBeFive.isSatisfied(evaluation) shouldBe true
        }

        listOf(1,2,3,4,6,7,8,9).forEach {
            "Should reject $it as value" {
                val evaluation = Evaluation()
                evaluation[variable] = it
                valueMustBeFive.isSatisfied(evaluation) shouldBe false
            }
        }
    }
})