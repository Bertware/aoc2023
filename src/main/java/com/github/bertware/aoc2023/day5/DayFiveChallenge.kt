package com.github.bertware.aoc2023.day5

import com.github.bertware.aoc2023.AocChallenge
import java.nio.file.Path
import java.util.stream.Collectors
import kotlin.math.max
import kotlin.math.min

class DayFiveChallenge : AocChallenge() {

    fun part1(path: Path): Long {
        val lines = readLines(path)
        val seeds = lines.removeFirst().split(": ")[1].split(" ").stream().map { it.toLong() }.collect(Collectors.toList())
        lines.removeFirst() // skip empty line
        val maps = readMapping(lines)
        for (map in maps) {
            println(seeds)
            for (i in 0..<seeds.size) {
                seeds[i] = map.map(seeds[i])
            }
        }
        return seeds.min()
    }

    fun part2(path: Path): Long {
        val lines = readLines(path)
        var seeds = ArrayList<InputRange>()
        val matcher = "(\\d+) (\\d+)".toRegex()
        matcher.findAll(lines.removeFirst().split(": ")[1]).map { InputRange(it.groups[1]!!.value.toLong(), it.groups[1]!!.value.toLong() + it.groups[2]!!.value.toLong() - 1) }.toCollection(seeds)
        lines.removeFirst() // skip empty line
        val maps = readMapping(lines)
        for (map in maps) {
            println(seeds)
            println(map)
            seeds = map.map(seeds)
            println(seeds)
            println()
        }

        return seeds.map { it -> it.startInclusive }.min()
    }

    private fun readMapping(lines: MutableList<String>): ArrayList<Map> {
        var from = ""
        var to = ""
        var mapLines = ArrayList<String>();
        val maps = ArrayList<Map>();
        for (line in lines) {
            if (line.isEmpty()) {
                maps.add(createMap(from, to, mapLines))
                from = "";       // signal a new map is coming
                continue
            } else if (from.isEmpty()) {
                mapLines = ArrayList();
                from = line.split(" ")[0].split("-")[0]
                to = line.split(" ")[0].split("-")[2]
            } else {
                mapLines.add(line)
            }
        }
        maps.add(createMap(from, to, mapLines));
        return maps;
    }

    private fun createMap(from: String, to: String, lines: java.util.ArrayList<String>): Map {
        var ranges = lines.map {
            val parts = it.split(" ")
            Range(parts[1].toLong(), parts[1].toLong() + parts[2].toLong() - 1, parts[0].toLong(), parts[0].toLong() + parts[2].toLong() - 1)
        }.sortedBy { it.inputFrom }.toList()
        // For part 2
        var filler = ArrayList<Range>()
        for (i in 1..<ranges.size) {
            val prevEndVal = ranges[i - 1].inputTo
            if (prevEndVal < ranges[i].inputFrom - 1) {
                filler.add(Range(prevEndVal + 1, ranges[i].inputFrom - 1, prevEndVal + 1, ranges[i].inputFrom - 1))
            }
        }
        filler.forEach { ranges.addLast(it) }
        ranges.sortedBy { it.inputFrom }
        return Map(from, to, ranges)
    }


    data class Map(val from: String, val to: String, val ranges: List<Range>) {
        fun map(input: Long): Long {
            return ranges.filter { it.contains(input) }.map { it.map(input) }.firstOrNull() ?: input
        }

        fun map(input: List<InputRange>): ArrayList<InputRange> {
            val result = ArrayList<InputRange>()
            for (item in input) {
                if (ranges.first().inputFrom > item.startInclusive) {
                    result.add(InputRange(item.startInclusive, min(item.endInclusive,ranges.first().inputFrom - 1)))
                }
                for (range in ranges) {
                    if (range.hasOverlap(item))
                        result.add(range.getOverlap(item)!!)
                }
                if (ranges.last().inputTo < item.endInclusive) {
                    result.add(InputRange(max(item.startInclusive, ranges.last().inputTo + 1), item.endInclusive))
                }
            }
            val sortedResult = ArrayList<InputRange>()
            result.sortedBy { it.startInclusive }.toCollection(sortedResult)
            return sortedResult
        }

        override fun toString(): String {
            return "$from-$to: $ranges"
        }
    }

    data class Range(val inputFrom: Long, val inputTo: Long, val outFrom: Long, val outTo: Long) {
        fun contains(value: Long): Boolean {
            return value in inputFrom..inputTo;
        }

        fun map(value: Long): Long {
            return outFrom + (value - inputFrom)
        }

        fun getOverlap(item: InputRange): InputRange? {
            if (!hasOverlap(item))
                return null
            val offset = outFrom - inputFrom
            return InputRange(max(inputFrom, item.startInclusive) + offset, min(inputTo, item.endInclusive) + offset)
        }

        fun hasOverlap(item: InputRange) =
                item.endInclusive >= inputFrom && item.startInclusive <= inputTo

        override fun toString(): String {
            return "$inputFrom-$inputTo => $outFrom-$outTo"
        }
    }

    data class InputRange(val startInclusive: Long, val endInclusive: Long) {
        fun contains(value: Long): Boolean {
            return value in startInclusive..endInclusive;
        }

        override fun toString(): String {
            return "$startInclusive-$endInclusive"
        }
    }

}