package com.huskies.ui;

import com.huskies.ui.Main;
import com.huskies.ui.RaceFrame;

import java.net.URL;

public class UIConstants {

    private UIConstants() {
        // empty for static class
    }

    // Defaults
    public static final String title = "TurboDuck Race 2020";
    public static final int DEFAULT_WIDTH = 900;
    public static final int DEFAULT_HEIGHT = 700;
    public static final int ANIMATION_SPEED = 10; // loop happens every n milliseconds
    public static final int IMAGE_SCALING = 50;

    // images for objects
    public static final URL FLAG_IMAGE = ClassLoader.getSystemResource("pictures/raceFlag.png");
    public static final URL BACKGROUND_IMAGE = ClassLoader.getSystemResource("pictures/animatedWater.png");

    public static final URL YELLOW_IMAGE = ClassLoader.getSystemResource("");
    public static final URL RED_IMAGE = ClassLoader.getSystemResource("pictures/redDuck.png");
    public static final URL BLUE_IMAGE = ClassLoader.getSystemResource("");
    public static final URL GREEN_IMAGE = ClassLoader.getSystemResource("");

}
