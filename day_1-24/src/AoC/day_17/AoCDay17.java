package AoC.day_17;

import AoC.data_manager.FileReader;

import java.util.ArrayList;

public class AoCDay17 {

    static private final String filePath = "./data/day_17";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Advent of Code - Day 17: Trick Shot");

        String coordTarget = FileReader.readFileToLines(defaultInputFile).get(0);

        int maxHeightY = TrickShot.getMaxHeight(coordTarget);
        System.out.println("Task 1 - Highest y position: " + maxHeightY);

        ArrayList<String> distinctVelocities = TrickShot.findAllVelocityValues(coordTarget);
        System.out.println("Task 2 - Number of distinct initial velocities: " + distinctVelocities.size());

    }
}
