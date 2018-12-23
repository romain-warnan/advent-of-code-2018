package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day23Test {

    private val day = Day23()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input23")
        println("Part 1 : $answer")
        Assert.assertEquals(599, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input23.1")
        Assert.assertEquals(7, answer)
    }
}