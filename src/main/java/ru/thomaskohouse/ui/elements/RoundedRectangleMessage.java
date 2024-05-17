package ru.thomaskohouse.ui.elements;

import ru.thomaskohouse.enums.Sizes;

import java.awt.*;

public class RoundedRectangleMessage extends RoundedRectangle {
    private final Color fontColor;
    private final String text;

    public RoundedRectangleMessage(int rectX, int rectY, int width, int height, int arcWidth, int arcHeight, Color color, Color fontColor, String text) {
        super(rectX, rectY, width, height, arcWidth, arcHeight, color);
        this.fontColor = fontColor;
        this.text = text;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(color);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
        g.fillRoundRect(rectX, rectY, width, height, arcWidth, arcHeight);

        Graphics gString = g.create();
        gString.setColor(fontColor);
        gString.setFont(new Font("Arial", Font.PLAIN, Sizes.RECTANGLE_MESSAGE_FONT.getValue()));
        gString.drawString(text, getWidth() / 2 - 70, getHeight() / 3);
    }
}
