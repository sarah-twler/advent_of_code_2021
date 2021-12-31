package AoC.day_4;

import AoC.day_3.TaskDay3;

import java.io.File;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.LinkedList;

public class AoCDay4 {

    static private final String filePath = "./data/day_4";
    static private final String defaultInputFile = filePath + "/task_input.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Advent of Code - Day 4: Giant Squid");

        GiantSquid task = new GiantSquid();
        LinkedList<Integer> drawNumbers = FileReader.readDrawNumbers(defaultInputFile);
        LinkedList<Board> boards = FileReader.readBoards(defaultInputFile);

        int score1 = task.playBingoFirstWin(drawNumbers, boards);
        System.out.println("Task 1 - Bingo score: " + score1);

        int score2 = task.playBingoLastWin(drawNumbers, boards);
        System.out.println("Task 2 - Bingo score: " + score2);
    }
}
