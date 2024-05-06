package ru.thomaskohouse;

import ru.thomaskohouse.enums.PaletteColors;
import ru.thomaskohouse.enums.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame{

    public static boolean isGameOver = false;
    public static void repaintMatrix(Game game, RoundedRectangleVault[][] rectangles){
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                rectangles[i][j].setValue(game.getValue(i, j));
                rectangles[i][j].repaint();
            }
        }
    }

    public static void printWin(JLayeredPane pane){
        printMessage(pane, "You win!", PaletteColors.WIN_MESSAGE.getColor());
    }

    public static void printLose(JLayeredPane pane){
        printMessage(pane, "You lose.", PaletteColors.LOSE_MESSAGE.getColor());
    }
    public static void printMessage(JLayeredPane pane, String message, Color color){
        RoundedRectangleMessage roundedRectangle = new RoundedRectangleMessage(0, 0, Sizes.BACKGROUND_RECTANGLE.getValue(),
                Sizes.BACKGROUND_RECTANGLE.getValue(), Sizes.BACKGROUND_RECT_ARC_WIDTH.getValue(),
                Sizes.BACKGROUND_RECT_ARC_HEIGHT.getValue(), Color.GRAY, color, message);
        roundedRectangle.setOpaque(false);
        roundedRectangle.setBounds(Sizes.BACKGROUND_INDENT_X.getValue(), Sizes.BACKGROUND_INDENT_Y.getValue(),
                Sizes.BACKGROUND_RECTANGLE.getValue(), Sizes.BACKGROUND_RECTANGLE.getValue());
        pane.add(roundedRectangle, JLayeredPane.POPUP_LAYER);
        roundedRectangle.repaint();
    }


    public static void repaintMatrix(Game game, RoundedRectangleVault[][] rectangles, boolean[] changedRows, int[] oldValues) {
        for (int i=0; i< rectangles.length; i++){
            if (changedRows[i]){
                //animate row move
                for (int j = 0; j < 4; j++){
                    if ((oldValues[j] == -1) && (game.getValue(i, j) != -1)){

                    }
                }
            }
        }
    }

        public static void runGame() {
        RoundedRectangle backRectangle = new RoundedRectangle(0, 0,
                Sizes.BACKGROUND_RECTANGLE.getValue(), Sizes.BACKGROUND_RECTANGLE.getValue(),
                Sizes.BACKGROUND_RECT_ARC_WIDTH.getValue(), Sizes.BACKGROUND_RECT_ARC_HEIGHT.getValue(),
                PaletteColors.DARK_GRAY.getColor());

        JLayeredPane jLayeredPane = new JLayeredPane();
        jLayeredPane.add(backRectangle, JLayeredPane.DEFAULT_LAYER);

        backRectangle.setOpaque(false);
        backRectangle.setBounds(Sizes.BACKGROUND_INDENT_X.getValue(), Sizes.BACKGROUND_INDENT_Y.getValue(),
                Sizes.BACKGROUND_RECTANGLE.getValue(), Sizes.WINDOW_HEIGHT.getValue());
        RoundedRectangleVault[][] rectangles = new RoundedRectangleVault[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                RoundedRectangleVault rectangle = new RoundedRectangleVault(0, 0, Sizes.GAME_RECTANGLE.getValue(),
                        Sizes.GAME_RECTANGLE.getValue(), Sizes.GAME_RECT_ARC_WIDTH.getValue(), Sizes.GAME_RECT_ARC_HEIGHT.getValue(),
                        PaletteColors.LIGHT_GRAY.getColor());
                rectangle.setOpaque(false);
                rectangle.setBounds(49 + (Sizes.GAME_RECTANGLE.getValue() + 4) * j,
                        34 + (Sizes.GAME_RECTANGLE.getValue() + 4) * i,
                        Sizes.GAME_RECTANGLE.getValue(),
                        Sizes.GAME_RECTANGLE.getValue());
                rectangles[i][j] = rectangle;
                jLayeredPane.add(rectangle, JLayeredPane.MODAL_LAYER);
            }
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("TFE");
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
      /*  Game game = new Game(new int[][]{
            {2, 4, 16, 32},
            {64, 128, 256, 512},
            {1024, 1024, -1, -1},
            {-1, -1, -1, -1}
        });*/
        Game game = new Game();
         /*  Game game = new Game(new int[][]{
                    {4, 2, 4, 2},
                    {2, 4, 2, 4},
                    {4, 2, 4, 2},
                    {2, 4, 2, 4}
            });*/
        repaintMatrix(game, rectangles);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!isGameOver) {
                    boolean[] changedRows, changedCols;
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> game.moveUp();
                        case KeyEvent.VK_DOWN -> game.moveDown();
                        case KeyEvent.VK_LEFT -> game.moveLeft();
                        case KeyEvent.VK_RIGHT -> game.moveRight();
                        default -> System.out.println("default");
                    }
                    repaintMatrix(game, rectangles);
                    switch (game.getGameState()) {
                        case WIN -> {
                            printWin(jLayeredPane);
                            isGameOver = true;
                        }
                        case LOSE -> {
                            printLose(jLayeredPane);
                            isGameOver = true;
                        }
                    }
                }
            }
        });
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runGame();

            }
        });
    }

}