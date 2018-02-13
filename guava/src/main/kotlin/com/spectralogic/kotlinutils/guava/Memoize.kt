package com.spectralogic.kotlinutils.guava

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder

/*
* Memoization allows the creation of  a lambda that caches each invocation
* This allows for cases where calls are expensive but are likely to be frequently repeated
 */
private fun <A, B> defaultCache(): Cache<A, B> =
        CacheBuilder.newBuilder().maximumSize(1000L).build()

/**
 * A function with no input is trivial to memoize
 *
 * @param A the return type of the function.
 * @return () -> A
 */
fun <A> (() -> A).memoize(): () -> A {
    val results = this.invoke()
    return object : () -> A {
        override fun invoke(): A = results
    }
}

/**
 * Memoize a single argument function
 *
 * @param A the type of the first parameter to the lambda.
 * @param B the return type of the lambda.
 * @property cache Optionally a Guava Cache<A,B> can change the default caching behavior.
 * @see Cache
 * @see CacheBuilder
 * @return (A) -> B
 */
fun <A, B> ((A) -> B).memoize(cache: Cache<A, B> = defaultCache()): (A) -> B {
    val lambda = this
    return object : (A) -> B {
        override fun invoke(a: A): B {
            return cache.get(a, { lambda.invoke(a) })
        }
    }
}

/**
 * Memoize a two argument function
 *
 * @param A the type of the first argument.
 * @param B the type of the second argument.
 * @param C the return type of the function.
 * @property cache optionally a Guava Cache<Pair<A,B>,C>
 * @see Cache
 * @see CacheBuilder
 * @see Pair
 * @return (A,B) -> C
 */
fun <A, B, C> ((A, B) -> C).memoize(cache: Cache<Pair<A, B>, C> = defaultCache()): (A, B) -> C {
    val lambda = this
    return object : (A, B) -> C {
        override fun invoke(a: A, b: B): C {
            return cache.get(Pair(a, b), { lambda.invoke(a, b) })
        }
    }
}

/**
 * Memoize a three argument function
 *
 * @param A the type of the first argument.
 * @param B the type of the second argument.
 * @param C the type of the third argument.
 * @param D the return type of the function.
 * @property cache optionally a Guava Cache<Triple<A,B,C>,D>.
 *
 */
fun <A, B, C, D> ((A, B, C) -> D).memoize(cache: Cache<Triple<A, B, C>, D> = defaultCache()): (A, B, C) -> D {
    val lambda = this
    return object : (A, B, C) -> D {
        override fun invoke(a: A, b: B, c: C): D {
            return cache.get(Triple(a, b, c), { lambda.invoke(a, b, c) })
        }
    }
}