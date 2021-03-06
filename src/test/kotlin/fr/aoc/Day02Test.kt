package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day02Test {

    private val day = Day02()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input02")
        println("Part 1 : $answer")
        Assert.assertEquals(6972, answer)
    }

    @Test
    fun part2() {
        val answer = day.part2("src/main/resources/input02")
        println("Part 2 : $answer")
        Assert.assertEquals("aixwcbzrmdvpsjfgllthdyoqe", answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input02.1")
        Assert.assertEquals(12, answer)
    }

    @Test
    fun test2() {
        val answer = day.part2("src/test/resources/input02.2")
        Assert.assertEquals("fgij", answer)
    }
}