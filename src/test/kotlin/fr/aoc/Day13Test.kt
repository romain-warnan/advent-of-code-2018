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
    fun part2() {
        val answer = day.part2("src/main/resources/input13")
        println("Part 2 : $answer")
        Assert.assertEquals(Pair(114, 136), answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input13.1")
        Assert.assertEquals(Pair(7, 3), answer)
    }

    @Test
    fun test2() {
        val answer = day.part2("src/test/resources/input13.2")
        Assert.assertEquals(Pair(6, 4), answer)
    }
}