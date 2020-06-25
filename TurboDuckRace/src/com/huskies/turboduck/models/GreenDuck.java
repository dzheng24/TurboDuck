package com.huskies.turboduck.models;

import java.text.DecimalFormat;

/**
 * A GreenDuck will always move forward, never backward. However, they only will only  move every
 * other time their move() method is called. Consistent, but not too fast.
 */
public class GreenDuck extends Duck {
    private int step = 0;

    public GreenDuck(String name, Color color) {
        super(name, color);
    }

    @Override
    public void move() {
        if (step % 2 == 0) { // even
            double randomIncrement = Math.random() * getColor().getStepModifer();
            randomIncrement = Double.parseDouble(df.format(randomIncrement));
            distancePoint.changeRacerPosition(randomIncrement);
            step++;
        } else {
            // dont do anything
            step++;
        }
    }
}
