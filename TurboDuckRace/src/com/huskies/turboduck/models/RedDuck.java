package com.huskies.turboduck.models;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A RedDuck is very aggressive in its movement. It will charge fast in a direction for WAITING_PERIOD number of steps,
 * but will have to rest after two charges. The charge may either be forward, backward, or nowhere at all. They aren't
 * the most tolerable of ducks.
 */
public class RedDuck extends Duck{

    private int direction = 1;
    private double stepSize = 0.5;
    private int numSteps = 0;
    private int waitSteps = 0;
    private final int WAITING_PERIOD = 5;

    public RedDuck(String name, Color color) {
        super(name, color);
    }

    @Override
    public void move() {
        if (numSteps < WAITING_PERIOD * 2) { // charge ahead
            if (numSteps % WAITING_PERIOD == 0) {
                direction = ThreadLocalRandom.current().nextInt(-1, 2);
            }
            double randomIncrement = stepSize * getColor().getStepModifer() * direction;
            randomIncrement = Double.parseDouble(df.format(randomIncrement));
            distancePoint.changeRacerPosition(randomIncrement);
            numSteps++;
        } else if (waitSteps < WAITING_PERIOD){
            waitSteps++; // need a cooldown
        } else { // reset and run move again
            numSteps = 0;
            waitSteps = 0;
            move();
        }
    }
}
