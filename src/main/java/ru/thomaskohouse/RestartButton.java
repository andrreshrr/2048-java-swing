package ru.thomaskohouse;

import javax.swing.*;
import java.awt.*;

public class RestartButton extends JButton {
    private final Color color;
    private final Color fontColor;
    private final String label;
    RestartButton(String label, Color color, Color fontColor){
        this.label = label;
        this.color = color;
        this.fontColor = fontColor;

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(fontColor);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString(label, getWidth()/ 2 - 55, getHeight() / 2 + 7);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += size.height;
        return size;
    }


}
