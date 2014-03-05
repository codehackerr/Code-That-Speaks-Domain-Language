package com.agileconf;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CellTest {

    private Cell dead;
    private Cell alive;

    @Before
    public void setUp() throws Exception {
        dead = new Cell(0, 0, false);
        alive = new Cell(0, 0, true);
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
}
