package fr.aoc

import java.io.File

class Day19 {

    fun part1(ipRegister: Int, path: String): Int {
        val register = mutableListOf(0, 0, 0, 0, 0, 0)
        var ip = 0
        val instructions = File(path).readLines().map { opcode(it) }
        while (ip < instructions.size) {
            register[ipRegister] = ip
            instructions[ip].apply(register)
            ip = register[ipRegister]
            ip ++
        }
        return register[0]
    }

    fun part2(ipRegister: Int, path: String): Int {
        val register = mutableListOf(1, 0, 0, 0, 0, 0)
        var ip = 0
        val instructions = File(path).readLines().map { opcode(it) }
        while (ip < instructions.size) {
            register[ipRegister] = ip
            instructions[ip].apply(register)
            ip = register[ipRegister]
            ip ++
            println(register)
        }
        return register[0]
    }

    private fun opcode(line: String): Opcode {
        val opcodeName = line.substring(0, 4)
        val (A, B, C) = line.substring(5).split(" ").map { it.toInt() }
        return when(opcodeName) {
            "eqri" -> Eqri(A, B, C)
            "gtrr" -> Gtrr(A, B, C)
            "gtri" -> Gtri(A, B, C)
            "eqir" -> Eqir(A, B, C)
            "eqrr" -> Eqrr(A, B, C)
            "gtir" -> Gtir(A, B, C)
            "banr" -> Banr(A, B, C)
            "setr" -> Setr(A, B, C)
            "bani" -> Bani(A, B, C)
            "seti" -> Seti(A, B, C)
            "addi" -> Addi(A, B, C)
            "mulr" -> Mulr(A, B, C)
            "addr" -> Addr(A, B, C)
            "borr" -> Borr(A, B, C)
            "bori" -> Bori(A, B, C)
            else -> Muli(A, B, C)
        }
    }

    interface Opcode {
        fun apply(register: MutableList<Int>)
    }

    class Addr(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            register[C] = register[A] + register[B]
        }
    }

    class Addi(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            register[C] = register[A] + B
        }
    }

    class Mulr(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            register[C] = register[A] * register[B]
        }
    }

    class Muli(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            register[C] = register[A] * B
        }
    }

    class Banr(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            register[C] = register[A] and register[B]
        }
    }

    class Bani(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            register[C] = register[A] and B
        }
    }

    class Borr(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            register[C] = register[A] or register[B]
        }
    }

    class Bori(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            register[C] = register[A] or B
        }
    }

    class Setr(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            register[C] = register[A]
        }
    }

    class Seti(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            register[C] = A
        }
    }

    class Gtir(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            if(A > register[B]) register[C] = 1 else register[C] = 0
        }
    }

    class Gtri(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            if(register[A] > B) register[C] = 1 else register[C] = 0
        }
    }

    class Gtrr(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            if(register[A] > register[B]) register[C] = 1 else register[C] = 0
        }
    }

    class Eqir(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            if(A == register[B]) register[C] = 1 else register[C] = 0
        }
    }

    class Eqri(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            if(register[A] == B) register[C] = 1 else register[C] = 0
        }
    }

    class Eqrr(val A: Int, val B: Int, val C: Int) : Opcode {
        override fun apply(register: MutableList<Int>) {
            if(register[A] == register[B]) register[C] = 1 else register[C] = 0
        }
    }
}
