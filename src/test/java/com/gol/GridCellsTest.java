package com.gol;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GridCellsTest {

    @Test
    public void cellStateAsString(){
        GridCells gridCells = new GridCells(newCell(0, 0, false), newCell(0, 1, true));
        assertThat(gridCells.state(), is("-x"));
    }

    private Cell newCell(int x, int y, boolean cellState) {
        return new Cell(x,y,cellState);
    }
}
