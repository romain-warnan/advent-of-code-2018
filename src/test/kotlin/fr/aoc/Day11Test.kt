package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day11Test {

    private val day = Day11()

    @Test
    fun part1() {
        val answer = day.part1(300, 4842)
        println("Part 1 : $answer")
        Assert.assertEquals(Pair(0, 0), answer)
    }

    @Test
    fun test1() {
        val answer = day.part1(300, 18)
        Assert.assertEquals(Pair(33,45), answer)
    }

    @Test
    fun test2() {
        val answer = day.part1(300, 42)
        Assert.assertEquals(Pair(21, 61), answer)
    }
}