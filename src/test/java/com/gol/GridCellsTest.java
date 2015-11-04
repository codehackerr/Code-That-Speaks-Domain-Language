package com.gol;

import com.google.common.base.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static com.gol.Cell.new_cell;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GridCellsTest {

    private GridCells cells;
    private Cell live_cell;
    private Cell dead_cell;

    @Before
    public void setUp() throws Exception {
        live_cell = new_cell(0, 1, true);
        dead_cell = new_cell(0, 0, false);
        cells = new GridCells(dead_cell, live_cell);
    }

    @Test
    public void cell_state_as_string(){
        GridCells gridCells = cells;
        assertThat(gridCells.state(), is("-x"));
    }

    @Test
    public void live_cells(){
        GridCells only_live_cells = new GridCells(live_cell);
        assertThat(this.cells.live_cells(), is(only_live_cells));
    }

    @Test
    public void filter(){
        Predicate predicate = mock(Predicate.class);

        cells.filter_by(predicate);

        verify(predicate).apply(live_cell);
        verify(predicate).apply(dead_cell);
    }

}
