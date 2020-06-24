package com.huskies.turboduck;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
//                    StringBuffer racerProgress = new StringBuffer();
//                    for (double i = 0; i < duck.getDistanceTraveled(); i = i + 0.1) {
//                        racerProgress.append("->");
//                    }
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
        finishRace(threads, racers);
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
//                System.out.println("Duck \"" + racer.getName() + "\" is at position: " + racer.getDistanceTraveled()); // remove for debugging later.
                Thread.sleep(500);
            } catch (InterruptedException e) {
//                System.out.println("Race finished, stopping ducks...");
//                System.out.println("Duck \"" + racer.getName() + "\" has stopped.");
                break;
            }
        }
    }

    /**
     * This finishes the race and announces the winner, recording if
     * @return
     */
    private static void finishRace(Collection<Thread> threads, Map<Integer, Duck> racers) {
        threads.forEach(Thread::interrupt);
    }
    
    public static int getWinningID(Map<Integer, Duck> racers){
        Integer winningID = racers.entrySet().stream()
                .max(Comparator.comparingDouble((entry) -> entry.getValue().getDistanceTraveled()))
                .orElse(null)
                .getKey();

        Duck winning = racers.get(winningID);
        return winningID;
    }

}
