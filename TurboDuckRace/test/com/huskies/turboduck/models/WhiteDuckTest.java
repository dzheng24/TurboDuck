package com.huskies.turboduck.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WhiteDuckTest {
    private Duck duck;

    @Before
    public void setUp() throws Exception {
        duck = new WhiteDuck("test", Color.WHITE);
    }

    @Test (timeout = 5000)
    public void testMove() {
        while(duck.getDistanceTraveled()!=0) {
            duck.move(); // should eventually move
        }
    }
}