package com.huskies.turboduck.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class RedDuckTest {

    private Duck duck;

    @Before
    public void setUp() {
        duck = new RedDuck("test", Color.RED);
    }

    @Test(timeout=15000)
    public void testMove_goesForward5TimesInARow() {
        while (duck.getDistanceTraveled() == 0) { // make sure it starts going forward
            duck.move();
        }
        double lastPosition = duck.getDistanceTraveled();
        for (int i = 0; i < 4; i++) {
            duck.move();
            if (lastPosition > duck.getDistanceTraveled()) {
                fail("Should have kept going forward");
            }
        }
    }

    @Test(timeout=15000)
    public void testMove_goesBackward5TimesInARow() {
        double last = 0;
        while (last < duck.getDistanceTraveled()) { // keep going until it starts moving backward
            duck.move();
        }
        last = duck.getDistanceTraveled();
        for (int i = 0; i < 4; i++) {
            duck.move();
            if (last < duck.getDistanceTraveled()) {
                fail("Should have kept moving backward");
            }
        }
    }

    @Test
    public void testMove_noMovementEverySteps10to15() {
        checkSteps();
        checkSteps(); // do twice to ensure pattern holds
    }

    private void checkSteps() {
        for (int i = 0; i < 10; i++) {
            duck.move();
        }
        double last = duck.getDistanceTraveled();
        for (int i = 0; i < 5; i++) {
            duck.move();
            if (last != duck.getDistanceTraveled()) {
                fail("Should not have moved during these steps");
            }
        }
    }
}
