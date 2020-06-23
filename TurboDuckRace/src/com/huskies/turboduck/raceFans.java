package com.huskies.turboduck;

public class raceFans {

    String raceFansName;     // the name of the winning racer
    int raceFansNumber;      // the number id of the winner

    public String getRaceFansName() { return raceFansName; }
    public void setRaceFansName(String raceFansName) { this.raceFansName = raceFansName; }

    public int getRaceFansNumber() {
        return raceFansNumber;
    }
    public void setRaceFansNumber(int raceFansNumber) {this.raceFansNumber = raceFansNumber;}

    public raceFans(int raceFansNumber, String raceFansName) {
        setRaceFansNumber(raceFansNumber);
        setRaceFansName(raceFansName);
    }
}

