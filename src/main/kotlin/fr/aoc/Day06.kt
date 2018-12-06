package fr.aoc

import java.io.File
import kotlin.math.abs
import kotlin.streams.toList

class Day06 {

    fun part1(path: String): Int {
        val points = points(path)
        val frame = frame(points)
        val zoneByPoints = zonesByPoints(frame, points)
        val infiniteZones = infiniteZones(zoneByPoints, frame)
        return largestZoneSuperficy(zoneByPoints, infiniteZones)
    }

    private fun largestZoneSuperficy(zoneByPoints: MutableMap<Point, Candidate>, infiniteZones: Set<Int>) = zoneByPoints
        .map { it.value }
        .filter { it.isValid() }
        .groupingBy { it.uniqueIndice() }
        .eachCount()
        .filterKeys { it !in infiniteZones }
        .maxBy { it.value }!!.value

    private fun infiniteZones(zoneByPoints: MutableMap<Point, Candidate>, frame: Frame) = zoneByPoints
        .filterKeys { it.touchesTheBorderOf(frame) }
        .map { it.value }
        .filter { it.isValid() }
        .map { it.uniqueIndice() }
        .toSet()

    private fun zonesByPoints(frame: Frame, points: List<Point>): MutableMap<Point, Candidate> {
        val zoneByPoints = mutableMapOf<Point, Candidate>()
        val (top,bottom, left, right) = frame
        for (i in left..right) {
            for (j in top..bottom) {
                val point = Point(i, j)
                for (indice in points.indices) {
                    val candidate = zoneByPoints[point]
                    if (candidate == null) zoneByPoints[point] = checkCandidate(Candidate(point), indice, points)
                    else checkCandidate(candidate, indice, points)
                }
            }
        }
        return zoneByPoints
    }

    private fun points(path: String) = File(path).bufferedReader()
        .lines()
        .map { point(it) }
        .toList()

    private fun point(string: String): Point {
        val tokens = string.split(",")
        return Point(tokens[0].trim().toInt(), tokens[1].trim().toInt())
    }

    private fun left(points: List<Point>) = points.map { it.x }.min()!!

    private fun top(points: List<Point>) = points.map { it.y }.min()!!

    private fun right(points: List<Point>) = points.map { it.x }.max()!!

    private fun bottom(points: List<Point>) = points.map { it.y }.max()!!

    private fun frame(points: List<Point>): Frame {
        val top = top(points)
        val bottom = bottom(points)
        val left = left(points)
        val right = right(points)
        return Frame(top, bottom, left, right)
    }

    private fun manhattan(a: Point, b: Point) = abs(a.x - b.x) + abs(a.y - b.y)

    private fun checkCandidate(candidate: Candidate, indice: Int, points: List<Point>): Candidate {
        val manhattan = manhattan(points[indice], candidate.point)
        if(candidate.manhattan < 0 || manhattan < candidate.manhattan) {
            candidate.indices = mutableListOf(indice)
            candidate.manhattan = manhattan
        }
        else if (manhattan == candidate.manhattan) {
            candidate.indices.add(indice)
        }
        return candidate
    }

    data class Point(val x: Int, val y: Int) {
        fun touchesTheBorderOf(frame: Frame) = x == frame.left || x == frame.right || y == frame.top || y == frame.bottom
    }

    class Candidate(val point: Point) {
        var indices = mutableListOf<Int>()
        var manhattan = -1

        fun isValid() = indices.size == 1

        fun uniqueIndice() = indices[0]
    }

    data class Frame(val top: Int, val bottom: Int, val left: Int, val right: Int)
}

