package AoC.day_3;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class TestTaskDay3 {

    private static final String filePath = "./data/day_3";
    private static final String testInputFile = filePath + "/test_input.txt";

    @Test
    public void testReadDataFromFile() throws IOException {
        TaskDay3 task = new TaskDay3();
        LinkedList<LinkedList<Integer>> resultData = task.readDataFromFile(testInputFile);

        assertEquals(5, resultData.size());
        assertEquals(7, countOccurence(resultData.get(0), 1));
        assertEquals(5, countOccurence(resultData.get(0), 0));

        assertEquals(5, countOccurence(resultData.get(1), 1));
        assertEquals(7, countOccurence(resultData.get(1), 0));

        assertEquals(8, countOccurence(resultData.get(2), 1));
        assertEquals(4, countOccurence(resultData.get(2), 0));

        assertEquals(7, countOccurence(resultData.get(3), 1));
        assertEquals(5, countOccurence(resultData.get(3), 0));

        assertEquals(5, countOccurence(resultData.get(4), 1));
        assertEquals(7, countOccurence(resultData.get(4), 0));
    }

    @Test
    public void testCalcOxygenGeneratorRating() throws IOException {
        TaskDay3 task = new TaskDay3();
        LinkedList<LinkedList<Integer>> data = task.readDataFromFile(testInputFile);

        assertEquals(23, task.calcRating(data, LifeSupportRatingTypeEnum.OXYGEN_GENERATOR));
    }

    @Test
    public void testCo2ScrubberRating() throws IOException {
        TaskDay3 task = new TaskDay3();
        LinkedList<LinkedList<Integer>> data = task.readDataFromFile(testInputFile);

        assertEquals(10, task.calcRating(data, LifeSupportRatingTypeEnum.CO2_SCRUBBER));
    }

    @Test
    public void testGetRowAsBinaryArray() throws IOException {
        TaskDay3 task = new TaskDay3();
        LinkedList<LinkedList<Integer>> data = task.readDataFromFile(testInputFile);

        assertTrue(Arrays.equals(new int[]{0,0,1,0,0}, task.getRowAsBinaryArray(data, 0)));
        assertTrue(Arrays.equals(new int[]{0,1,1,1,1}, task.getRowAsBinaryArray(data, 5)));
        assertTrue(Arrays.equals(new int[]{1,1,0,0,1}, task.getRowAsBinaryArray(data, 9)));
    }

    @Test
    public void testCalcGammaRate() throws IOException {
        TaskDay3 task = new TaskDay3();
        LinkedList<LinkedList<Integer>> data = task.readDataFromFile(testInputFile);

        assertEquals(22, task.calcGammaRate(data));
    }

    @Test
    public void testCalcEpsilonRate() throws IOException {
        TaskDay3 task = new TaskDay3();
        LinkedList<LinkedList<Integer>> data = task.readDataFromFile(testInputFile);

        assertEquals(9, task.calcEpsilonRate(data));
    }

    @Test
    public void testCalcPowerConsumption() throws IOException {
        TaskDay3 task = new TaskDay3();
        LinkedList<LinkedList<Integer>> data = task.readDataFromFile(testInputFile);

        assertEquals(198, task.calcPowerConsumption(data));
    }

    @Test
    public void testCalcLifeSupportRating() throws IOException {
        TaskDay3 task = new TaskDay3();
        LinkedList<LinkedList<Integer>> data = task.readDataFromFile(testInputFile);

        assertEquals(230, task.calcLifeSupportRating(data));
    }


    private int countOccurence(LinkedList<Integer> data, int binary) {
        int count = 0;
        for (int num : data) {
            if (num == binary) {
                count++;
            }
        }
        return count;
    }
}
