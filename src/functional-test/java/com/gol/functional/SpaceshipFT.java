package com.gol.functional;

import org.junit.Test;

import static com.gol.functional.dsl.GridDSL.a;

public class SpaceshipFT {

    private static final String NEW_LINE = System.getProperty("line.separator");

    public static final String IDE_SPACER = "";

    private static final String GLIDER =
            IDE_SPACER +
                    "-----" + NEW_LINE +
                    "-x---" + NEW_LINE +
                    "--xx-" + NEW_LINE +
                    "-xx--" + NEW_LINE +
                    "-----";

    private static final String GLIDER_PERIOD_1 =
            IDE_SPACER +
                    "-----" + NEW_LINE +
                    "--x--" + NEW_LINE +
                    "---x-" + NEW_LINE +
                    "-xxx-" + NEW_LINE +
                    "-----";

    private static final String GLIDER_PERIOD_2 =
            IDE_SPACER +
                    "-----" + NEW_LINE +
                    "-----" + NEW_LINE +
                    "-x-x-" + NEW_LINE +
                    "--xx-" + NEW_LINE +
                    "--x--";

    private static final String GLIDER_PERIOD_3 =
            IDE_SPACER +
                    "-----" + NEW_LINE +
                    "-----" + NEW_LINE +
                    "---x-" + NEW_LINE +
                    "-x-x-" + NEW_LINE +
                    "--xx-";

    private static final String GLIDER_ORIGINAL_SHIFTED_RIGHT_AND_DOWN =
            IDE_SPACER +
                    "-----" + NEW_LINE +
                    "-----" + NEW_LINE +
                    "--x--" + NEW_LINE +
                    "---xx" + NEW_LINE +
                    "--xx-";

    @Test
    public void glider_period_1() {
        a(GLIDER).becomes(GLIDER_PERIOD_1).after(1).generations();
    }

    @Test
    public void glider_period_2() {
        a(GLIDER).becomes(GLIDER_PERIOD_2).after(2).generations();
    }

    @Test
    public void glider_period_3() {
        a(GLIDER).becomes(GLIDER_PERIOD_3).after(3).generations();
    }

    @Test
    public void glider_period_4() {
        a(GLIDER).becomes(GLIDER_ORIGINAL_SHIFTED_RIGHT_AND_DOWN).after(4).generations();
    }
}
