package com.huskies.ui;

import com.huskies.turboduck.Duck;

import java.awt.*;
import java.util.Map;

public class Main {

    private Map<Integer, Duck> racers;

    /**
     * Starts the UI in its own thread.
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(RaceFrame::new);
    }
}
