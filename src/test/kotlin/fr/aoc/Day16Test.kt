package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day16Test {

    private val day = Day16()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input16.1")
        println("Part 1 : $answer")
        Assert.assertEquals(0, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input16.1")
        Assert.assertEquals(1, answer)
    }
}