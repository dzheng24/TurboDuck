package com.huskies.ui;

import com.huskies.turboduck.DuckFarm;

import javax.swing.*;
import java.awt.*;

public class RaceFrame extends JFrame {

    private JSplitPane splitPane;

    public RaceFrame() {
        initUI();
    }

    private void initUI() {
        setTitle(UIConstants.title);
        setIconImage(Images.FLAG.createImage().getImage());

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        add(splitPane);
        ControlPanel controlPanel = new ControlPanel();
        RacePanel racePanel = new RacePanel(controlPanel, DuckFarm.getDucks(20));
        controlPanel.setRacePanel(racePanel);
        splitPane.add(racePanel);
        splitPane.add(controlPanel);

        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }
}
