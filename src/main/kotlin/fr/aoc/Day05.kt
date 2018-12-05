package fr.aoc

import java.io.File

class Day05 {

    fun part1(path: String): Int {
        var text = File(path).readText()
        var react = true
        while (react){
            react = false
            var n = 0
            while (n < text.length - 1) {
                if(willReact(text[n], text[n + 1])) {
                    react = true
                    text = text.removeRange(n, n + 2)
                    n = 0
                }
                n ++
            }
        }
        return text.length
    }

    private fun willReact(a: Char, b: Char) = a != b && a.equals(b, ignoreCase = true)
}