package com.spectralogic.kotlinutils.core

import io.kotlintest.properties.forAll
import io.kotlintest.specs.WordSpec

class ArgumentShiftTest : WordSpec() {
    init {
        "lArgShift" should {
            "be idempotent for a single argument" {
                forAll { a: Int ->
                    { b: Int -> b }.lArgShift().invoke(a) == a
                }
            }
            "Flip the arguments for a two argument function" {
                forAll { a: Float, b: Float ->
                    { c: Float, d: Float -> (c / d) }.lArgShift().invoke(b,a) == (a / b)
                }
            }
            "Shift the arguments one to the left for three arguments" {
                forAll { a: Float, b: Float, c: Float ->
                    {d: Float, e: Float, f:Float -> d/e/f}.lArgShift().invoke(b,c,a) == (a/b/c)
                }
            }
            "shift the arguments one to the left for four arguments" {
                forAll {a: Float, b: Float, c: Float, d: Float ->
                    {e: Float, f: Float, g: Float, h: Float -> e/f/g/h}.lArgShift().invoke(b,c,d,a) == a/b/c/d
                }
            }
            "Work with method references" {
                forAll {a: Double, b: Double ->
                   Math::pow.lArgShift().invoke(b,a) == Math.pow(a,b)
                }
            }
        }
        "rArgShift" should {
            "be idempotent for a single argument" {
                forAll { a: Int ->
                    { b: Int -> b }.rArgShift().invoke(a) == a
                }
            }
            "Flip the arguments for a two argument function" {
                forAll { a: Float, b: Float ->
                    { c: Float, d: Float -> (c / d) }.rArgShift().invoke(b,a) == (a / b)
                }
            }
            "Shift the arguments one to the left for three arguments" {
                forAll { a: Float, b: Float, c: Float ->
                    {d: Float, e: Float, f:Float -> d/e/f}.rArgShift().invoke(c,a,b) == (a/b/c)
                }
            }
            "shift the arguments one to the left for four arguments" {
                forAll {a: Float, b: Float, c: Float, d: Float ->
                    {e: Float, f: Float, g: Float, h: Float -> e/f/g/h}.rArgShift().invoke(d,a,b,c) == a/b/c/d
                }
            }
            "Work with method references" {
                forAll {a: Double, b: Double ->
                    Math::pow.rArgShift().invoke(b,a) == Math.pow(a,b)
                }
            }
        }
    }
}