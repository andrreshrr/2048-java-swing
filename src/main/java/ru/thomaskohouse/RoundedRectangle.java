package ru.thomaskohouse;

import javax.swing.*;
import java.awt.*;

public class RoundedRectangle extends JPanel {
    private final int rectX;
    private final int rectY;
    private final int width;
    private final int height;
    private final int arcWidth;
    private final int arcHeight;
    private final Color color;
    private int value;
    private boolean isValueable;

    RoundedRectangle(int rectX, int rectY, int width, int height, int arcWidth, int arcHeight, Color color) {
        this.rectX = rectX;
        this.rectY = rectY;
        this.width = width;
        this.height = height;
        this.color = color;
        this.value = 0;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.isValueable = false;
    }

    RoundedRectangle(int rectX, int rectY, int width, int height, int arcWidth, int arcHeight, Color color, int value) {
        this(rectX, rectY, width, height, arcWidth, arcHeight, color);
        this.isValueable = true;
        this.value = value;
    }

    public void setValue(int value) {
        this.value  = value;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the rectangle here
       // g.drawRoundRect(rectX , rectY, width, height, 30, 30);
        g.setColor(color);
        g.fillRoundRect(rectX, rectY, width, height, arcWidth, arcHeight);

        if (isValueable) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString(String.valueOf(value), rectX + width / 2, rectY + height / 2);
        }
    }



    @Override
    public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(width + 2 * rectX, height + 2 * rectY);
    }
}
