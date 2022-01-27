package AoC.day_11;

import AoC.data_manager.DataConverter;
import AoC.data_manager.FileReader;

public class AoCDay11 {

    static private final String filePath = "./data/day_11";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Advent of Code - Day 11: Dumbo Octopus");

        DumboOctopus octopus1 = new DumboOctopus(
                DataConverter.readLinesToMatrixNoDelimiter(
                        FileReader.readFileToLines(defaultInputFile)));
        int totalFlashCount = octopus1.stepThroughOctopuses(100);
        System.out.println("Task 1 - Total number of flashes: " + totalFlashCount);

        DumboOctopus octopus2 = new DumboOctopus(
                DataConverter.readLinesToMatrixNoDelimiter(
                        FileReader.readFileToLines(defaultInputFile)));
        int firstSimultaneousFlash = octopus2.stepThroughOctopusesUntilTotalFlash();
        System.out.println("Task 2 - First simultaneous flash occurrs after step: " + firstSimultaneousFlash);
    }
}
