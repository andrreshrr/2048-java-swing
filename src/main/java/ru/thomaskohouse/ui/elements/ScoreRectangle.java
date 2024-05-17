package ru.thomaskohouse.ui.elements;

import ru.thomaskohouse.enums.Sizes;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class ScoreRectangle extends RoundedRectangle {
    private int score;
    private final Color fontColor;

    public ScoreRectangle(int rectX, int rectY, int width, int height, int arcWidth, int arcHeight, Color color, Color fontColor, int score) {
        super(rectX, rectY, width, height, arcWidth, arcHeight, color);
        this.fontColor = fontColor;
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRoundRect(rectX, rectY, width, height, arcWidth, arcHeight);
        Graphics gString = g.create();
        gString.setColor(fontColor);
        Font font = new Font("Arial", Font.PLAIN, Sizes.SCORE_HEADER_FONT.getValue());
        gString.setFont(font);
        gString.drawString("SCORE", (width - font.getSize() - getX() - 10 ) / 2 , font.getSize());
        font = new Font("Arial", Font.PLAIN, Sizes.SCORE_VALUE_FONT.getValue());
        gString.setFont(font);
        gString.drawString(String.valueOf(score), (width - font.getSize() ) / 2, height - font.getSize() + 10);
    }
}
