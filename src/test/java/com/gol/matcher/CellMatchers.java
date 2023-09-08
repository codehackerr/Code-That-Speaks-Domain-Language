package com.gol.matcher;

import com.gol.Cell;
import com.gol.Grid;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


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

    public static Matcher<? super Cell> dies_of_underpopulation(final Grid next_generation) {
        return dies(next_generation);
    }

    public static Matcher<? super Cell> dies_of_overpopulation(final Grid next_generation) {
        return dies(next_generation);
    }

    public static Matcher<? super Cell> dies(final Grid next_generation) {
        return new BaseMatcher<Cell>() {
            public boolean matches(Object cell_object) {
                Cell cell = (Cell) cell_object;
                Cell dead_cell = cell.die();
                return next_generation.contains(dead_cell);
            }

            public void describeTo(Description description) {

            }
        };
    }

    public static  Matcher<? super Cell> is_neighbour_of(final Cell one_cell){
        return new TypeSafeMatcher<Cell>() {
            private Cell matched;
            public void describeTo(Description description) {
                description.appendText(matched.toString()).appendText(" to be neighbour of ").appendText(one_cell.toString());
            }

            @Override
            protected boolean matchesSafely(Cell cell) {
                this.matched = cell;
                return this.matched.is_neighbour_of(one_cell);
            }

            @Override
            protected void describeMismatchSafely(Cell item, Description mismatchDescription) {
                mismatchDescription.appendText(" was not a neighbour");
            }
        };
    }
}
