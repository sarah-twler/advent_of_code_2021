package AoC.day_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Task {

    private static final int WINDOW_SIZE = 3;
    private static LinkedList<Integer> numbers;

    public Task(String filename) throws IOException {
        numbers = readNumbersFromFile(filename);
    }

    public LinkedList<Integer> getNumbersList() {
        return numbers;
    }

    /**
     * @param index starting index of the sliding window
     * @return the sum of the sliding window or -1 when not enough measurements are left
     */
     int calcWindowSum(int index) {
        if (index + WINDOW_SIZE > numbers.size()) {
            return -1;
        }
        int windowSum = 0;
        for (int i = 0; i < WINDOW_SIZE; i++) {
            windowSum += numbers.get(index + i);
        }
        return windowSum;
    }

    LinkedList<Integer> calcSums() {
        LinkedList<Integer> sums = new LinkedList();
        int lastSum = 0;
        int currIndex = 0;
        while (true) {
            lastSum = calcWindowSum(currIndex);
            if (lastSum <= 0) {
                break;
            }
            sums.add(lastSum);
            currIndex++;
        }
        return sums;
    }

    int countTimesIncreased(LinkedList<Integer> numbers) {
        int timesIncreased = 0;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i-1) < numbers.get(i)) {
                timesIncreased++;
            }
        }
        return timesIncreased;
    }

    private LinkedList<Integer> readNumbersFromFile(String filename) throws IOException, NumberFormatException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        LinkedList<Integer> numbers = new LinkedList();
        for (String str : lines) {
            numbers.add(Integer.parseInt(str));
        }
        return numbers;
    }

}

