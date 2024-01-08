package com.github.jspurim.csp.model.core

/**
 * A [Variable] that can take any value withing a given [Domain].
 *
 * Implementations may define additional fields to identify a variable, but the variable [Domain] is always part of the
 * definition its identity, that is two variables that are identical except for their domains are different variables.
 *
 * This interface represents the variable itself, and not the value associated with that variable.
 * Such values are represented via [Evaluation]s which are maps from variables to concrete values.
 */
interface Variable<out D: Domain<T>, T> : StructurallyEquatable {
    val domain: D
}

/**
 * A [Variable] with a finite domain.
 */
typealias FiniteVariable<T> = Variable<FiniteDomain<T>, T>

/**
 * A [Variable] identified by a single id and its domain.
 */
data class SimpleVariable<T>(val variableId: Int, override val domain: Domain<T>) : Variable<Domain<T>, T>

/**
 * A [FiniteVariable] identified by a single id and its domain.
 */
data class SimpleFiniteVariable<T>(val variableId: Int, override val domain: FiniteDomain<T>) : FiniteVariable<T>

fun simpleIntVariable(id: Int) = SimpleVariable(id, typeDomain<Int>())
