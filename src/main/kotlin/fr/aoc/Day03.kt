package fr.aoc

import java.io.File
import kotlin.streams.toList
import java.util.Optional
import kotlin.math.min

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
        return Claim(id.toInt(), Segment(top.toInt(), height.toInt()), Segment(left.toInt(), width.toInt()))
    }

    data class Claim(val id: Int, private val height: Segment , private val width: Segment) {

        private fun intersection(other: Claim): Optional<Claim> {
            val intersectionHeight = this.height.intersection(other.height)
            val intersectionWidth = this.width.intersection(other.width)
            return if (intersectionHeight.isPresent && intersectionWidth.isPresent) {
                Optional.of(Claim(0, intersectionHeight.get(), intersectionWidth.get()))
            } else {
                Optional.empty()
            }
        }

        private fun points(): Set<Point> {
            val points = mutableSetOf<Point>()
            for (j in 0 until height.length) {
                for (i in 0 until width.length) {
                    points.add(Point(width.start + i, height.start + j))
                }
            }
            return points
        }

        fun commonPoints(other: Claim): Set<Point> = this.intersection(other)
            .map{ it.points() }
            .orElse(setOf())

        fun overlaps(other: Claim) = this.id != other.id && commonPoints(other).isNotEmpty()

        data class Point(val x: Int, val y: Int)
    }

    data class Segment(val start: Int, val length: Int) {

        private val end = start + length -1

        fun intersection(other: Segment) = when {
            this.start <= other.start && other.start <= this.end -> Optional.of(Segment(other.start, min(this.end, other.end) + 1 - other.start))
            other.start <= this.start && this.start <= other.end -> Optional.of(Segment(this.start, min(this.end, other.end) + 1 - this.start))
            else -> Optional.empty()
        }
    }
}


