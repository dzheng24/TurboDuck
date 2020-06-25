package com.huskies.turboduck;

import com.huskies.turboduck.models.*;

import java.util.HashMap;
import java.util.List;
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
            Duck singleDuck = new YellowDuck();
            singleDuck.setName("Duck " + i);
            ducksForRace.put(i, singleDuck);
        }
        return ducksForRace;
    }

    public static Map<Integer, Duck> getDucks(List<RaceFan> fansList) {
        Map<Integer, Duck> returning = new HashMap<>();
        for (RaceFan fan : fansList) {
            returning.put(fan.getRaceFansNumber(),
                    getDuck(fan.getRaceFansName() + "'s " + fan.getPreferredColor() + " duck", fan.getPreferredColor()));
        }
        return returning;
    }



    /**
     * Constructs a Duck with default parameters and returns it.
     * @return Duck
     */
    public static Duck getDuck() {
        return getDuckImplementation("default duck", Color.YELLOW);
    }

    /**
     * Constructs a Duck with a give name and color as preference.
     * @param name String name to give the duck
     * @param color Enum Color for the color of the duck to be.
     * @return Duck
     */
    public static Duck getDuck(String name, Color color) {
        return getDuckImplementation(name, color);
    }

    private static Duck getDuckImplementation(String name, Color color) {
        Duck returning;
        switch (color) {
            case BLACK -> returning = new BlackDuck(name, color);
            case YELLOW -> returning = new YellowDuck(name, color);
            case RED -> returning = new RedDuck(name, color);
            case BLUE -> returning = new BlueDuck(name, color);
            case GREY -> returning = new GreyDuck(name, color);
            case BROWN -> returning = new BrownDuck(name, color);
            case GREEN -> returning = new GreenDuck(name, color);
            case WHITE -> returning = new WhiteDuck(name, color);
            default -> returning = new YellowDuck();
        }
        return returning;
    }
}