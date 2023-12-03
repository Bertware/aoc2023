package com.github.bertware.aoc2023.day3;

import com.github.bertware.aoc2023.AocChallenge;
import com.github.bertware.aoc2023.data.AocMatrix;
import com.github.bertware.aoc2023.data.Cell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayThreeChallenge extends AocChallenge {
    Logger log = LogManager.getLogger(DayThreeChallenge.class);

    public int runPart1(Path inputFilePath) {
        AocMatrix data = readMatrix(inputFilePath);
        int sum = 0;

        int number = 0;
        Cell prevCell = null;
        boolean neighbouringSymbol = false;
        for (Cell c : data) {
            log.info("Cell " + c.row() + ", " + c.column() + ": " + c.charValue());

            // Number ended by row change or current cell not being a number
            if ((c.row() > prevCell.row() && prevCell.isDigit()) || !c.isDigit()) {
                log.info("Ending number " + number);
                if (neighbouringSymbol) {
                    sum += number;
                }
                number = 0;
                neighbouringSymbol = false;
            }
            prevCell = c;

            if (c.isDigit()) {
                number = number * 10 + c.intValue();
                log.info("Appended number, result " + number);
                if (!data.getNeighbouringCellsWhere(c, this::isSymbol).isEmpty()) {
                    neighbouringSymbol = true;
                }
            }
        }

        return sum;
    }

    private boolean isSymbol(Cell cell) {
        return !cell.isDigit() && cell.charValue() != '.';
    }

    public int runPart2(Path inputFilePath) {
        AocMatrix data = readMatrix(inputFilePath);
        int sum = 0;
        for (Cell c : data) {
            log.info("Cell " + c.row() + ", " + c.column() + ": " + c.charValue());
            if (c.charValue() == '*') {
                List<Cell> digitCells = data.getNeighbouringCellsWhere(c, Cell::isDigit);
                List<Integer> neighbouringNumbers = extractNumbers(digitCells);
                if (neighbouringNumbers.size() == 2) {
                    sum += neighbouringNumbers.get(0) * neighbouringNumbers.get(1);
                }
            }
        }

        return sum;
    }

    private List<Integer> extractNumbers(List<Cell> digitCells) {
        List<Integer> numbers = new ArrayList<>();
        Set<Cell> handledCells = new HashSet<>();
        digitCells.forEach(c -> {
            if (handledCells.contains(c)) {
                return; // Dont handle cells twice
            }
            while (c.hasPreviousOnSameRow() && c.previous().isDigit()) {
                c = c.previous();
            }
            int number = c.intValue();
            handledCells.add(c);
            while (c.hasNextOnSameRow() && c.next().isDigit()) {
                c = c.next();
                number = number * 10 + c.intValue();
                handledCells.add(c);
            }
            numbers.add(number);
        });
        return numbers;
    }

    record Number(int startPos, int endPos, int value) {

    }
}
