package com.gol;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CellTest {

    private Cell dead;
    private Cell alive;
    private Cell one_cell;

    @Before
    public void setUp() throws Exception {
        dead = new Cell(0, 0, false);
        alive = new Cell(0, 0, true);
        one_cell = new Cell(1, 1, false);
    }

    @Test
    public void dead(){
        assertThat(dead.state(), is("-"));
    }

    @Test
    public void alive(){
        assertThat(alive.state(), is("x"));
    }

    @Test
    public void die(){
        assertThat(alive.die().dead(), is(true));
    }

    @Test
    public void cell_on_the_same_row_immediately_to_the_left(){
        assertThat(new Cell(1,0,false).is_neighbour_of(one_cell),is(true));
    }
    @Test
    public void cell_on_the_same_row_immediately_to_the_right(){
        assertThat(new Cell(1,2,false).is_neighbour_of(one_cell),is(true));
    }
    @Test
    public void cell_on_the_previous_row_diagonally_to_the_left(){
        assertThat(new Cell(0,0,false).is_neighbour_of(one_cell),is(true));
    }
    @Test
    public void cell_on_the_previous_row_diagonally_to_the_right(){
        assertThat(new Cell(0,2,false).is_neighbour_of(one_cell),is(true));
    }
    @Test
    public void cell_on_the_previous_row_immediately_above(){
        assertThat(new Cell(0,1,false).is_neighbour_of(one_cell),is(true));
    }

    @Test
    public void cell_on_the_next_row_diagonally_to_the_left(){
        assertThat(new Cell(2,0,false).is_neighbour_of(one_cell),is(true));
    }
    @Test
    public void cell_on_the_next_row_diagonally_to_the_right(){
        assertThat(new Cell(2,2,false).is_neighbour_of(one_cell),is(true));
    }
    @Test
    public void cell_on_the_next_row_immediately_below(){
        assertThat(new Cell(2,1,false).is_neighbour_of(one_cell),is(true));
    }
}
