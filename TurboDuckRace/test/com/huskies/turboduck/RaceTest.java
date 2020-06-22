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

    @Test
    public void testCheckDuration() {
        Race race1 = new Race();
        Race race2 = new Race(15);

        race1.startRace(allDucks);
    }

    @Test
    public void testMakeRace_withLogging() {

    }

    @Test
    public void testStartRace_1Duck() {

    }
}