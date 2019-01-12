package fr.aoc

import java.io.File

class Day08 {

    private val numbers = mutableListOf<Int>()

    fun part1(path: String): Int {
        numbers.addAll(numbers(path))
        val tree = readTree()
        return tree.sumOfMetadata()
    }

    private fun numbers(path: String) = File(path).readText().split(" ").map { it.toInt() }.toList()

    private fun node(index: Int, numbers: List<Int>, parentNode: Node? = null): Node {
        val node = Node(index)
        node.parent = parentNode
        node.childNodeQuantity = numbers[index]
        node.metadataQuantity = numbers[index + 1]
        return node
    }

    private fun readTree() = readNode(node(0, numbers))

    private fun readNode(node: Node): Node {
        if(node.isComplete()) {
            node.metadata = metadata(node)
            val parent = node.parent
            if(parent != null) {
                parent.childNodes += node
                readNode(parent)
            }
        }
        else {
            val nextChild = node(node.nextChildIndex(), numbers, node)
            readNode(nextChild)
        }
        return node
    }

    private fun metadata(node: Node)= numbers
        .take(node.index + node.length())
        .takeLast(node.metadataQuantity)

    data class Node(val index: Int) {
        var childNodeQuantity = 0
        var metadataQuantity = 0
        var parent: Node? = null
        var metadata = listOf<Int>()
        val childNodes = mutableListOf<Node>()

        fun isComplete() = childNodes.size == childNodeQuantity

        fun length(): Int = metadataQuantity + 2 + childNodes.map { it.length() }.sum()

        fun sumOfMetadata(): Int =  metadata.sum() + childNodes.map { it.sumOfMetadata() }.sum()

        fun nextChildIndex(): Int {
            if(childNodes.isEmpty()) return index + 2
            val latestNode = childNodes.last()
            return latestNode.index + latestNode.length()
        }
    }
}
