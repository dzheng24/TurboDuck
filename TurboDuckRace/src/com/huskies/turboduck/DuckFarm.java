package com.huskies.turboduck;

import java.util.Collection;
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
        // TODO
        return null;
    }

    /**
     * Constructs a Duck with default parameters and returns it.
     * @return Duck
     */
    public static Duck getDuck() {
        // TODO
        return null;
    }

    /**
     * Constructs a Duck with a give name and color as preference.
     * @param name String name to give the duck
     * @param color Enum Color for the color of the duck to be.
     * @return Duck
     */
    public static Duck getDuck(String name, Color color) {
        return null;
    }
}
