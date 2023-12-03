package com.github.bertware.aoc2023.data;

import java.util.Objects;

public final class Cell {
    private final int row;
    private final int column;
    private final char charValue;
    private final Cell next;
    private Cell previous;

    public Cell(int row, int column, char charValue, Cell next) {
        this.row = row;
        this.column = column;
        this.charValue = charValue;
        this.next = next;
    }


    public void setPrevious(Cell cell) {
        this.previous = cell;
    }

    public boolean isDigit() {
        return Character.isDigit(charValue);
    }


    public int intValue() {
        return charValue - '0';
    }

    public String stringValue() {
        return Character.toString(charValue);
    }

    public boolean hasNextOnSameRow() {
        return next.row == row;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public char charValue() {
        return charValue;
    }

    public Cell next() {
        return next;
    }

    public boolean hasPrevious() {
        return previous != null;
    }
    public boolean hasPreviousOnSameRow() {
        return hasPrevious() && previous.row == row;
    }

    public Cell previous() {
        return previous;
    }

    @Override
    public String toString() {
        return "Cell[" +
                "row=" + row + ", " +
                "column=" + column + ", " +
                "charValue=" + charValue + ']';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Cell) obj;
        return this.row == that.row &&
                this.column == that.column &&
                this.charValue == that.charValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, charValue);
    }


}
