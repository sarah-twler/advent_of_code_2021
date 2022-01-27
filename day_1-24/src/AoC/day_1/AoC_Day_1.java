package AoC.day_1;

import java.io.IOException;
import java.util.LinkedList;

public class AoC_Day_1 {

    static private final String filePath = "./data/day_1";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Advent of Code - Day 1: Sonar Sweep");

        Task task_1 = new Task(defaultInputFile);
        LinkedList<Integer> numbersList = task_1.getNumbersList();
        int timesIncreased_1 = task_1.countTimesIncreased(numbersList);
        System.out.println("Task 1 - Times increased: " + timesIncreased_1);

        Task task_2 = new Task(defaultInputFile);
        LinkedList<Integer> sumList = task_2.calcSums();
        int timesIncreased_2 = task_2.countTimesIncreased(sumList);
        System.out.println("Task 2 - Times increased: " + timesIncreased_2);
    }
}
