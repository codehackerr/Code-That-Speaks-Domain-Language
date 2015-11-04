package com.gol;

import com.google.common.base.Function;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Joiner.on;
import static com.google.common.collect.Iterables.concat;
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

    public Grid next_generation() {

        List<GridCells> new_generation = new ArrayList(transform(rows, new Function<GridCells, GridCells>() {
            public GridCells apply(GridCells cells) {
                return map_row(cells);
            }
        }));

        return new Grid(new_generation);
    }

    @Override
    public boolean equals(Object obj) {
        return rows.equals(((Grid) obj).rows);
    }

    @Override
    public String toString() {
        return on(NEW_LINE).join(transform(rows, TO_CELL_STATE_STRING));
    }

    public static Grid from_string(String grid_string) {

        String[] rows_as_string = split_by_row_boundary(grid_string);

        List<GridCells> grid_rows = new ArrayList<GridCells>();

        for (int row_size = rows_as_string.length, rowIndex = 0; rowIndex < row_size; rowIndex++) {
            GridCells grid_row = map_row(rows_as_string, rowIndex);
            grid_rows.add(grid_row);
        }

        return new Grid(grid_rows);
    }

    private static GridCells map_row(String[] rows, int row_index) {
        String row = rows[row_index];
        return GridCells.from_string(row_index, row);
    }


    private static String[] split_by_row_boundary(String gridString) {
        return gridString.split(NEW_LINE);
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
        GridCells all_cells = this.flatten();
        GridCells neighbours = all_cells.filter_by(cell.neighbour_of());
        return neighbours.live_cells();
    }

    private GridCells flatten() {
        return new GridCells(newArrayList(concat(rows)));
    }

    public boolean contains(Cell cell) {
        return this.flatten().contains(cell);
    }
}
