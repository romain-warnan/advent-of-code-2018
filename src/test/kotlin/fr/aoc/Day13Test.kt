package fr.aoc
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class Day13Test {

    private val day = Day13()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input13")
        println("Part 1 : $answer")
        Assert.assertEquals(Pair(113, 136), answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input13.1")
        Assert.assertEquals(Pair(7, 3), answer)
    }
}