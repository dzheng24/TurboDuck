package com.huskies.turboduck;

import com.huskies.ui.Images;

import java.awt.*;
import java.text.DecimalFormat;

public class Duck {

    // FIELDS
    private String name;
    private Color color;
    private Point distancePoint;
    private Image image;

    // CTORS
    public Duck() {
        this("default duck", Color.RED);
    }

    public Duck(String name, Color color) {
        this.name = name;
        this.color = color;
        this.distancePoint = new Point();
        image = Images.valueOf(color.name()).createImage().getImage();
    }

    // BUSINESS METHODS
    public void move() {
        // DONE
        double randomIncrement = Math.random();
        DecimalFormat df = new DecimalFormat("#.##");
        randomIncrement = Double.valueOf(df.format(randomIncrement));
        this.distancePoint.changeRacerPosition(randomIncrement);
//        System.out.println("Duck has moved " + randomIncrement + " spots, and is now at " + this.getDistanceTraveled());

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

    public double getLineUpPosition() {
        return distancePoint.getYPosition();
    }

    public void setLineUpPosition(double yPosition) {
        distancePoint.setyPosition(yPosition);
    }

    public Image getImage() {
        return image;
    }
}

