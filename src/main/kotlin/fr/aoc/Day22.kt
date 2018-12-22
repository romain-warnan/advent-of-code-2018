package fr.aoc

import java.util.*

class Day22 {
    fun part1(depth: Int, target: Point): Int {
        val mouth = Point(0, 0)
        val cave = cave(mouth, target, depth)
        return cave.values
            .map { erosionLevel(it, depth) }
            .map { regionType(it) }
            .map { riskLevel(it) }
            .sum()
    }

    fun part2(depth: Int, target: Point): Int {
        val mouth = Point(0, 0)
        val cave = largeCave(mouth, target, depth)
        val regions = mutableMapOf<Point, Char>()
        for(entry in cave) {
            regions[entry.key] = when(regionType(erosionLevel(entry.value, depth))) {
                Type.ROCKY -> '.'
                Type.WET -> '='
                Type.NARROW -> '|'
            }
        }
        for(y in 0..2 * target.y) {
            println()
            for(x in 0..2 * target.x) {
                print(regions[Point(x, y)])
            }
        }
        return 0
    }

    private fun cave(mouth: Point, target: Point, depth: Int): Map<Point, Int> {
        val cave = mutableMapOf(mouth to 0, target to 0)
        for (x in 0..target.x) {
            for (y in 0..target.y) {
                val point = Point(x, y)
                cave[point] = geologicIndex(point, depth, mouth, target, cave)
            }
        }
        return cave
    }

    private fun largeCave(mouth: Point, target: Point, depth: Int): Map<Point, Int> {
        val cave = mutableMapOf(mouth to 0, target to 0)
        for (x in 0..2 * target.x) {
            for (y in 0..2 * target.y) {
                val point = Point(x, y)
                cave[point] = geologicIndex(point, depth, mouth, target, cave)
            }
        }
        return cave
    }

    private fun geologicIndex(point: Point, depth: Int, mouth: Point, target: Point, cave: MutableMap<Point, Int>): Int {
        if(point == mouth || point == target) return 0
        if(point.y == 0) {
            val geologicIndex = point.x * 16807
            cave[point] = geologicIndex
            return geologicIndex
        }
        if(point.x == 0) {
            val geologicIndex = point.y * 48271
            cave[point] = geologicIndex
            return geologicIndex
        }

        val leftPoint = Point(point.x - 1, point.y)
        val upPoint = Point(point.x, point.y - 1)
        val a = cave.getOrElse(leftPoint) { geologicIndex(leftPoint, depth, mouth, target, cave) }
        val b = cave.getOrElse(upPoint) { geologicIndex(upPoint, depth, mouth, target, cave) }
        val geologicIndex = erosionLevel(a, depth) * erosionLevel(b, depth)
        cave[point] = geologicIndex
        return geologicIndex
    }

    private fun erosionLevel(geologicIndex: Int, depth: Int) = (geologicIndex + depth) % 20183

    private fun regionType(erosionLevel: Int) = when(erosionLevel % 3) {
        0 -> Type.ROCKY
        1 -> Type.WET
        else -> Type.NARROW
    }

    private fun riskLevel(regionType: Type) = when(regionType) {
        Type.ROCKY -> 0
        Type.WET -> 1
        Type.NARROW -> 2
    }

    data class Point(val x: Int, val y: Int): Comparable<Point> {

        private val comparator = Comparator.comparingInt<Point> { it.y }.thenComparingInt { it.x }
        override fun compareTo(other: Point) = comparator.compare(this, other)
    }

    enum class Type { ROCKY, WET, NARROW }
}
