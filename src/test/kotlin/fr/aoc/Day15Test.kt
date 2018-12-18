package fr.aoc
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class Day15Test {

    private val day = Day15()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input15")
        println("Part 1 : $answer")
        Assert.assertEquals(518028, answer)
    }

    @Test
    fun part2() {
        val answer = day.part2("src/main/resources/input15")
        println("Part 2 : $answer")
        Assert.assertEquals(518028, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input15.1")
        Assert.assertEquals(1147, answer)
    }
}