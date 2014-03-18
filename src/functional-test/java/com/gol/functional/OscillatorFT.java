package com.gol.functional;

import org.junit.Test;

import static com.gol.functional.dsl.GridDSL.a;

public class OscillatorFT {

    private static final String NEW_LINE = System.getProperty("line.separator");

    public static final String IDE_SPACER = "";
    private static final String BLINKER = IDE_SPACER +
            "-----" + NEW_LINE +
            "-----" + NEW_LINE +
            "-xxx-" + NEW_LINE +
            "-----" + NEW_LINE +
            "-----";

    private static final String BLINKER_PERIOD_1 = IDE_SPACER +
            "-----" + NEW_LINE +
            "--x--" + NEW_LINE +
            "--x--" + NEW_LINE +
            "--x--" + NEW_LINE +
            "-----";

    @Test
    public void blinker_period_1() {
        a(BLINKER).becomes(BLINKER_PERIOD_1).after(1).generations();
    }
    @Test
    public void blinker_period_2() {
        a(BLINKER).returns_to(BLINKER).after(2).generations();
    }

    private static final String TOAD = "" +
            "------" + NEW_LINE +
            "------" + NEW_LINE +
            "--xxx-" + NEW_LINE +
            "-xxx--" + NEW_LINE +
            "------" + NEW_LINE +
            "------";
   private static final String TOAD_PERIOD_1 = "" +
            "------" + NEW_LINE +
            "---x--" + NEW_LINE +
            "-x--x-" + NEW_LINE +
            "-x--x-" + NEW_LINE +
            "--x---" + NEW_LINE +
            "------";


    @Test
    public void toad_period_1() {
        a(TOAD).becomes(TOAD_PERIOD_1).after(1).generations();
    }

    @Test
    public void toad_period_2() {
        a(TOAD).returns_to(TOAD).after(2).generations();
    }
}
