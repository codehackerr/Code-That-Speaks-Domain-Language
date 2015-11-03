package com.gol;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

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
    public static final int UNDER_POPULATION_THRESHOLD = 2;
    public static final int OVER_POPULATION_THRESHOLD = 3;
    public static final int RESURRECTION_THRESHOLD = 3;
    private List<GridCells> rows;

    Grid(List<GridCells> rows) {
        this.rows = rows;
    }

    @Override
    public boolean equals(Object obj) {
        return rows.equals(((Grid) obj).rows);
    }

    @Override
    public String toString() {
        return on(NEW_LINE).join(transform(rows, TO_CELL_STATE_STRING));
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

        List<GridCells> new_generation = new ArrayList(transform(rows, new Function<GridCells, GridCells>() {
            public GridCells apply(GridCells cells) {
                return map_row(cells);
            }
        }));

        return new Grid(new_generation);
    }

    private GridCells map_row(GridCells row) {
        GridCells mapped_row = new GridCells();
        for (Cell cell : row) {
            mapped_row.add(map_cell(cell));
        }
        return mapped_row;
    }

    private Cell map_cell(Cell cell) {
        if (cell.is_alive() && is_in_under_populated_area(cell)) return cell.die();
        else if (cell.is_alive() && is_in_over_populated_area(cell)) return cell.die();
        else if (cell.is_dead() && is_in_pleasant_conditions_to_resurrect(cell)) return cell.live();
        else return cell.copy();
    }


    private boolean is_in_pleasant_conditions_to_resurrect(Cell cell) {
        return live_neighbours_of(cell).count() == RESURRECTION_THRESHOLD;
    }


    private boolean is_in_over_populated_area(Cell cell) {
        return live_neighbours_of(cell).count() > OVER_POPULATION_THRESHOLD;
    }

    private boolean is_in_under_populated_area(Cell cell) {
        return live_neighbours_of(cell).count() < UNDER_POPULATION_THRESHOLD;
    }

    private GridCells live_neighbours_of(final Cell cell) {
        GridCells neighbours = filter_by(cell.neighbour_of());
        return live_cells(neighbours);
    }

    private GridCells live_cells(GridCells cells) {
        return new GridCells(filter(cells, Cell.IS_LIVE));

    }

    private GridCells filter_by(Predicate<Cell> predicate) {
        return new GridCells(filter(all_cells(), predicate));
    }

    private GridCells all_cells() {
        GridCells all_cells = new GridCells();
        for (GridCells gridRow : rows) {
            all_cells.addAll(gridRow);
        }
        return all_cells;
    }

    public boolean contains(Cell cell) {
        return this.rows.get(cell.row_index()).contains(cell);
    }
}
