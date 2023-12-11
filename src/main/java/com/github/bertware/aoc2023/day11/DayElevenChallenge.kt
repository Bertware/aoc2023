package com.github.bertware.aoc2023.day11;

import com.github.bertware.aoc2023.AocChallenge
import java.nio.file.Path
import kotlin.math.abs

class DayElevenChallenge : AocChallenge() {

    fun part1(path: Path): Long {
        return getDistances(path, 2)
    }

    fun part2(path: Path): Long {
        return getDistances(path, 1000000)
    }

    fun getDistances(path: Path, expansionFactor: Int): Long {
        val grid = readArray(path)
        val expandingRows = grid.indices.toMutableSet()
        val expandingColumns = grid[0].indices.toMutableSet()
        val positions = ArrayList<Coordinate>()
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (grid[row][col] != '.') {
                    expandingRows.remove(row)
                    expandingColumns.remove(col)
                    positions.add(Coordinate(row, col))
                }
            }
        }

        val colMap = grid.indices.associateWith { col -> col + (expandingColumns.count { it < col } * (expansionFactor-1)) }
        val rowMap = grid[0].indices.associateWith { row -> row + (expandingRows.count { it < row } * (expansionFactor-1)) }

        var sum = 0L
        for (i in positions.indices)
            for (j in i + 1..<positions.size)
                sum += abs(colMap[positions[i].col]!! - colMap[positions[j].col]!!) + abs(rowMap[positions[i].row]!! - rowMap[positions[j].row]!!)

        return sum
    }

    data class Coordinate(val row: Int, val col: Int) {

    }

}
