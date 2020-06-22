package com.huskies.turboduck;

public class Point {

    private double xPosition = 0.0;   // Location along x dimension of this point

    public Point() {
        xPosition = 0.0;
    }

    /**
     * Apply some transformation to this point.
     */
    public void changeRacerPosition(double increment) {
        // TODO
        this.xPosition = this.xPosition + increment;
    }

    /**
     * Returns the xPosition of this point.
     * @return double
     */
    public double getxPosition() {
        return xPosition;
    }
}
