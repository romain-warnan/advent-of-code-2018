package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day03Test {

    private val day = Day03()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input03")
        println("Part 1 : $answer")
        Assert.assertEquals(116491, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input03.1")
        Assert.assertEquals(4, answer)
    }
}