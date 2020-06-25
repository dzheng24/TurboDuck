package com.huskies.turboduck.models;

import java.text.DecimalFormat;

public abstract class Duck {

    // FIELDS
    private String name;
    private Color color;
    Point distancePoint;
    final DecimalFormat df = new DecimalFormat("#.##");


    // CTORS
    public Duck() {
        this("default duck", Color.YELLOW);
    }

    public Duck(String name, Color color) {
        this.name = name;
        this.color = color;
        this.distancePoint = new Point();
    }

    // BUSINESS METHODS
    public abstract void move();

    // GETTERS/SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public double getDistanceTraveled() {
        return distancePoint.getxPosition();
    }


    class Point {

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

        public void setxPosition(double xPosition) {
            this.xPosition = xPosition;
        }
    }

}

