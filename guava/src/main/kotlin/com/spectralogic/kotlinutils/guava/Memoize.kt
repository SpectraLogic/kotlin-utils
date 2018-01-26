package com.spectralogic.kotlinutils.guava

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder

fun <A> (() -> A).memoize(memoSize: Long = 1000L): () -> A {
    val results = this.invoke()
    return object : () -> A {
        override fun invoke(): A = results
    }
}

fun <A, B> ((A) -> B).memoize(memoSize: Long = 1000L): (A) -> B {
    val lambda = this
    return object : (A) -> B {
        private val cache: Cache<A, B> = CacheBuilder.newBuilder().maximumSize(memoSize).build()
        override fun invoke(a: A): B {
            return cache.get(a, { lambda.invoke(a) })
        }
    }
}

fun <A, B, C> ((A, B) -> C).memoize(memoSize: Long = 1000L): (A, B) -> C {
    val lambda = this
    return object : (A, B) -> C {
        private val cache: Cache<Pair<A, B>, C> = CacheBuilder.newBuilder().maximumSize(memoSize).build()
        override fun invoke(a: A, b: B): C {
            return cache.get(Pair(a, b), { lambda.invoke(a, b) })
        }
    }
}

fun <A, B, C, D> ((A, B, C) -> D).memoize(memoSize: Long = 1000L): (A, B, C) -> D {
    val lambda = this
    return object : (A, B, C) -> D {
        private val cache: Cache<Triple<A, B, C>, D> = CacheBuilder.newBuilder().maximumSize(memoSize).build()
        override fun invoke(a: A, b: B, c: C): D {
            return cache.get(Triple(a, b, c), { lambda.invoke(a, b, c) })
        }
    }
}