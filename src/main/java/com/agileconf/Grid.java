package com.agileconf;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    public static final String NEW_LINE = System.getProperty("line.separator");

    public static final Function<GridRow,String> TO_CELL_STATE_STRING = new Function<GridRow, String>() {
        public String apply(GridRow gridRow) {
            return gridRow.state();
        }
    };
    private List<GridRow> cells;

    Grid(List<GridRow> cells) {
        this.cells = cells;
    }

    @Override
    public boolean equals(Object obj) {
        return cells.equals(((Grid) obj).cells);
    }

    @Override
    public String toString() {
        return Joiner.on(NEW_LINE).join(Lists.transform(cells, TO_CELL_STATE_STRING));
    }

    public static Grid parse(String gridString) {

        String[] rows = gridString.split(NEW_LINE);

        List<GridRow> grid_rows = new ArrayList<GridRow>();
        for (int rowSize = rows.length, rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            String row = rows[rowIndex];
            GridRow gridRow = new GridRow();
            char[] cellChars = row.toCharArray();
            for (int cellSize = cellChars.length, columnIndex = 0; columnIndex < cellSize; columnIndex++) {
                Cell cell = new Cell(rowIndex, columnIndex, cellstate(cellChars[columnIndex]));
                gridRow.add(cell);
            }

            grid_rows.add(gridRow);

        }


        return new Grid(grid_rows);

    }

    private static boolean cellstate(Character character) {
        return character.equals("x");
    }
}
