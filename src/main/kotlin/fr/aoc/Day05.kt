package fr.aoc

import java.io.File

class Day05 {

    fun part1(path: String) = reducePolymer(File(path).readText())

    fun part2(path: String): Int {
        val text = File(path).readText()
        val min = ('a'..'z')
            .map { text.replace(it.toString(), "", ignoreCase = true) }
            .map { reducePolymer(it) }
            .min()
        return min!!
    }

    private fun reducePolymer(text: String): Int {
        var text1 = text
        var react = true
        while (react) {
            react = false
            var n = 0
            while (n < text1.length - 1) {
                if (willReact(text1[n], text1[n + 1])) {
                    react = true
                    text1 = text1.removeRange(n, n + 2)
                    n = 0
                }
                n++
            }
        }
        return text1.length
    }

    private fun willReact(a: Char, b: Char) = a != b && a.equals(b, ignoreCase = true)
}