package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day19Test {

    private val day = Day19()

    @Test
    fun part1() {
        val answer = day.part1(3, "src/main/resources/input19")
        println("Part 1 : $answer")
        Assert.assertEquals(2016, answer)
    }

    @Test
    fun part2() {
        val answer = day.part2(3, "src/main/resources/input19")
        println("Part 2 : $answer")
        Assert.assertEquals(-1, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1(0, "src/test/resources/input19.1")
        Assert.assertEquals(6, answer)
    }
}