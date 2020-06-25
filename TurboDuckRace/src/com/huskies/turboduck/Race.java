package com.huskies.turboduck;

import com.huskies.turboduck.models.Duck;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Thread.interrupted;

public class Race {

    private Race() {
        // empty for static class
    }

    /**
     * Runs race. e.g. each racer in racers gets their own thread.
     */
    public static void startRace(Map<Integer, Duck> racers, double duration) {
        if (racers == null || duration < 0) {
            throw new IllegalArgumentException();
        }

        int hours = (int) duration / 60;
        int minutes =  (int) duration % 60;
        int seconds = getSeconds(duration);
        LocalDateTime endTime = LocalDateTime.now()
                .plusHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds);

        // need to keep track of each thread to interrupt later.
        Collection<Thread> threads = new LinkedList<>();
        racers.values().forEach((entry) -> {
            Thread thread = new Thread(() -> runDuckThread(entry));
            thread.start();
            threads.add(thread);
        });


        // wait until race is done
        LocalDateTime curr;
        while ((curr = LocalDateTime.now()).isBefore(endTime)) {
            Duration dur = Duration.between(curr, endTime);

            // adding the distance point
            try {

                racers.values().forEach((duck) -> {
                    String racerProgress = "->".repeat((int) duck.getDistanceTraveled());
                    System.out.println(duck.getName() + racerProgress);
                });
                System.out.println();
                System.out.printf("Remaining time %d:%d:%d", dur.toHoursPart(),
                        dur.toMinutesPart(), dur.toSecondsPart());
                System.out.println();
                System.out.println("==================================");
                System.out.println();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("Race finished, stopping ducks...");
        finishRace(threads);
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
     * Runnable method for each duck to start its own thread.
     * @param racer
     * @return
     */
    private static void runDuckThread(Duck racer) {
        while (!interrupted()) {
            try {
                racer.move();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * Interrupts each thread for each racer in the race.
     * @return
     */
    private static void finishRace(Collection<Thread> threads) {
        threads.forEach(Thread::interrupt);
    }

    /**
     * Gets the ID of the winner based on the distance traveled. Returns -1 if map is empty or null.
     * @param racers
     * @return
     */
    public static int getWinningID(Map<Integer, Duck> racers){
        Integer winningID;
        try {
            winningID = Objects.requireNonNull(racers.entrySet().stream()
                    .max(Comparator.comparingDouble((entry) -> entry.getValue().getDistanceTraveled()))
                    .orElse(null))
                    .getKey();
        } catch (NullPointerException e) {
            winningID = -1;
        }

        return winningID;
    }

}
