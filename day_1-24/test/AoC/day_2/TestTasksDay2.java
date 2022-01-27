package AoC.day_2;

import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;


public class TestTasksDay2 {

    private static final String filePath = "./data/day_2";
    private static final String testInputFile = filePath + "/test_input.txt";
    
    @Test
    public void testReadDataFromFile() throws IOException {
        Task1Day2 task = new Task1Day2();
        LinkedList<DirectionsTuple> resultData = FileReader.readDataFromFile(testInputFile);

        assertEquals(resultData.size(), 6);

        assertEquals(resultData.get(0).getDirection(), DirectionsEnum.FORWARD);
        assertEquals(resultData.get(0).getSteps(), 5);

        assertEquals(resultData.get(1).getDirection(), DirectionsEnum.DOWN);
        assertEquals(resultData.get(1).getSteps(), 5);

        assertEquals(resultData.get(2).getDirection(), DirectionsEnum.FORWARD);
        assertEquals(resultData.get(2).getSteps(), 8);

        assertEquals(resultData.get(3).getDirection(), DirectionsEnum.UP);
        assertEquals(resultData.get(3).getSteps(), 3);

        assertEquals(resultData.get(4).getDirection(), DirectionsEnum.DOWN);
        assertEquals(resultData.get(4).getSteps(), 8);

        assertEquals(resultData.get(5).getDirection(), DirectionsEnum.FORWARD);
        assertEquals(resultData.get(5).getSteps(), 2);
    }

    @Test
    public void testMoveSubmarineTask1() throws IOException {
        Task1Day2 task = new Task1Day2();
        LinkedList<DirectionsTuple> data = FileReader.readDataFromFile(testInputFile);

        assertEquals(150, task.moveSubmarine(data));
    }

    @Test
    public void testMoveSubmarineTask2() throws IOException {
        Task2Day2 task = new Task2Day2();
        LinkedList<DirectionsTuple> data = FileReader.readDataFromFile(testInputFile);

        assertEquals(900, task.moveSubmarine(data));
        assertEquals(60, task.getDepthPos());
        assertEquals(15, task.getHorizontalPos());
    }
}
