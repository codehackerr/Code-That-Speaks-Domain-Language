package com.gol;

public class Cell {
    public static final String DEAD = "-";
    public static final String ALIVE = "x";

    private int x;
    private int y;
    private boolean state;

    public Cell(int x, int y, boolean alive) {
        this.x = x;
        this.y = y;
        this.state = alive;
    }

    @Override
    public boolean equals(Object obj) {
        Cell other = (Cell) obj;
        return this.x == other.x &&
               this.y == other.y &&
               this.state == other.state;
    }

    public String state() {
        return state? ALIVE : DEAD;
    }

    public Cell die() {
        return new Cell(this.x,this.y,false);
    }

    public boolean dead() {
        return !this.state;
    }
}
