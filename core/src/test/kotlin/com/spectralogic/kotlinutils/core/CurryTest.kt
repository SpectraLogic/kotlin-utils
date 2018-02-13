package com.spectralogic.kotlinutils.core

import io.kotlintest.properties.forAll
import io.kotlintest.specs.WordSpec


class CurryTest : WordSpec() {
    init {
        "curry" should {
            "create a single argument function" {
                forAll { a: Int, b: Int ->
                    { c: Int, d: Int -> c + d }.curry(a).invoke(b) == (a + b)
                }
            }
            "create a two argument function" {
                forAll { z: Int, y: Int, x: Int ->
                    { a: Int, b: Int, c: Int -> a + b + c }.curry(z).invoke(y, x) == (z + y + x)
                }
            }
            "create a three argument function" {
                forAll { z: Int, y: Int, x: Int, w: Int ->
                    { a: Int, b: Int, c: Int, d: Int -> a + b + c + d }.curry(z)
                            .invoke(y, x, w) == (z + y + x + w)
                }
            }
            "be composable" {
                forAll { z: Int, y: Int, x: Int, w: Int ->
                    { a: Int, b: Int, c: Int, d: Int -> a + b + c + d }.curry(x)
                            .curry(y)
                            .curry(z)
                            .curry(w).invoke() == (z + y + x + w)
                }
            }
        }
    }
}