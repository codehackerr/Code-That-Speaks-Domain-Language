package com.gol;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.gol.Cell.from_string;
import static com.gol.Grid.NEW_LINE;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GridTest {


    private static final String TWO_BY_TWO =
            "--" + NEW_LINE +
            "--";

    @Test
    public void gridFromString() {
        Grid actual = Grid.from_string(TWO_BY_TWO);
        assertThat(actual, equalTo(expectedTwoByTwo()));
    }

    @Test
    public void gridAsString(){
        assertThat(expectedTwoByTwo().toString(),is(TWO_BY_TWO));
    }

    private Grid expectedTwoByTwo() {
        List<GridCells> cells = new ArrayList<GridCells>();

        GridCells firstRow = new GridCells(cell(0, 0), cell(0, 1));
        GridCells secondRow = new GridCells(cell(1, 0), cell(1, 1));

        cells.add(firstRow);
        cells.add(secondRow);

        return new Grid(cells);
    }

    private Cell cell(int row_index, int column_index) {
        return from_string(row_index, column_index, '_');
    }

}
