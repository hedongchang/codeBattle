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

    public void parseInput(String[] input) {
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
