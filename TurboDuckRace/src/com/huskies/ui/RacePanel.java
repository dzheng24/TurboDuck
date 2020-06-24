package com.huskies.ui;

import com.huskies.turboduck.Duck;
import com.huskies.turboduck.DuckFarm;
import com.huskies.turboduck.Race;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Map;

public class RacePanel extends JPanel {

    private ImageIcon backgroundImage;  // racing panel background image
    private Timer timer;                // for animation timing and painting
    private Map<Integer, Duck> racers;    // ducks in the race
    private Thread racingThread;        // thread for racing. Hold onto to interrupt.
    private Collection<Thread> racerThreads;
    private boolean isRacing;

    public RacePanel(ControlPanel controlPanel, Map<Integer, Duck> racers) {
        initUI();
        initVariables(controlPanel, racers);
    }

    private void initUI() {
        setPreferredSize(new Dimension(UIConstants.DEFAULT_WIDTH, UIConstants.DEFAULT_HEIGHT));
    }

    private void initVariables(ControlPanel controlPanel, Map<Integer, Duck> racers) {
        backgroundImage = Images.BACKGROUND.createImage();
        timer = new Timer(UIConstants.ANIMATION_SPEED, new RaceLoop(this, controlPanel));
        timer.start();
        this.racers = racers;
        isRacing = false;

    }

    /**
     * Method to draw a racer image icon where they are located.
     * @param graphics
     * @param racer
     */
    private void drawRacer(Graphics graphics, Duck racer) {
        graphics.drawImage(racer.getImage(), (int) racer.getDistanceTraveled(), (int) racer.getLineUpPosition(), this);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(backgroundImage.getImage(),
                0,0, this.getWidth(), this.getHeight(),
                null);

        racers.values().forEach((racer) -> drawRacer(graphics, racer));

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Method to use with a listener event to update paint and location of racers
     */
    public void doLoop() {
        update();
        repaint();
    }

    /**
     * Method to update each racer. Will be diverted to instead read the position of each duck isntead of manually moving them.
     */
    private void update() {
        if (racingThread == null) {
            isRacing = false;
        } else if (racingThread.isAlive()) {
            isRacing = true;
        } else if (!racingThread.isAlive() && isRacing()) {
            stopRace();
            System.out.println("The winner is \"" + racers.get(Race.getWinningID(racers)).getName() + "\"!");
        }

    }

    public void startRace(double duration, boolean logging) {
        // reset all of them to an xposition of 0
        racers.values().forEach(Duck::resetToStart);
        racerThreads = Race.startRaceUI(racers, duration, logging);
        racingThread = Race.getWaitingRaceThread();
        isRacing = true;
    }
    public boolean isRacing() {
        return isRacing;
    }

    public void stopRace() {
        racingThread.interrupt();
        Race.finishRace(racerThreads, racers);
        isRacing = false;
    }
}
