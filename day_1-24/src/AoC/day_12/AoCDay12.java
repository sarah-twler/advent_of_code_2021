package AoC.day_12;

import AoC.data_manager.FileReader;

public class AoCDay12 {

    static private final String filePath = "./data/day_12";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Advent of Code - Day 12: Passage Pathing");

        CaveGraph graph = new CaveGraph(FileReader.readFileToLines(defaultInputFile));
        int numOfPaths = graph.countPathsInGraph(false);
        System.out.println("Task 1 - Number of paths in cave: " + numOfPaths);

        int numOfPaths2 = graph.countPathsInGraph(true);
        System.out.println("Task 2 - Number of paths in cave: " + numOfPaths2);
    }

}
