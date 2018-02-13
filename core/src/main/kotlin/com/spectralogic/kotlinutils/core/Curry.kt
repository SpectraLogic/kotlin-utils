package com.spectralogic.kotlinutils.core
/*
 Composable curry function for reducing the arity of a given function.
 */

/**
 * Take an arity 1 lambda and return a corresponding arity 0 lambda
 *
 * @param A the input of the lambda.
 * @param B the output of the original lambda and the new lambda.
 * @property a the first argument to the lambda to be curried.
 * @return () -> B
 */
fun <A,B> ((A) -> B).curry(a:A): () -> B = { this.invoke(a)}

/**
 * Take an arity 2 lambda and return a corresponding arity 1 lambda.
 *
 * @param A the first input of the lambda.
 * @param B the second input of the lambda.
 * @param C the return type of the lambda to be curried.
 * @property a input of the first argument for the lambda.
 * @return (B) -> C
 */
fun <A, B, C> ((A, B) -> C).curry(a: A): (B) -> C {
    return { b: B -> this.invoke(a, b) }
}

/**
 * Take an arity 3 lambda and return a corresponding arity 2 lambda.
 *
 * @param A the first input of the lambda.
 * @param B the second input of the lambda.
 * @param C the third input of the lambda.
 * @property a input of the first type into the lambda.
 * @return (B,C) -> D
 */
fun <A, B, C, D> ((A, B, C) -> D).curry(a: A): (B, C) -> D {
    return { b: B, c: C -> this.invoke(a, b, c) }
}

/**
 * Take an arity 4 lambda and return a corresponding arity 3 lambda
 *
 * @param A the first input of the lambda.
 * @param B the second input of the lambda.
 * @param C the third input of the lambda.
 * @param D the fourth input of the lambda.
 * @param E the output of the lambda.
 * @property a input of the first type into the lambda.
 * @return (B,C,D) -> E
 */
fun <A, B, C, D, E> ((A, B, C, D) -> E).curry(a: A): (B, C, D) -> E {
    return { b: B, c: C, d: D -> this.invoke(a, b, c, d) }
}
