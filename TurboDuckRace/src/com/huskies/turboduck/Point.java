package com.huskies.turboduck;

public class Point {

    private double xPosition = 0.0;   // Location along x dimension of this point
    private final double MIN_POSITION = 0;
    private final double MAX_POSITION = 50;

    public Point() {
        xPosition = MIN_POSITION;
    }

    /**
     * Apply some transformation to this point.
     */
    public void changeRacerPosition(double increment) {
        double temp = xPosition + increment;
        if (temp < MIN_POSITION) {
            xPosition = MIN_POSITION;
        } else if (temp > MAX_POSITION) {
            xPosition = MAX_POSITION;
        } else {
            xPosition = temp;
        }
    }

    /**
     * Returns the xPosition of this point.
     * @return double
     */
    public double getxPosition() {
        return xPosition;
    }
}
