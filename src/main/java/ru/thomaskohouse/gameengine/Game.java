package ru.thomaskohouse.gameengine;

import ru.thomaskohouse.gameengine.enums.GameStates;

import java.util.Random;

public class Game {
    private final int[][] matrix = new int[4][4];
    private int score;
    private final Random random;

    public Game() {
        random = new Random();
        initGame();
    }

    public Game(int[][] matrix){
        random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, matrix[i].length);
        }
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public int getValue(int row, int col) {
        return matrix[row][col];
    }

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

    private boolean containsEmptyCell() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == -1){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Обновление случйной ячейки матрицы
     */
    private void updateRandomCell(){
        int randColumn, randRow;
        if (containsEmptyCell()) {
            do {
                randColumn = random.nextInt(4);
                randRow = random.nextInt(4);
            } while (matrix[randRow][randColumn] != -1);
            matrix[randRow][randColumn] = getTwoOrFour();
        }
    }

    private void initGame(){
        score = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = -1;
            }
        }
        updateRandomCell();
        updateRandomCell();
    }


    public void moveLeft(){
        boolean validMove = false;
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 4; i++) {       //сжимаем
                for (int j = 1; j < 4; j++) {
                    if ((k!=1) && (matrix[i][j - 1] == -1) && (matrix[i][j] > 0)) {
                        int temp = matrix[i][j - 1];
                        matrix[i][j - 1] = matrix[i][j];
                        matrix[i][j] = temp;
                        validMove = true;
                        if (j > 1) j -= 2;
                    } else if ((k == 1) && (matrix[i][j] == matrix[i][j - 1]) && (matrix[i][j] > 0)){
                        matrix[i][j - 1] = matrix[i][j - 1] * 2;
                        score += matrix[i][j - 1];
                        matrix[i][j] = -1;
                        validMove = true;
                    }
                }
            }
        }
        if (validMove){
            updateRandomCell();
        }
    }

    public void moveRight(){
        boolean validMove = false;
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 2; j >= 0; j--) {
                    if ((k != 1) && (matrix[i][j + 1] == -1) && (matrix[i][j] > 0)) {
                        int temp = matrix[i][j + 1];
                        matrix[i][j + 1] = matrix[i][j];
                        matrix[i][j] = temp;
                        validMove = true;
                        if (j < 2) j+=2;
                    } else if ((k == 1) && (matrix[i][j] == matrix[i][j+1]) && (matrix[i][j] > 0)) {
                        matrix[i][j + 1] = matrix[i][j + 1] * 2;
                        score += matrix[i][j + 1];
                        matrix[i][j] = -1;
                        validMove = true;
                    }
                }
            }
        }

        if (validMove){
            updateRandomCell();
        }
    }
    public void moveDown(){
        boolean validMove = false;
        for (int k = 0; k < 3; k ++) {
            for (int j = 0; j < 4; j++) {
                for (int i = 2; i >= 0; i--) {
                    if ((k != 1) && (matrix[i + 1][j] == -1) && (matrix[i][j] > 0)) {
                        int temp = matrix[i + 1][j];
                        matrix[i + 1][j] = matrix[i][j];
                        matrix[i][j] = temp;
                        validMove = true;
                        if (i < 2) i += 2;
                    } else if ((k == 1)&&(matrix[i][j] == matrix[i + 1][j]) && (matrix[i][j] > 0)) {
                        matrix[i + 1][j] = matrix[i][j] * 2;
                        score += matrix[i + 1][j];
                        matrix[i][j] = -1;
                        validMove = true;
                    }
                }
            }
        }

        if (validMove){
            updateRandomCell();
        }
    }

    public void moveUp(){
        boolean validMove = false;
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 4; j++) {
                for (int i = 1; i < 4; i++) {
                    if ((k != 1) && (matrix[i - 1][j] == -1) && (matrix[i][j] > 0)) {
                        int temp = matrix[i - 1][j];
                        matrix[i - 1][j] = matrix[i][j];
                        matrix[i][j] = temp;
                        validMove = true;
                        if (i > 1) i -= 2;
                    } else if ((k == 1) && (matrix[i][j] == matrix[i - 1][j]) && (matrix[i][j] > 0)) {
                        matrix[i - 1][j] = matrix[i][j] * 2;
                        score += matrix[i - 1][j];
                        matrix[i][j] = -1;
                        validMove = true;
                    }
                }
            }
        }
        if (validMove){
            updateRandomCell();
        }
    }

    public GameStates getGameState(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (matrix[i][j] == 2048){
                    return GameStates.WIN;
                }
            }
        }
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (((matrix[i][j] == -1))|| ((i > 1) && (matrix[i - 1][j] == matrix[i][j])) ||
                ((j > 1) && (matrix[i][j - 1] == matrix[i][j]))) {
                    return GameStates.ACTIVE;
                }
            }
        }
        return GameStates.LOSE;
    }

    public void print(){
        System.out.println("Score: " + score);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("|" + matrix[i][j] + "|");
            }
            System.out.println();
        }
    }

    public void restart(){
        initGame();
    }
}
