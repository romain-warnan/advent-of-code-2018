package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day17Test {

    private val day = Day17()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input17")
        println("Part 1 : $answer")
        Assert.assertEquals(0, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input17.1")
        Assert.assertEquals(57, answer)
    }
}