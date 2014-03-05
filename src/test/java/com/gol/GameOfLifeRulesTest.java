package com.gol;

import com.google.common.collect.Lists;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameOfLifeRulesTest {

    @Mock
    private Cell lonely_live_cell;

    private Cell cell_0_0;
    private Cell cell_0_1;
    private Cell cell_0_2;
    private Cell cell_1_0;
    private Cell cell_1_1;
    private Cell cell_1_2;
    private Cell cell_2_0;
    private Cell cell_2_1;
    private Cell cell_2_2;

    @Before
    public void setUp() {
        initMocks(this);
        this.cell_0_0 = new Cell(0, 0, true);
        this.cell_0_1 = new Cell(0, 1, false);
        this.cell_0_2 = new Cell(0, 2, false);
        this.cell_1_0 = new Cell(1, 0, false);
        this.cell_1_1 = new Cell(1, 1, true);
        this.cell_1_2 = new Cell(1, 2, false);
        this.cell_2_0 = new Cell(2, 0, false);
        this.cell_2_1 = new Cell(2, 1, false);
        this.cell_2_2 = new Cell(2, 2, true);
    }

    @Test
    public void live_cell_with_less_than_two_live_neighbours() {
        Grid grid = new Grid(Lists.<GridCells>newArrayList(
                new GridCells(lonely_live_cell)
        ));

        grid.next_generation();

        verify(lonely_live_cell).die();
    }

    @Test
    public void live_cell_with_two_live_neighbours() {

        Grid old_generation = new Grid(Lists.<GridCells>newArrayList(
                new GridCells(cell_0_0, cell_0_1,cell_0_2),
                new GridCells(cell_1_0,cell_1_1,cell_1_2),
                new GridCells(cell_2_0,cell_2_1,cell_2_2)

        ));

        Grid new_generation = old_generation.next_generation();

        assertThat(cell_1_1, lives_on_to(new_generation));

    }


    private Matcher<? super Cell> lives_on_to(final Grid new_generation) {
        return new BaseMatcher<Cell>() {
            public boolean matches(Object cell_object) {
                Cell cell = (Cell) cell_object;
                return new_generation.contains(cell);
            }

            public void describeTo(Description description) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }


}
