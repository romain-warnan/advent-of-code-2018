package fr.aoc

import java.lang.Integer.min
import java.util.*

class Day22 {
    /*
    The region at 0,0 (the mouth of the cave) has a geologic index of 0.
    The region at the coordinates of the target has a geologic index of 0.
    If the region's Y coordinate is 0, the geologic index is its X coordinate times 16807.
    If the region's X coordinate is 0, the geologic index is its Y coordinate times 48271.
    Otherwise, the region's geologic index is the result of multiplying the erosion levels of the regions at X-1,Y and X,Y-1.


    If the erosion level modulo 3 is 0, the region's type is rocky.
    If the erosion level modulo 3 is 1, the region's type is wet.
    If the erosion level modulo 3 is 2, the region's type is narrow.
    */

    fun part1(depth: Int, target: Point): Int {
        val mouth = Point(0, 0)
        val cave = mapOf(mouth to 0)

        return -1
    }

    private fun geologicIndex(point: Point, depth: Int, target: Point, cave: Map<Point, Int>) {

    }

    /*
        A region's erosion level is its geologic index plus the cave system's depth, all modulo 20183
    */
    private fun erosionLevel(geologicIndex: Int, depth: Int) = (geologicIndex + depth) / 20183

    data class Point(val x: Int, val y: Int): Comparable<Point> {

        private val comparator = Comparator.comparingInt<Point> { it.y }.thenComparingInt { it.x }
        override fun compareTo(other: Point) = comparator.compare(this, other)
    }
}
