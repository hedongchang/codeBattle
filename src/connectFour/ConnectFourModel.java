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
        parseInput(input);
        int column = 0;
        for (int j = 0; j < GRID_SIZE; j++) {
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
                    inputData[i][j] = 1;
                } else if (result[j].equals("X")) {
                    inputData[i][j] = 2;
                } else if (result[j].equals(" ")) {
                    inputData[i][j] = 0;
                }
            }
        }
    }

}
