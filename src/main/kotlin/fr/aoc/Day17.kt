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
        .toSet()

    fun bottom(clay: Set<Point>) = clay.map { it.y }.max()!!

    private fun points(line: String): Set<Point> {
        if(hRegex.matches(line)) {
            val (y, x1, x2) = hRegex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
            return (x1..x2).map { Point(it, y) }.toSet()
        }
        val (x, y1, y2) = vRegex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
        return (y1..y2).map { Point(x, it) }.toSet()
    }

    data class Point(val x: Int, val y: Int) {
        var type = '.'

        fun canMove(bottom: Int) = y < bottom
    }
}
