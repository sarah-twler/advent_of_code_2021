package AoC.day_7;

import java.util.Map;

public class AoCDay7 {

    static private final String filePath = "./data/day_7";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Advent of Code - Day 7: The Treachery of Whales");

        Map<Integer, Integer> data = FileReader.readMappedDataFromFile(defaultInputFile);

        CrabDistribution crabDistribution1 = new CrabDistribution(data);
        int fuelConsumption1 = crabDistribution1.calcLowestFuelConsumption();
        System.out.println("Task 1 - total fuel consumption: " + fuelConsumption1);

        CrabDistribution crabDistribution2 = new CrabDistribution(data);
        int fuelConsumption2 = crabDistribution2.calcLowestExpensiveFuelConsumption();
        System.out.println("Task 2 - total fuel consumption: " + fuelConsumption2);
    }

}
