package fr.aoc

import java.io.File
import kotlin.streams.toList

class Day07 {
    private val regex = Regex("Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.")

    fun part1(path: String): String {
        var result = ""
        val pairs = pairs(path)
        val steps = steps(pairs)
        while (steps.isNotEmpty()) {
            val nextStep = steps
                .filter { it.canBeDone(result) }
                .sorted()
                .first()
                .id
            result += nextStep
            steps.removeIf { it.id == nextStep }
        }
        return result
    }

    private fun steps(pairs: List<Pair<Char, Char>>): MutableCollection<Step> {
        val mapOfSteps = mutableMapOf<Char, Step>()
        for (pair in pairs) {
            val step = mapOfSteps.getOrPut(pair.second) { Step(pair.second) }
            step.blockedBy.add(pair.first)
            mapOfSteps.putIfAbsent(pair.first, Step(pair.first))
        }
        val steps = mapOfSteps.values
        return steps
    }

    private fun pairs(path: String): List<Pair<Char, Char>> {
        val pairs = File(path).bufferedReader()
                .lines()
                .map { pair(it) }
                .toList()
        return pairs
    }

    private fun pair(line: String): Pair<Char, Char> {
        val (a, b) = regex.matchEntire(line)!!.destructured
        return Pair(a.toCharArray().first(), b.toCharArray().first())
    }

    data class Step(val id: Char): Comparable<Step> {

        val blockedBy = mutableSetOf<Char>()

        fun canBeDone(result: String) = blockedBy.isEmpty() ||  blockedBy.all { it in result }

        override fun compareTo(other: Step) = this.id.compareTo(other.id)

    }
}
