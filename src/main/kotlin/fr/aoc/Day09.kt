package fr.aoc

class Day09 {


    fun part1(numberOfPlayers: Int, lastMarbleValue: Int): Long {
        val scores = mutableMapOf<Int, Long>()
        var currentMarble = Marble(0)
        currentMarble.prevMarble = currentMarble
        currentMarble.nextMarble = currentMarble
        for(index in 1..lastMarbleValue) {
            val player = index % numberOfPlayers
            val marble = Marble(index)
            currentMarble = play(marble, currentMarble, scores, player)
        }
        return winningScore(scores)
    }

    private fun play(marble: Marble, currentMarble: Marble, scores: MutableMap<Int, Long>, player: Int): Marble {
        if(marble.id % 23 == 0) {
            return playMarble23(currentMarble, scores, player, marble)
        }
        return playMarble(currentMarble, marble)
    }

    private fun playMarble(currentMarble: Marble, marble: Marble): Marble {
        val nextMarble = currentMarble.nextMarble
        val nextNextMarble = nextMarble!!.nextMarble!!

        nextMarble.nextMarble = marble
        nextNextMarble.prevMarble = marble

        marble.prevMarble = nextMarble
        marble.nextMarble = nextNextMarble

        return marble
    }

    private fun playMarble23(currentMarble: Marble, scores: MutableMap<Int, Long>, player: Int, marble: Marble): Marble {
        val removedMarble = removeMarble(currentMarble)
        scores[player] = scores.getOrPut(player) { 0 } + marble.id + removedMarble.id

        val nextMarble = removedMarble.nextMarble!!
        val prevMarble = removedMarble.prevMarble!!

        prevMarble.nextMarble = nextMarble
        nextMarble.prevMarble = prevMarble

        return nextMarble
    }

    private fun removeMarble(currentMarble: Marble): Marble {
        var marble = currentMarble
        repeat(7) {
            marble = marble.prevMarble!!
        }
        return marble
    }

    private fun winningScore(scores: Map<Int, Long>) = scores.values.max()!!

    data class Marble(val id: Int, var prevMarble: Marble? = null, var nextMarble: Marble? = null) {
        override fun toString(): String {
            return "$prevMarble -> $id -> $nextMarble"
        }
    }
}
