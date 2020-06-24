package com.huskies.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaceLoop implements ActionListener {

    private RacePanel racePanel;
    private ControlPanel controlPanel;

    public RaceLoop(RacePanel racePanel, ControlPanel controlPanel) {
        this.racePanel = racePanel;
        this.controlPanel = controlPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        racePanel.doLoop();
        controlPanel.doLoop();
    }
}
