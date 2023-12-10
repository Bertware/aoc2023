package com.github.bertware.aoc2023.day9;

import com.github.bertware.aoc2023.AocChallenge
import java.nio.file.Path


class DayNineChallenge : AocChallenge() {

    fun part1(path: Path): Int {
        return readLines(path).map { getNextValue(it.split(" ").map { it.toInt() }.toList()) }.sum()
    }

    fun getNextValue(list: List<Int>): Int {
        if (list.all { it == 0 })
            return 0
        val diffs = ArrayList<Int>()
        for (i in 1..<list.size)
            diffs.add(list[i] - list[i - 1])
        val result = list.last() + getNextValue(diffs)
        return result
    }

    fun part2(path: Path): Int {
        return readLines(path).map { getPreviousValue(it.split(" ").map { it.toInt() }.toList()) }.sum()
    }

    fun getPreviousValue(list: List<Int>): Int {
        if (list.all { it == 0 }) {
            return 0
        }
        val diffs = ArrayList<Int>()
        for (i in 1..<list.size)
            diffs.add(list[i] - list[i - 1])
        val result = list.first() - getPreviousValue(diffs)
        return result
    }
}
