package com.huskies.turboduck;

import java.text.DecimalFormat;

public class Duck {

    // FIELDS
    private String name;
    private Color color;
    private Point distancePoint;

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
    public void move() {
        double randomIncrement = (Math.random() - 0.5) * 2; // -0.5 to 0.5
        DecimalFormat df = new DecimalFormat("#.##");
        randomIncrement = Double.parseDouble(df.format(randomIncrement));
        this.distancePoint.changeRacerPosition(randomIncrement);
    }

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
}

