package com.agileconf;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    public static final String NEW_LINE = System.getProperty("line.separator");

    public static final Function<Cells,String> TO_CELL_STATE_STRING = new Function<Cells, String>() {
        public String apply(Cells cells) {
            return cells.state();
        }
    };
    private List<Cells> cells;

    Grid(List<Cells> cells) {
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

        List<Cells> cellsGrid = new ArrayList<Cells>();
        for (int rowSize = rows.length, rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            String row = rows[rowIndex];
            Cells cells = new Cells();
            char[] cellChars = row.toCharArray();
            for (int cellSize = cellChars.length, columnIndex = 0; columnIndex < cellSize; columnIndex++) {
                Cell cell = new Cell(rowIndex, columnIndex, cellstate(cellChars[columnIndex]));
                cells.add(cell);
            }

            cellsGrid.add(cells);

        }


        return new Grid(cellsGrid);

    }

    private static boolean cellstate(Character character) {
        return character.equals("x");
    }
}
