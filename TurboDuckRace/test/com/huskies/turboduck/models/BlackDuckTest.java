package com.huskies.turboduck.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlackDuckTest {
    Duck duck;

    @Before
    public void setUp() {
        duck = new BlackDuck("test", Color.BLACK);
    }

    @Test(timeout = 10000)
    public void testMove() {
        // should eventually move
        while (duck.getDistanceTraveled() == 0) {
            duck.move();
        }
        assertTrue(true);
    }
}