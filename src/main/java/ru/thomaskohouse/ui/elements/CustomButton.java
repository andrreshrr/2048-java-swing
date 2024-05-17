package ru.thomaskohouse.ui.elements;

import ru.thomaskohouse.enums.Sizes;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Dimension;

public class CustomButton extends JButton {
    private final Color backgroundColor;
    private final Color fontColor;
    private final String text;

    public CustomButton(String text, Color backgroundColor, Color fontColor){
        this.text = text;
        this.backgroundColor = backgroundColor;
        this.fontColor = fontColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(fontColor);
        g.setFont(new Font("Arial", Font.PLAIN, Sizes.CUSTOM_BUTTON_FONT.getValue()));
        g.drawString(text, getWidth()/ 2 - 55, getHeight() / 2 + 7);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += size.height;
        return size;
    }

}
