package ru.thomaskohouse;

import javax.swing.*;
import java.awt.*;

public class RoundedRectangle extends JPanel {
    protected final int rectX;
    protected final int rectY;
    protected final int width;
    protected final int height;
    protected final int arcWidth;
    protected final int arcHeight;
    protected final Color color;


    RoundedRectangle(int rectX, int rectY, int width, int height, int arcWidth, int arcHeight, Color color) {
        this.rectX = rectX;
        this.rectY = rectY;
        this.width = width;
        this.height = height;
        this.color = color;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRoundRect(rectX, rectY, width, height, arcWidth, arcHeight);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width + 2 * rectX, height + 2 * rectY);
    }
}
