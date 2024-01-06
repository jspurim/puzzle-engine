package com.github.jspurim.csp.model.core

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual

class VariableTests : FreeSpec({
    "SimpleVariable" - {
        "Should be equal to identically defined variable" {
            val var1 = SimpleVariable<Int>(1, typeDomain())
            val var2 = SimpleVariable<Int>(1, typeDomain())
            var1 shouldBeEqual var2
        }
    }
})