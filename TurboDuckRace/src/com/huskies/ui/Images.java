package com.huskies.ui;

import javax.swing.*;
import java.net.URL;

public enum Images {
    YELLOW(UIConstants.YELLOW_IMAGE),
    RED(UIConstants.RED_IMAGE),
    BLUE(UIConstants.BLUE_IMAGE),
    GREEN(UIConstants.GREEN_IMAGE),

    FLAG(UIConstants.FLAG_IMAGE),
    BACKGROUND(UIConstants.BACKGROUND_IMAGE);

    // Image URL
    private final URL imageURL;

    private Images(URL imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Makes an image to use for painting onto the UI
     * @return
     */
    public ImageIcon createImage() {
        return switch (this) {
            case FLAG, BACKGROUND -> new ImageIcon(imageURL);
            default -> new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(UIConstants.IMAGE_SCALING, UIConstants.IMAGE_SCALING, java.awt.Image.SCALE_SMOOTH));
        };
    }
}
