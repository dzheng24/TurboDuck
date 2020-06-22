package com.huskies.turboduck;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuckTest {

    @Test
    public void testSingleDuck() {
        Duck testDuck = new Duck();

        assertEquals("default duck", testDuck.getName());
        assertEquals(Color.YELLOW, testDuck.getColor());
    }

    @Test
    public void testSingleDuckWithCustomParams() {
        Duck testDuck = new Duck("David", Color.BLUE);

        assertEquals("David", testDuck.getName());
        assertEquals(Color.BLUE, testDuck.getColor());
        assertEquals(0.0, testDuck.getDistanceTraveled(), .001);
    }

    @Test
    public void testingDistanceTraveled() {
        Duck testDuck = new Duck("Jay", Color.GREEN);

        assertEquals(0.0, testDuck.getDistanceTraveled(), .001);
        testDuck.move();
        // tell the duck to move again
        testDuck.move();
        // and again...
        testDuck.move();
        System.out.println();
        System.out.println(testDuck.getDistanceTraveled());

    }
}