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
        assertEquals(0.0, testDuck.getPoint(), .001);
    }
}