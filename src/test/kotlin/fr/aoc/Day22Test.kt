package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day22Test {

    private val day = Day22()

    @Test
    fun part1() {
        val answer = day.part1(6969, Day22.Point(9, 796))
        println("Part 1 : $answer")
        Assert.assertEquals(7901, answer)
    }

    @Test
    fun part2() {
        val answer = day.part2(6969, Day22.Point(9, 796))
        println("Part 1 : $answer")
        Assert.assertEquals(7901, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1(510, Day22.Point(10, 10))
        Assert.assertEquals(114, answer)
    }

    @Test
    fun test2() {
        val answer = day.part2(510, Day22.Point(10, 10))
        Assert.assertEquals(45, answer)
    }
}