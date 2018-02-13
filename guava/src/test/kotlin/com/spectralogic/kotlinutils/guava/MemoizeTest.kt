package com.spectralogic.kotlinutils.guava

import io.kotlintest.eventually
import io.kotlintest.matchers.shouldBe
import io.kotlintest.seconds
import io.kotlintest.specs.WordSpec

class MemoizeTest : WordSpec() {
    private val lambda3: (Int, Int, Int) -> Int = { a: Int, b: Int, c: Int -> a * b * c }.memoize()
    private val lambda2: (Int, Int) -> Int = { a: Int, b: Int -> a + b }.memoize()
    private val lambda1: (Int) -> Int = { b: Int -> Thread.sleep(1000); b + 15 }.memoize()
    private val lambda0: () -> Int = { 100 * 100 * 100 }.memoize()

    init {
        "memoise" should {
            "return the evaluated value" {
                lambda1.invoke(1) shouldBe 16
            }
            "only execute the same code once" {
                eventually(2.seconds) {
                    (1..10).forEach { lambda1.invoke(1) }
                }
            }
            "return a single value" {
                lambda0.invoke() shouldBe 1000000
            }
            "work with an arity of two" {
                lambda2.invoke(1, 2) shouldBe 3
            }

            "work with an arity of three" {
                lambda3.invoke(3, 2, 1) shouldBe 6
            }
        }
    }
}