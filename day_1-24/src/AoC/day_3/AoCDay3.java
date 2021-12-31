package AoC.day_3;

import AoC.day_2.DirectionsTuple;
import AoC.day_2.FileReader;
import AoC.day_2.Task1Day2;
import AoC.day_2.Task2Day2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class AoCDay3 {

    static private final String filePath = "./data/day_3";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Advent of Code - Day 3: Binary Diagnostic");

        TaskDay3 task = new TaskDay3();
        LinkedList<LinkedList<Integer>> data = task.readDataFromFile(defaultInputFile);

        int powerConsumption = task.calcPowerConsumption(data);
        System.out.println("Task 1 - Power consumption: " + powerConsumption);

        int lifeSupportRating = task.calcLifeSupportRating(data);
        System.out.println("Task 2 - Life support rating: " + lifeSupportRating);
    }
}
