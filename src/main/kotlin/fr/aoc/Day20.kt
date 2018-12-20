package fr.aoc

class Day20 {

    fun part1(regex: String): Int {
        val input = regex.removeSurrounding("^", "$")
        return maxLength(input)
    }

    private fun maxLength(input: String): Int {
        var maxLength = 0
        val elements = elements(input)
        for (element in elements) {
            maxLength += if(element.startsWith('(')) groups(element).map { maxLength(it) }.max()!! else  element.length
        }
        return maxLength
    }

    private fun elements(input: String, elements: MutableList<String> = mutableListOf()): List<String> {
        if('(' !in input) elements += input
        else {
            val prefix = input.substringBefore("(")
            val tailIndex = tailIndex(input)
            elements += prefix
            elements += input.substring(prefix.length, tailIndex + 1)
            if(tailIndex + 1 < input.length) elements.addAll(elements(input.substring(tailIndex + 1)))
        }
        return elements
    }

    private fun tailIndex(input: String): Int {
        var level = -1
        for(indice in input.indices) {
            val c = input[indice]
            if(c == '(') level ++
            if(c == ')') {
                if(level == 0) return indice
                level --
            }
        }
        return -1
    }

    private fun groups(input: String): List<String> {
        var level = -1
        val groups = mutableListOf<String>()
        var currentGroup = ""
        for(c in input) {
            if(level > 0) {
                currentGroup += c
            }
            if(level == 0) {
                when (c) {
                    '|' -> {
                        groups += currentGroup
                        currentGroup = ""
                    }
                    ')' -> {
                        groups += currentGroup
                        if("" in groups) return listOf("") // Cas des détours
                        return groups
                    }
                    else -> currentGroup += c
                }
            }
            if(c == '(') level ++
            else if(c == ')') level --
        }
        return listOf()
    }
}
