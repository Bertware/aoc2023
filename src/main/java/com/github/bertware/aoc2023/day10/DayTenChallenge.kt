package com.github.bertware.aoc2023.day10;

import com.github.bertware.aoc2023.AocChallenge
import java.nio.file.Path
import kotlin.math.max
import kotlin.math.min


class DayTenChallenge : AocChallenge() {

    fun part1(path: Path): Int {
        val grid = readArray(path)
        val positions = getPipelinePositions(grid)
        return (positions.size + 1) / 2
    }

    private fun getPipelinePositions(grid: Array<CharArray>): ArrayList<Coordinate> {
        val startPos = findStartPosition(grid)

        val positions = ArrayList<Coordinate>()
        positions.add(startPos)
        positions.add(getNextPositionFromStart(grid, startPos))

        while (positions.last() != startPos) {
            positions.add(getNextPosition(grid, positions))
        }
        positions.removeLast() // Remove the last item, since the start position is now present twice
        return positions
    }

    private fun getNextPositionFromStart(grid: Array<CharArray>, startPos: Coordinate): Coordinate {
        if (startPos.hasUp()) {
            val up = startPos.up()
            val charAbove = grid[up.row][up.col]
            if (charAbove in listOf('|', 'F', '7'))
                return up;
        }
        if (startPos.hasRight(grid)) {
            val right = startPos.right()
            val charToRight = grid[right.row][right.col]
            if (charToRight in listOf('-', 'J', '7'))
                return right;
        }
        return startPos.down() // The two connections are down and to the left
    }

    private fun getNextPosition(grid: Array<CharArray>, positions: List<Coordinate>): Coordinate {
        val lastPos = positions.last()
        val nextLastPos = positions[positions.size - 2]
        val value = grid[lastPos.row][lastPos.col]
        return when (value) {
            '|' -> listOf(lastPos.up(), lastPos.down())
            '-' -> listOf(lastPos.left(), lastPos.right())
            'L' -> listOf(lastPos.up(), lastPos.right())
            'J' -> listOf(lastPos.up(), lastPos.left())
            'F' -> listOf(lastPos.down(), lastPos.right())
            '7' -> listOf(lastPos.down(), lastPos.left())
            else -> throw IllegalArgumentException("No next position")
        }.first { it != nextLastPos }
    }

    private fun findStartPosition(grid: Array<CharArray>): Coordinate {
        for (r in grid.indices) {
            val row = grid[r]
            for (c in row.indices)
                if (row[c] == 'S')
                    return Coordinate(r, c)
        }
        throw IllegalStateException("No staring position in grid")
    }

    /*===================================================================
     *===================================================================
     * PART 2
     *===================================================================
     *=================================================================*/

    fun part2(path: Path): Int {
        val grid = readArray(path)
        val pipePositions = getPipelinePositions(grid)
        // now we have the entire grid, find the enclosed parts
        val corners = getCornerPoints(pipePositions)
        // now we have a polygon, so we can run point in polygon
        var enclosedPointCount = 0
        for (i in grid.indices)
            for (j in grid[i].indices)
                enclosedPointCount += if (!pipePositions.contains(Coordinate(i, j)) && inPolygon(
                        Coordinate(i, j),
                        corners
                    )
                ) 1 else 0
        println()
        return enclosedPointCount;
    }

    private fun inPolygon(coordinate: Coordinate, corners: List<Coordinate>): Boolean {
        // Point in polygon algorithm, shoot rays down from each point and check the number of intersections
        var intersections = 0
        for (i in corners.indices) {
            val c1 = corners[i]
            val c2 = corners[(i + 1) % corners.size]
            if (c1.col == c2.col) { // Vertical walls have no effect, skip them
                val c0 = corners[(i - 1 + corners.size) % corners.size]
                val c3 = corners[(i + 2) % corners.size]
                // ] or [ vertical lines are ignored, but Z or S shapes should count
                if ((c0.col < c1.col && c3.col < c1.col) || (c0.col > c1.col && c3.col > c1.col))
                    continue
            }
            // Only vertical and horizontal lines, so only need to check horizontal lines here
            if (c1.row < coordinate.row
                && min(c1.col, c2.col) <= coordinate.col
                && max(c1.col, c2.col) >= coordinate.col
            ) {
                intersections += 1
            }
        }
        val enclosedInPolygon = intersections > 0 && intersections % 2 == 1
        if (enclosedInPolygon)
            println(coordinate)
        return enclosedInPolygon
    }

    private fun getCornerPoints(pipePositions: List<Coordinate>): List<Coordinate> {
        val results = ArrayList<Coordinate>()

        val lastIndex = pipePositions.size - 1

        if (pipePositions[lastIndex].col == pipePositions[0].col && pipePositions[1].col != pipePositions[0].col)
            results.add(pipePositions[0])
        if (pipePositions[lastIndex].row == pipePositions[0].row && pipePositions[1].row != pipePositions[0].row)
            results.add(pipePositions[0])

        for (i in 1..<pipePositions.size - 1) {
            if (pipePositions[i - 1].col == pipePositions[i].col && pipePositions[i + 1].col != pipePositions[i].col)
                results.add(pipePositions[i])
            if (pipePositions[i - 1].row == pipePositions[i].row && pipePositions[i + 1].row != pipePositions[i].row)
                results.add(pipePositions[i])
        }

        if (pipePositions[lastIndex].col == pipePositions[lastIndex - 1].col && pipePositions[lastIndex].col != pipePositions[0].col)
            results.add(pipePositions[lastIndex])
        if (pipePositions[lastIndex].row == pipePositions[lastIndex - 1].row && pipePositions[lastIndex].row != pipePositions[0].row)
            results.add(pipePositions[lastIndex])

        return results;
    }

    data class Coordinate(val row: Int, val col: Int) {
        fun hasUp(): Boolean {
            return row > 0
        }

        fun up(): Coordinate {
            return Coordinate(row - 1, col)
        }

        fun down(): Coordinate {
            return Coordinate(row + 1, col)
        }

        fun left(): Coordinate {
            return Coordinate(row, col - 1)
        }

        fun hasRight(grid: Array<CharArray>): Boolean {
            return col < grid[0].size - 1
        }

        fun right(): Coordinate {
            return Coordinate(row, col + 1)
        }
    }

}
