package fr.aoc

import kotlin.math.min

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
