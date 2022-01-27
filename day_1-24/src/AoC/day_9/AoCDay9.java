package AoC.day_9;

import AoC.data_manager.DataConverter;
import AoC.data_manager.FileReader;

public class AoCDay9 {

    static private final String filePath = "./data/day_9";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Advent of Code - Day 9: Smoke Basin");

        SmokeBasin smokeBasin = new SmokeBasin(DataConverter.readLinesToMatrixNoDelimiter(FileReader.readFileToLines(defaultInputFile)));
        int totalRiskLevel = smokeBasin.calcTotalRiskLevel();
        System.out.println("Task 1 - total risk level: " + totalRiskLevel);

        SmokeBasin smokeBasin2 = new SmokeBasin(DataConverter.readLinesToMatrixNoDelimiter(FileReader.readFileToLines(defaultInputFile)));
        int basinSize = smokeBasin.multiplyLargestThreeBasinSizes();
        System.out.println("Task 1 - largest basin sizes multiplied: " + basinSize);
    }
}
