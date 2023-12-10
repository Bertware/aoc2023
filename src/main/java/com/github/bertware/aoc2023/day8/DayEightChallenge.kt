package com.github.bertware.aoc2023.day8

import com.github.bertware.aoc2023.AocChallenge
import java.nio.file.Path
import java.util.stream.Collectors

class DayEightChallenge : AocChallenge() {

    fun part1(path: Path): Int {
        val lines = readLines(path);
        val directions = lines.removeFirst().toList()
        lines.removeFirst();
        val nodes: Map<String, LeftRightNode> =
            lines.stream().map { LeftRightNode(it) }.collect(Collectors.toMap(LeftRightNode::name, { it }))
        var steps = 0
        var node = "AAA"
        while (node != "ZZZ") {
            node = if (directions[steps % directions.size].equals('L')) nodes[node]!!.left else nodes[node]!!.right
            steps += 1
        }
        return steps

    }

    fun part2(path: Path): Long {
        val lines = readLines(path);
        val directions = lines.removeFirst().toList()
        lines.removeFirst();
        val nodes: Map<String, LeftRightNode> =
            lines.stream().map { LeftRightNode(it) }.collect(Collectors.toMap(LeftRightNode::name, { it }))
        var currentNodes = nodes.keys.filter { it.endsWith('A') }.toList()

        var endPositions = getEndNodePositions(nodes, currentNodes, directions)
        return endPositions.reduce { acc, it -> lcm(acc, it) }
    }

    fun gcd(a: Long, b: Long): Long {
        return if (a == 0L) b else gcd(b % a, a)
    }

    fun lcm(a: Long, b: Long): Long {
        return a * b / gcd(a, b)
    }

    private fun getEndNodePositions(
        allNodes: Map<String, LeftRightNode>,
        currentNodes: List<String>,
        directions: List<Char>
    ): ArrayList<Long> {
        var endNodePositions = ArrayList<Long>();
        for (n in 0..<currentNodes.size) {
            endNodePositions.add(0)
            var node = currentNodes[n]
            var i = 0L;
            while (endNodePositions[n] == 0L) {
                node =
                    if (directions[(i++ % directions.size).toInt()].equals('L')) allNodes[node]!!.left else allNodes[node]!!.right
                if (node.endsWith('Z'))
                    endNodePositions[n] = i
            }
        }
        return endNodePositions
    }


    class LeftRightNode(line: String) {
        val name = line.split(" = ")[0]
        val left = line.split("(")[1].split(",")[0]
        val right = line.split(", ")[1].trim(')')
    }

}