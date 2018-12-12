package fr.aoc

import java.io.File

class Day12 {

    fun part1(path: String, initialState: String): Int {
        val patterns = patterns(path)
        val initialLength = initialState.length
        val padding = 20
        var state = initialState.padStart(padding + initialLength, '.').padEnd(2 * padding + initialLength, '.')
        for (step in 0 until 20) {
            var nextState = ""
            for(indice in 2 until state.length - 2) {
                val substring = state.substring(indice - 2..indice + 2)
                nextState += if(substring in patterns) "#" else "."
            }
            state = "..$nextState.."
        }
        return score(state, padding)
    }

    private fun score(state: String, padding: Int): Int {
        var score = 0
        for (index in 0 until state.length) {
            if(state[index] == '#') {
                score += (index - padding)
            }
        }
        return score
    }

    private fun patterns(path: String) = File(path).readLines()
        .filter { it.endsWith("#") }
        .map { it.substringBefore(" => ") }
        .toList()
}
