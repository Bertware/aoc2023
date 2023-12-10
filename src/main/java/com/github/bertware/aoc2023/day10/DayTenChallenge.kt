package com.github.bertware.aoc2023.day10;

import com.github.bertware.aoc2023.AocChallenge
import java.nio.file.Path
import kotlin.math.max
import kotlin.math.min


class DayTenChallenge : AocChallenge() {

    fun part1(path: Path): Int {
        val grid = readArray(path)
        var positions = getPipelinePositions(grid)
        return (positions.size + 1) / 2
    }

    private fun getPipelinePositions(grid: Array<CharArray>): ArrayList<Coordinate> {
        val startPos = findStartPosition(grid)

        var positions = ArrayList<Coordinate>()
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
            val upval = grid[up.row][up.col]
            if (upval in listOf('|', 'F', '7'))
                return up;
        }
        if (startPos.hasRight(grid)) {
            val right = startPos.right()
            val rightval = grid[right.row][right.col]
            if (rightval in listOf('-', 'J', '7'))
                return right;
        }
        return startPos.down() // The two connections are on down and left
    }

    private fun getNextPosition(grid: Array<CharArray>, positions: List<Coordinate>): Coordinate {
        val lastPos = positions.last()
        val nextLastPos = positions[positions.size - 2]
        val value = grid[lastPos.row][lastPos.col]

        if (value == '|') {
            return getNextPosition(lastPos, nextLastPos, grid, listOf(Direction.UP, Direction.DOWN))
        }
        if (value == '-') {
            return getNextPosition(lastPos, nextLastPos, grid, listOf(Direction.LEFT, Direction.RIGHT))
        }
        if (value == 'L') {
            return getNextPosition(lastPos, nextLastPos, grid, listOf(Direction.UP, Direction.RIGHT))
        }
        if (value == 'J') {
            return getNextPosition(lastPos, nextLastPos, grid, listOf(Direction.UP, Direction.LEFT))
        }
        if (value == 'F') {
            return getNextPosition(lastPos, nextLastPos, grid, listOf(Direction.DOWN, Direction.RIGHT))
        }
        if (value == '7') {
            return getNextPosition(lastPos, nextLastPos, grid, listOf(Direction.DOWN, Direction.LEFT))
        }
        throw IllegalArgumentException("No next position")
    }

    private fun getNextPosition(
        lastPos: Coordinate,
        nextLastPos: Coordinate,
        grid: Array<CharArray>,
        dirs: List<Direction>
    ): Coordinate {
        if (dirs.contains(Direction.UP) && lastPos.hasUp() && nextLastPos != lastPos.up())
            return lastPos.up()
        if (dirs.contains(Direction.DOWN) && lastPos.hasDown(grid) && nextLastPos != lastPos.down())
            return lastPos.down()
        if (dirs.contains(Direction.LEFT) && lastPos.hasLeft() && nextLastPos != lastPos.left())
            return lastPos.left()
        if (dirs.contains(Direction.RIGHT) && lastPos.hasRight(grid) && nextLastPos != lastPos.right())
            return lastPos.right()
        throw IllegalArgumentException("No next position")
    }

    private fun findStartPosition(grid: Array<CharArray>): Coordinate {
        for (r in 0..<grid.size) {
            val row = grid[r]
            for (c in 0..<row.size)
                if (row[c].equals('S'))
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
        var pipePositions = getPipelinePositions(grid)
        // now we have the entire grid, find the enclosed parts
        val corners = getCornerPoints(pipePositions)
        // now we have a polygon, so we can run point in polygon
        var enclosedPointCount = 0
        for (i in 0..<grid.size)
            for (j in 0..<grid[i].size)
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
        for (i in 0..<corners.size) {
            val c1 = corners.get(i)
            val c2 = corners.get((i + 1) % corners.size)
            if (c1.col == c2.col) { // Vertical walls have no effect, skip them
                val c0 = corners.get((i - 1 + corners.size) % corners.size)
                val c3 = corners.get((i + 2) % corners.size)
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


    enum class Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN

    }

    data class Coordinate(val row: Int, val col: Int) {
        fun hasUp(): Boolean {
            return row > 0
        }

        fun up(): Coordinate {
            return Coordinate(row - 1, col)
        }

        fun hasDown(grid: Array<CharArray>): Boolean {
            return row < grid.size - 1
        }

        fun down(): Coordinate {
            return Coordinate(row + 1, col)
        }

        fun hasLeft(): Boolean {
            return col > 0
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
