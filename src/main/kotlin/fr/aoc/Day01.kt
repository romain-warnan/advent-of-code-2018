package fr.aoc

import java.io.File

class Day01 {

    fun part1(path: String) =  File(path).bufferedReader()
        .lines()
        .mapToLong { it.toLong() }
        .sum()
}