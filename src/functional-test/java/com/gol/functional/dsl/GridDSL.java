package com.gol.functional.dsl;

import com.gol.Grid;
import org.hamcrest.CoreMatchers;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GridDSL {

    private int count;
    private Grid grid;
    private DeferredAssertion deferred;
    private Grid next_generation;
    private String pattern;


    public GridDSL(String pattern) {
        this.pattern = pattern;
        this.grid = Grid.from_string(pattern);
    }

    public void generations() {
        if (count == 0) return;

        Grid g = grid;
        for (int generations = 0; generations < count; generations++) {
            g = g.next_generation();
        }
        this.next_generation = g;

        deferred.execute();
    }

    public GridDSL after(int count) {
        this.count = count;
        return this;
    }

    public GridDSL remains_unchanged() {
        this.deferred = new DeferredAssertion() {
            public void execute() {
                assertThat(next_generation.toString(), CoreMatchers.is(pattern));
            }
        };
        return this;
    }

    public static GridDSL a(String pattern) {
        return new GridDSL(pattern);
    }

    public GridDSL becomes(final String pattern) {
        this.deferred = new DeferredAssertion() {
            public void execute() {
                assertThat(next_generation.toString(), CoreMatchers.is(pattern));
            }
        };
        return this;
    }

    public GridDSL returns_to(String pattern) {
        return becomes(pattern);
    }
}
