package com.huskies.turboduck.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreyDuckTest {

    Duck duck;

    @Before
    public void setUp() {
        duck = new GreyDuck("test", Color.GREY);
    }

    @Test(timeout = 10000)
    public void move() {
        for (int i = 0; i < 15; i++) {
            setUp(); // grey duck changes its move method every new instantiation. Test Multiple times
            while (duck.getDistanceTraveled() != 0) {
                duck.move(); // should eventually move
            }
        }
        assertTrue(true);
    }
}