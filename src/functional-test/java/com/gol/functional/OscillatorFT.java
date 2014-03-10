package com.gol.functional;

import org.junit.Test;

import static com.gol.functional.dsl.GridDSL.a;

public class OscillatorFT {

    private static final String NEW_LINE = System.getProperty("line.separator");

    private static final String BLINKER = "" +
            "-----" + NEW_LINE +
            "-----" + NEW_LINE +
            "-xxx-" + NEW_LINE +
            "-----" + NEW_LINE +
            "-----";

    private static final String BLINKER_PERIOD_1 = "" +
            "-----" + NEW_LINE +
            "--x--" + NEW_LINE +
            "--x--" + NEW_LINE +
            "--x--" + NEW_LINE +
            "-----";

    @Test
    public void blinker_period1() {
        a(BLINKER).becomes(BLINKER_PERIOD_1).after(1).generations();
    }
    @Test
    public void blinker_period2() {
        a(BLINKER).returns_to(BLINKER).after(2).generations();
    }
}
