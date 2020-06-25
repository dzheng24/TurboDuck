package com.huskies.turboduck.models;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A BlackDuck may or may not move fast, and be helpful when it does. It chooses its step frequency during
 * instantiation, which is between 1 (every time), or 100 (every 100 times move() is called). It has the
 * largest modifier of step distance out of all the ducks.
 */
public class BlackDuck extends Duck {

    private int stepFrequency;
    private int currStep;

    public BlackDuck(String name, Color color) {
        super(name, color);
        stepFrequency = ThreadLocalRandom.current().nextInt(1, 100);
        currStep = 1;

    }

    @Override
    public void move() {
        if (currStep != stepFrequency) {
            currStep++;
        } else {
            currStep = 1; // reset back to one
            double randomIncrement = (Math.random() - 0.5) * getColor().getStepModifer();
            randomIncrement = Double.parseDouble(df.format(randomIncrement));
            distancePoint.changeRacerPosition(randomIncrement);
        }
    }
}
