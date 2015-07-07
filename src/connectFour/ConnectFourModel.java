package connectFour;

import java.util.Arrays;

/**
 * Created by hedon on 7/7/15.
 */
public class ConnectFourModel {
    private int[][] tempData = new int[7][7];
    public static final int GRID_SIZE = 7;
    public static final int GRID_HEIGHT = 6;
    public static final int GRID_WIDTH = 7;
    private int ourSymbol;
    private int theirSymbol;
    private int[][] inputData;
    public ConnectFourModel() {
        this.inputData = new int[GRID_HEIGHT][GRID_WIDTH];
    }
    public int makeMove(String[] input, String symbol) {
        if(symbol.equals("X")) {
            ourSymbol = 2;
            theirSymbol = 1;
        } else {
            ourSymbol = 1;
            theirSymbol = 2;
        }
        int[] scores = new int[GRID_WIDTH];
        parseInput(input);

        // if the bottom center is not claimed, GET IT
        if(inputData[GRID_HEIGHT - 1][GRID_WIDTH / 2] == 0) {
            return GRID_WIDTH / 2;
        }

        // for every column of our move
        for(int i = 0; i < GRID_WIDTH; i++) {
            int row = 0;
            // find lowest empty spot in that column
            while(row < GRID_HEIGHT - 1 && inputData[row][i] == 0) {
                row++;
            }
            // if column is full, move on
            if(inputData[row][i] == 0) {
                // simulate our move
                inputData[row][i] = ourSymbol;
                int resultMoveOne = findWinner(i, row, ourSymbol);
                if(resultMoveOne == ourSymbol) {
                    return i;
                }
                // no need to check if enemy won, since they haven't moved yet

                // simulate their move; for each col
                for (int j = 0; j < GRID_WIDTH; j++) {
                    int enemyRow = 0;
                    while (enemyRow < GRID_HEIGHT - 1 && inputData[enemyRow][j] == 0) {
                        enemyRow++;
                    }
                    inputData[enemyRow][j] = theirSymbol;
                    int resultMoveTwo = findWinner(j, enemyRow, ourSymbol);
                    if(resultMoveTwo == theirSymbol) {
                        // subtract 1000 for a loss
                        scores[i] -= 1000;
                    }
                    // no need to check if we won, we didn't move

                    for(int k = 0; k < GRID_WIDTH; k++) {
                        int ourRow = 0;
                        while(ourRow < GRID_HEIGHT - 1 && inputData[ourRow][k] == 0) {
                            ourRow++;
                        }
                        inputData[ourRow][k] = ourSymbol;
                        int resultMoveThree = findWinner(k, ourRow, ourSymbol);
                        if(resultMoveThree == ourSymbol) {
                            scores[i] += 1000;
                        }

                        inputData[ourRow][k] = 0;
                    }

                    // undo fake move
                    inputData[enemyRow][j] = 0;
                }
            }
            // undo fake move
            inputData[row][i] = 0;
        }
        int max = 0;
        for(int i = 1; i < scores.length; i++) {
            if(scores[max] < scores[i]) {
                max = i;
            }
        }
        System.out.println("Scores: " + Arrays.toString(scores));
        return max;
        
    }
    // i is row
    // j is column
    private void parseInput(String[] input) {
        for (int i = 1; i < GRID_SIZE; i++) {
            String toParse = input[i];
            String[] parsed = toParse.split("|");
            String[] result = new String[7];
            int index = 0;
            for (String res: parsed) {
                if (res.equals("O") || res.equals("X") || res.equals(" ")) {
                    result[index] = res;
                    index++;
                }
            }
            for (int j = 0; j < result.length; j++) {
                if (result[j].equals("O")) {
                    inputData[i - 1][j] = 1;
                } else if (result[j].equals("X")) {
                    inputData[i - 1][j] = 2;
                } else if (result[j].equals(" ")) {
                    inputData[i - 1][j] = 0;
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(inputData[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int findWinner(int column, int row, int symbol) {
        String res = "O";
        int winCount = 0;
        if (column >= 3) {
            for (int i = column; i > column - 4 ; i--) {
                if (inputData[row][i] == symbol) {
                    winCount++;
                }
            }
            if (winCount == 4) {
                return symbol;
            }
        }
        winCount = 0;
        if (column <= 3) {
            for (int i = column; i < column + 4; i++) {
                if (inputData[row][i] == symbol) {
                    winCount++;
                }
            }
            if (winCount == 4) {
                return symbol;
            }
        }
        if (row >= 3) {
            for (int i = row; i > row - 4; i--) {
                if (inputData[i][column] == symbol) {
                    winCount++;
                }
            }
            if (winCount == 4) {
                return symbol;
            }
        }
        if (row <= 2) {
            for (int i = row; i < row + 4; i++) {
                if (inputData[i][column] == symbol) {
                    winCount++;
                }
            }
            if (winCount == 4) {
                return symbol;
            }
        }
        if (row <= 2 && column <= 3) {
            for (int i = 0; i < 4; i++) {
                if (inputData[row + i][column + i] == symbol) {
                    winCount++;
                }
            }
            if (winCount == 4) {
                return symbol;
            }
        }

        if (row >= 3 && column >= 3) {
            for (int i = 0; i < 4; i++) {
                if (inputData[row - i][column - i] == symbol) {
                    winCount++;
                }
            }
            if (winCount == 4) {
                return symbol;
            }
        }
        return symbol;
    }
}