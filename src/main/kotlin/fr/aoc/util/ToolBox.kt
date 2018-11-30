package fr.aoc.util

import java.math.BigInteger
import java.util.*


fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) +  start

fun ClosedRange<BigInteger>.surrounds(range: ClosedRange<BigInteger>) = range.start >= this.start && range.endInclusive <= this.endInclusive

fun ClosedRange<BigInteger>.touches(range: ClosedRange<BigInteger>) = range.start <= this.endInclusive

class Memoize<in T, out R>(private val f: (T) -> R) : (T) -> R {
    private val values = mutableMapOf<T, R>()
    override fun invoke(x: T): R {
        return values.getOrPut(x, { f(x) })
    }
}

fun <T, R> ((T) -> R).memoize(): (T) -> R = Memoize(this)