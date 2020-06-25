package com.huskies.turboduck.models;

public enum Color {
    YELLOW(2),
    RED(5),
    BLACK(30),
    GREEN(1),
    WHITE(2.5),
    GREY(0);

    private double stepModifer;

    Color(double stepModifer) {
        this.stepModifer = stepModifer;
    }

    public double getStepModifer() {
        return stepModifer;
    }
}
