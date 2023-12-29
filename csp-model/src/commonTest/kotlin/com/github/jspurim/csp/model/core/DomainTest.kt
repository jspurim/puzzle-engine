package com.github.jspurim.csp.model.core


import kotlin.test.assertTrue
import kotlin.test.Test


class TypeDomainTests{

    @Test
    fun `TypeDomain(Int) accepts any integer as value`() {
        val domain = typeDomain<Int>()
        listOf(0,1,42, Int.MAX_VALUE, -1, Int.MIN_VALUE).forEach {
            assertTrue { domain.isValidValue(it) }
        }
    }
}