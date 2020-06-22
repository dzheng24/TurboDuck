package com.huskies.turboduck;

import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Thread.interrupted;

public class Race {
    LocalDateTime endTime;  // the ending time for the race
    int duration;           // duration of the race (in minutes)
    boolean logResults;     // if winningBoard is going to log results

    public Race(int duration) {
        this.duration = duration;
    }

    public Race(int duration, boolean logResults) {
        this(duration);
        this.logResults = logResults;
    }

    /**
     * Starts the race. e.g. each racer in racers gets their own thread.
     */
    public void startRace(Map<Integer, Duck> racers) {
        // TODO
        // iterate through each racer,
        // start its own thread
        // update willy nilly <- need ot discuss where the runnable implmentation should be (in duck?)

        endTime = LocalDateTime.now().plusMinutes(duration);
        System.out.println("Race is ending at " + endTime);

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

        finishRace(threads, racers);
    }

    /**
     * The runnable portion of the race
     * @param racer
     * @return
     */
    private void runDuckThread(Duck racer) {
        while (!interrupted()) {
            try {
                racer.move();
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
    public WinningBoard finishRace(Map<Integer, Thread> threads, Map<Integer, Duck> racers) {
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
