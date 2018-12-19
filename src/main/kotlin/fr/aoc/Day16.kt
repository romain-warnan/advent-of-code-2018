package fr.aoc

class Day16 {

    private val register = hashMapOf(
        0 to 0,
        1 to 0,
        2 to 0,
        3 to 0
    )

    fun part1(path: String): Int {

        return -1
    }

    interface Opcode {
        fun apply(A: Int, B: Int, C: Int)
    }

    inner class Addr() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            register[C] = register[A]!! + register[B]!!
        }
    }

    inner class Addi() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            register[C] = register[A]!! + B
        }
    }

    inner class Mulr() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            register[C] = register[A]!! * register[B]!!
        }
    }

    inner class Muli() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            register[C] = register[A]!! * B
        }
    }

    inner class Banr() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            register[C] = register[A]!! and register[B]!!
        }
    }

    inner class Bani() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            register[C] = register[A]!! and B
        }
    }

    inner class Borr() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            register[C] = register[A]!! or register[B]!!
        }
    }

    inner class Bori() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            register[C] = register[A]!! or B
        }
    }

    inner class Setr() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            register[C] = register[A]!!
        }
    }

    inner class Seti() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            register[C] = A
        }
    }

    inner class Gtir() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            if(A >= register[B]!!) register[C] = 1 else register[C] = 0
        }
    }

    inner class Gtri() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            if(register[A]!! >= B) register[C] = 1 else register[C] = 0
        }
    }

    inner class Gtrr() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            if(register[A]!! >= register[B]!!) register[C] = 1 else register[C] = 0
        }
    }

    inner class Eqir() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            if(A == register[B]!!) register[C] = 1 else register[C] = 0
        }
    }

    inner class Eqri() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            if(register[A]!! == B) register[C] = 1 else register[C] = 0
        }
    }

    inner class Eqrr() : Opcode {
        override fun apply(A: Int, B: Int, C: Int) {
            if(register[A]!! == register[B]!!) register[C] = 1 else register[C] = 0
        }
    }
}
