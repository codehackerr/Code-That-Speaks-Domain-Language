package com.gol;

import com.google.common.base.Function;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Joiner.on;
import static com.google.common.collect.Lists.transform;

public class Grid {
    public static final String NEW_LINE = System.getProperty("line.separator");

    public static final Function<GridCells,String> TO_CELL_STATE_STRING = new Function<GridCells, String>() {
        public String apply(GridCells gridCells) {
            return gridCells.state();
        }
    };
    private List<GridCells> gridRows;

    Grid(List<GridCells> gridRows) {
        this.gridRows = gridRows;
    }

    @Override
    public boolean equals(Object obj) {
        return gridRows.equals(((Grid) obj).gridRows);
    }

    @Override
    public String toString() {
        return on(NEW_LINE).join(transform(gridRows, TO_CELL_STATE_STRING));
    }

    public static Grid from_string(String gridString) {

        String[] rows = gridString.split(NEW_LINE);

        List<GridCells> grid_rows = new ArrayList<GridCells>();
        for (int rowSize = rows.length, rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            String row = rows[rowIndex];

            GridCells gridRow = GridCells.from_string(rowIndex, row);

            grid_rows.add(gridRow);
        }


        return new Grid(grid_rows);

    }


    public Grid next_generation() {
        for (GridCells gridRow : gridRows) {
            for (Cell cell : gridRow) {
                cell.die();
            }

        }
        return null;
    }
}
