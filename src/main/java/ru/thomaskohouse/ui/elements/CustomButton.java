package ru.thomaskohouse.ui.elements;

import ru.thomaskohouse.enums.Sizes;

import javax.swing.JButton;
import java.awt.*;

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
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
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
