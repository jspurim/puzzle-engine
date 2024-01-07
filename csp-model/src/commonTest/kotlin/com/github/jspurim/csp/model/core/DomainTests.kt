package com.github.jspurim.csp.model.core

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.sequences.shouldContainExactly
import io.kotest.matchers.shouldBe

class DomainTests : FreeSpec({

    "TypeDomain" - {
        "<Int>" - {
            val domain = typeDomain<Int>();
            listOf(0, 1, -1, 42, Int.MAX_VALUE, Int.MIN_VALUE).forEach {
                "Should accept $it as valid value" {
                    domain.isValidValue(it) shouldBe true
                }
            }
        }
        "<String>" - {
            val domain = typeDomain<String>();
            "Should accept \"EXAMPLE\" as valid value" {
                domain.isValidValue("EXAMPLE") shouldBe true
            }
        }
    }
    "SUDOKU_DIGIT" - {
        for (digit in 1..9) {
            "Should accept $digit as valid value" {
                Domains.SUDOKU_DIGITS.isValidValue(digit) shouldBe true
            }
        }
        listOf(0, 10, -1).forEach {
            "Should reject $it as valid value" {
                Domains.SUDOKU_DIGITS.isValidValue(it) shouldBe false
            }
        }
        "Should lists all valid values" {
            Domains.SUDOKU_DIGITS.values shouldContainExactly sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        }
    }

})