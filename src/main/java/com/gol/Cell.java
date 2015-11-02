package com.gol;

import static java.lang.Math.abs;

public class Cell {
    public static final String DEAD = "-";
    public static final String ALIVE = "x";
    public static final int UNIT_CELL_DISTANCE = 1;

    private int row_index;
    private int column_index;
    private boolean alive;

    public Cell(int row_index, int column_index, boolean alive) {
        this.row_index = row_index;
        this.column_index = column_index;
        this.alive = alive;
    }

    static Cell cell(int row_index, int column_index) {
        return new Cell(row_index, column_index, false);
    }

    @Override
    public boolean equals(Object obj) {
        Cell other = (Cell) obj;
        return this.row_index == other.row_index &&
                this.column_index == other.column_index &&
                this.alive == other.alive;
    }

    public String state_string() {
        return alive ? ALIVE : DEAD;
    }

    public Cell die() {
        return new Cell(this.row_index, this.column_index, false);
    }

    public int row_index() {
        return row_index;
    }

    public boolean is_neighbour_of(Cell cell) {
        return !is_same_cell(cell) &&
                column_distance(cell) <= UNIT_CELL_DISTANCE &&
                row_distance(cell) <= UNIT_CELL_DISTANCE;
    }

    private boolean is_same_cell(Cell cell) {
        return cell.equals(this);
    }

    private int row_distance(Cell cell) {
        return abs(this.row_index - cell.row_index);
    }

    private int column_distance(Cell cell) {
        return abs(this.column_index - cell.column_index);
    }

    public boolean is_alive() {
        return alive;
    }

    public Cell live() {
        return new Cell(this.row_index, this.column_index, true);
    }

    public boolean is_dead() {
        return !is_alive();
    }

    public Cell copy() {
        return new Cell(this.row_index, this.column_index, this.alive);
    }
}
