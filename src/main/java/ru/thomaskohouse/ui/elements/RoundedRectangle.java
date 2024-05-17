package ru.thomaskohouse.ui.elements;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class RoundedRectangle extends JPanel {
    protected int rectX;
    protected int rectY;
    protected final int width;
    protected final int height;
    protected final int arcWidth;
    protected final int arcHeight;
    protected final Color color;

    public RoundedRectangle(int rectX, int rectY, int width, int height, int arcWidth, int arcHeight, Color color) {
        this.rectX = rectX;
        this.rectY = rectY;
        this.width = width;
        this.height = height;
        this.color = color;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRoundRect(rectX, rectY, width, height, arcWidth, arcHeight);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width + 2 * rectX, height + 2 * rectY);
    }
}
