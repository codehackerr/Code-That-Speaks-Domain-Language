package com.gol;

import org.junit.Test;

import static com.gol.Cell.new_cell;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GridCellsTest {

    @Test
    public void cellStateAsString(){
        GridCells gridCells = new GridCells(new_cell(0, 0, false), new_cell(0, 1, true));
        assertThat(gridCells.state(), is("-x"));
    }

}
