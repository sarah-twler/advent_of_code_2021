package AoC.day_10;

import AoC.data_manager.FileReader;

import java.math.BigInteger;

public class AoCDay10 {

    static private final String filePath = "./data/day_10";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Advent of Code - Day 10: Syntax Scoring");

        SyntaxScoring syntaxScoring = new SyntaxScoring();
        int totalCorruptScore = syntaxScoring.checkForCorruptedLines(FileReader.readFileToLines(defaultInputFile));
        System.out.println("Task 1 - total score of corrupted lines: " + totalCorruptScore);

        BigInteger completeLineScore = syntaxScoring.checkForIncompleteLines(FileReader.readFileToLines(defaultInputFile));
        System.out.println("Task 2 - score of complete lines: " + completeLineScore);
    }
}