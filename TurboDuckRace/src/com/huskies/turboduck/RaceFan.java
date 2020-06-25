package com.huskies.turboduck;

import com.huskies.turboduck.models.Color;

public class RaceFan {

    //Fields
    private String raceFansName;
    private int raceFansNumber;
    private Color preferredColor;

    //Ctor
    public RaceFan(int raceFansNumber, String raceFansName, Color color) {
        setRaceFansNumber(raceFansNumber);
        setRaceFansName(raceFansName);
        if (color == null) { // no preferred color, set to yellow
            color = Color.YELLOW;
        }
        setPreferredColor(color);
    }

    //getters/setters
    public String getRaceFansName() { return raceFansName; }
    public void setRaceFansName(String raceFansName) { this.raceFansName = raceFansName; }

    public int getRaceFansNumber() {
        return raceFansNumber;
    }
    public void setRaceFansNumber(int raceFansNumber) {this.raceFansNumber = raceFansNumber;}

    public Color getPreferredColor() {
        return preferredColor;
    }
    public void setPreferredColor(Color color) {
        preferredColor = color;
    }


}

