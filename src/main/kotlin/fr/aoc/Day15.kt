package fr.aoc

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day15 {

    fun part1(path: String): Int {
        var acres = acres(path)
        repeat(10) {
            acres = nextAcres(acres)
        }
        val numberOfWoodedAcres = acres.flatMap { it }.count { it.isWooded() }
        val numberOfLumberyardsAcres = acres.flatMap { it }.count { it.isLumberyard() }
        return numberOfWoodedAcres * numberOfLumberyardsAcres
    }

    fun part2(path: String): Int {
        var acres = acres(path)
        // 1000. 607 * 351 = 213057
        // Le cycle est de 28
        val resourcesValues = mutableListOf<Int>()
        repeat(1_000) {
            acres = nextAcres(acres)
        }
        repeat(28) {
            val numberOfWoodedAcres = acres.flatMap { it }.count { it.isWooded() }
            val numberOfLumberyardsAcres = acres.flatMap { it }.count { it.isLumberyard() }
            val resourcesValue = numberOfWoodedAcres * numberOfLumberyardsAcres
            resourcesValues += resourcesValue
            acres = nextAcres(acres)
        }
        println(resourcesValues)
        return resourcesValues[(1000000000 - 1000) % 28]
    }

    private fun acres(path: String): List<List<Acre>> {
        val lines = File(path).readLines()
        val acres = mutableListOf<List<Acre>>()
        for(i in lines.indices) {
            val lineOfAcres = mutableListOf<Acre>()
            val line = lines[i]
            for(j in line.indices) {
                lineOfAcres += Acre(i, j, line[j])
            }
            acres += lineOfAcres
        }
        return acres
    }

    private fun nextAcres(acres: List<List<Acre>>) = acres.map { it.map { it.nextType(acres) } }

    class Acre(val i: Int, val j: Int, var type: Char) {

        fun isOpen() = type == '.'
        
        fun isWooded() = type == '|'
        
        fun isLumberyard () = type == '#'

        fun nextType(acres: List<List<Acre>>): Acre {
            val openAcres = mutableListOf<Acre>()
            val woodedAcres = mutableListOf<Acre>()
            val lumberyardAcres = mutableListOf<Acre>()
            val yRange = max(0, i - 1)..min(acres.lastIndex, i + 1)
            val xRange = max(0, j - 1)..min(acres.first().lastIndex, j + 1)
            for(y in yRange) {
                for(x in xRange) {
                    if(y != i || x != j) {
                        when {
                            acres[y][x].isOpen() -> openAcres += acres[y][x]
                            acres[y][x].isWooded() -> woodedAcres += acres[y][x]
                            acres[y][x].isLumberyard() -> lumberyardAcres += acres[y][x]
                        }
                    }
                }
            }

            if(this.isOpen() && woodedAcres.size >= 3) return Acre(i, j, '|')
            if(this.isWooded() && lumberyardAcres.size >= 3) return Acre(i, j, '#')
            if(this.isLumberyard() && (lumberyardAcres.isEmpty() || woodedAcres.isEmpty())) return Acre(i, j, '.')
            return this
        }

        override fun toString(): String {
            return type.toString()
        }
    }
}
