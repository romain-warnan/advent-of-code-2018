package fr.aoc

import java.io.File

class Day16 {

    private val register = mutableListOf(0, 0, 0, 0)

    private val opcodes = listOf(
        Addr(),
        Addi(),
        Mulr(),
        Muli(),
        Banr(),
        Bani(),
        Borr(),
        Bori(),
        Setr(),
        Seti(),
        Gtir(),
        Gtri(),
        Gtrr(),
        Eqir(),
        Eqri(),
        Eqrr())

    fun part1(path: String): Int {
        val monitorings = monitorings(path)
        return monitorings.count { it.countMatches() >= 3 }
    }

    fun part2(monitoringPath: String, programPath: String): Int {
        val monitorings = monitorings(monitoringPath)
        val candidates = candidates(monitorings)
        val opcodes = opcodes(candidates)

        val lines = File(programPath).readLines()
        for(line in lines) {
            val args = line.split(" ").map { it.toInt() }
            val opcode = opcodes[args[0]]!!
            opcode.apply(register, args[1], args[2], args[3])
        }

        return register[0]
    }

    private fun opcodes(candidates: MutableMap<Int, MutableSet<Opcode>>): MutableMap<Int, Opcode> {
        val opcodes = mutableMapOf<Int, Opcode>()
        while (candidates.isNotEmpty()) {
            val iterator = candidates.iterator()
            while (iterator.hasNext()) {
                val candidate = iterator.next()
                if (candidate.value.size == 1) {
                    val opcode = candidate.value.first()
                    opcodes[candidate.key] = opcode
                    iterator.remove()
                    candidates.forEach { it.value.remove(opcode) }
                }
            }
        }
        return opcodes
    }

    private fun candidates(monitorings: List<Monitoring>): MutableMap<Int, MutableSet<Opcode>> {
        val candidates = mutableMapOf<Int, MutableSet<Opcode>>()

        for (monitoring in monitorings) {
            val opcodeId = monitoring.opcodeId
            val opcodes = candidates[opcodeId]
            if (opcodes != null) {
                candidates[opcodeId] = opcodes.intersect(monitoring.matchesOpcodes()).toMutableSet()
            } else {
                candidates[opcodeId] = monitoring.matchesOpcodes().toMutableSet()
            }
        }
        return candidates
    }

    private fun monitorings(path: String): List<Monitoring> {
        val monitorings = mutableListOf<Monitoring>()
        val lines = File(path).readLines()
        for(i in lines.indices step 4) {
            monitorings += monitoring(lines.subList(i, i + 3))
        }
        return monitorings
    }

    private fun monitoring(lines: List<String>) = Monitoring(beforeOrAfter(lines[0]), beforeOrAfter(lines[2]), args(lines[1]))

    private fun beforeOrAfter(line: String) = line
        .substringAfter("[")
        .substringBefore("]")
        .replace(" ", "")
        .split(",")
        .map { it.toInt() }
        .toList()

    private fun args(line: String) = line
        .split(" ")
        .map { it.toInt() }
        .toList()

    inner class Monitoring(val before: List<Int>, val after: List<Int>, val args: List<Int>) {

        val opcodeId = args[0]

        private fun matches(opcode: Opcode): Boolean {
            val register = before.toMutableList()
            opcode.apply(register, args[1], args[2], args[3])
            return register == after
        }

        fun countMatches() = opcodes.count { matches(it) }

        fun matchesOpcodes() = opcodes.filter { matches(it) }.toSet()


    }

    abstract class Opcode {

        val name = javaClass.simpleName.toLowerCase()

        abstract fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int)

        override fun hashCode() = name.hashCode()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            other as Opcode
            if (this.name != other.name) return false
            return true
        }

        override fun toString() = name
    }

    class Addr : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            register[C] = register[A] + register[B]
        }
    }

    class Addi : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            register[C] = register[A] + B
        }
    }

    class Mulr : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            register[C] = register[A] * register[B]
        }
    }

    class Muli : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            register[C] = register[A] * B
        }
    }

    class Banr : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            register[C] = register[A] and register[B]
        }
    }

    class Bani : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            register[C] = register[A] and B
        }
    }

    class Borr : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            register[C] = register[A] or register[B]
        }
    }

    class Bori : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            register[C] = register[A] or B
        }
    }

    class Setr : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            register[C] = register[A]
        }
    }

    class Seti : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            register[C] = A
        }
    }

    class Gtir : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            if(A > register[B]) register[C] = 1 else register[C] = 0
        }
    }

    class Gtri : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            if(register[A] > B) register[C] = 1 else register[C] = 0
        }
    }

    class Gtrr : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            if(register[A] > register[B]) register[C] = 1 else register[C] = 0
        }
    }

    class Eqir : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            if(A == register[B]) register[C] = 1 else register[C] = 0
        }
    }

    class Eqri : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            if(register[A] == B) register[C] = 1 else register[C] = 0
        }
    }

    class Eqrr : Opcode() {
        override fun apply(register: MutableList<Int>, A: Int, B: Int, C: Int) {
            if(register[A] == register[B]) register[C] = 1 else register[C] = 0
        }
    }
}
