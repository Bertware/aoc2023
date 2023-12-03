package com.github.bertware.aoc2023.data;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class AocMatrix implements Iterable<Cell> {
    private final char[][] data;
    private final List<Row> rows;

    public AocMatrix(char[][] data) {
        this.data = data;
        rows = new ArrayList<>();
        initializeRowsAndCells();
    }

    private void initializeRowsAndCells() {
        Cell nextCell = null;
        Row nextRow = null;
        // Loop in reverse to create forward pointers
        for (int r = data.length - 1; r >= 0; r--) {
            List<Cell> cellsOnRow = new ArrayList<>();
            for (int c = data[r].length - 1; c >= 0; c--) {
                Cell cell = new Cell(r, c, data[r][c], nextCell);

                if (nextCell != null) {
                    nextCell.setPrevious(cell);
                }

                cellsOnRow.addFirst(cell); // going back to front, so add in front of the list
                nextCell = cell;
            }
            Row row = new Row(r, cellsOnRow, nextRow);
            nextRow = row;
            rows.addFirst(row); // going back to front, so add in front of the list
        }
    }

    public char charAt(int row, int column) {
        return rows.get(row).cellAt(column).charValue();
    }

    public int intAt(int row, int column) {
        return rows.get(row).cellAt(column).intValue();
    }

    public String stringAt(int row, int column) {
        return rows.get(row).cellAt(column).stringValue();
    }

    public int rowCount() {
        return rows.size();
    }

    public int columnCount() {
        return data[0].length;
    }

    public Cell start() {
        return rows.get(0).cellAt(0);
    }

    public List<Cell> getNeighbouringCellsWhere(Cell c, Predicate<Cell> filter) {
        return getNeighbouringAocMatrixCells(c.row(), c.column()).stream().filter(filter).toList();
    }

    public Set<Cell> getNeighbouringAocMatrixCells(int row, int column) {
        Set<Cell> neighbouringCells = new HashSet<>();
        for (int r = Math.max(row - 1, 0); r <= Math.min(rowCount() - 1, row + 1); r++) {
            for (int c = Math.max(column - 1, 0); c <= Math.min(columnCount() - 1, column + 1); c++) {
                if (r == row && c == column) {
                    continue; // Don't include the current cell
                }
                neighbouringCells.add(rows.get(r).cellAt(c));
            }
        }
        return neighbouringCells;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<>() {
            Cell c = start();

            @Override
            public boolean hasNext() {
                return c.next() != null;
            }

            @Override
            public Cell next() {
                Cell result = c;
                c = c.next();
                return result;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Cell> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Cell> spliterator() {
        return Iterable.super.spliterator();
    }

    public Stream<Cell> stream() {
        return StreamSupport.stream(spliterator(), false);
    }


}
