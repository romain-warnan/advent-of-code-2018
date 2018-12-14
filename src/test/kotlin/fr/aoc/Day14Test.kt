package fr.aoc
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class Day14Test {

    private val day = Day14()

    @Test
    fun part1() {
        val answer = day.part1(165061)
        println("Part 1 : $answer")
        Assert.assertEquals("5992684592", answer)
    }

    @Test
    fun test1() {
        val answer = day.part1(9)
        Assert.assertEquals("5158916779", answer)
    }

    @Test
    fun test2() {
        val answer = day.part1(5)
        Assert.assertEquals("0124515891", answer)
    }

    @Test
    fun test3() {
        val answer = day.part1(18)
        Assert.assertEquals("9251071085", answer)
    }

    @Test
    fun test4() {
        val answer = day.part1(2018)
        Assert.assertEquals("5941429882", answer)
    }
}