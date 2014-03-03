package com.agileconf;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<Cells> cells;

    public Grid(int size, String stillLife) {
        Grid.parse(size, stillLife);
    }

    Grid(List<Cells> cells) {
        this.cells = cells;
    }

    public Grid tick() {
        return new Grid(null);
    }

    @Override
    public boolean equals(Object obj) {
        return cells.equals(((Grid) obj).cells);
    }

    public static Grid parse(int size, String gridString) {

        String[] rows = gridString.split("(?<=\\G.{" + size + "})");

        List<Cells> cellsGrid = new ArrayList<Cells>();
        for (int rowSize = rows.length, rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            String row = rows[rowIndex];
            Cells cells = new Cells();
            char[] cellChars = row.toCharArray();
            for (int cellSize = cellChars.length, columnIndex = 0; columnIndex < cellSize; columnIndex++) {
                Cell cell = new Cell(rowIndex, columnIndex, cellstate(cellChars[columnIndex]));
                cells.add(cell);
            }

            cellsGrid.add(cells);

        }


        return new Grid(cellsGrid);

    }

    private static boolean cellstate(Character character) {
        return character.equals("x");
    }
}
