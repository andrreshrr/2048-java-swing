package ru.thomaskohouse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.thomaskohouse.gameengine.Game;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    @DisplayName("Move left")
    void moveLeft() {
        Game game = new Game(new int[][]{
                {-1, -1, -1, 2},
                {-1, -1, 2, -1},
                {-1, 2, -1, -1},
                {2, -1, -1, -1}});
        game.moveLeft();

        assertEquals( 2, game.getValue(0, 0));
        assertEquals(2, game.getValue(1, 0));
        assertEquals( 2, game.getValue(2, 0));
        assertEquals(2, game.getValue(3, 0));

        game = new Game(new int[][]{
                {2, -1, -1, 2},
                {2, 2, 2, -1},
                {-1, 2, 2, -1},
                {2, 2, 2, 2}});
        game.moveLeft();

        assertEquals( 4, game.getValue(0, 0));
        assertEquals(4, game.getValue(1, 0));
        assertEquals(2, game.getValue(1, 1));
        assertEquals( 4, game.getValue(2, 0));
        assertEquals(4, game.getValue(3, 0));
        assertEquals(4, game.getValue(3, 1));

        game = new Game(new int[][]{
                {2, 2, 4, -1},
                {2, 2, 4, 4},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1}
        });
        game.moveLeft();

        assertEquals( 4, game.getValue(0, 0));
        assertEquals(4, game.getValue(0, 1));
        assertEquals( 4, game.getValue(1, 0));
        assertEquals( 8, game.getValue(1, 1));

        game = new Game(new int[][]{
                {2, 4, -1, -1},
                {2, 8, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1}
        });
        game.moveLeft();

        int[][] expectedMatrix = new int[][]{
                {2, 4, -1, -1},
                {2, 8, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1}
        };
        for (int i = 0; i < expectedMatrix.length; i++) {
            for (int j = 0; j < expectedMatrix[i].length; j++) {
                assertEquals(expectedMatrix[i][j], game.getValue(i, j));
            }
        }
    }

    @DisplayName("Move right")
    @Test
    void moveRight() {
        Game game = new Game(new int[][]{
                {-1, -1, -1, 2},
                {-1, -1, 2, -1},
                {-1, 2, -1, -1},
                {2, -1, -1, -1}});
        game.moveRight();

        assertEquals( 2, game.getValue(0, 3));
        assertEquals(2, game.getValue(1, 3));
        assertEquals( 2, game.getValue(2, 3));
        assertEquals(2, game.getValue(3, 3));

        game = new Game(new int[][]{
                {2, -1, -1, 2},
                {2, 2, 2, -1},
                {-1, 2, 2, -1},
                {2, 2, 2, 2}});
        game.moveRight();

        assertEquals( 4, game.getValue(0, 3));
        assertEquals(4, game.getValue(1, 3));
        assertEquals(2, game.getValue(1, 2));
        assertEquals( 4, game.getValue(2, 3));
        assertEquals(4, game.getValue(3, 3));
        assertEquals(4, game.getValue(3, 2));

        game = new Game(new int[][]{
                {2, 2, 4, -1},
                {2, 2, 4, 4},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1}
        });
        game.moveRight();

        assertEquals( 4, game.getValue(0, 3));
        assertEquals(4, game.getValue(0, 2));
        assertEquals( 4, game.getValue(1, 2));
        assertEquals( 8, game.getValue(1, 3));

        game = new Game(new int[][]{
                {-1, -1, 2, 4},
                {-1, -1, 2, 8},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1}
        });
        game.moveRight();

        int[][] expectedMatrix = new int[][]{
                {-1, -1, 2, 4},
                {-1, -1, 2, 8},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1}
        };
        for (int i = 0; i < expectedMatrix.length; i++) {
            for (int j = 0; j < expectedMatrix[i].length; j++) {
                assertEquals(expectedMatrix[i][j], game.getValue(i, j));
            }
        }
    }

    @Test
    void moveUp() {
        Game game = new Game(new int[][]{
                {-1, -1, -1, 2},
                {-1, -1, 2, -1},
                {-1, 2, -1, -1},
                {2, -1, -1, -1}});
        game.moveUp();

        assertEquals( 2, game.getValue(0, 0));
        assertEquals(2, game.getValue(0, 1));
        assertEquals( 2, game.getValue(0, 2));
        assertEquals(2, game.getValue(0, 3));

        game = new Game(new int[][]{
                {2, -1, -1, 2},
                {2, 2, 2, -1},
                {-1, 2, 2, -1},
                {2, 2, 2, 2}});
        game.moveUp();

        assertEquals( 4, game.getValue(0, 0));
        assertEquals(2, game.getValue(1, 0));
        assertEquals(4, game.getValue(0, 1));
        assertEquals(2, game.getValue(1, 1));
        assertEquals(4, game.getValue(0, 2));
        assertEquals(2, game.getValue(1, 2));
        assertEquals( 4, game.getValue(0, 3));

        game = new Game(new int[][]{
                {2, -1, -1, -1},
                {2, -1, -1, -1},
                {4, -1, -1, -1},
                {-1, -1, -1, -1}
        });
        game.moveUp();

        assertEquals( 4, game.getValue(0, 0));
        assertEquals(4, game.getValue(1, 0));

        game = new Game(new int[][]{
                {2, 4, -1, -1},
                {4, 8, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1}
        });
        game.moveUp();

        int[][] expectedMatrix = new int[][]{
                {2, 4, -1, -1},
                {4, 8, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1}
        };
        for (int i = 0; i < expectedMatrix.length; i++) {
            for (int j = 0; j < expectedMatrix[i].length; j++) {
                assertEquals(expectedMatrix[i][j], game.getValue(i, j));
            }
        }
    }

    @Test
    void moveDown() {
        Game game = new Game(new int[][]{
                {-1, -1, -1, 2},
                {-1, -1, 2, -1},
                {-1, 2, -1, -1},
                {2, -1, -1, -1}});
        game.moveDown();

        assertEquals( 2, game.getValue(3, 0));
        assertEquals(2, game.getValue(3, 1));
        assertEquals( 2, game.getValue(3, 2));
        assertEquals(2, game.getValue(3, 3));

        game = new Game(new int[][]{
                {2, -1, -1, 2},
                {2, 2, 2, -1},
                {-1, 2, 2, -1},
                {2, 2, 2, 2}});
        game.moveDown();

        assertEquals( 4, game.getValue(3, 0));
        assertEquals(2, game.getValue(2, 0));
        assertEquals(4, game.getValue(3, 1));
        assertEquals(2, game.getValue(2, 1));
        assertEquals(4, game.getValue(3, 2));
        assertEquals(2, game.getValue(2, 2));
        assertEquals( 4, game.getValue(3, 3));

        game = new Game(new int[][]{
                {2, -1, -1, -1},
                {2, -1, -1, -1},
                {4, -1, -1, -1},
                {-1, -1, -1, -1}
        });
        game.moveDown();

        assertEquals( 4, game.getValue(2, 0));
        assertEquals(4, game.getValue(3, 0));

        game = new Game(new int[][]{
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {2, 4, -1, -1},
                {4, 8, -1, -1}
        });
        game.moveDown();

        int[][] expectedMatrix = new int[][]{
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {2, 4, -1, -1},
                {4, 8, -1, -1}
        };
        for (int i = 0; i < expectedMatrix.length; i++) {
            for (int j = 0; j < expectedMatrix[i].length; j++) {
                assertEquals(expectedMatrix[i][j], game.getValue(i, j));
            }
        }
    }
}