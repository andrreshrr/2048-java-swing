package ru.thomaskohouse;

import ru.thomaskohouse.enums.Direction;

import java.util.Random;

public class Game {
    private final int[][] matrix = new int[4][4];
    private boolean isVictory;
    private final Random random;

    /**
     *
     * @return  с вероятностью 0.1 - 2, с вероятностью 0.9 - 4
     */
    private int getTwoOrFour(){
        if (random.nextInt(10) != 9){
            return  2;
        } else {
            return  4;
        }
    }

    /**
     * Обновление случйной ячейки матрицы
     */
    private void updateRandomCell(){
        int randColumn, randRow;
        do {
            randColumn = random.nextInt(4);
            randRow = random.nextInt(4);
        } while (matrix[randRow][randColumn] != -1);
        matrix[randRow][randColumn] = getTwoOrFour();
    }

    public Game() {
        isVictory = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = -1;
            }
        }
        random = new Random();
        updateRandomCell();
    }

    Game(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        isVictory = false;
        random = new Random();
    }

    public void moveLeft(){
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if ((matrix[i][j - 1] == -1) && (matrix[i][j] > 0)) {
                    int temp = matrix[i][j - 1];
                    matrix[i][j - 1] = matrix[i][j];
                    matrix[i][j] = temp;
                    if (j > 1) j -= 2;
                } else if ((matrix[i][j] == matrix[i][j-1]) && (matrix[i][j] > 0)) {
                    matrix[i][j - 1] = matrix[i][j - 1] * 2;
                    matrix[i][j] = -1;
                }
            }
        }
        updateRandomCell();
    }

    public void moveRight(){
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j >= 0; j--) {
                if ((matrix[i][j + 1] == -1) && (matrix[i][j] > 0)) {
                    int temp = matrix[i][j + 1];
                    matrix[i][j + 1] = matrix[i][j];
                    matrix[i][j] = temp;
                    if (j < 2) j+=2;
                } else if ((matrix[i][j] == matrix[i][j+1]) && (matrix[i][j] > 0)) {
                    matrix[i][j + 1] = matrix[i][j + 1] * 2;
                    matrix[i][j] = -1;
                }
            }
        }
        updateRandomCell();
    }
    public void moveDown(){
        for (int j = 0; j < 4; j++){
            for (int i = 2; i >= 0; i--){
                if ((matrix[i + 1][j] == -1) && (matrix[i][j] > 0)) {
                    int temp = matrix[i + 1][j];
                    matrix[i + 1][j] = matrix[i][j];
                    matrix[i][j] = temp;
                    if (i < 2) i += 2;
                } else if ((matrix[i][j] == matrix[i + 1][j]) && (matrix[i][j] > 0)) {
                    matrix[i + 1][j] = matrix[i][j] * 2;
                    matrix[i][j] = -1;
                }
            }
        }
        updateRandomCell();
    }

    public void moveUp(){
        for (int j = 0; j < 4; j++){
            for (int i = 1; i < 4; i++){
                if ((matrix[i - 1][j] == -1) && (matrix[i][j] > 0)) {
                    int temp = matrix[i - 1][j];
                    matrix[i - 1][j] = matrix[i][j];
                    matrix[i][j] = temp;
                    if (i > 1) i -= 2;
                } else if ((matrix[i][j] == matrix[i - 1][j]) && (matrix[i][j] > 0)) {
                    matrix[i - 1][j] = matrix[i][j] * 2;
                    matrix[i][j] = -1;
                }
            }
        }
        updateRandomCell();
    }

    public void printMatrix(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getValue(int row, int col) {
        return matrix[row][col];
    }
}
