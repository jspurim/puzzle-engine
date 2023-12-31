package com.github.jspurim.csp.model.core

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class DomainTests : FreeSpec({

    "TypeDomain" - {
        "<Int>" - {
            val domain = typeDomain<Int>();
            listOf(0,1,-1,42,Int.MAX_VALUE, Int.MIN_VALUE).forEach {
                "Should accept $it as valid value" {
                    domain.isValidValue(it) shouldBe true
                }
            }
        }
    }

})