package fr.aoc
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import java.io.File

class Day20Test {

    private val day = Day20()

    @Test
    @Ignore
    fun part1() {
        val answer = day.part1(File("src/main/resources/input20").readText())
        println("Part 1 : $answer")
        Assert.assertEquals(0, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("^WNE$")
        Assert.assertEquals(3, answer)
    }

    @Test
    fun test2() {
        val answer = day.part1("^ENWWW(NEEE|SSE(EE|N))$")
        Assert.assertEquals(10, answer)
    }

    @Test
    fun test3() {
        val answer = day.part1("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$")
        Assert.assertEquals(18, answer)
    }

    @Test
    fun test4() {
        val answer = day.part1("^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$")
        Assert.assertEquals(23, answer)
    }

    @Test
    fun test5() {
        val answer = day.part1("^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$")
        Assert.assertEquals(31, answer)
    }
}