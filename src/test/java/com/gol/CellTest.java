package com.gol;


import org.junit.Before;
import org.junit.Test;

import static com.gol.matcher.CellMatchers.is_neighbour_of;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class CellTest {

    private Cell dead_cell;
    private Cell live_cell;

    /**
     * |.|.|.|.|
     * ---------
     * |.|-|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     */
    private Cell cell_1_1;

    @Before
    public void setUp() throws Exception {
        dead_cell = cell(0, 0);
        live_cell = Cell.new_cell(0, 0, true);
        cell_1_1 = cell(1, 1);
    }

    @Test
    public void dead_cell(){
        assertThat(dead_cell.state(), is('-'));
    }

    @Test
    public void live_cell(){
        assertThat(live_cell.state(), is('x'));
    }

    @Test
    public void die(){
        assertThat(live_cell.die().is_dead(), is(true));
    }

    /**
     * |.|.|.|.|
     * ---------
     * |x|-|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     */
    @Test
    public void cell_on_the_same_row_immediately_to_the_left(){
        assertThat(cell(1, 0), is_neighbour_of(cell_1_1));
    }

    /**
     * |.|.|.|.|
     * ---------
     * |.|-|x|.|
     * ---------
     * |.|.|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     */
    @Test
    public void cell_on_the_same_row_immediately_to_the_right() {
        assertThat(cell(1, 2), is_neighbour_of(cell_1_1));
    }

    /**
     * |x|.|.|.|
     * ---------
     * |.|-|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     */
    @Test
    public void cell_on_the_previous_row_diagonally_to_the_left(){
        assertThat(cell(0, 0), is_neighbour_of(cell_1_1));
    }

    /**
     * |.|.|x|.|
     * ---------
     * |.|-|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     */
    @Test
    public void cell_on_the_previous_row_diagonally_to_the_right(){
        assertThat(cell(0, 2), is_neighbour_of(cell_1_1));
    }

    /**
     * |.|x|.|.|
     * ---------
     * |.|-|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     */
    @Test
    public void cell_on_the_previous_row_immediately_above(){
        assertThat(cell(0, 1), is_neighbour_of(cell_1_1));
    }

    /**
     * |.|.|.|.|
     * ---------
     * |.|-|.|.|
     * ---------
     * |x|.|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     */
    @Test
    public void cell_on_the_next_row_diagonally_to_the_left(){
        assertThat(cell(2, 0), is_neighbour_of(cell_1_1));
    }

    /**
     * |.|.|.|.|
     * ---------
     * |.|-|.|.|
     * ---------
     * |.|.|x|.|
     * ---------
     * |.|.|.|.|
     * ---------
     */
    @Test
    public void cell_on_the_next_row_diagonally_to_the_right(){
        assertThat(cell(2, 2), is_neighbour_of(cell_1_1));
    }

    /**
     * |.|.|.|.|
     * ---------
     * |.|-|.|.|
     * ---------
     * |.|x|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     */
    @Test
    public void cell_on_the_next_row_immediately_below(){
        assertThat(cell(2, 1), is_neighbour_of(cell_1_1));
    }

    /**
     * |.|.|.|.|
     * ---------
     * |.|-|.|.|
     * ---------
     * |.|.|.|x|
     * ---------
     * |.|.|.|.|
     * ---------
     */
    @Test
    public void cell_on_the_next_row_more_than_unit_distance(){
        assertThat(cell(2, 3), not(is_neighbour_of(cell_1_1)));
    }

    /**
     * |.|.|.|.|
     * ---------
     * |.|-|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     * |.|.|.|.|
     * ---------
     */
    @Test
    public void same_cell(){
        assertThat(cell_1_1, not(is_neighbour_of(cell_1_1)));
    }

    @Test
    public void from_string(){
        assertThat(Cell.from_string(0,0,'-'), isA(Cell.class));
    }


    private Cell cell(int row, int column) {
        return Cell.new_cell(row, column, false);
    }
}
