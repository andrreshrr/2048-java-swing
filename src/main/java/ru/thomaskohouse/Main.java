package ru.thomaskohouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame{
    public static void createGUI() {
        Rectangle rectangle = new Rectangle(10, 20, 150, 200, Color.ORANGE);
        Rectangle smallRectangle = new Rectangle(0, 0, 16, 20, Color.RED);
        JLayeredPane jLayeredPane = new JLayeredPane();

        jLayeredPane.add(rectangle, JLayeredPane.DEFAULT_LAYER);
        jLayeredPane.add(smallRectangle, JLayeredPane.POPUP_LAYER);

        rectangle.setBounds(0, 0, 200, 200);
        smallRectangle.setBounds(50,50, 10, 10);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Test label");
        frame.getContentPane().add(jLayeredPane);

        frame.setPreferredSize(new Dimension(500, 500));

        frame.pack();
        frame.addKeyListener(new KeyAdapter() {
            // Key Pressed method
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> {
                        System.out.println("UP");
                        rectangle.setValue(4);
                        rectangle.repaint();}
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