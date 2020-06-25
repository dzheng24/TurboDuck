package com.huskies.turboduck.models;

import java.text.DecimalFormat;

/**
 * YellowDuck is the standard duck. It will either move forward or backward by (-0.5 0.5)*stepModifier steps every
 * time move() is called.
 */
public class YellowDuck extends Duck {

    public YellowDuck(String name, Color color) {
        super(name, color);
    }

    public YellowDuck() {
        super();
    }

    @Override
    public void move() {
        double randomIncrement = (Math.random() - 0.5) * 2;
        randomIncrement = Double.parseDouble(df.format(randomIncrement));
        distancePoint.changeRacerPosition(randomIncrement);
    }
}
