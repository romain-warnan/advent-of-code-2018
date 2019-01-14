package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day09Test {

    private val day = Day09()

    @Test
    fun part1() {
        val answer = day.part1(419, 71052)
        println("Part 1 : $answer")
        Assert.assertEquals(0, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1(9, 25)
        Assert.assertEquals(32, answer)
    }

    @Test
    fun test2() {
        val answer = day.part1(10, 1618)
        Assert.assertEquals(8317, answer)
    }

    @Test
    fun test3() {
        val answer = day.part1(13, 7999)
        Assert.assertEquals(146373, answer)
    }

    @Test
    fun test4() {
        val answer = day.part1(17, 1104)
        Assert.assertEquals(2764, answer)
    }

    @Test
    fun test5() {
        val answer = day.part1(21, 6111)
        Assert.assertEquals(54718, answer)
    }

    @Test
    fun test6() {
        val answer = day.part1(30, 5807)
        Assert.assertEquals(37305, answer)
    }
}