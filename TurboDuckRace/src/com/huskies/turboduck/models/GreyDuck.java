package com.huskies.turboduck.models;

import com.huskies.turboduck.DuckFarm;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A GreyDuck is morally ambiguous. Meaning that its move() method is picked at instantiation from the other ducks.
 * Nobody knows its functionality until it starts moving.
 */
public class GreyDuck extends Duck {

    private final Duck implementingDuck;

    public GreyDuck(String name, Color color) {
        super(name, color);
        Color[] choices = Color.values();
        int choice = ThreadLocalRandom.current().nextInt(0, choices.length);
        implementingDuck = DuckFarm.getDuck(getName(), choices[choice]);
    }



    @Override
    public void move() {
        implementingDuck.move();
        distancePoint.setxPosition(implementingDuck.getDistanceTraveled());
    }
}
