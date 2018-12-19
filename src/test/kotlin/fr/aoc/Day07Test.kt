package fr.aoc
import org.junit.Assert
import org.junit.Test

class Day07Test {

    private val day = Day07()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input07")
        println("Part 1 : $answer")
        Assert.assertEquals("FHICMRTXYDBOAJNPWQGVZUEKLS", answer)
    }

    @Test
    fun part2() {
        val answer = day.part2("src/main/resources/input07")
        println("Part 2 : $answer")
        Assert.assertEquals(946, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input07.1")
        Assert.assertEquals("CABDFE", answer)
    }

    @Test
    fun test2() {
        val answer = day.part2("src/test/resources/input07.1", 2, 0)
        Assert.assertEquals(15, answer)
    }
}