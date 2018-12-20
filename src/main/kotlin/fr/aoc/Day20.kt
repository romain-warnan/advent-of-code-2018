package fr.aoc

import java.util.*

class Day20 {

    fun part1(regex: String): Int {
        val input = regex.removeSurrounding("^", "$")
        val rooms = rooms(input)
        return rooms.maxBy { it.value }!!.value
    }

    fun part2(regex: String): Int {
        val input = regex.removeSurrounding("^", "$")
        val rooms = rooms(input)
        return rooms.filter { it.value >= 1000 }.count()
    }

    private fun rooms(input: String): MutableMap<Point, Int> {
        var point = Point(0, 0)
        val rooms = mutableMapOf(point to 0)
        val crossings = LinkedList<Point>()
        for (c in input) {
            when (c) {
                '(' -> crossings.add(point)
                ')' -> crossings.removeLast()
                '|' -> point = crossings.peekLast()
                else -> {
                    val nextPoint = point.move(c)
                    rooms.getOrPut(nextPoint) { rooms[point]!! + 1 } // Cas des détours
                    point = nextPoint
                }
            }
        }
        return rooms
    }

    data class Point(val x: Int, val y: Int) {
        fun move(way: Char): Point = when (way) {
            'N' -> Point(x, y - 1)
            'E' -> Point(x - 1, y)
            'S' -> Point(x, y + 1)
            else -> Point(x + 1, y)
        }
    }
}
