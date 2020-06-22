package com.huskies.turboduck;

import java.time.LocalDateTime;
import java.util.Map;

public class Race {
    LocalDateTime endTime;  // the ending time for the race
    int duration;           // duration of the race (in minutes)
    boolean logResults;     // if winningBoard is going to log results

    public Race(int duration) {
        // TODO
    }

    public Race(int duration, boolean logResults) {
        // TODO
    }

    /**
     * Starts the race. e.g. each racer in racers gets their own thread.
     */
    public void startRace(Map<Integer, ? extends Duck> racers) {
        // TODO
        // iterate through each racer,
        // start its own thread
        // update willy nilly <- need ot discuss where the runnable implmentation should be (in duck?)
    }

    /**
     * This finishes the race and announces the winner, recording if
     * @return
     */
    public WinningBoard finishRace() {
        // TODO
        return null;
    }

}
