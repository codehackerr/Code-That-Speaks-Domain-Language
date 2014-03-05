package com.gol;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameOfLifeRulesTest {

    @Mock
    private Cell lonelyCell;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void cell_with_less_than_two_live_neighbours(){
        Grid grid = new Grid(Lists.<GridCells>newArrayList(new GridCells(lonelyCell)));

        grid.next_generation();

        verify(lonelyCell).die();
    }

}
