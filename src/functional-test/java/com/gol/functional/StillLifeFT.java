package com.gol.functional;

import com.gol.Grid;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StillLifeFT {
    private static final String NEW_LINE = System.getProperty("line.separator");

    private static final String BLOCK =
            "----" + NEW_LINE +
            "-xx-" + NEW_LINE +
            "-xx-" + NEW_LINE +
            "----" ;

    @Test
    public void block() {
        Grid grid = Grid.from_string(BLOCK);
        Grid next_generation = grid.next_generation();
        assertThat(next_generation.toString(),is(BLOCK));
    }

    private static final String BEEHIVE =
            "-----" + NEW_LINE +
            "--xx--" + NEW_LINE +
            "-x--x-" + NEW_LINE +
            "--xx--" + NEW_LINE +
            "-----" ;

    @Test
    public void beehive() {
        Grid grid = Grid.from_string(BEEHIVE);
        Grid next_generation = grid.next_generation();
        assertThat(next_generation.toString(),is(BEEHIVE));
    }
}
