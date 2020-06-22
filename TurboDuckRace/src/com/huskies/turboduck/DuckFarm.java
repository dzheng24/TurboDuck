package com.huskies.turboduck;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DuckFarm {

    private DuckFarm() {
        // private for static class
    }

    /**
     * Constructs a Map containing ducks. Each duck has its own integer value as an ID.
     * @param num the number of ducks to place in the map
     * @return
     */
    public static Map<Integer, Duck> getDucks(int num) {
        // DONE
        Map<Integer, Duck> ducksForRace = new HashMap<>();
        for (int i = 1; i <= num; i++) {
            Duck singleDuck = new Duck();
            ducksForRace.put(i, singleDuck);
        }
        System.out.println(ducksForRace);
        return ducksForRace;
    }

    /**
     * Constructs a Duck with default parameters and returns it.
     * @return Duck
     */
    public static Duck getDuck() {
        // DONE
        Duck defaultDuck = new Duck();
        return defaultDuck;
    }

    /**
     * Constructs a Duck with a give name and color as preference.
     * @param name String name to give the duck
     * @param color Enum Color for the color of the duck to be.
     * @return Duck
     */
    public static Duck getDuck(String name, Color color) {
        Duck customDuck = new Duck();
        customDuck.setName(name);
        customDuck.setColor(color);
        return customDuck;
    }
}