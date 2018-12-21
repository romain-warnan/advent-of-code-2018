package fr.aoc

import java.io.File
import kotlin.streams.toList

class Day17 {

    private val hRegex = Regex("y=(\\d+), x=(\\d+)\\.\\.(\\d+)")
    private val vRegex = Regex("x=(\\d+), y=(\\d+)\\.\\.(\\d+)")


    fun part1(path: String): Int {
        val map = map(path)
        println(map)
        return -1
    }

    private fun map(path: String) = File(path).bufferedReader()
        .lines()
        .map { points(it) }
        .flatMap { it.stream() }
        .toList()
        .toList()

    private fun bottom(clay: List<Point>) = clay.map { it.y }.max()!!

    private fun updateRestWater(clay: List<Point>, restWater: MutableList<Point>, water: MutableList<Point>) {
        for(w in water) {
            val segment = w.segment(water)
            if(segment.first().leftPoint() in clay && segment.last().rightPoint() in clay) {
                restWater.addAll(segment)
            }
        }
        water.removeIf { it in restWater }
    }

    private fun points(line: String): List<Point> {
        if(hRegex.matches(line)) {
            val (y, x1, x2) = hRegex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
            return (x1..x2).map { Point(it, y) }.toList()
        }
        val (x, y1, y2) = vRegex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
        return (y1..y2).map { Point(x, it) }.toList()
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

    data class Water(val point: Point) {

        fun canMove(bottom: Int) = point.y < bottom


        fun move(clay: List<Point>, restWater: List<Point>, water: List<Point>): List<Water> {
            val downPoint = point.downPoint()
            val leftPoint = point.leftPoint()
            val rightPoint = point.rightPoint()

            if(downPoint !in clay && downPoint !in restWater) return setOf(Water(downPoint))
            if(leftPoint !in clay && rightPoint !in clay) return setOf(Water(leftPoint), Water(rightPoint))
            if(leftPoint !in clay && rightPoint in clay) return setOf(Water(leftPoint))
            if(leftPoint in clay && rightPoint !in clay) return setOf(Water(rightPoint))

            return setOf()
        }
    }
}
