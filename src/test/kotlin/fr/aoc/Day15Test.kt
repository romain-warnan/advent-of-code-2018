package fr.aoc
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class Day15Test {

    private val day = Day15()

    @Test
    fun part1() {
        val answer = day.part1(165061)
        println("Part 1 : $answer")
        Assert.assertEquals("5992684592", answer)
    }

    @Test
    fun part2() {
        val answer = day.part2("165061")
        println("Part 2 : $answer")
        Assert.assertEquals(0, answer)
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
    fun test5() {
        val answer = day.part2("51589")
        Assert.assertEquals(9, answer)
    }

    @Test
    fun test6() {
        val answer = day.part2("01245")
        Assert.assertEquals(5, answer)
    }

    @Test
    fun test7() {
        val answer = day.part2("92510")
        Assert.assertEquals(18, answer)
    }

    @Test
    fun test8() {
        val answer = day.part2("59414")
        Assert.assertEquals(2018, answer)
    }
}