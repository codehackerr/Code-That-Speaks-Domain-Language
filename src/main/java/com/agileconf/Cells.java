package com.agileconf;

import java.util.ArrayList;
import java.util.Arrays;

import static com.google.common.collect.Lists.newArrayList;

public class Cells extends ArrayList<Cell> {
    public Cells(Cell ... cells) {
           this.addAll(newArrayList(cells));
    }
}
