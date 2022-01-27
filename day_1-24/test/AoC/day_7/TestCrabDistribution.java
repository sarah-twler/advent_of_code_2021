package AoC.day_7;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class TestCrabDistribution {

    private static final String filePath = "./data/day_7";
    private static final String testInputFile = filePath + "/test_input.txt";

    @Test
    public void testCalcFuelConsumption() throws IOException {
        Map<Integer, Integer> data = FileReader.readMappedDataFromFile(testInputFile);
        CrabDistribution task = new CrabDistribution(data);

        assertEquals(3, task.getFrequencyFor(2));
        assertEquals(37, task.calcLowestFuelConsumption());

//        task.printCrabDistribution();
    }

    @Test
    public void testCalcExpensiveFuelConsumption() throws IOException {
        Map<Integer, Integer> data = FileReader.readMappedDataFromFile(testInputFile);
        CrabDistribution task = new CrabDistribution(data);

        assertEquals(3, task.getFrequencyFor(2));
        assertEquals(168, task.calcLowestExpensiveFuelConsumption());

//        task.printCrabDistribution();
    }
    
    @Test
    public void testReadMappedDataFromFile() throws IOException {
        Map<Integer, Integer> data = FileReader.readMappedDataFromFile(testInputFile);

        ArrayList<Integer> expectedKeys = new ArrayList(Arrays.asList(new Integer[] {0, 1, 2, 4, 7, 14, 16}));
        ArrayList<Integer> expectedValues = new ArrayList(Arrays.asList(new Integer[] {1, 2, 3, 1, 1, 1, 1}));
        Set<Integer> actualKeys = data.keySet();
        assertEquals(expectedKeys.size(), actualKeys.size());
        assertTrue(expectedKeys.containsAll(actualKeys));
        assertTrue(actualKeys.containsAll(expectedKeys));

        for (int i = 0; i < expectedKeys.size(); i++) {
            assertEquals(data.get(expectedKeys.get(i)), expectedValues.get(i));
        }
    }
}
