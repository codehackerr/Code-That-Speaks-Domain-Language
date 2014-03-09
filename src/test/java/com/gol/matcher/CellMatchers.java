package com.gol.matcher;

import com.gol.Cell;
import com.gol.Grid;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


public class CellMatchers {
    public static Matcher<Cell> alive() {
        return new BaseMatcher<Cell>() {
            public boolean matches(Object cell_as_object) {
                return ((Cell) cell_as_object).is_alive();
            }

            public void describeTo(Description description) {
            }
        };
    }

    public static Matcher<? super Cell> lives_on_to(final Grid new_generation) {
        return new BaseMatcher<Cell>() {
            public boolean matches(Object cell_object) {
                Cell cell = (Cell) cell_object;
                return cell.is_alive() && new_generation.contains(cell);
            }

            public void describeTo(Description description) {

            }
        };
    }

    public static Matcher<? super Cell> stays_dead_in(final Grid new_generation) {
        return new BaseMatcher<Cell>() {
            public boolean matches(Object cell_object) {
                Cell cell = (Cell) cell_object;
                if (cell.is_alive()) {
                    throw new AssertionError("stays_dead is valid only for a Dead Cell.");
                }
                return new_generation.contains(cell);
            }

            public void describeTo(Description description) {
                 description.appendValue("Dead cell(-)");
            }

            @Override
            public void describeMismatch(Object cell_object, Description description) {
                description.appendValue( "but could not find");
            }
        };
    }

    public static Matcher<? super Cell> becomes_live_in(final Grid new_generation) {
        return new BaseMatcher<Cell>() {
            public boolean matches(Object cell_object) {
                Cell cell = (Cell) cell_object;
                Cell live_cell = cell.live();
                return new_generation.contains(live_cell);
            }

            public void describeTo(Description description) {

            }
        };
    }

    public static Matcher<? super Cell> becomes_dead_in(final Grid new_generation) {
        return new BaseMatcher<Cell>() {
            public boolean matches(Object cell_object) {
                Cell cell = (Cell) cell_object;
                Cell dead_cell = cell.die();
                return new_generation.contains(dead_cell);
            }

            public void describeTo(Description description) {

            }
        };
    }
}
