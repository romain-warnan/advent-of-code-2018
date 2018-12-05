package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day05Test {

    private val day = Day05()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input05")
        println("Part 1 : $answer")
        Assert.assertEquals(-1, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input05.1")
        Assert.assertEquals(10, answer)
    }
}