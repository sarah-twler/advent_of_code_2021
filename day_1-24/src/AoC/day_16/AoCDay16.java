package AoC.day_16;

import AoC.data_manager.DataConverter;
import AoC.data_manager.FileReader;

import java.math.BigInteger;

public class AoCDay16 {

    static private final String filePath = "./data/day_16";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Advent of Code - Day 16: Packet Decoder");

        String hexadecimal = DataConverter.convertListToString(FileReader.readFileToLines(defaultInputFile));
        PacketDecoder decoder = new PacketDecoder(hexadecimal);

        int sumOfVersions = decoder.getSumVersionNumbers();
        System.out.println("Task 1 - Sum of version numbers in packets: " + sumOfVersions);

        BigInteger expResult = decoder.getExpressionResult();
        System.out.println("Task 2 - Result of the expression: " + expResult.toString());

    }
}
