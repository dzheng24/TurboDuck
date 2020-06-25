package com.huskies.turboduck.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreenDuckTest {

    private Duck duck;

    @Before
    public void setUp() throws Exception {
        duck = new GreenDuck("test", Color.GREEN);
    }

    /**
     * This has a very rare chance to fail due to random double being 0.
     */
    @Test
    public void testMove_failsIfMovedOnSecondCall() {
        duck.move();
        double distance = duck.getDistanceTraveled();
        assertNotEquals(0, duck.getDistanceTraveled());

        duck.move(); // should not have moved
        assertEquals(distance, duck.getDistanceTraveled(), 0.00001);
    }
}