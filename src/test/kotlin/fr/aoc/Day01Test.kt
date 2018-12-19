package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day01Test {

    private val day = Day01()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input01")
        println("Part 1 : $answer")
        Assert.assertEquals(525, answer)
    }

    @Test
    fun part2() {
        val answer = day.part2("src/main/resources/input01")
        println("Part 2 : $answer")
        Assert.assertEquals(75749, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input01.1")
        Assert.assertEquals(3, answer)
    }

    @Test
    fun test2() {
        val answer = day.part1("src/test/resources/input01.2")
        Assert.assertEquals(3, answer)
    }

    @Test
    fun test3() {
        val answer = day.part1("src/test/resources/input01.3")
        Assert.assertEquals(0, answer)
    }

    @Test
    fun test4() {
        val answer = day.part1("src/test/resources/input01.4")
        Assert.assertEquals(-6, answer)
    }

    @Test
    fun test5() {
        val answer = day.part2("src/test/resources/input01.5")
        Assert.assertEquals(0, answer)
    }

    @Test
    fun test6() {
        val answer = day.part2("src/test/resources/input01.6")
        Assert.assertEquals(10, answer)
    }

    @Test
    fun test7() {
        val answer = day.part2("src/test/resources/input01.7")
        Assert.assertEquals(5, answer)
    }

    @Test
    fun test8() {
        val answer = day.part2("src/test/resources/input01.8")
        Assert.assertEquals(14, answer)
    }
}