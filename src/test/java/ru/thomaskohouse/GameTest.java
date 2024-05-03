package ru.thomaskohouse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    }

    @Test
    void moveUp() {
    }

    @Test
    void moveDown() {
    }
}