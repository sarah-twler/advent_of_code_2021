package AoC.day_13;

import AoC.data_manager.FileReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestTransparentOrigami {

    private static final String filePath = "./data/day_13";
    private static final String testInputFile = filePath + "/test_input.txt";
    private static List<String> inputLines;

    @BeforeClass
    public static void initialize() throws IOException {
        inputLines = FileReader.readFileToLines(testInputFile);
    }

    @Test
    public void testCreateDotMatrix() {
        String expectedMatrix =
                "...#..#..#.\n" +
                "....#......\n" +
                "...........\n" +
                "#..........\n" +
                "...#....#.#\n" +
                "...........\n" +
                "...........\n" +
                "...........\n" +
                "...........\n" +
                "...........\n" +
                ".#....#.##.\n" +
                "....#......\n" +
                "......#...#\n" +
                "#..........\n" +
                "#.#........\n";
        String actualMatrix = new TransparentOrigami().createOrigami(inputLines);
        assertEquals(expectedMatrix, actualMatrix);
    }

    @Test
    public void testFoldMatrixAlongYCoordinate() {
        String expectedMatrix =
                "#.##..#..#.\n" +
                "#...#......\n" +
                "......#...#\n" +
                "#...#......\n" +
                ".#.#..#.###\n" +
                "...........\n" +
                "...........\n";
        TransparentOrigami origami = new TransparentOrigami();
        origami.createOrigami(inputLines);
        String actualMatrix = origami.foldOrigamiAlongYCoordinate(7);
        assertEquals(expectedMatrix, actualMatrix);
        assertEquals(17, origami.countDots());
    }

    @Test
    public void testFoldMatrixAlongXCoordinate() {
        String expectedMatrix =
                "#####\n" +
                "#...#\n" +
                "#...#\n" +
                "#...#\n" +
                "#####\n" +
                ".....\n" +
                ".....\n";
        TransparentOrigami origami = new TransparentOrigami();
        origami.createOrigami(inputLines);
        origami.foldOrigamiAlongYCoordinate(7);
        String actualMatrix = origami.foldOrigamiAlongXCoordinate(5);
        assertEquals(expectedMatrix, actualMatrix);
    }

    @Test
    public void testFoldOrigami() {
        String expectedMatrix =
                        "#####\n" +
                        "#...#\n" +
                        "#...#\n" +
                        "#...#\n" +
                        "#####\n" +
                        ".....\n" +
                        ".....\n";
        TransparentOrigami origami = new TransparentOrigami();
        origami.createOrigami(inputLines);
        String actualMatrix = origami.foldOrigami(inputLines);
        assertEquals(expectedMatrix, actualMatrix);
    }

    @Test
    public void testGetCoordinate() {
        TransparentOrigami origami = new TransparentOrigami();
        assertEquals(7, origami.getCoordinate("fold along y=7"));
        assertEquals(5, origami.getCoordinate("fold along x=5"));
    }

    @Test
    public void testGetAxis() {
        TransparentOrigami origami = new TransparentOrigami();
        assertEquals("y", origami.getAxis("fold along y=7"));
        assertEquals("x", origami.getAxis("fold along x=5"));
    }

}
