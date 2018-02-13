package com.spectralogic.kotlinutils.core

import io.kotlintest.properties.forAll
import io.kotlintest.specs.WordSpec

class ArgumentShiftTest : WordSpec() {
    init {
        "lArgShift" should {
            "Flip the arguments for a two argument function" {
                forAll { a: Float, b: Float ->
                    { c: Float, d: Float -> (c / d) }.leftArgShift().invoke(b,a) == (a / b)
                }
            }
            "Shift the arguments one to the left for three arguments" {
                forAll { a: Float, b: Float, c: Float ->
                    {d: Float, e: Float, f:Float -> d/e/f}.leftArgShift().invoke(b,c,a) == (a/b/c)
                }
            }
            "shift the arguments one to the left for four arguments" {
                forAll {a: Float, b: Float, c: Float, d: Float ->
                    {e: Float, f: Float, g: Float, h: Float -> e/f/g/h}.leftArgShift().invoke(b,c,d,a) == a/b/c/d
                }
            }
            "Work with method references" {
                forAll {a: Double, b: Double ->
                   Math::pow.leftArgShift().invoke(b,a) == Math.pow(a,b)
                }
            }
        }
        "rArgShift" should {
            "Flip the arguments for a two argument function" {
                forAll { a: Float, b: Float ->
                    { c: Float, d: Float -> (c / d) }.rightArgShift().invoke(b,a) == (a / b)
                }
            }
            "Shift the arguments one to the left for three arguments" {
                forAll { a: Float, b: Float, c: Float ->
                    {d: Float, e: Float, f:Float -> d/e/f}.rightArgShift().invoke(c,a,b) == (a/b/c)
                }
            }
            "shift the arguments one to the left for four arguments" {
                forAll {a: Float, b: Float, c: Float, d: Float ->
                    {e: Float, f: Float, g: Float, h: Float -> e/f/g/h}.rightArgShift().invoke(d,a,b,c) == a/b/c/d
                }
            }
            "Work with method references" {
                forAll {a: Double, b: Double ->
                    Math::pow.rightArgShift().invoke(b,a) == Math.pow(a,b)
                }
            }
        }
    }
}