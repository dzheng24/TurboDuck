package com.huskies.turboduck.models;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A WhiteDuck chooses if it wants to move or not. Similar to YellowDuck but with a slightly better step modifier.
 * Decides at each move() call if it wants to do anything. Very snobby.
 */
public class WhiteDuck extends Duck {

    public WhiteDuck(String name, Color color) {
        super(name, color);
    }

    @Override
    public void move() {
        if (ThreadLocalRandom.current().nextBoolean()) {
            double randomIncrement = (Math.random() - 0.5) * getColor().getStepModifer();
            randomIncrement = Double.parseDouble(df.format(randomIncrement));
            distancePoint.changeRacerPosition(randomIncrement);
        }
    }
}
