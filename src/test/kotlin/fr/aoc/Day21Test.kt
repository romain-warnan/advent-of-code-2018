package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day21Test {

    private val day = Day21()

    @Test
    fun part1() {
        val answer = day.part1(5, "src/main/resources/input21")
        println("Part 1 : $answer")
        Assert.assertEquals(6132825, answer)
    }

    @Test
    fun part2() {
        val answer = day.part2(5, "src/main/resources/input21")
        println("Part 2 : $answer")
        Assert.assertEquals(8307757, answer)
    }
}