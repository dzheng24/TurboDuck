package com.huskies.turboduck.models;

import com.huskies.turboduck.models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DuckTest {

    @Test
    public void testSingleDuck() {
        Duck testDuck = new YellowDuck();

        assertEquals("default duck", testDuck.getName());
        assertEquals(Color.YELLOW, testDuck.getColor());
    }

    @Test
    public void testSingleDuckWithCustomParams() {
        Duck testDuck = new GreenDuck("David", Color.GREEN);

        assertEquals("David", testDuck.getName());
        assertEquals(Color.GREEN, testDuck.getColor());
        assertEquals(0.0, testDuck.getDistanceTraveled(), .001);
    }

    @Test
    public void testingDistanceTraveled() {
        Duck testDuck = new YellowDuck();

        assertEquals(0.0, testDuck.getDistanceTraveled(), .001);
        testDuck.move();
        // tell the duck to move again
        testDuck.move();
        // and again...
        testDuck.move();
        System.out.println();
        System.out.println(testDuck.getDistanceTraveled());

        testDuck.distancePoint.setxPosition(1.34);
        assertEquals(1.34, testDuck.getDistanceTraveled(), 0.001);

    }

}