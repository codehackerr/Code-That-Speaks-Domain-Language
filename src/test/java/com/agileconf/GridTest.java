package com.agileconf;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GridTest {

    private static final String DIE_OF_BOREDOM =
                                             "----" +
                                             "-x--" +
                                             "-x--" +
                                             "----" ;

    private static final String DEAD_OF_BOREDOM =
                                             "----" +
                                             "-x--" +
                                             "-x--" +
                                             "----" ;


    @Test
    public void cellsDieOfBoredom() {
        Grid grid = new Grid(4, DIE_OF_BOREDOM);
        Grid nextGeneration = grid.tick();
        assertThat(nextGeneration, equalTo(new Grid(4, DEAD_OF_BOREDOM)));
    }


}
