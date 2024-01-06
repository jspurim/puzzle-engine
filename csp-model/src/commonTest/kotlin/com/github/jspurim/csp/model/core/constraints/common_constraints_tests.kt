package com.github.jspurim.csp.model.core.constraints

import com.github.jspurim.csp.model.core.Evaluation
import com.github.jspurim.csp.model.core.Variable
import com.github.jspurim.csp.model.core.constrains.NotEqualConstraint
import com.github.jspurim.csp.model.core.simpleIntVariable
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class NotEqualConstraintTests : FreeSpec({

    "A not equal to B constriant:" - {
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
