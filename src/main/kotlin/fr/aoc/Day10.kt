package fr.aoc

import java.io.File
import kotlin.streams.toList

class Day10 {

    private val regex = Regex("position=<(-?\\d+),(-?\\d+)>velocity=<(-?\\d+),(-?\\d+)>")

    fun part1(path: String, result: String): String {
        val points = points(path)
        var running = true
        while (running) {
            val positions = points.map { position(it) }.toSet()
            running = !stop(positions)
            if(!running) printMessage(points, positions)
            points.forEach { it.move() }
        }
        return result
    }

    fun part2(path: String): Long {
        val points = points(path)
        var running = true
        var elapsed = 0L
        while (running) {
            elapsed ++
            val positions = points.map { position(it) }.toSet()
            running = !stop(positions)
            points.forEach { it.move() }
        }
        return elapsed - 1
    }

    private fun printMessage(points: List<Point>, positions: Set<Position>) {
        val (minX, maxX, minY, maxY) = frame(points)
        for (y in minY..maxY) {
            for (x in minX..maxX) {
                if (Position(x, y) in positions) print("#")
                else print(" ")
            }
            println()
        }
    }

    private fun points(path: String) = File(path)
        .bufferedReader()
        .lines()
        .map { point(it) }
        .sorted()
        .toList()

    private fun point(line: String): Point {
        val lineWithoutSpaces = line.replace(" ", "")
        val (positionX, positionY, velocityX, velocityY) = regex.matchEntire(lineWithoutSpaces)!!.destructured
        return Point(positionX.toInt(), positionY.toInt(), velocityX.toInt(), velocityY.toInt())
    }

    private fun stop(positions: Set<Position>) = positions
        .groupBy { it.x }
        .map { it.value }
        .filter { it.size == 8 }
        .filter { touchesEachOther(it) }
        .count() >= 3

    private fun touchesEachOther(positions: List<Position>): Boolean {
        val max = positions.map { it.y }.max()!!
        val min = positions.map { it.y }.min()!!
        return (1 + max - min) == positions.size
    }

    private fun minX(points: Collection<Point>) = points.map { it.positionX }.min()!!
    private fun maxX(points: Collection<Point>) = points.map { it.positionX }.max()!!
    private fun minY(points: Collection<Point>) = points.map { it.positionY }.min()!!
    private fun maxY(points: Collection<Point>) = points.map { it.positionY }.max()!!

    private fun frame(points: Collection<Point>) = Frame(minX(points), maxX(points), minY(points), maxY(points))

    private fun position(point: Point) = Position(point.positionX, point.positionY)

    data class Frame(val minX: Int, val maxX: Int, val minY: Int, val maxY: Int)

    data class Position(val x: Int, val y: Int)

    class Point(var positionX: Int, var positionY: Int, val velocityX: Int, val velocityY: Int): Comparable<Point> {

        fun move() {
            positionX += velocityX
            positionY += velocityY
        }

        private val comparator = Comparator.comparingInt<Point> { it.positionY }.thenComparingInt { it.positionX }

        override fun compareTo(other: Point) = comparator.compare(this, other)
    }
}
