package com.huskies.turboduck;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RaceTest {
    Map<Integer, Duck> allDucks;

    @Before
    public void setUp() {
        allDucks = new HashMap<>();
        allDucks.put(0, new Duck());
    }

    @Test(timeout = 180000) // 3 minutes check
    public void testCheckDuration() {
       Race.startRace(allDucks, 1, false);
       Race.startRace(allDucks, 10, false);

    }

    @Test
    public void testMakeRace_withLogging() {

    }

    @Test
    public void testStartRace_1Duck() {
        Map<Integer, Duck> singleDuck = new HashMap<>();
        singleDuck.put(1, new Duck());
        Race.startRace(singleDuck, 1, false);
    }

    @Test
    public void testWinningDuck() {
        Race.startRace(allDucks, 1, false);
    }


    @Test
    public void testGetSeconds() {
        assertEquals(15, Race.getSeconds(15.25));
        assertEquals(30, Race.getSeconds(12.5));
        assertEquals(54, Race.getSeconds(11.9));
    }
}