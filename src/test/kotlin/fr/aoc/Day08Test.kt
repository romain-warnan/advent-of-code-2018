package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day08Test {

    private val day = Day08()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input08")
        println("Part 1 : $answer")
        Assert.assertEquals(0, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input08.1")
        Assert.assertEquals(138, answer)
    }
}