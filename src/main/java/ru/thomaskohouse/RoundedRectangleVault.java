package ru.thomaskohouse;

import ru.thomaskohouse.enums.PaletteColors;

import java.awt.*;

public class RoundedRectangleVault extends RoundedRectangle {
    private int value;

    RoundedRectangleVault(int rectX, int rectY, int width, int height, int arcWidth, int arcHeight, Color color) {
        super(rectX, rectY, width, height, arcWidth, arcHeight, color);
        this.value = 0;
    }

    RoundedRectangleVault(int rectX, int rectY, int width, int height, int arcWidth, int arcHeight, Color color, int value) {
            super(rectX, rectY, width, height, arcWidth, arcHeight, color);
            this.value = value;
    }

    public void setValue(int value) {
        this.value  = value;
    }

    public int getValue(){
        return value;
    }

    private Color defineColor(){
        Color result = color;
        switch (value){
            case 2 -> result = PaletteColors.RED1.getColor();
            case 4 -> result = PaletteColors.RED2.getColor();
            case 8 -> result = PaletteColors.RED3.getColor();
            case 16 -> result = PaletteColors.RED4.getColor();
            case 32 -> result = PaletteColors.RED5.getColor();
            case 64 -> result = PaletteColors.RED6.getColor();
            case 128 -> result = PaletteColors.RED7.getColor();
            case 256 -> result = PaletteColors.RED8.getColor();
            case 512 -> result = PaletteColors.RED9.getColor();
            case 1024 -> result = PaletteColors.RED10.getColor();
            case 2048 -> result = PaletteColors.RED11.getColor();
        }
        return result;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (value != -1) {
            super.paintComponent(g);
            g.setColor(defineColor());
            g.fillRoundRect(rectX, rectY, width, height, arcWidth, arcHeight);
            g.setColor(Color.WHITE);
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

    public void moveTo(int xStep, int yStep, int xLeftLimit, int xRightLimit, int yUpLimit, int yDownLimit){
        Rectangle currentBounds = getBounds();
        if (currentBounds.x + xStep > xRightLimit){
            currentBounds.x = xRightLimit;
        } else if (currentBounds.x + xStep < xLeftLimit) {
            currentBounds.x = xLeftLimit;
        } else {
            currentBounds.x += xStep;
        }
        if (currentBounds.y + yStep > yDownLimit){
            currentBounds.y = yDownLimit;
        } else if (currentBounds.y + yStep < yUpLimit) {
            currentBounds.y = yUpLimit;
        } else {
            currentBounds.y += yStep;
        }
        setBounds(currentBounds);
        repaint();
    }

}
