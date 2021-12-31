package AoC.day_13;

import AoC.data_manager.FileReader;

import java.io.IOException;
import java.util.List;

public class AoCDay13 {

    static private final String filePath = "./data/day_13";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Advent of Code - Day 13: Transparent Origami");

        TransparentOrigami origami = new TransparentOrigami();
        List<String> dataInput = FileReader.readFileToLines(defaultInputFile);
        origami.createOrigami(dataInput);
        origami.foldOrigami(dataInput, 1);
        int dotCount = origami.countDots();
        System.out.println("Task 1 - Number of dots after 1 fold: " + dotCount);
        origami.createOrigami(dataInput);
        String finalOrigami = origami.foldOrigami(dataInput);
        System.out.println("Task 2 - Output origami after completing folding instructions (Code): \n" + finalOrigami);
    }
}
