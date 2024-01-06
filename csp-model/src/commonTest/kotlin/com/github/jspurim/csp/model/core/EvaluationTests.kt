package com.github.jspurim.csp.model.core

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe


private val intVariable_1 = SimpleVariable(1, typeDomain<Int>())
private val duplicateIntVariable_1 = SimpleVariable(1, typeDomain<Int>())
private val intVariable_2 = SimpleVariable(2, typeDomain<Int>())
private val charVariable_1 = SimpleVariable(1, typeDomain<Char>())
private val sudokuVariable_1 = SimpleVariable(2, Domains.SUDOKU_DIGITS)


class EvaluationTests : FreeSpec({

    "Empty evaluation should return null when fetching variable" {
        val emptyEvaluation = Evaluation()
        emptyEvaluation[intVariable_1] shouldBe null
    }

    "Evaluation should allow setting and retrieving the values" - {
        "for a  single variable" {
            val evaluation = Evaluation()
            evaluation[intVariable_1] = 42
            evaluation[intVariable_1] shouldBe 42
        }

        "for a  single variable, retrieving via a different but equal variable" {
            val evaluation = Evaluation()
            evaluation[intVariable_1] = 42
            evaluation[duplicateIntVariable_1] shouldBe 42
        }


        "for a  multiple variables of the same type" {
            val evaluation = Evaluation()
            evaluation[intVariable_1] = 42
            evaluation[intVariable_2] = 43
            evaluation[intVariable_1] shouldBe 42
            evaluation[intVariable_2] shouldBe 43
        }

        "for a  multiple variables of different types, with same ids" {
            val evaluation = Evaluation()
            evaluation[intVariable_1] = 42
            evaluation[charVariable_1] = 'A'
            evaluation[intVariable_1] shouldBe 42
            evaluation[charVariable_1] shouldBe 'A'
        }
    }

    "Evaluation should allow overriding the value of a variable" -{
        "through the same variable instance" {
            val evaluation = Evaluation()
            evaluation[intVariable_1] = 41
            evaluation[intVariable_1] = 42
            evaluation[intVariable_1] shouldBe 42
        }
        "through an equivalent variable instance" {
            val evaluation = Evaluation()
            evaluation[intVariable_1] = 41
            evaluation[duplicateIntVariable_1] = 42
            evaluation[intVariable_1] shouldBe 42
        }
    }

    "Evaluation should throw when attempting to set values outside the variable domain, for example" -{
        "for as sudoku digit variable" - {
            listOf(0,10,-1).forEach {
                "should throw when setting the value to $it" {
                    shouldThrow<IllegalArgumentException> {
                        val evaluation = Evaluation()
                        evaluation[sudokuVariable_1] = it
                        return@shouldThrow Unit
                    }
                }
            }
        }
    }


})