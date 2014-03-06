package com.gol;

import com.gol.matcher.CellMatchers;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import static com.gol.matcher.CellMatchers.alive;
import static com.gol.matcher.CellMatchers.is_dead_in;
import static com.gol.matcher.CellMatchers.lives_on_to;
import static org.hamcrest.core.Is.is;
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
    private Grid old_generation;

    @Before
    public void setUp() {
        this.cell_0_0 = new Cell(0, 0, true);
        this.cell_0_1 = new Cell(0, 1, false);
        this.cell_0_2 = new Cell(0, 2, false);
        this.cell_1_0 = new Cell(1, 0, false);
        this.cell_1_1 = new Cell(1, 1, true);
        this.cell_1_2 = new Cell(1, 2, false);
        this.cell_2_0 = new Cell(2, 0, false);
        this.cell_2_1 = new Cell(2, 1, true);
        this.cell_2_2 = new Cell(2, 2, true);

        old_generation = new Grid(Lists.<GridCells>newArrayList(
                new GridCells(cell_0_0, cell_0_1, cell_0_2),
                new GridCells(cell_1_0, cell_1_1, cell_1_2),
                new GridCells(cell_2_0, cell_2_1, cell_2_2)

        ));
    }

    @Test
    public void live_cell_with_less_than_two_live_neighbours() {

        assertThat(cell_0_0, is(alive()));

        Grid next_generation = old_generation.next_generation();

        assertThat(cell_0_0, is_dead_in(next_generation));
    }

    @Test
    public void live_cell_with_two_live_neighbours() {

        Grid new_generation = old_generation.next_generation();

        assertThat(cell_2_2, lives_on_to(new_generation));
    }

    @Test
    public void live_cell_with_three_live_neighbours() {

        Grid new_generation = old_generation.next_generation();

        assertThat(cell_1_1, lives_on_to(new_generation));
    }


}
