package com.github.jspurim.csp.model.core

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlin.reflect.KClass

/**
 * A set of possible values a variable can take.
 */
interface Domain<in T> : StructurallyEquatable {
    /**
     * Indicates whether the provided value is valid for this domain.
     */
    fun isValidValue(value: T): Boolean
}

/**
 * A [Domain] with a finite set of possible values.
 */
interface FiniteDomain<T> : Domain<T> {
    val values: Sequence<T>
    override fun isValidValue(value: T) = values.contains(value)
}

/**
 * A [Domain] that contains every valid value of a given generic type parameter.
 *
 * Notice that depending on the value of [T] some of these may actually be finite domains.
 *
 */
data class TypeDomain<T : Any>(val clazz: KClass<T>) : Domain<T> {
    override fun isValidValue(value: T) = true
}

inline fun <reified T : Any> typeDomain(): TypeDomain<T> = TypeDomain(T::class)

/**
 * A [FiniteDomain] that is defined by am explicit set of valid values.
 */
data class ExplicitValuesDomain<T>(val validValues: ImmutableList<T>) : FiniteDomain<T> {
    override val values = validValues.asSequence()
}

object Domains {
    val SUDOKU_DIGITS = ExplicitValuesDomain(persistentListOf(1, 2, 3, 4, 5, 6, 7, 8, 9))
    val BINARY_DIGITS = ExplicitValuesDomain(persistentListOf(0, 1))
}