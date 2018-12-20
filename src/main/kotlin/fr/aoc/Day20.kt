package fr.aoc

class Day20 {

    fun part1(regex: String): Int {
        val input = regex.removeSurrounding("^", "$")
        return maxLength(input)
    }

    private fun maxLength(input: String): Int {
        if('(' !in input) return input.length
        val prefix = input.substringBefore("(")
        val groups = groups(input.removePrefix(prefix))

        val tailIndex = tailIndex(input)
        val tail = if(tailIndex > 0) input.substring(tailIndex + 1) else ""

        return prefix.length + groups.map { maxLength(it) }.max()!! + maxLength(tail)
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
