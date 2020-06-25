package com.huskies.turboduck;

import com.huskies.turboduck.models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DuckFarmTest {

    private List<RaceFan> racers;

    @Before
    public void setUp() throws Exception {
        racers = new ArrayList<>();
        racers.add(new RaceFan(1, "Levi", Color.YELLOW));
        racers.add(new RaceFan(2, "Levi", Color.YELLOW));
        racers.add(new RaceFan(3, "Doug", Color.YELLOW));
        racers.add(new RaceFan(4, "Levi", Color.RED));
    }

    @Test
    public void testGetMultipleDucks() {
        Map<Integer, Duck> testMap = DuckFarm.getDucks(3);
        Map<Integer, Duck> testMap2 = DuckFarm.getDucks(10);

        assertEquals(3, testMap.size());
        assertEquals(10, testMap2.size());
    }

    @Test
    public void testGetMultipleDucks_returnEmptyMap_with0DucksRequested() {
        Map<Integer, Duck> ducks = DuckFarm.getDucks(0);
        assertTrue(ducks.isEmpty());
    }

    @Test
    public void testGetMultipleDucks_returnEmptyMap_withNegativeNumber() {
        assertTrue(DuckFarm.getDucks(-1).isEmpty());
    }

    @Test
    public void testGetSingleDuck() {
        Duck testDuck = DuckFarm.getDuck();

        assertEquals("default duck", testDuck.getName());
        assertEquals(Color.YELLOW, testDuck.getColor());
    }

    @Test
    public void testGetSingleDuckWithCustomParams() {
        Duck testDuck = DuckFarm.getDuck("JB", Color.RED);

        assertEquals("JB", testDuck.getName());
        assertEquals(Color.RED, testDuck.getColor());
        assertEquals(RedDuck.class, testDuck.getClass());
    }

    @Test
    public void testMakingDucks_fromRaceFanList() {
        Map<Integer, Duck> ducks = DuckFarm.getDucks(racers);
        assertEquals(4, ducks.size());
        assertEquals(ducks.get(1).getName(), "Levi's YELLOW duck");
        assertEquals(ducks.get(4).getName(), "Levi's RED duck");
    }

    @Test
    public void testMakingDucks_fromRaceFanList_returnsEmptyMap_whenGivenEmptyList() {
        Map<Integer, Duck> ducks = DuckFarm.getDucks(new ArrayList<>());
        assertTrue(ducks.isEmpty());
    }

    @Test
    public void testGetDuck_makesCorrectClass() {
        Duck duck1 = DuckFarm.getDuck("temp", Color.RED);
        Duck duck2 = DuckFarm.getDuck("temp", Color.BLACK);
        Duck duck3 = DuckFarm.getDuck("", Color.BLUE);

        assertEquals(RedDuck.class, duck1.getClass());
        assertEquals(BlackDuck.class, duck2.getClass());
        assertEquals(BlueDuck.class, duck3.getClass());
    }
}