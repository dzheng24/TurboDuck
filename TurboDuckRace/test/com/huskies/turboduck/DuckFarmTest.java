package com.huskies.turboduck;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class DuckFarmTest {

    private DuckFarm duckFactory;

    @Test
    public void testGetMultipleDucks() {
        Map<Integer, Duck> testMap = duckFactory.getDucks(3);
        Map<Integer, Duck> testMap2 = duckFactory.getDucks(10);

        assertEquals(3, testMap.size());
        assertEquals(10, testMap2.size());
    }

    @Test
    public void testGetSingleDuck() {
        Duck testDuck = duckFactory.getDuck();

        assertEquals("default duck", testDuck.getName());
        assertEquals(Color.YELLOW, testDuck.getColor());
    }

    @Test
    public void testGetSingleDuckWithCustomParams() {
        Duck testDuck = duckFactory.getDuck("JB", Color.RED);

        assertEquals("JB", testDuck.getName());
        assertEquals(Color.RED, testDuck.getColor());

    }

}