package com.github.bertware.aoc2023.day6

import com.github.bertware.aoc2023.AocChallenge
import java.nio.file.Path

class DaySixChallenge : AocChallenge() {

    fun part1(path: Path): Int {
        val lines = readLines(path)
        val recordValues = lines.map { it -> it.split(":")[1].split(" ").filter { !it.isBlank() } }.toList();
        val records = ArrayList<RaceRecord>();
        for (i in 0..<recordValues[0].size)
            records.add(RaceRecord(recordValues[0][i].toLong(), recordValues[1][i].toLong()))

        val list = records.map { rc -> LongRange(1, rc.time).filter { ((rc.distance) / it) + it < rc.time }.count() }.toList();
        return list.reduce { acc, it -> acc * it }
    }

    fun part2(path: Path): Int {
        val lines = readLines(path)
        val recordValues = lines.map { it -> it.split(":")[1].replace(" ", "").toLong() }.toList();
        val record = RaceRecord(recordValues[0], recordValues[1])
        return LongRange(1, record.time).filter { ((record.distance) / it) + it < record.time }.count();
    }

    data class RaceRecord(val time: Long, val distance: Long)
}