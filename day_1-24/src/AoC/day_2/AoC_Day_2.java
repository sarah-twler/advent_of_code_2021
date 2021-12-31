package AoC.day_2;

import java.io.IOException;
import java.util.LinkedList;

public class AoC_Day_2 {

    static private final String filePath = "./data/day_2";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Advent of Code - Day 2: Dive!");
        LinkedList<DirectionsTuple> data = FileReader.readDataFromFile(defaultInputFile);

        Task1Day2 task1 = new Task1Day2();
        int result1 = task1.moveSubmarine(data);
        System.out.println("Task 1 - Final position: " + result1);

        Task2Day2 task2 = new Task2Day2();
        int result2 = task2.moveSubmarine(data);
        System.out.println("Task 2 - Final position: " + result2);
    }
}
