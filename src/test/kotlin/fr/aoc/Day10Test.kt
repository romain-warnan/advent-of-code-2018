package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day10Test {

    private val day = Day10()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input10", "HKJFAKAF")
        println("Part 1 : $answer")
        Assert.assertEquals("HKJFAKAF", answer)
    }

    @Test
    fun part2() {
        val answer = day.part2("src/main/resources/input10")
        println("Part 2 : $answer")
        Assert.assertEquals(10888, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input10.1", "HI")
        Assert.assertEquals("HI", answer)
    }

    @Test
    fun test2() {
        val answer = day.part2("src/test/resources/input10.1")
        Assert.assertEquals(3, answer)
    }
}