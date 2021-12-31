package AoC.day_9;

import AoC.data_manager.DataConverter;
import AoC.data_manager.DataPrinter;
import AoC.data_manager.FileReader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestSmokeBasin {

    private static final String filePath = "./data/day_9";
    private static final String testInputFile = filePath + "/test_input.txt";
    private static List<String> fileInputList;

    private SmokeBasin smokeBasin;

    @BeforeClass
    public static void init() throws IOException {
        fileInputList = FileReader.readFileToLines(testInputFile);
    }

    @Before
    public void setup() throws IOException {
        smokeBasin = new SmokeBasin(DataConverter.readLinesToMatrixNoDelimiter(fileInputList));
    }

    @Test
    public void testMultiplyLargestThreeBasinSizes() throws IOException {
        assertEquals(1134, smokeBasin.multiplyLargestThreeBasinSizes());
    }

    @Test
    public void testCalcBasinSize() throws IOException {
        int actualCount1 = smokeBasin.calcBasinSize(0, 1, smokeBasin.createBasinMap(0, 1));
//        DataPrinter.printMatrix(smokeBasin.createBasinMap(0,1));
        assertEquals(3, actualCount1);

        int actualCount2 = smokeBasin.calcBasinSize(0, 9, smokeBasin.createBasinMap(0, 9));
//        DataPrinter.printMatrix(smokeBasin.createBasinMap(0,9));
        assertEquals(9, actualCount2);

        int actualCount3 = smokeBasin.calcBasinSize(2, 2, smokeBasin.createBasinMap(2, 2));
//        DataPrinter.printMatrix(smokeBasin.createBasinMap(2,2));
        assertEquals(14, actualCount3);

        int actualCount4 = smokeBasin.calcBasinSize(4, 6, smokeBasin.createBasinMap(4, 6));
//        DataPrinter.printMatrix(smokeBasin.createBasinMap(4,6));
        assertEquals(9, actualCount4);
    }

    @Test
    public void testCreateBasinMap() throws IOException {
        int[][] expectedBasinMap = new int[][] {
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,1,1,1,0,0,0,0,0 },
                { 0,1,1,1,1,1,0,0,0,0 },
                { 1,1,1,1,1,0,0,0,0,0 },
                { 0,1,0,0,0,0,0,0,0,0 }
        };
        int[][] actualBasinMap = smokeBasin.createBasinMap(2,2);
//        DataPrinter.printMatrix(actualBasinMap);
        assertArrayEquals(expectedBasinMap, actualBasinMap);
    }

    @Test
    public void testFindLowPoints() {
        ArrayList<int[]> resultList = smokeBasin.findLowPointPositions();

        assertEquals(0, resultList.get(0)[0]);
        assertEquals(1, resultList.get(0)[1]);

        assertEquals(0, resultList.get(1)[0]);
        assertEquals(9, resultList.get(1)[1]);

        assertEquals(2, resultList.get(2)[0]);
        assertEquals(2, resultList.get(2)[1]);

        assertEquals(4, resultList.get(3)[0]);
        assertEquals(6, resultList.get(3)[1]);
    }

    @Test
    public void testCalcTotalRiskLevel() {
        assertEquals(15, smokeBasin.calcTotalRiskLevel());
    }
}
