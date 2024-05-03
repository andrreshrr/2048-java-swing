package ru.thomaskohouse.enums;

public enum Sizes {
    WINDOW_WIDTH(700),
    WINDOW_HEIGHT(800),
    BACKGROUND_RECTANGLE(600),
    BACKGROUND_INDENT_X(45),
    BACKGROUND_INDENT_Y(30),
    GAME_RECTANGLE(145);

    private final int value;

    Sizes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
