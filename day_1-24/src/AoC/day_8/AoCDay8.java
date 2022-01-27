package AoC.day_8;

import AoC.data_manager.FileReader;

public class AoCDay8 {

    static private final String filePath = "./data/day_8";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Advent of Code - Day 8: Seven Segment Search");

        SevenSegmentSearch search = new SevenSegmentSearch();
        int numUniqueDigitPatterns = search.countUniqueDigitPatterns(FileReader.readFileToLines(defaultInputFile));
        System.out.println("Task 1 - Number of unique digit patterns: " + numUniqueDigitPatterns);

        int outValue = search.getOutputValueAllLines(FileReader.readFileToLines(defaultInputFile));
        System.out.println("Task 2 - Value for all lines added up: " + outValue);
    }
}