package AoC.day_11;

import AoC.data_manager.DataConverter;
import AoC.data_manager.FileReader;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDumboOctopus {

    private static final String filePath = "./data/day_11";
    private static final String testInputFile = filePath + "/test_input.txt";
    private static final String testOutputFile = filePath + "/test_output.txt";
    private static int[][] testfileInputMatrix;

    @BeforeClass
    public static void init() throws IOException {
        testfileInputMatrix = DataConverter.readLinesToMatrixNoDelimiter(FileReader.readFileToLines(testInputFile));
    }

    @Test
    public void testStepThroughOctopusesSmallInput() {
        DumboOctopus octopus1 = new DumboOctopus(getSmallInputMatrix());
        assertEquals(9, octopus1.stepThroughOctopuses(1));
        assertArrayEquals(getSmallOutputMatrix1(), octopus1.getOctopusMatrix());

        DumboOctopus octopus2 = new DumboOctopus(getSmallInputMatrix());
        assertEquals(9, octopus2.stepThroughOctopuses(2));
        assertArrayEquals(getSmallOutputMatrix2(), octopus2.getOctopusMatrix());
    }

    @Test
    public void testStepThroughOctopuses() throws IOException {
        Map<Integer, int[][]> expectedOutputMap = createExpectedTestOutput();
        Iterator<Integer> iter = expectedOutputMap.keySet().iterator();
        while (iter.hasNext()) {
            int stepCount = iter.next();
            DumboOctopus octopus = new DumboOctopus(DataConverter.readLinesToMatrixNoDelimiter(FileReader.readFileToLines(testInputFile)));
            int totalCount = octopus.stepThroughOctopuses(stepCount);
            assertArrayEquals(expectedOutputMap.get(stepCount), octopus.getOctopusMatrix());

            if (stepCount == 10)
                assertEquals(204, totalCount);
            if (stepCount == 100)
                assertEquals(1656, totalCount);
        }
    }

    @Test
    public void testStepThroughOctopusesUntilTotalFlash() throws IOException {
        DumboOctopus octopus = new DumboOctopus(DataConverter.readLinesToMatrixNoDelimiter(FileReader.readFileToLines(testInputFile)));
        int firstSimultaneousFlash = octopus.stepThroughOctopusesUntilTotalFlash();
        assertEquals(195, firstSimultaneousFlash);
    }

    private Map<Integer, int[][]> createExpectedTestOutput() throws IOException {
        LinkedList<LinkedList<String>> data = FileReader.readFileToLinesInSectionsWhitespaceSeparator(testOutputFile);
        Map<Integer, int[][]> matrixMap = new HashMap();
        for (LinkedList<String> section : data) {
            String comment = section.getFirst();
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(comment);
            int stepCount = 0;
            while(m.find()) {
                stepCount = Integer.parseInt(m.group());
            }
            section.removeFirst();
            int[][] matrix = DataConverter.readLinesToMatrixNoDelimiter(section);
            matrixMap.put(stepCount, matrix);
        }
        return matrixMap;
    }

    private int[][] getSmallInputMatrix() {
        return new int[][] {
                { 1,1,1,1,1 },
                { 1,9,9,9,1 },
                { 1,9,1,9,1 },
                { 1,9,9,9,1 },
                { 1,1,1,1,1 }};
    }

    private int[][] getSmallOutputMatrix1() {
        return new int[][] {
                { 3,4,5,4,3 },
                { 4,0,0,0,4 },
                { 5,0,0,0,5 },
                { 4,0,0,0,4 },
                { 3,4,5,4,3 }};
    }

    private int[][] getSmallOutputMatrix2() {
        return new int[][] {
                { 4,5,6,5,4 },
                { 5,1,1,1,5 },
                { 6,1,1,1,6 },
                { 5,1,1,1,5 },
                { 4,5,6,5,4 }};
    }

}
