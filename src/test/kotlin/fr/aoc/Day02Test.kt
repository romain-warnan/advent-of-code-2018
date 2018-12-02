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
    fun test1() {
        val answer = day.part1("src/test/resources/input02")
        Assert.assertEquals(12, answer)
    }
}