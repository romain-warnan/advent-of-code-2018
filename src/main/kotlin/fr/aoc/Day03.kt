package fr.aoc

import java.io.File
import kotlin.streams.toList

class Day03 {

    private val regex = Regex("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)")

    fun part1(path: String): Int {
        val claims = claims(path)
        val inches = mutableSetOf<Claim.Point>()
        for (i in claims.indices) {
            for (j in i + 1 until claims.size) {
                val commonPoints = claims[i].commonPoints(claims[j])
                inches.addAll(commonPoints)
            }
        }
        return inches.size
    }

    fun part2(path: String): Int {
        val claims = claims(path)
        for (i in claims.indices) {
            for (j in claims.indices) {
                if(claims[i].overlaps(claims[j])) break;
                if(j == claims.lastIndex) return claims[i].id
            }
        }
        return -1
    }

    private fun claims(path: String) = File(path).bufferedReader()
        .lines()
        .map { claim(it) }
        .toList()

    private fun claim(string: String): Claim {
        val (id, left, top, width, height) = regex.matchEntire(string)!!.destructured
        return Claim(id.toInt(), left.toInt(), top.toInt(), width.toInt(), height.toInt())
    }
}

data class Claim(val id: Int, val left: Int, val top: Int, val width: Int, val height: Int) {

    private fun points(): Set<Point> {
        val points = mutableSetOf<Point>()
        for (x in left until left + width) {
            for (y in top until top + height) {
                points += Point(x, y)
            }
        }
        return points
    }

    fun commonPoints(other: Claim) = this.points().intersect(other.points()).toSet()

    fun overlaps(other: Claim) = this.id != other.id && commonPoints(other).isNotEmpty()

    data class Point(val x: Int, val y: Int)
}

