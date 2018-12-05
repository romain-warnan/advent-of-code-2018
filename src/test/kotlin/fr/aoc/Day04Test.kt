package fr.aoc
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class Day04Test {

    private val day = Day04()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input04")
        println("Part 1 : $answer")
        Assert.assertEquals(109659, answer)
    }

    @Test
    fun part2() {
        val answer = day.part2("src/main/resources/input04")
        println("Part 2 : $answer")
        Assert.assertEquals(36371, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input04.1")
        Assert.assertEquals(240, answer)
    }

    @Test
    fun test2() {
        val answer = day.part2("src/test/resources/input04.1")
        Assert.assertEquals(4455, answer)
    }
}