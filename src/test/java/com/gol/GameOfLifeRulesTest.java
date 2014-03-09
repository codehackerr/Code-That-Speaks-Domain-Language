package com.gol;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import static com.gol.matcher.CellMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameOfLifeRulesTest {

    private Cell cell_0_0_l;
    private Cell cell_0_1_d;
    private Cell cell_0_2_l;
    private Cell cell_0_3_d;
    private Cell cell_1_0_d;
    private Cell cell_1_1_l;
    private Cell cell_1_2_d;
    private Cell cell_1_3_d;
    private Cell cell_2_0_d;
    private Cell cell_2_1_l;
    private Cell cell_2_2_l;
    private Cell cell_2_3_d;
    private Cell cell_3_0_d;
    private Cell cell_3_1_d;
    private Cell cell_3_2_d;
    private Cell cell_3_3_l;
    private Grid old_generation;

    @Before
    public void setUp() {
        this.cell_0_0_l = new Cell(0, 0, true);
        this.cell_0_1_d = new Cell(0, 1, false);
        this.cell_0_2_l = new Cell(0, 2, true);
        this.cell_0_3_d = new Cell(0, 3, false);

        this.cell_1_0_d = new Cell(1, 0, false);
        this.cell_1_1_l = new Cell(1, 1, true);
        this.cell_1_2_d = new Cell(1, 2, false);
        this.cell_1_3_d = new Cell(1, 3, false);

        this.cell_2_0_d = new Cell(2, 0, false);
        this.cell_2_1_l = new Cell(2, 1, true);
        this.cell_2_2_l = new Cell(2, 2, true);
        this.cell_2_3_d = new Cell(2, 3, false);

        this.cell_3_0_d = new Cell(3, 0, false);
        this.cell_3_1_d = new Cell(3, 1, false);
        this.cell_3_2_d = new Cell(3, 2, false);
        this.cell_3_3_l = new Cell(3, 3, true);

        old_generation = new Grid(Lists.<GridCells>newArrayList(
                new GridCells(cell_0_0_l, cell_0_1_d, cell_0_2_l, cell_0_3_d),
                new GridCells(cell_1_0_d, cell_1_1_l, cell_1_2_d, cell_1_3_d),
                new GridCells(cell_2_0_d, cell_2_1_l, cell_2_2_l, cell_2_3_d),
                new GridCells(cell_3_0_d, cell_3_1_d, cell_3_2_d, cell_3_3_l)

        ));
    }

    @Test
    public void live_cell_with_less_than_two_live_neighbours() {

        assertThat(cell_0_0_l, is(alive()));

        Grid next_generation = old_generation.next_generation();

        assertThat(cell_0_0_l, becomes_dead_in(next_generation));
    }

    @Test
    public void live_cell_with_two_live_neighbours() {

        Grid new_generation = old_generation.next_generation();

        assertThat(cell_2_1_l, lives_on_to(new_generation));
    }

    @Test
    public void live_cell_with_three_live_neighbours() {

        Grid new_generation = old_generation.next_generation();

        assertThat(cell_2_2_l, lives_on_to(new_generation));
    }

    @Test
    public void live_cell_with_more_than_three_live_neighbours() {

        Grid new_generation = old_generation.next_generation();

        assertThat(cell_1_1_l, becomes_dead_in(new_generation));
    }

    @Test
    public void dead_cell_with_exactly_three_live_neighbours() {

        Grid new_generation = old_generation.next_generation();

        assertThat(cell_0_1_d, becomes_live_in(new_generation));
    }

    @Test
    public void dead_cell_with_less_than_three_live_neighbours(){
        Grid new_generation = old_generation.next_generation();

        assertThat(cell_1_3_d, stays_dead_in(new_generation));
    }

    @Test
    public void dead_cell_with_more_than_three_live_neighbours(){
        Grid new_generation = old_generation.next_generation();

        assertThat(cell_1_2_d, stays_dead_in(new_generation));
    }


}
