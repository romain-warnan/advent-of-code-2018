package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day05Test {

    private val day = Day05()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input05")
        println("Part 1 : $answer")
        Assert.assertEquals(9238, answer)
    }

    @Test
    fun part2() {
        val answer = day.part2("src/main/resources/input05")
        println("Part 2 : $answer")
        Assert.assertEquals(4052, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input05.1")
        Assert.assertEquals(10, answer)
    }

    @Test
    fun test2() {
        val answer = day.part2("src/test/resources/input05.1")
        Assert.assertEquals(4, answer)
    }
}