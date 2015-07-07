package connectFour;

/**
 * Created by hedon on 7/7/15.
 */
public class ConnectFourModel {
    private int[][] inputData;
    public static final int GRID_SIZE = 7;

    public ConnectFourModel() {
        this.inputData = new int[GRID_SIZE][GRID_SIZE];
    }

    public int makeMove(String[] input, String symbol) {
        //parseInput(input);
        int column = 0;
        for (int j = 1; j < GRID_SIZE; j++) {
            int numInColumn = 0;
            for (int i = 0; i < GRID_SIZE; i++) {
                if (inputData[i][j] != 0) {
                    numInColumn++;
                }
            }
            if (numInColumn != GRID_SIZE - 1) {
                return j;
            }
        }
        return column;
    }

    // i is row
    // j is column
    private void parseInput(String[] input) {
        for (int i = 1; i < input.length; i++) {
            String toParse = input[i];
            String[] parsed = toParse.split("|");
            for (int j = 0; j < parsed.length; j++) {
                if (parsed[j].equals("O")) {
                    inputData[i][j] = 1;
                } else {
                    inputData[i][j] = 2;
                }
            }
        }
    }

}
