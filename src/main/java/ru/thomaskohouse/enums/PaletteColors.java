package ru.thomaskohouse.enums;

import java.awt.*;

public enum PaletteColors {
    DARK_GRAY(new Color(144, 144, 144)),
    LIGHT_GRAY(new Color(234, 228, 233)),
    LINEN(new Color(255, 241, 230));

    private Color color;

    PaletteColors(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
}
