package fr.aoc

import java.io.File
import kotlin.math.abs

class Day23 {

    private val regex = Regex("pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(\\d+)")

    fun part1(path: String): Int {
        val bots = bots(path)
        val strongestBot = bots.last()
        return bots.count { manhattan(strongestBot, it) <= strongestBot.radius }
    }

    fun part2(path: String): Int {
        val bots = bots(path)
        val strongestBot = bots.last()
        return bots.count { manhattan(strongestBot, it) <= strongestBot.radius }
    }

    private fun bots(path: String) = File(path).readLines().map { bot(it) }.sorted()

    private fun bot(line: String): Bot {
        val (x, y, z, radius) = regex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
        return Bot(x, y, z, radius)
    }

    private fun manhattan(a: Bot, b: Bot) = abs(a.x - b.x) + abs(a.y - b.y) + abs(a.z - b.z)

    data class Bot(val x: Int, val y: Int, val z: Int, val radius: Int): Comparable<Bot> {
        override fun compareTo(other: Bot) = this.radius - other.radius
    }
}
