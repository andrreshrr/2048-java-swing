package ru.thomaskohouse.enums;

public enum Sizes {
    WINDOW_WIDTH(700),
    WINDOW_HEIGHT(800),
    BACKGROUND_RECTANGLE(600),
    BACKGROUND_INDENT_X(45),
    BACKGROUND_INDENT_Y(30),
    BACKGROUND_RECT_ARC_WIDTH(30),
    BACKGROUND_RECT_ARC_HEIGHT(30),
    GAME_RECTANGLE(145),
    GAME_RECT_ARC_WIDTH(20),
    GAME_RECT_ARC_HEIGHT(20);

    private final int value;

    Sizes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
