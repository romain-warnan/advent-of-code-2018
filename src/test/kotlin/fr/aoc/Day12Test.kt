package fr.aoc
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class Day12Test {

    private val day = Day12()

    @Test
    fun part1() {
        val answer = day.part1("src/main/resources/input12", "##.###.......#..#.##..#####...#...#######....##.##.##.##..#.#.##########...##.##..##.##...####..####")
        println("Part 1 : $answer")
        Assert.assertEquals(1430, answer)
    }

    @Test
    fun test1() {
        val answer = day.part1("src/test/resources/input12.1", "#..#.#..##......###...###")
        Assert.assertEquals(325, answer)
    }
}