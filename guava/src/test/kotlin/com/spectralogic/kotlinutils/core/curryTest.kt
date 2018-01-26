package com.spectralogic.kotlinutils.core

import io.kotlintest.specs.FlatSpec

class CurryTest : FlatSpec() {
    init {
        "curry" should "create a single argument function" {
            {a:Int, b:Int -> a + b}.curry(1).invoke(2) shouldBe 3
        }
    }

    init {
        "curry" should "create a two argument function" {
            {a:Int, b:Int, c:Int -> a + b + c}.curry(1).invoke(2,3) shouldBe 6
        }
    }

    init {
        "curry" should "create a three argument function" {
            {a:Int, b:Int, c:Int, d:Int -> a + b + c + d}.curry(1)
                    .invoke(2,3,4) shouldBe 10
        }
    }

    init {
        "curry" should "be composible" {
            {a:Int, b:Int, c:Int, d:Int -> a + b + c + d}.curry(1)
                    .curry(2)
                    .curry(3)
                    .curry(4) shouldBe 10
        }
    }
}