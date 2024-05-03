package ru.thomaskohouse;

import ru.thomaskohouse.enums.PaletteColors;
import ru.thomaskohouse.enums.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame{


    public static void createGUI() {
        RoundedRectangle backRectangle = new RoundedRectangle(0, 0, Sizes.BACKGROUND_RECTANGLE.getValue(),
                Sizes.BACKGROUND_RECTANGLE.getValue(), 30, 30,
                PaletteColors.DARK_GRAY.getColor());

        JLayeredPane jLayeredPane = new JLayeredPane();
        jLayeredPane.add(backRectangle, JLayeredPane.DEFAULT_LAYER);

        backRectangle.setOpaque(false);
        backRectangle.setBounds(Sizes.BACKGROUND_INDENT_X.getValue(), Sizes.BACKGROUND_INDENT_Y.getValue(),
                Sizes.BACKGROUND_RECTANGLE.getValue(), Sizes.WINDOW_HEIGHT.getValue());

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                RoundedRectangle rectangle = new RoundedRectangle(0, 0, 145, 145, 20, 20,
                        PaletteColors.LIGHT_GRAY.getColor());
                rectangle.setOpaque(false);
                rectangle.setBounds(49 + (Sizes.GAME_RECTANGLE.getValue() + 4) * j,
                        34 + (Sizes.GAME_RECTANGLE.getValue() + 4) * i,
                        Sizes.GAME_RECTANGLE.getValue(),
                        Sizes.GAME_RECTANGLE.getValue());
                jLayeredPane.add(rectangle, JLayeredPane.POPUP_LAYER);
            }
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Test frame");
        frame.getContentPane().setBackground(PaletteColors.LINEN.getColor());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.getContentPane().add(jLayeredPane);
        frame.setPreferredSize(
                new Dimension(
                        Sizes.WINDOW_WIDTH.getValue(),
                        Sizes.WINDOW_HEIGHT.getValue()
                )
        );
        frame.pack();
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> {
                        System.out.println("UP");
                        backRectangle.setValue(4);
                        backRectangle.repaint();}
                    case KeyEvent.VK_DOWN -> System.out.println("DOWN");
                    case KeyEvent.VK_LEFT -> System.out.println("LEFT");
                    case KeyEvent.VK_RIGHT -> System.out.println("RIGHT");
                    default -> System.out.println("default");
                }
            }
        });
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }

}