package fr.aoc

class Day11 {

    fun part1(gridSize: Int, gridLevel: Int): Pair<Int, Int> {
        var maxPower = -1
        var pair = Pair(-1, -1)
        val grid = grid(gridSize, gridLevel)
        for (y in 0 until gridSize - 3) {
            for (x in 0 until gridSize - 3) {
                val point = Point(x + 1, y + 1)
                val totalPower = totalPower(point, grid)
                if(totalPower > maxPower) {
                    maxPower = totalPower
                    pair = Pair(point.x + 1, point.y + 1)
                }
            }
        }
        return pair
    }

    private fun powerLevel(point: Point, gridLevel: Int): Int {
        val rackId = point.x + 10
        return (((rackId * point.y + gridLevel) * rackId / 100) % 10) - 5
    }

    private fun grid(gridSize: Int, gridLevel: Int): Array<IntArray> {
        val grid = Array(gridSize) {IntArray(gridSize)}
        for (y in 1..gridSize) {
            for (x in 1..gridSize) {
                val point = Point(x, y)
                grid[y - 1][x - 1] = powerLevel(point, gridLevel)
            }
        }
        return grid
    }

    private fun totalPower(point: Point, grid: Array<IntArray>): Int {
        var totalPower = 0
        for(y in 0 until 3) {
            for(x in 0 until 3) {
                totalPower += grid[point.y + y][point.x + x]
            }
        }
        return totalPower
    }

    data class Point(val x: Int, val y: Int)
}
