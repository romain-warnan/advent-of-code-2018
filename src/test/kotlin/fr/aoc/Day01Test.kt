package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day01Test {

    private val day = Day01()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input01")
        println("Part 1 : $answer")
        Assert.assertEquals(0, answer)
    }
}