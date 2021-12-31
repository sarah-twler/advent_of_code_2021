package AoC.day_5;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestHydrothermalVent {

    private static final String filePath = "./data/day_5";
    private static final String testInputFile = filePath + "/test_input.txt";

    @Test
    public void testIsDiagonalLine() {
        CoordinatesXY xy1 = new CoordinatesXY(1, 1, 3, 3);
        assertTrue(xy1.isDiagonalLine());

        CoordinatesXY xy2 = new CoordinatesXY(9,7,7,9);
        assertTrue(xy2.isDiagonalLine());

        CoordinatesXY xy3 = new CoordinatesXY(9,7,7,8);
        assertFalse(xy3.isDiagonalLine());

        CoordinatesXY xy4 = new CoordinatesXY(1,1,6,7);
        assertFalse(xy4.isDiagonalLine());
    }

    @Test
    public void testCreateDiagram() throws IOException {
        ArrayList<CoordinatesXY> data = FileReader.readDataFromFile(testInputFile);
        HydrothermalVent task = new HydrothermalVent();

        int[][] actualDiagram1 = task.createDiagram(data, false);
        int[][] expectedDiagram1 = getExpectedDiagramHorizontalVerticalOnly();
        assertArrayEquals(expectedDiagram1, actualDiagram1);

        int[][] actualDiagram2 = task.createDiagram(data, true);
        int[][] expectedDiagram2 = getExpectedDiagramWithDiagonals();
        task.printDiagram(actualDiagram2);
        assertArrayEquals(expectedDiagram2, actualDiagram2);
    }

    @Test
    public void testCountDangerZones() throws IOException {
        ArrayList<CoordinatesXY> data = FileReader.readDataFromFile(testInputFile);
        HydrothermalVent task = new HydrothermalVent();

        assertEquals(5, task.countDangerZones(task.createDiagram(data, false)));
        assertEquals(12, task.countDangerZones(task.createDiagram(data, true)));
    }

    private int[][] getExpectedDiagramHorizontalVerticalOnly() {
        int[][] diagram = new int[][] {
                { 0,0,0,0,0,0,0,1,0,0 },
                { 0,0,1,0,0,0,0,1,0,0 },
                { 0,0,1,0,0,0,0,1,0,0 },
                { 0,0,0,0,0,0,0,1,0,0 },
                { 0,1,1,2,1,1,1,2,1,1 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 0,0,0,0,0,0,0,0,0,0 },
                { 2,2,2,1,1,1,0,0,0,0 }
        };
        return diagram;
    }

    private int[][] getExpectedDiagramWithDiagonals() {
        int[][] diagram = new int[][] {
                { 1,0,1,0,0,0,0,1,1,0 },
                { 0,1,1,1,0,0,0,2,0,0 },
                { 0,0,2,0,1,0,1,1,1,0 },
                { 0,0,0,1,0,2,0,2,0,0 },
                { 0,1,1,2,3,1,3,2,1,1 },
                { 0,0,0,1,0,2,0,0,0,0 },
                { 0,0,1,0,0,0,1,0,0,0 },
                { 0,1,0,0,0,0,0,1,0,0 },
                { 1,0,0,0,0,0,0,0,1,0 },
                { 2,2,2,1,1,1,0,0,0,0 }
        };
        return diagram;
    }
}
