package com.gol;

import com.google.common.collect.Lists;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class GameOfLifeRulesTest {

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
                new GridCells(cell_0_0)
        ));

        assertThat(cell_0_0, is(alive()));

        Grid next_generation = grid.next_generation();

        assertThat(cell_0_0, is_dead_in(next_generation));
    }

    private Matcher<Cell> alive() {
        return new BaseMatcher<Cell>() {
            public boolean matches(Object cell_as_object) {
                return ((Cell) cell_as_object).is_alive();
            }

            public void describeTo(Description description) {
            }
        };
    }

    @Test
    public void live_cell_with_two_live_neighbours() {

        Grid old_generation = new Grid(Lists.<GridCells>newArrayList(
                new GridCells(cell_0_0, cell_0_1, cell_0_2),
                new GridCells(cell_1_0, cell_1_1, cell_1_2),
                new GridCells(cell_2_0, cell_2_1, cell_2_2)

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

    private Matcher<? super Cell> is_dead_in(final Grid new_generation) {
        return new BaseMatcher<Cell>() {
            public boolean matches(Object cell_object) {
                Cell cell = (Cell) cell_object;
                Cell dead_cell = cell.die();
                return new_generation.contains(dead_cell);
            }

            public void describeTo(Description description) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }


}
