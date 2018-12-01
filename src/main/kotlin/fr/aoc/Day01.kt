package fr.aoc

import java.io.File
import kotlin.streams.toList

class Day01 {

    fun part1(path: String) =  File(path).bufferedReader()
        .lines()
        .mapToLong { it.toLong() }
        .sum()

    fun part2(path: String): Long {
        val changes = File(path).bufferedReader()
                .lines()
                .mapToLong { it.toLong() }
                .toList()
        var frequency = 0L
        var index = 0
        val frequencies = mutableSetOf(frequency)
        while (true) {
            frequency += changes[index]
            if (frequency in frequencies) return frequency
            frequencies += frequency
            index = (index + 1) % changes.size
        }
    }    
}