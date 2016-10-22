package com.gol;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import static com.gol.Cell.from_string;
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
        this.cell_0_0_l = from_string(0, 0, 'x');
        this.cell_0_1_d = from_string(0, 1, '_');
        this.cell_0_2_l = from_string(0, 2, 'x');
        this.cell_0_3_d = from_string(0, 3, '_');

        this.cell_1_0_d = from_string(1, 0, '_');
        this.cell_1_1_l = from_string(1, 1, 'x');
        this.cell_1_2_d = from_string(1, 2, '_');
        this.cell_1_3_d = from_string(1, 3, '_');

        this.cell_2_0_d = from_string(2, 0, '_');
        this.cell_2_1_l = from_string(2, 1, 'x');
        this.cell_2_2_l = from_string(2, 2, 'x');
        this.cell_2_3_d = from_string(2, 3, '_');

        this.cell_3_0_d = from_string(3, 0, '_');
        this.cell_3_1_d = from_string(3, 1, '_');
        this.cell_3_2_d = from_string(3, 2, '_');
        this.cell_3_3_l = from_string(3, 3, 'x');

        old_generation = new Grid(Lists.<GridCells>newArrayList(
                new GridCells(cell_0_0_l, cell_0_1_d, cell_0_2_l, cell_0_3_d),
                new GridCells(cell_1_0_d, cell_1_1_l, cell_1_2_d, cell_1_3_d),
                new GridCells(cell_2_0_d, cell_2_1_l, cell_2_2_l, cell_2_3_d),
                new GridCells(cell_3_0_d, cell_3_1_d, cell_3_2_d, cell_3_3_l)

        ));
    }

    @Test
    public void live_cell_with_less_than_two_live_neighbours() {

        Cell live_cell_with_less_than_two_two_live_neighbours = this.cell_0_0_l;

        assertThat(live_cell_with_less_than_two_two_live_neighbours, is(alive()));

        Grid next_generation = old_generation.next_generation();

        assertThat(live_cell_with_less_than_two_two_live_neighbours, becomes_dead_in(next_generation));
    }

    @Test
    public void live_cell_with_two_live_neighbours() {

        Cell live_cell_with_two_live_neighbours = this.cell_2_1_l;

        Grid new_generation = old_generation.next_generation();

        assertThat(live_cell_with_two_live_neighbours, lives_on_to(new_generation));
    }

    @Test
    public void live_cell_with_three_live_neighbours() {

        Cell live_cell_with_three_live_neighbours = this.cell_2_2_l;

        Grid new_generation = old_generation.next_generation();

        assertThat(live_cell_with_three_live_neighbours, lives_on_to(new_generation));
    }

    @Test
    public void live_cell_with_more_than_three_live_neighbours() {

        Cell live_cell_with_more_than_three_neighbours = this.cell_1_1_l;

        Grid new_generation = old_generation.next_generation();

        assertThat(live_cell_with_more_than_three_neighbours, becomes_dead_in(new_generation));
    }

    @Test
    public void dead_cell_with_exactly_three_live_neighbours() {

        Cell dead_cell_with_exactly_three_live_neighbours = this.cell_0_1_d;

        Grid new_generation = old_generation.next_generation();

        assertThat(dead_cell_with_exactly_three_live_neighbours, becomes_live_in(new_generation));
    }

    @Test
    public void dead_cell_with_less_than_three_live_neighbours(){
        Cell dead_cell_with_less_than_three_live_neighbours = this.cell_1_3_d;

        Grid new_generation = old_generation.next_generation();

        assertThat(dead_cell_with_less_than_three_live_neighbours, stays_dead_in(new_generation));
    }

    @Test
    public void dead_cell_with_more_than_three_live_neighbours(){
        Cell dead_cell_with_more_than_three_live_neighbours = this.cell_1_2_d;

        Grid new_generation = old_generation.next_generation();

        assertThat(dead_cell_with_more_than_three_live_neighbours, stays_dead_in(new_generation));
    }


}
