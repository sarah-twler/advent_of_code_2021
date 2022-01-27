package AoC.day_6;

import java.math.BigInteger;
import java.util.ArrayList;

public class AoCDay6 {

    static private final String filePath = "./data/day_6";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Advent of Code - Day 6: Lanternfish");

        ArrayList<Integer> data = FileManager.readDataFromFile(defaultInputFile);

        SchoolOfFish schoolOfFish1 = new SchoolOfFish(data);
        BigInteger fishCount1 = schoolOfFish1.countTotalFishAfterDays(80);
        System.out.println("Task 1 - Number of fish after 80 days: " + fishCount1);

        SchoolOfFish schoolOfFish2 = new SchoolOfFish(data);
        BigInteger fishCount2 = schoolOfFish2.countTotalFishAfterDays(256);
        System.out.println("Task 2 - Number of fish after 256 days: " + fishCount2);

    }
}
