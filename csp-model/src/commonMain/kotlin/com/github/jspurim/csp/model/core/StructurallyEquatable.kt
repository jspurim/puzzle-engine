package com.github.jspurim.csp.model.core

/**
 * This interface forces implementations to provide explicit [Any.equals] and [Any.hashCode].
 *
 * In practice the easiest way of satisfying this requirement is to have implementations be data classes.
 */
interface StructurallyEquatable {
    override operator fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}