package com.huskies.turboduck;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Thread.interrupted;

public class Race {

    private Race() {
        // empty for static class
    }

    /**
     * Runs race. e.g. each racer in racers gets their own thread.
     */
    public static WinningBoard startRace(Map<Integer, Duck> racers, double duration, boolean logResults) {

        int hours = (int) duration / 60;
        int minutes =  (int) duration % 60;
        int seconds = getSeconds(duration);
        LocalDateTime endTime = LocalDateTime.now()
                .plusHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds);
        System.out.println("Race is ending at " + endTime.format(DateTimeFormatter.ISO_LOCAL_TIME)); //////////////////// delete this later?

        // need to keep track of each thread to interrupt later.
        Map<Integer, Thread> threads = new HashMap<>();

        for (Integer entry : racers.keySet()) {
            threads.put(entry, new Thread(() -> runDuckThread(racers.get(entry))));
            threads.get(entry).start();
        }

        while (LocalDateTime.now().isBefore(endTime)) {
            // wait until race is done, do nothing for now
            // TODO track the position of each duck
        }

        return finishRace(threads, racers);
    }

    /**
     * Finds the seconds of a a double representing time in minutes. Rounds to whole seconds.
     * @param val minutes
     * @return
     */
    static int getSeconds(double val) {
        BigDecimal temp = new BigDecimal(String.valueOf(val));
        return temp.subtract(new BigDecimal(temp.intValue())) // get the decimal part
                .multiply(new BigDecimal(60)) // get percentage of 60
                .intValue();
    }

    /**
     * The runnable portion of the race
     * @param racer
     * @return
     */
    private static void runDuckThread(Duck racer) {
        while (!interrupted()) {
            try {
                racer.move();
                System.out.println("The duck moved!");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread finished. Delete later (only for debugging)");
                break;
            }
        }
    }

    /**
     * This finishes the race and announces the winner, recording if
     * @return
     */
    private static  WinningBoard finishRace(Map<Integer, Thread> threads, Map<Integer, Duck> racers) {
        for (Integer item : threads.keySet()) { // stop each duck
            threads.get(item).interrupt();
        }

        // Figure out who won.
        Integer winningID = racers.entrySet().stream()
                .max((entry1, entry2) -> (int) (entry1.getValue().getPoint().getxPosition() - entry2.getValue().getPoint().getxPosition()))
                .orElseGet(null)
                .getKey();

        Duck winning = racers.get(winningID);

        return null;
    }

}
