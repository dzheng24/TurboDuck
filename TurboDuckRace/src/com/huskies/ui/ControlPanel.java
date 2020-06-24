package com.huskies.ui;

import com.huskies.turboduck.Race;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

public class ControlPanel extends JPanel {

    private JLabel time;
    private JButton startButton;
    private JButton pauseButton;
    private JTextField duration;
    private JButton loggingButton;
    private boolean willLog = false;


    private RacePanel racePanel;

    public ControlPanel() {
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        time = new JLabel("Time Remaining: 00:00:00");
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        duration = new JTextField("");
        loggingButton = new JButton("Log Results");

        setUp();

        // put constraints on different buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0, 2, 10);
        this.add(startButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(pauseButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        this.add(time, gbc);

        gbc.gridy = 5;
        gbc.gridheight = 1;
        this.add(duration, gbc);

        gbc.gridy = 6;
        this.add(loggingButton, gbc);

    }

    public void setRacePanel(RacePanel racePanel) {
        this.racePanel = racePanel;
    }

    private void setUp() {
        addActionEvents();

        PlainDocument document = (PlainDocument) duration.getDocument();
        document.setDocumentFilter(new DigitFilter());
    }

    private void addActionEvents() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!racePanel.isRacing()) { // start the race!
                    Double raceDuration = null;
                    try {
                        raceDuration = Double.parseDouble(duration.getText());
                    } catch (NumberFormatException nfe) {
                        raceDuration = 0.0;
                    }

                    racePanel.startRace(raceDuration, false);
                    startButton.setText("Cancel");
                } else { // cancel the race
                    racePanel.stopRace();
                    System.out.println("Canceled the race, no one won.");
                    startButton.setText("Start");
                }
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });



    }

    public void doLoop() {
        Duration timeLeft = Race.getTimeRemaining();
        time.setText("Time until race done " +
                timeLeft.toHoursPart() +
                ":" + timeLeft.toMinutesPart() +
                ":" + timeLeft.toSecondsPart());
    }

    /**
     * Private class to only let digits through. Extends document filter to apply with
     */
   private class DigitFilter extends DocumentFilter {
        @Override
       public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            super.insertString(fb, offset, checkString(string), attr);
        }

        @Override
       public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            super.replace(fb, offset, length, checkString(text), attrs);
        }

        private String checkString(String text) {
            StringBuilder sb = new StringBuilder(text);
            int index = 0;
            while (index < sb.length()) {
                if (Character.isDigit(sb.charAt(index)) || sb.charAt(index) == '.') {
                    index++;
                } else {
                    sb.deleteCharAt(index);
                }
            }
            return sb.toString();
        }
   }


}
