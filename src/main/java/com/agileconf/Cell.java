package com.agileconf;

public class Cell {
    private int x;
    private int y;
    private boolean cellstate;

    public Cell(int x, int y, boolean cellstate) {
        this.x = x;
        this.y = y;
        this.cellstate = cellstate;
    }

    @Override
    public boolean equals(Object obj) {
        Cell other = (Cell) obj;
        return this.x == other.x &&
               this.y == other.y &&
               this.cellstate == other.cellstate;
    }
}
