package com.huskies.turboduck;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RaceTest {
    Map<Integer, Duck> allDucks;
    Map<Integer, Duck> singleDuck;

    @Before
    public void setUp() {
        allDucks = new HashMap<>();
        singleDuck = new HashMap<>();
        singleDuck.put(0, new Duck());

        for (int i = 0; i < 5; i++) {
            allDucks.put(i, new Duck("Duck " + i, Color.YELLOW));
        }
    }

    @Ignore @Test
    public void testCheckDuration() {
       Race.startRace(singleDuck, 1, false);
       Race.startRace(singleDuck, 10, false);

    }

    @Ignore("Waiting for Logging class.")
    @Test
    public void testMakeRace_withLogging() {

    }

    @Test(timeout = 16000)
    public void testStartRace_1Duck() {
        Race.startRace(singleDuck, 0.25, false);
    }

    @Ignore("Dont know how to test this yet. ")
    @Test
    public void testWinningDuck() {
        Race.startRace(allDucks, 0.1, false);

    }

    @Test
    public void testGetSeconds() {
        assertEquals(15, Race.getSeconds(15.25));
        assertEquals(30, Race.getSeconds(12.5));
        assertEquals(54, Race.getSeconds(11.9));
    }
}