package com.huskies.turboduck;

public class Point {

    private double xPosition = 0.0;   // Location along x dimension of this point
    private double yPosition;   // y Position

    public Point() {
        xPosition = 0.0;
        yPosition = 0.0;
    }

    /**
     * Apply some transformation to this point.
     */
    public void changeRacerPosition(double increment) {
        // DONE
        this.xPosition = this.xPosition + increment;
    }

    /**
     * Returns the xPosition of this point.
     * @return double
     */
    public double getxPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

}
