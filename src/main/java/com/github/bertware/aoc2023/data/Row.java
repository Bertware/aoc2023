package com.github.bertware.aoc2023.data;

import java.util.List;

public record Row(int rowNumber, List<Cell> cellsOnRow, Row nextRow) {
    public Cell cellAt(int column) {
        return cellsOnRow.get(column);
    }
}
