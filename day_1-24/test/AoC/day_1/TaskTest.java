package AoC.day_1;

import AoC.day_1.Task;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class TaskTest {

    private static final int[] expectedResults = new int[] { 607, 618, 618, 617, 647, 716, 769, 792 };
    private static final String filePath = "./data/day_1";
    private static final String testInputFile = filePath + "/test_input.txt";

    @Test
    public void testTask1() throws IOException {
        Task task_1 = new Task(testInputFile);
        LinkedList<Integer> numbersList = task_1.getNumbersList();
        int timesIncreased = task_1.countTimesIncreased(numbersList);
        assertEquals(7, timesIncreased);
    }

    @Test
    public void testTask2() throws IOException {
        Task task_2 = new Task(testInputFile);
        LinkedList<Integer> sumList = task_2.calcSums();
        int timesIncreased = task_2.countTimesIncreased(sumList);
        assertEquals(5, timesIncreased);
    }

    @Test
    public void testCalcWindowSum() throws IOException {
        Task task = new Task(testInputFile);

        for (int i = 0; i < expectedResults.length; i++) {
            assertEquals(expectedResults[i], task.calcWindowSum(i));
        }
        assertEquals(-1, task.calcWindowSum(expectedResults.length));
    }

    @Test
    public void testCalcSums() throws IOException {
        Task task = new Task(testInputFile);
        LinkedList<Integer> resultList = task.calcSums();
        assertEquals("Size does not match!", expectedResults.length, resultList.size());
        for (int i = 0; i < expectedResults.length; i++) {
            assertEquals(Integer.valueOf(expectedResults[i]), resultList.get(i));
        }
    }

    @Test
    public void testCountTimesIncreased() throws IOException {
        Task task = new Task(testInputFile);
        LinkedList<Integer> sumList = new LinkedList();
        for (int number : expectedResults) {
            sumList.add(number);
        }
        assertEquals(5, task.countTimesIncreased(sumList));
    }
}