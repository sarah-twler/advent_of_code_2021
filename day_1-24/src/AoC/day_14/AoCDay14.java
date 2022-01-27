package AoC.day_14;

import AoC.data_manager.FileReader;

import java.math.BigInteger;

public class AoCDay14 {

    static private final String filePath = "./data/day_14";
    static private final String defaultInputFile = filePath + "/task_input.txt";
    private static final String DELIMITER = " -> ";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Advent of Code - Day 14: Extended Polymerization");

        Polymerization polymerization = new Polymerization(FileReader.readFileToMap(defaultInputFile, DELIMITER));
        BigInteger quantity1 = polymerization.calcBigPolymerResult(FileReader.readFileToLines(defaultInputFile).get(0), 10);
        System.out.println("Task 1 - Quantity: " + quantity1.toString());
        BigInteger quantity2 = polymerization.calcBigPolymerResult(FileReader.readFileToLines(defaultInputFile).get(0), 40);
        System.out.println("Task 2 - Quantity: " + quantity2.toString());
    }

}
