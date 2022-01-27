package AoC.day_15;

import AoC.data_manager.DataConverter;
import AoC.data_manager.FileReader;

public class AoCDay15 {
    static private final String filePath = "./data/day_15";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Advent of Code - Day 15: Chiton");

        ChitonMatrix chitonMatrix = new ChitonMatrix(DataConverter.readLinesToMatrixNoDelimiter(FileReader.readFileToLines(defaultInputFile)));
        int lowestRiskLevel = chitonMatrix.calcEndRiskValue();
        System.out.println("Task 1 - Risk level of shortest path: " + lowestRiskLevel);

        int[][] enlargedMatrix = chitonMatrix.enlargeChitonMatrix();
        int lowestRiskLevelEnlarged = chitonMatrix.calcEndRiskValue(enlargedMatrix);
        System.out.println("Task 2 - Risk level of shortest path in enlarged matrix: " + lowestRiskLevelEnlarged);
        // answer 2846 is incorrect! :(

    }

}
