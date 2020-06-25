package com.huskies.turboduck;

import com.huskies.turboduck.models.Color;
import com.huskies.turboduck.models.Duck;
import com.huskies.turboduck.models.YellowDuck;
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
        singleDuck.put(0, DuckFarm.getDuck());

        for (int i = 0; i < 5; i++) {
            allDucks.put(i, new YellowDuck("Duck " + i, Color.YELLOW));
        }
    }

    @Ignore("Test is long, comment out if checking. ")
    @Test
    public void testCheckDuration() {
       Race.startRace(singleDuck, 1);
       Race.startRace(singleDuck, 3.2);

    }

    @Test(timeout = 16000)
    public void testStartRace_1Duck() {
        Race.startRace(singleDuck, 0.25);
    }

    @Test
    public void testWinningDuck() {
        Race.startRace(allDucks, 0.1);
        allDucks.values().forEach((duck) -> System.out.println("Duck \"" + duck.getName() + "\" finished at "
                + duck.getDistanceTraveled()));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullRace() {
        Race.startRace(null, 0.1);
    }

    @Test(timeout = 7000)
    public void testEmptyRace_stillRuns() {
        Race.startRace(new HashMap<>(), 0.1);
    }

    @Test
    public void testNegativeDuration() {
        try {
            Race.startRace(allDucks, -10);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e ){
            // it worked
        }
    }

    @Test
    public void testGetSeconds() {
        assertEquals(15, Race.getSeconds(15.25));
        assertEquals(30, Race.getSeconds(12.5));
        assertEquals(54, Race.getSeconds(11.9));
    }

    @Test
    public void testGetWinningID() {
        // move() is random, keep iterating until the duck isn't at the start point
        while (allDucks.get(0).getDistanceTraveled() != 0) {
            moveDuck();
        }
        assertEquals(0, Race.getWinningID(allDucks));
    }

    private void moveDuck() {
        for (int i = 0; i < 10; i++) {
            allDucks.get(0).move();
        }
    }

    @Test
    public void testGetWinningID_returnsNegative1_withEmptyMap() {
        assertEquals(-1,Race.getWinningID(new HashMap<>()));
    }
}