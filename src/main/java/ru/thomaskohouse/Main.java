package ru.thomaskohouse;

import ru.thomaskohouse.enums.Direction;
import ru.thomaskohouse.enums.PaletteColors;
import ru.thomaskohouse.enums.Sizes;
import ru.thomaskohouse.gameengine.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame{

    public static boolean isGameOver = false;

    private static RoundedRectangleVault[][] rectangles;
    private static ScoreRectangle scoreRectangle;
    private static JLayeredPane jLayeredPane;
    private static Game game;
    private static RoundedRectangleMessage messageRectangle;

    public static void repaintField(){
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                rectangles[i][j].setValue(game.getValue(i, j));
                rectangles[i][j].setBounds(Sizes.BACKGROUND_INDENT_X.getValue() + 4 + (Sizes.GAME_RECTANGLE.getValue() + 4) * j,
                        Sizes.BACKGROUND_INDENT_Y.getValue() + 4 + (Sizes.GAME_RECTANGLE.getValue() + 4) * i,
                        Sizes.GAME_RECTANGLE.getValue(),
                        Sizes.GAME_RECTANGLE.getValue());
                rectangles[i][j].repaint();
            }
        }
        scoreRectangle.setScore(game.getScore());
        scoreRectangle.repaint();
    }

    public static int getDistance(int i, int j, Direction direction){
        int distance = 0;
        switch (direction){
            case UP -> distance = rectangles[i][j].getBounds().y - getNearestUp(i, j);
            case LEFT -> distance = rectangles[i][j].getBounds().x - getNearestLeft(i, j);
            case RIGHT -> distance = getNearestRight(i, j) - rectangles[i][j].getBounds().x;
            case DOWN -> distance = getNearestDown(i, j) - rectangles[i][j].getBounds().y;
        }
        return distance;
    }

    private static int getNearestUp(int i, int j){
        if (i == 0){
            return Sizes.BACKGROUND_INDENT_Y.getValue() + 4;
        } else if (rectangles[i-1][j].getValue() != -1) {
            return rectangles[i-1][j].getBounds().y + 4 + Sizes.BACKGROUND_INDENT_Y.getValue();
        } else {
            return getNearestUp(i - 1, j);
        }
    }

    private static int getNearestLeft(int i, int j){
        if (j == 0){
            return Sizes.BACKGROUND_INDENT_X.getValue() + 4;
        } else if (rectangles[i][j-1].getValue() != -1){
            return rectangles[i][j-1].getBounds().x + 4 + Sizes.GAME_RECTANGLE.getValue();
        }
        else {
            return getNearestLeft(i, j-1);
        }
    }

    private static int getNearestRight(int i, int j){
        if (j == 3) {
            return Sizes.BACKGROUND_INDENT_X.getValue() + Sizes.BACKGROUND_RECTANGLE.getValue() - Sizes.GAME_RECTANGLE.getValue();
        } else if (rectangles[i][j+1].getValue() != -1){
            return rectangles[i][j+1].getBounds().x - Sizes.GAME_RECTANGLE.getValue();
        } else{
            return getNearestRight(i, j+1);
        }

    }

    private static int getNearestDown(int i, int j){
        if (i == 3) {
            return Sizes.BACKGROUND_INDENT_Y.getValue() + Sizes.BACKGROUND_RECTANGLE.getValue() - Sizes.GAME_RECTANGLE.getValue();
        } else if (rectangles[i+1][j].getValue() != -1){
            return rectangles[i+1][j].getBounds().y - Sizes.GAME_RECTANGLE.getValue();
        } else{
            return getNearestDown(i+1, j);
        }

    }

    public static void moveField(Direction direction) throws InterruptedException {
        int frames = 50;

        int mills = 1000;
        int framePause = mills / frames;
        int xStep = 0, yStep = 0;
        int distance = 0;
        switch (direction) {
            case UP -> yStep = -1;
            case RIGHT -> xStep = 1;
            case LEFT -> xStep = -1;
            case DOWN -> yStep = 1;
        }

        for (int i = 0; i < rectangles.length; i++){
            for (int j = 0; j < rectangles[i].length; j++){
                if (rectangles[i][j].getValue() != -1) {
                    distance = getDistance(i, j, direction);
                    int stepCount = distance / frames;
                    if (stepCount == 0) continue;
                    int step = distance / stepCount;
                    Timer timer = new Timer(framePause, new ActionListener() {
                        private int i, j, yStep, xStep, counter;
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (counter > stepCount){
                                ((Timer)e.getSource()).stop();
                            } else if (counter == stepCount){
                                repaintField();
                                counter++;
                            } else {
                                rectangles[i][j].moveTo(step * xStep, step * yStep,
                                        Sizes.BACKGROUND_INDENT_X.getValue() + 4,
                                        Sizes.BACKGROUND_INDENT_X.getValue() + Sizes.BACKGROUND_RECTANGLE.getValue() - Sizes.GAME_RECTANGLE.getValue() - 4,
                                        Sizes.BACKGROUND_INDENT_Y.getValue() + 4,
                                        Sizes.BACKGROUND_INDENT_Y.getValue() + Sizes.BACKGROUND_RECTANGLE.getValue() - Sizes.GAME_RECTANGLE.getValue() - 4);
                                counter++;
                            }
                        }
                        private ActionListener init (int i, int j, int xStep, int yStep){
                            this.i = i;
                            this.j = j;
                            this.xStep = xStep;
                            this.yStep = yStep;
                            counter = 0;
                            return this;
                        }
                    }.init(i, j, xStep, yStep));
                    timer.start();

                }
           }
        }
    }

    public static void moveGame(Direction direction){
        if (!isGameOver){
            switch (direction){
                case UP -> game.moveUp();
                case DOWN -> game.moveDown();
                case LEFT -> game.moveLeft();
                case RIGHT -> game.moveRight();
            }
            try {
                moveField(direction);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            game.print();
            switch (game.getGameState()) {
                case WIN -> {
                    printWin();
                    isGameOver = true;
                }
                case LOSE -> {
                    printLose();
                    isGameOver = true;
                }
            }
        }
    }

    public static void printWin(){
        printMessage( "You win!", PaletteColors.WIN_MESSAGE.getColor());
    }

    public static void printLose(){
        printMessage( "You lose.", PaletteColors.LOSE_MESSAGE.getColor());
    }

    public static void printMessage(String message, Color color){
        messageRectangle = new RoundedRectangleMessage(0, 0, Sizes.BACKGROUND_RECTANGLE.getValue(),
                Sizes.BACKGROUND_RECTANGLE.getValue(), Sizes.BACKGROUND_RECT_ARC_WIDTH.getValue(),
                Sizes.BACKGROUND_RECT_ARC_HEIGHT.getValue(), Color.GRAY, color, message);
        messageRectangle.setOpaque(false);
        messageRectangle.setBounds(Sizes.BACKGROUND_INDENT_X.getValue(), Sizes.BACKGROUND_INDENT_Y.getValue(),
                Sizes.BACKGROUND_RECTANGLE.getValue(), Sizes.BACKGROUND_RECTANGLE.getValue());
        jLayeredPane.add(messageRectangle, JLayeredPane.POPUP_LAYER);

        messageRectangle.repaint();
    }

    public static void setKeyBindings(){
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");

        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "DOWN");
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "UP");
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "LEFT");
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "RIGHT");

        jLayeredPane.getActionMap().put("DOWN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveGame(Direction.DOWN);
            }
        });
        jLayeredPane.getActionMap().put("UP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveGame(Direction.UP);
            }
        });
        jLayeredPane.getActionMap().put("LEFT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveGame(Direction.LEFT);
            }
        });
        jLayeredPane.getActionMap().put("RIGHT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveGame(Direction.RIGHT);
            }
        });

    }

    public static void restartGame(){
        isGameOver = false;
        game.restart();
        repaintField();
        jLayeredPane.remove(messageRectangle);
    }

    public static void runGame() {
        scoreRectangle = new ScoreRectangle(0, 0,
                Sizes.SCORE_RECTANGLE_WIDTH.getValue(), Sizes.SCORE_RECTANGLE_HEIGHT.getValue(),
                Sizes.SCORE_RECT_ARC_WIDTH.getValue(), Sizes.SCORE_RECT_ARC_HEIGHT.getValue(),
                PaletteColors.DARK_GRAY.getColor(),  PaletteColors.LIGHT_GRAY.getColor(), 0);

        RoundedRectangle backRectangle = new RoundedRectangle(0, 0,
                Sizes.BACKGROUND_RECTANGLE.getValue(), Sizes.BACKGROUND_RECTANGLE.getValue(),
                Sizes.BACKGROUND_RECT_ARC_WIDTH.getValue(), Sizes.BACKGROUND_RECT_ARC_HEIGHT.getValue(),
                PaletteColors.DARK_GRAY.getColor());

        RestartButton restartButton = new RestartButton("RESTART", PaletteColors.DARK_GRAY.getColor(), PaletteColors.LIGHT_GRAY.getColor());

        jLayeredPane = new JLayeredPane();
        jLayeredPane.add(backRectangle, JLayeredPane.DEFAULT_LAYER);
        jLayeredPane.add(scoreRectangle, JLayeredPane.DEFAULT_LAYER);
        jLayeredPane.add(restartButton, JLayeredPane.DEFAULT_LAYER);

        restartButton.setBounds(Sizes.BACKGROUND_INDENT_X.getValue() + Sizes.BACKGROUND_RECTANGLE.getValue() - Sizes.SCORE_RECTANGLE_WIDTH.getValue(),
                Sizes.SCORE_RECT_INDENT_Y.getValue(), Sizes.SCORE_RECTANGLE_WIDTH.getValue(),  Sizes.SCORE_RECTANGLE_HEIGHT.getValue());

        scoreRectangle.setOpaque(false);
        scoreRectangle.setBounds(Sizes.BACKGROUND_INDENT_X.getValue(), Sizes.SCORE_RECT_INDENT_Y.getValue(),
                Sizes.SCORE_RECTANGLE_WIDTH.getValue(), Sizes.SCORE_RECTANGLE_HEIGHT.getValue());

        backRectangle.setOpaque(false);
        backRectangle.setBounds(Sizes.BACKGROUND_INDENT_X.getValue(), Sizes.BACKGROUND_INDENT_Y.getValue(),
                Sizes.BACKGROUND_RECTANGLE.getValue(), Sizes.WINDOW_HEIGHT.getValue());
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                RoundedRectangle rectangle = new RoundedRectangle(0, 0, Sizes.GAME_RECTANGLE.getValue(),
                        Sizes.GAME_RECTANGLE.getValue(), Sizes.GAME_RECT_ARC_WIDTH.getValue(), Sizes.GAME_RECT_ARC_HEIGHT.getValue(),
                        PaletteColors.LIGHT_GRAY.getColor());
                rectangle.setOpaque(false);
                rectangle.setBounds(Sizes.BACKGROUND_INDENT_X.getValue() + 4 + (Sizes.GAME_RECTANGLE.getValue() + 4) * j,
                        Sizes.BACKGROUND_INDENT_Y.getValue() + 4 + (Sizes.GAME_RECTANGLE.getValue() + 4) * i,
                        Sizes.GAME_RECTANGLE.getValue(),
                        Sizes.GAME_RECTANGLE.getValue());
                jLayeredPane.add(rectangle, JLayeredPane.PALETTE_LAYER);
            }
        }

        rectangles = new RoundedRectangleVault[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                RoundedRectangleVault rectangle = new RoundedRectangleVault(0, 0, Sizes.GAME_RECTANGLE.getValue(),
                        Sizes.GAME_RECTANGLE.getValue(), Sizes.GAME_RECT_ARC_WIDTH.getValue(), Sizes.GAME_RECT_ARC_HEIGHT.getValue(),
                        PaletteColors.LIGHT_GRAY.getColor());
                rectangle.setOpaque(false);
                rectangle.setBounds(Sizes.BACKGROUND_INDENT_X.getValue() + 4 + (Sizes.GAME_RECTANGLE.getValue() + 4) * j,
                        Sizes.BACKGROUND_INDENT_Y.getValue() + 4 + (Sizes.GAME_RECTANGLE.getValue() + 4) * i,
                        Sizes.GAME_RECTANGLE.getValue(),
                        Sizes.GAME_RECTANGLE.getValue());
                rectangles[i][j] = rectangle;
                jLayeredPane.add(rectangle, JLayeredPane.MODAL_LAYER);
            }
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("TFFE");
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
        game = new Game();
          /* game = new Game(new int[][]{
                    {4, 2, -1, -1},
                    {2, -1, -1, 4},
                    {4, -1, -1, 2},
                    {2, -1, 2, -1}
            });*/
        repaintField();
        setKeyBindings();

        restartButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restartGame();
                frame.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Main::runGame);
    }

}