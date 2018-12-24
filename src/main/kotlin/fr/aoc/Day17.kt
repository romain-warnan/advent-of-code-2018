package fr.aoc

import java.io.File
import java.util.*
import kotlin.streams.toList

class Day17 {

    private val hRegex = Regex("y=(\\d+), x=(\\d+)\\.\\.(\\d+)")
    private val vRegex = Regex("x=(\\d+), y=(\\d+)\\.\\.(\\d+)")


    val blocks = setOf('#', '|')
    var left = 0
    var right = 0
    var bottom = 0

    fun part1(path: String): Int {
        val spring = Point(500, 0)
        val clay = clay(path)
        val map = map(clay, spring)
        val water = mutableSetOf(Water(spring))
        var previousWaterSize = water.size
        var freshWater = water
        while (true) {
            val nextWater = freshWater
                .flatMap { it.move(map) }
                .toMutableSet()
            val updated = updateRestWater(map, water, nextWater)
            water.addAll(nextWater)
            freshWater = nextWater
            val waterSize = waterSize(map)
            if(waterSize <= previousWaterSize && !updated) {
                draw(map)
                return waterSize - 1
            }
            previousWaterSize = waterSize
        }
    }

    private fun waterSize(map: MutableList<MutableList<Char>>) = map.flatMap { it }.count { it == '|' || it == '~' }

    private fun map(clay: SortedSet<Point>, spring: Point): MutableList<MutableList<Char>> {
        val map = mutableListOf<MutableList<Char>>()
        left = clay.map { it.x }.min()!! - 1
        right = clay.map { it.x }.max()!! + 1
        bottom = clay.map { it.y }.max()!!
        for (y in 0..bottom) {
            val line = mutableListOf<Char>()
            for (x in left..right) {
                val point = Point(x, y)
                line += when (point) {
                    spring -> '|'
                    in clay -> '#'
                    else -> '.'
                }
            }
            map += line
        }
        return map
    }

    private fun draw(map: MutableList<MutableList<Char>>) {
        println()
        map.forEach {
            it.forEach { print(it) }
            println()
        }
    }

    private fun clay(path: String) = File(path).bufferedReader()
        .lines()
        .map { points(it) }
        .flatMap { it.stream() }
        .toList().toSortedSet()

    private fun updateRestWater(map: MutableList<MutableList<Char>>, water: MutableSet<Water>, nextWater: MutableSet<Water>): Boolean {
        var updated = false
        val waterPoints = water.map { it.point }
        for(waterPoint in waterPoints) {
            if(get(waterPoint, map) != '~') {
                val segment = waterPoint.segment(waterPoints)
                val leftPoint = segment.first().leftPoint()
                val rightPoint = segment.last().rightPoint()
                if(get(leftPoint, map) == '#' && get(rightPoint, map) == '#') {
                    updated = true
                    for (x in leftPoint.x + 1 until rightPoint.x) {
                        map[leftPoint.y][x - left] = '~'
                        if(map[leftPoint.y - 1][x - left] == '|')  nextWater += Water(Point(x, leftPoint.y - 1))
                        water.removeIf { it.point == Point(x, leftPoint.y) }
                    }
                }
            }
        }
        return updated
    }

    private fun points(line: String): List<Point> {
        if(hRegex.matches(line)) {
            val (y, x1, x2) = hRegex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
            return (x1..x2).map { Point(it, y) }.toList()
        }
        val (x, y1, y2) = vRegex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
        return (y1..y2).map { Point(x, it) }.toList()
    }

    fun get(point: Point, map: MutableList<MutableList<Char>>): Char {
        if(point.y !in 0..bottom || point.x !in left..right) return '.'
        return map[point.y][point.x - left]
    }

    fun set(point: Point, map: MutableList<MutableList<Char>>, value: Char) {
        if(point.y in 0..bottom && point.x in left..right) map[point.y][point.x - left] = value
    }

    data class Point(val x: Int, val y: Int): Comparable<Point> {

        private val comparator = Comparator.comparingInt<Point> { it.y }.thenComparingInt { it.x }
        override fun compareTo(other: Point) = comparator.compare(this, other)

        fun segment(points: List<Point>): List<Point> {
            val segment = mutableListOf(this)
            val sameLinePoints = points.filter { it.y == this.y }.sorted()
            var leftPoint = this.leftPoint()
            while (leftPoint in sameLinePoints) {
                segment += leftPoint
                leftPoint = leftPoint.leftPoint()
            }
            var rightPoint = this.rightPoint()
            while (rightPoint in sameLinePoints) {
                segment += rightPoint
                rightPoint = rightPoint.leftPoint()
            }
            return segment.sorted()
        }

        fun downPoint() = Point(x, y + 1)

        fun leftPoint() = Point(x - 1, y)

        fun rightPoint() = Point(x + 1, y)
    }

    inner class Water(val point: Point) {

        fun move(map: MutableList<MutableList<Char>>): Set<Water> {
            if (point.y < bottom) {
                val downPoint = point.downPoint()
                val leftPoint = point.leftPoint()
                val rightPoint = point.rightPoint()

                if(get(downPoint, map) != '#' && get(downPoint, map) != '~') {
                    set(downPoint, map, '|')
                    return setOf(Water(downPoint))
                }
                if(get(leftPoint, map) !in blocks && get(rightPoint, map) !in blocks) {
                    set(leftPoint, map, '|')
                    set(rightPoint, map, '|')
                    return setOf(Water(leftPoint), Water(rightPoint))
                }
                if(get(leftPoint, map) !in blocks && get(rightPoint, map) in blocks) {
                    set(leftPoint, map, '|')
                    return setOf(Water(leftPoint))
                }
                if(get(leftPoint, map) in blocks && get(rightPoint, map) !in blocks) {
                    set(rightPoint, map, '|')
                    return setOf(Water(rightPoint))
                }
            }
            return setOf()
        }

        override fun hashCode() = point.hashCode()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other?.javaClass != javaClass) return false
            other as Water
            return this.point == other.point
        }
    }
}
