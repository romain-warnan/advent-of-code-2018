package fr.aoc

import java.io.File
import kotlin.streams.asSequence
import kotlin.streams.toList

class Day02 {

    fun part1(path: String): Int {
        val countsByChars = File(path).bufferedReader()
                .lines()
                .map {
                    it.chars()
                    .asSequence()
                    .groupingBy { it }
                    .eachCount()
                }.toList()
        val a = countsByChars.filter { it.values.any { it == 2 } }.count()
        val b = countsByChars.filter { it.values.any { it == 3 } }.count()
        return a * b
    }

    fun part2(path: String): String {
        val words = File(path).bufferedReader().lines().toList()
        for (a in words) {
            for (b in words) {
                val indices = divergentIndices(a, b)
                if(indices.size == 1) {
                    val indice = indices.first()
                    return a.removeRange(indice, indice + 1)
                }
            }
        }
        return ""
    }

    private fun divergentIndices(a: String, b: String): List<Int> {
        val differences = mutableListOf<Int>()
        for (indice in a.indices) {
            if(a[indice] != b[indice]) differences += indice
        }
        return differences
    }

    fun differsByOneChar(a: String, b: String) = divergentIndices(a, b).size == 1
}