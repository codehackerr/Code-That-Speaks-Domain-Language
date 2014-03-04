package com.agileconf;

import com.google.common.base.Function;

import java.util.ArrayList;

import static com.google.common.base.Joiner.on;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;

public class Cells extends ArrayList<Cell> {
    public Cells(Cell... cells) {
        this.addAll(newArrayList(cells));
    }

    public String state() {
        return on("").join(transform(this, new Function<Cell, String>() {
            public String apply(Cell cell) {
                return cell.state();
            }
        }));
    }
}
