package com.agileconf;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GridRowTest {

    @Test
    public void cellStateAsString(){
        GridRow gridRow = new GridRow(newCell(0, 0, false), newCell(0, 1, true));
        assertThat(gridRow.state(), is("-x"));
    }

    private Cell newCell(int x, int y, boolean cellState) {
        return new Cell(x,y,cellState);
    }
}
