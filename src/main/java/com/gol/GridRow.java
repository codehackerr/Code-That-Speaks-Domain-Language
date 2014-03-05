package com.gol;

import com.google.common.base.Function;

import java.util.ArrayList;

import static com.google.common.base.Joiner.on;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;

public class GridRow extends ArrayList<Cell> {
    public GridRow(Cell... cells) {
        this.addAll(newArrayList(cells));
    }

    static GridRow from_row_string(int rowIndex, String row) {
        GridRow grid_row = new GridRow();
        char[] cellChars = row.toCharArray();
        for (int cellSize = cellChars.length, columnIndex = 0; columnIndex < cellSize; columnIndex++) {
            Cell cell = new Cell(rowIndex, columnIndex, cellstate(cellChars[columnIndex]));
            grid_row.add(cell);
        }
        return grid_row;
    }

    public String state() {
        return on("").join(transform(this, new Function<Cell, String>() {
            public String apply(Cell cell) {
                return cell.state();
            }
        }));
    }

    private static boolean cellstate(Character character) {
        return character.equals("x");
    }
}
