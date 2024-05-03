package ru.thomaskohouse.enums;

import java.awt.*;

public enum PaletteColors {
    DARK_GRAY(new Color(144, 144, 144)),
    LIGHT_GRAY(new Color(234, 228, 233)),
    LINEN(new Color(255, 241, 230)),
    MISTY_ROSE(new Color(253, 226, 228)),
    MIMY_PINK(new Color(250, 210, 225)),
    MINT_CREAM(new Color(226, 236, 233)),
    LIGHT_BLUE(new Color(190, 225, 230)),
    ISABELLINE(new Color(240, 239, 235)),
    LAVENDER(new Color(223, 231, 253)),
    PERIWINKLE(new Color(205, 218, 253)),
    CARNATION_PINK(new Color(250, 168, 198)),
    RAZZLE_ROSE(new Color(250, 75, 198)),
    FOLLY(new Color(250, 75, 98)),
    TOMATO(new Color(250, 75, 63)),
    TANGELLO(new Color(250, 75, 5));

    private Color color;

    PaletteColors(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
}
