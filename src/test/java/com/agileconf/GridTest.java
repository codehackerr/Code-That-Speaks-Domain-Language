package com.agileconf;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.agileconf.Grid.NEW_LINE;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GridTest {


    private static final String TWO_BY_TWO =
            "--" + NEW_LINE +
            "--";


    @Test
    public void gridFromString() {
        Grid actual = Grid.parse(TWO_BY_TWO);
        assertThat(actual, equalTo(expectedTwoByTwo()));
    }

    @Test
    public void gridAsString(){
        assertThat(expectedTwoByTwo().toString(),is(TWO_BY_TWO));
    }

    private Grid expectedTwoByTwo() {
        List<Cells> cells = new ArrayList<Cells>();

        Cells firstRow = new Cells(newCell(0, 0), newCell(0, 1));
        Cells secondRow = new Cells(newCell(1, 0), newCell(1, 1));

        cells.add(firstRow);
        cells.add(secondRow);

        return new Grid(cells);
    }

    private Cell newCell(int x, int y) {
        return new Cell(x, y, false);
    }
}
