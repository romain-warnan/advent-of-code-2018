package fr.aoc

import java.io.File

class Day12 {

    fun part1(path: String, initialState: String, padding: Int = 20): Int {
        val patterns = patterns(path)
        val initialLength = initialState.length
        var state = initialState.padStart(padding + initialLength, '.').padEnd(2 * padding + initialLength, '.')
        for (step in 0 until padding) {
            var nextState = ""
            for(indice in 2 until state.length - 2) {
                val substring = state.substring(indice - 2..indice + 2)
                nextState += if(substring in patterns) "#" else "."
            }
            state = "..$nextState.."
        }
        return score(state, padding)
    }

    fun part2(path: String, initialState: String): Long {
        val padding = 111 // A partir de 111, les plantes sont disposées pareil mais se décallent d'un cran vers la droite
        val score = part1(path, initialState, padding) // 3010
        val numberOfPlants = 23 // Il y a 23 plantes à partir de l'étape 111
        val max = 50_000_000_000
        return score + (max - padding) * 23 // A chaque étape, le score augmente du nombre de plante c'est à dire 23
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
