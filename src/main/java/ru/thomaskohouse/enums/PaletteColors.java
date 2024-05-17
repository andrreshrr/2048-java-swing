package ru.thomaskohouse.enums;

import java.awt.Color;

public enum PaletteColors {
    DARK_GRAY(new Color(0x9c9c92)),
    LIGHT_GRAY(new Color(204, 192, 179)),
    LINEN(new Color(255, 241, 230)),
    RED1(new Color(0xeee4da)),
    RED2(new Color(0xede0c8)),
    RED3(new Color(0xf2b179)),
    RED4(new Color(0xf59563)),
    RED5(new Color(0xf67c5f)),
    RED6(new Color(0xf65e3b)),
    RED7(new Color(0xedcf72)),
    RED8(new Color(0xedcc61)),
    RED9(new Color(0xedc850)),
    RED10(new Color(0xedc53f)),
    RED11(new Color(0xedc22e)),
    SMALL_VAL( new Color(0x776e65)),
    BIG_VAL(new Color(0xf9f6f2)),
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
