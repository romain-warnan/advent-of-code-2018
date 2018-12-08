package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day06Test {

    private val day = Day06()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input06")
        println("Part 1 : $answer")
        Assert.assertEquals(4754, answer)
    }

    @Test
    fun part2() {
        val answer = day.part2("src/main/resources/input06")
        println("Part 2 : $answer")
        Assert.assertEquals(42344, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input06.1")
        Assert.assertEquals(17, answer)
    }

    @Test
    fun test2() {
        val answer = day.part2("src/test/resources/input06.1", 32)
        Assert.assertEquals(16, answer)
    }
}