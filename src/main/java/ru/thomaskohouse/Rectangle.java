package ru.thomaskohouse;

import javax.swing.*;
import java.awt.*;

public class Rectangle extends JPanel {
    private final int rectX;
    private final int rectY;
    private final int width;
    private final int height;
    private final Color color;
    private int value;

    Rectangle(int rectX, int rectY, int width, int height, Color color) {
        this.rectX = rectX;
        this.rectY = rectY;
        this.width = width;
        this.height = height;
        this.color = color;
        this.value = 0;
    }
    public void setValue(int value) {
        this.value  = value;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the rectangle here
        g.drawRect(rectX - 1, rectY - 1, width - 1, height - 1);
        g.setColor(color);
        g.fillRect(rectX, rectY, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(String.valueOf(value), rectX + width / 2, rectY + height / 2);

    }



    @Override
    public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(width + 2 * rectX, height + 2 * rectY);
    }
}
