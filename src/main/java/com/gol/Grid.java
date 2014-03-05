package com.gol;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Joiner.on;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;

public class Grid {
    public static final String NEW_LINE = System.getProperty("line.separator");

    public static final Function<GridCells, String> TO_CELL_STATE_STRING = new Function<GridCells, String>() {
        public String apply(GridCells gridRow) {
            return gridRow.state();
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

        List<GridCells> new_generation = Lists.<GridCells>newArrayList();

        for (GridCells gridRow : gridRows) {

            GridCells mapped_row = new GridCells();
            new_generation.add(mapped_row);

            for (Cell cell : gridRow) {
                if (live_neighbours_of(cell).count() < 2)
                    mapped_row.add(cell.die());
                else
                    mapped_row.add(cell.copy());
            }

        }

        return new Grid(new_generation);
    }

    private GridCells live_neighbours_of(final Cell cell) {
        return map_cells_by(neighbour_of(cell));
    }

    private Predicate<Cell> neighbour_of(final Cell cell) {
        return new Predicate<Cell>() {
            public boolean apply(Cell c) {
                return c.is_neighbour_of(cell);
            }
        };
    }

    private GridCells map_cells_by(Predicate<Cell> predicate) {
        return new GridCells(filter(all_cells(), predicate));
    }

    private GridCells all_cells() {
        GridCells all_cells = new GridCells();
        for (GridCells gridRow : gridRows) {
            all_cells.addAll(gridRow);
        }
        return all_cells;
    }

    public boolean contains(Cell cell) {
        return this.gridRows.get(cell.row_index()).contains(cell);
    }
}
