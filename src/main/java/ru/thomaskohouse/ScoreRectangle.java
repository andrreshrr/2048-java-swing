package ru.thomaskohouse;

import java.awt.*;

public class ScoreRectangle extends RoundedRectangle {
    private int score;
    private Color fontColor;

    ScoreRectangle(int rectX, int rectY, int width, int height, int arcWidth, int arcHeight, Color color, Color fontColor, int score) {
        super(rectX, rectY, width, height, arcWidth, arcHeight, color);
        this.fontColor = fontColor;
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.setColor(color);

        g.fillRoundRect(rectX, rectY, width, height, arcWidth, arcHeight);

        Graphics gString = g.create();
        gString.setColor(fontColor);
        Font font = new Font("Arial", Font.PLAIN, 30);
        gString.setFont(font);
        gString.drawString("SCORE", (width - font.getSize() - getX() - 10 ) / 2 , font.getSize());

        font = new Font("Arial", Font.PLAIN, 20);
        gString.setFont(font);
        gString.drawString(String.valueOf(score), (width - font.getSize() ) / 2, height - font.getSize() + 10);

    }
}
