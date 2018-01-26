package com.spectralogic.kotlinutils.guava

import io.kotlintest.Eventually
import io.kotlintest.specs.FlatSpec
import java.util.concurrent.TimeUnit

class MemoizeTest : FlatSpec(), Eventually {
    private val lambda3: (Int, Int, Int) -> Int = { a: Int, b: Int, c: Int -> a * b * c }.memoize()
    private val lambda2: (Int, Int) -> Int = { a: Int, b: Int -> a + b }.memoize()
    private val lambda1: (Int) -> Int = { b: Int -> Thread.sleep(1000); b + 15 }.memoize()
    private val lambda0: () -> Int = { 100 * 100 * 100 }.memoize()

    init {
        "lambda.memoize" should "return the evaluated value" {
            lambda1.invoke(1) shouldBe 16
        }

    }

    init {
        "lambda.memoize" should "only execute the same code once" {
            eventually(2, TimeUnit.SECONDS) {
                (1..10).forEach { lambda1.invoke(1) }
            }
        }
    }

    init {
        "lambda.memoize" should "return a single value" {
            lambda0.invoke() shouldBe 1000000
        }
    }

    init {
        "lambda.memoize" should "work with an arity of two" {
            lambda2.invoke(1, 2) shouldBe 3
        }
    }

    init {
        "lambda.memoize" should "work with an arity of three" {
            lambda3.invoke(3,2,1) shouldBe 6
        }
    }


}