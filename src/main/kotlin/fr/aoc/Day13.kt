package fr.aoc

import java.io.File

class Day13 {
    fun part1(path: String): Pair<Int, Int> {
        val wagons = wagons(path)
        val track = track(path)
        while(true) {
            for (wagon in wagons) {
                wagon.move(track)
            }
            val point = collision(wagons)
            if(point != null) return Pair(point.x, point.y)
            wagons.sort()
        }
    }

    private fun trackPoint(x: Int, y: Int, type: Char) = TrackPoint(Point(x, y), type)

    private fun wagon(x: Int, y: Int, type: Char) = Wagon(Point(x, y), type)

    private fun collision(wagons: List<Wagon>): Point? {
        for(a in wagons) {
            for(b in wagons){
                if (a.collision(b)) return a.point
            }
        }
        return null
    }

    private fun wagons(path: String): MutableList<Wagon> {
        val wagons = mutableListOf<Wagon>()
        val types = listOf('<', '>', 'v', '^')
        val lines = File(path).readLines()
        for (y in lines.indices) {
            val line = lines[y]
            for (x in line.indices) {
                val type = line[x]
                if(type in types) {
                    wagons += wagon(x, y, type)
                }
            }
        }
        return wagons
    }

    private fun track(path: String): List<TrackPoint> {
        val track = mutableListOf<TrackPoint>()
        val lines = File(path).readLines()
        for (y in lines.indices) {
            val line = lines[y]
            for (x in line.indices) {
                val type = line[x]
                if(type != ' '){
                    val trackPoint = when(type) {
                        '>' -> trackPoint(x, y, '-')
                        '<' -> trackPoint(x, y, '-')
                        '^' -> trackPoint(x, y, '|')
                        'v' -> trackPoint(x, y, '|')
                        else -> trackPoint(x, y, type)
                    }
                    track += trackPoint
                }
            }
        }
        track.sort()
        return track
    }


    data class Point(val x: Int, val y: Int): Comparable<Point> {

        private val comparator = Comparator.comparingInt<Point> { it.y }.thenComparingInt { it.x }

        override fun compareTo(other: Point) = comparator.compare(this, other)
    }

    data class TrackPoint(val point: Point, val type: Char): Comparable<TrackPoint> {

        override fun compareTo(other: TrackPoint) = this.point.compareTo(other.point)
    }

    data class Wagon(var point: Point, var way: Char): Comparable<Wagon> {

        var nextTurn = Turn.LEFT

        enum class Turn {
            LEFT, STRAIGHT, RIGHT
        }

        private fun type(track: List<TrackPoint>) = track
            .filter { it.point.x == this.point.x }
            .first { it.point.y == this.point.y }
            .type

        private fun nextPoint() = when(this.way) {
            '>' -> Point(this.point.x + 1, this.point.y)
            '<' -> Point(this.point.x - 1, this.point.y)
            'v' -> Point(this.point.x, this.point.y + 1)
            else -> Point(this.point.x, this.point.y - 1)
        }

        private fun nextTurn() = when(nextTurn) {
            Turn.LEFT -> Turn.STRAIGHT
            Turn.STRAIGHT -> Turn.RIGHT
            else -> Turn.LEFT
        }

        fun move(track: List<TrackPoint>) {
            this.point = nextPoint()
            val type = type(track)
            when (type) {
                '/' -> when (way) {
                    '^' -> way = '>'
                    'v' -> way = '<'
                    '<' -> way = 'v'
                    '>' -> way = '^'
                }
                '\\' -> when (way) {
                    '^' -> way = '<'
                    'v' -> way = '>'
                    '<' -> way = '^'
                    '>' -> way = 'v'
                }
                '+' -> {
                    when (nextTurn){
                        Turn.LEFT -> when (way) {
                            '^' -> way = '<'
                            'v' -> way = '>'
                            '<' -> way = 'v'
                            '>' -> way = '^'
                        }
                        Turn.RIGHT -> when (way) {
                            '^' -> way = '>'
                            'v' -> way = '<'
                            '<' -> way = '^'
                            '>' -> way = 'v'
                        }
                    }
                    nextTurn = nextTurn()
                }
            }
        }

        fun collision(other: Wagon) = this.point == other.point && this.way != other.way

        override fun compareTo(other: Wagon) = this.point.compareTo(other.point)
    }
}
