package ru.thomaskohouse;

import ru.thomaskohouse.enums.PaletteColors;

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
        this.value = -1;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.isValueable = true;

    }

    RoundedRectangle(int rectX, int rectY, int width, int height, int arcWidth, int arcHeight, Color color, int value) {
        this(rectX, rectY, width, height, arcWidth, arcHeight, color);
        this.isValueable = true;
        this.value = value;
    }

    public void setValue(int value) {
        this.value  = value;
    }

    private Color defineColor(){
        Color result = color;
        switch (value){
            case 2 -> result = PaletteColors.ISABELLINE.getColor();
            case 4 -> result = PaletteColors.MISTY_ROSE.getColor();
            case 8 -> result = PaletteColors.MIMY_PINK.getColor();
            case 16 -> result = PaletteColors.PERIWINKLE.getColor();
            case 32 -> result = PaletteColors.LIGHT_BLUE.getColor();
            case 64 -> result = PaletteColors.MINT_CREAM.getColor();
            case 128 -> result = PaletteColors.CARNATION_PINK.getColor();
            case 256 -> result = PaletteColors.RAZZLE_ROSE.getColor();
            case 512 -> result = PaletteColors.FOLLY.getColor();
            case 1024 -> result = PaletteColors.TOMATO.getColor();
            case 2048 -> result = PaletteColors.TANGELLO.getColor();
        }
        return result;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the rectangle here
       // g.drawRoundRect(rectX , rectY, width, height, 30, 30);
        g.setColor(defineColor());
        g.fillRoundRect(rectX, rectY, width, height, arcWidth, arcHeight);
        if (value != -1) {
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            if (value < 10)
                g.drawString(String.valueOf(value), getWidth() / 2 - 10, getHeight() / 2 + 10 );
            else if (value < 100)
                g.drawString(String.valueOf(value), getWidth() / 2 - 20, getHeight() / 2 + 10 );
            else if (value < 1000){
                g.drawString(String.valueOf(value), getWidth() / 2 - 30, getHeight() / 2 + 10 );
            } else {
                g.drawString(String.valueOf(value), getWidth() / 2 - 45, getHeight() / 2 + 10 );
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(width + 2 * rectX, height + 2 * rectY);
    }
}
