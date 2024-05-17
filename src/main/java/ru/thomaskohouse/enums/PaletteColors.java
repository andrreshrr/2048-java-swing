package ru.thomaskohouse.enums;

import java.awt.Color;

public enum PaletteColors {
    DARK_GRAY(new Color(144, 144, 144)),
    LIGHT_GRAY(new Color(234, 228, 233)),
    LINEN(new Color(255, 241, 230)),
    RED1(new Color(100, 18, 32)),
    RED2(new Color(110, 20, 35)),
    RED3(new Color(133, 24, 42)),
    RED4(new Color(161, 29, 51)),
    RED5(new Color(167, 30, 52)),
    RED6(new Color(178, 30, 53)),
    RED7(new Color(189, 31, 54)),
    RED8(new Color(199, 31, 55)),
    RED9(new Color(218, 30, 55)),
    RED10(new Color(224, 30, 55)),
    RED11(new Color(236, 31, 58)),
    WIN_MESSAGE(new Color(213, 205, 78)),
    LOSE_MESSAGE(new Color(213, 0, 78)),;

    private final Color color;

    PaletteColors(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
}
