package fr.aoc

class Day14 {

    fun part1(numberOfRecipes: Int): String {
        val recipes = mutableListOf(3, 7)
        val elves = listOf(Elf(0), Elf(1))
        while (recipes.size < numberOfRecipes + 10) {
            recipes.addAll(newRecipes(elves, recipes))
            elves.forEach { it.moveToNextPosition(recipes) }
        }
        return recipes.subList(numberOfRecipes, numberOfRecipes + 10).joinToString("")
    }

    fun part2(scoreSequence: String): Int {
        val scores = scoreSequence.map { it.toString().toInt() }.toList()
        val recipes = mutableListOf(3, 7)
        val elves = listOf(Elf(0), Elf(1))
        while (true) {
            val newRecipes = newRecipes(elves, recipes)
            recipes.addAll(newRecipes)
            elves.forEach { it.moveToNextPosition(recipes) }
            val length = scores.size
            if(recipes.size >= length) {
                for(i in newRecipes.indices) {
                    val to = recipes.size - i
                    if(to - length >= 0) {
                        if(recipes.subList(to - length, to) == scores) return to - length
                    }
                }
            }
        }
    }

    private fun newRecipes(elves: List<Elf>, recipes: List<Int>)= elves
        .map { it.currentRecipe(recipes) }
        .sum()
        .toString()
        .map { it.toString().toInt() }
        .toList()

    class Elf(val id: Int): Comparable<Elf> {

        var position = id

        fun currentRecipe(recipes: List<Int>) = recipes[position]

        override fun compareTo(other: Elf) = this.id.compareTo(other.id)

        fun moveToNextPosition(recipes: List<Int>) {
            position = (position + 1 + currentRecipe(recipes)) % recipes.size
        }
    }
}
