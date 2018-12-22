package fr.aoc

import java.lang.Integer.min
import java.util.*

class Day22 {

    fun part1(depth: Int, target: Point): Int {
        return -1
    }

    data class Point(val x: Int, val y: Int): Comparable<Point> {

        private val comparator = Comparator.comparingInt<Point> { it.y }.thenComparingInt { it.x }
        override fun compareTo(other: Point) = comparator.compare(this, other)
    }
}
