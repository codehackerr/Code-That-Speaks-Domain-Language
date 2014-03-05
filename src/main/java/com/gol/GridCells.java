package com.gol;

import com.google.common.base.Function;

import java.util.ArrayList;

import static com.google.common.base.Joiner.on;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;

public class GridCells extends ArrayList<Cell> {
    public GridCells(Cell... cells) {
        this.addAll(newArrayList(cells));
    }

    static GridCells from_string(int rowIndex, String cells_as_String) {
        GridCells grid_row = new GridCells();
        char[] cell_chars = cells_as_String.toCharArray();
        for (int cell_size = cell_chars.length, columnIndex = 0; columnIndex < cell_size; columnIndex++) {
            Cell cell = new Cell(rowIndex, columnIndex, cellstate(cell_chars[columnIndex]));
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
