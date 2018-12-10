package fr.aoc

import java.io.File
import kotlin.streams.toList

class Day07 {
    private val regex = Regex("Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.")

    fun part1(path: String, minDuration: Int = 60): String {
        var result = ""
        val pairs = pairs(path)
        val steps = steps(pairs, minDuration)
        while (steps.isNotEmpty()) {
            val nextStep = nextStep(steps, result)
            result += nextStep
            steps.removeIf { it.id == nextStep }
        }
        return result
    }

    fun part2(path: String, numberOfWorkers: Int = 5, minDuration: Int = 60): Int {
        var elapsedTime = 0
        var result = ""
        val pairs = pairs(path)
        val steps = steps(pairs, minDuration)
        val workers = workers(numberOfWorkers)

        while (steps.isNotEmpty()) {
            elapsedTime ++
            val availableSteps = steps.filter { !it.isRunning() }
                .filter { it.canBeDone(result) }
                .sorted()
                .toMutableList()
            for(worker in workers) {
                if(worker.isIdle() && availableSteps.isNotEmpty()) {
                    worker.step = availableSteps.first()
                    availableSteps.removeIf { it.id == worker.step!!.id }
                }
            }
            for(worker in workers) {
                if(worker.isWorking()) {
                    worker.step!!.remainingDuration--
                    if(worker.step!!.isDone()) {
                        val id = worker.step!!.id
                        result += id
                        steps.removeIf { it.id == id }
                        worker.step = null
                    }
                }
            }
        }
        return elapsedTime
    }

    private fun workers(numberOfWorkers: Int) = (0 until numberOfWorkers).map { Worker(it) }.toSet()

    private fun availableSteps(steps: MutableCollection<Step>, result: String) = steps
        .filter { it.canBeDone(result) }
        .sorted()
        .toList()

    private fun nextStep(steps: MutableCollection<Step>, result: String) = availableSteps(steps, result).first().id

    private fun steps(pairs: List<Pair<Char, Char>>, minDuration: Int): MutableCollection<Step> {
        val mapOfSteps = mutableMapOf<Char, Step>()
        for (pair in pairs) {
            val step = mapOfSteps.getOrPut(pair.second) { Step(pair.second, minDuration) }
            step.blockedBy.add(pair.first)
            mapOfSteps.putIfAbsent(pair.first, Step(pair.first, minDuration))
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

    data class Worker(val id: Int) {
        var step: Step? = null

        fun isIdle() = step == null

        fun isWorking() = step != null
    }

    data class Step(val id: Char, val minDuration: Int): Comparable<Step> {

        val blockedBy = mutableSetOf<Char>()

        private val totalDuration = 1 + minDuration + id.toInt() - 'A'.toInt()

        var remainingDuration = totalDuration

        fun canBeDone(result: String) = blockedBy.isEmpty() ||  blockedBy.all { it in result }

        fun isDone() = remainingDuration == 0

        fun isRunning() = remainingDuration < totalDuration

        override fun compareTo(other: Step) = this.id.compareTo(other.id)
    }

}
