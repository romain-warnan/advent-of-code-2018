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
}