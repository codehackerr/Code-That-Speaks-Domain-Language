package com.agileconf;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Joiner.on;

public class Grid {
    public static final String NEW_LINE = System.getProperty("line.separator");

    public static final Function<GridRow,String> TO_CELL_STATE_STRING = new Function<GridRow, String>() {
        public String apply(GridRow gridRow) {
            return gridRow.state();
        }
    };
    private List<GridRow> gridCells;

    Grid(List<GridRow> gridCells) {
        this.gridCells = gridCells;
    }

    @Override
    public boolean equals(Object obj) {
        return gridCells.equals(((Grid) obj).gridCells);
    }

    @Override
    public String toString() {
        return on(NEW_LINE).join(Lists.transform(gridCells, TO_CELL_STATE_STRING));
    }

    public static Grid from_string(String gridString) {

        String[] rows = gridString.split(NEW_LINE);

        List<GridRow> grid_rows = new ArrayList<GridRow>();
        for (int rowSize = rows.length, rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            String row = rows[rowIndex];

            GridRow gridRow = GridRow.from_row_string(rowIndex, row);

            grid_rows.add(gridRow);
        }


        return new Grid(grid_rows);

    }


}
