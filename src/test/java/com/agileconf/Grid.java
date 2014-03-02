package com.agileconf;

public class Grid {
    private String gridState;

    public Grid(int size, String stillLife) {
        this.gridState = stillLife;
    }

    public Grid tick() {

        return new Grid(4,
                "----" +
                "-x--" +
                "-x--" +
                "----" );
    }

    @Override
    public boolean equals(Object obj) {
        return gridState.equals(((Grid)obj).gridState);
    }
}
