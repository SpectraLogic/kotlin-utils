package com.spectralogic.kotlinutils.core

/**
 * Applies a left argument shift to a single argument function.
 * This is included only for completeness.
 *
 * @param A the type of the function input.
 * @param B the type of the function output.
 * @return (A) -> B
 */
fun <A, B> ((A) -> B).lArgShift(): ((A) -> B) = { a: A -> this.invoke(a) }

/**
 * Applies a right argument shift to a single argument function.
 * This is included only for completeness.
 *
 * @param A the type of the function input.
 * @param B the type of the function output.
 * @return (A) -> B
 */
fun <A, B> ((A) -> B).rArgShift(): ((A) -> B) = { a: A -> this.invoke(a) }

/**
 * Applies a left argument shift to a two argument function.
 * For a function with two arguments this amounts to reversing the arguments.
 *
 * @param A first type of function input.
 * @param B second type of function input.
 * @param C the type of the function output.
 * @return (B,A) -> C
 */
fun <A, B, C> ((A, B) -> C).lArgShift(): (B, A) -> C = { b: B, a: A -> this.invoke(a, b) }

/**
 * Applies a right argument shift to a two argument function.
 * For a function with two arguments this amounts to reversing the arguments.
 *
 * @param A first type of function input.
 * @param B second type of function input.
 * @param C the type of the function output.
 * @return (B,A) -> C
 */
fun <A, B, C> ((A, B) -> C).rArgShift(): (B, A) -> C = { b: B, a: A -> this.invoke(a, b) }

/**
 * Applies a left argument shift to a function with three arguments
 * @param A first type of function input.
 * @param B second type of function input.
 * @param C third type of function input.
 * @param D type of function return.
 * @return (B,C,A) -> D
 */
fun <A, B, C, D> ((A, B, C) -> D).lArgShift(): (B, C, A) -> D = { b: B, c: C, a: A -> this.invoke(a, b, c) }

/**
 * Applies a right argument shift to a function with three arguments
 * @param A first type of function input.
 * @param B second type of function input.
 * @param C third type of function input.
 * @param D type of function return.
 * @return (C,A,B) -> D
 */
fun <A, B, C, D> ((A, B, C) -> D).rArgShift(): (C, A, B) -> D = { c: C, a: A, b: B -> this.invoke(a, b, c) }

/**
 * Applies a left argument shift to a function with four arguments
 * @param A first type of function input.
 * @param B second type of function input.
 * @param C third type of function input.
 * @param D fourth type of function input.
 * @param E type of function return
 * @return (B,C,D,A) -> E
 */
fun <A, B, C, D, E> ((A, B, C, D) -> E).lArgShift(): (B, C, D, A) -> E = { b: B, c: C, d: D, a: A -> this.invoke(a, b, c, d) }

/**
 * Applies a right argument shift to a function with four arguments
 * @param A first type of function input.
 * @param B second type of function input.
 * @param C third type of function input.
 * @param D fourth type of function input.
 * @param E type of function return
 * @return (D,A,B,C) -> E
 */
fun <A, B, C, D, E> ((A, B, C, D) -> E).rArgShift(): (D, A, B, C) -> E = { d: D, a: A, b: B, c: C -> this.invoke(a, b, c, d) }


