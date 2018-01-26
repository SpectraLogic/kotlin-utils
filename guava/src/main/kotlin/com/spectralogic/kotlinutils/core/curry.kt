package com.spectralogic.kotlinutils.core
/*
 Composible curry function for reducing the arity of a given function
 */

fun <A,B> ((A) -> B).curry(a: A): B = this.invoke(a)

fun <A, B, C> ((A, B) -> C).curry(a: A): (B) -> C {
    return { b: B -> this.invoke(a, b) }
}

fun <A, B, C, D> ((A, B, C) -> D).curry(a: A): (B, C) -> D {
    return { b: B, c: C -> this.invoke(a, b, c) }
}

fun <A, B, C, D, E> ((A, B, C, D) -> E).curry(a: A): (B, C, D) -> E {
    return { b: B, c: C, d: D -> this.invoke(a, b, c, d) }
}
