package AoC.day_5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class AoCDay5 {

    static private final String filePath = "./data/day_5";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Advent of Code - Day 5: Hydrothermal Venture");

        HydrothermalVent task = new HydrothermalVent();
        ArrayList<CoordinatesXY> data = FileReader.readDataFromFile(defaultInputFile);

        int[][] diagram1 = task.createDiagram(data, false);
        int countDangerZones1 = task.countDangerZones(diagram1);
        System.out.println("Task 1 - Number of danger zones: " + countDangerZones1);

        int[][] diagram2 = task.createDiagram(data, true);
        int countDangerZones2 = task.countDangerZones(diagram2);
        System.out.println("Task 2 - Number of danger zones: " + countDangerZones2);
    }
}
