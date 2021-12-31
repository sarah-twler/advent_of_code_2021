package AoC.day_12;

import AoC.data_manager.FileReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TestCaveGraph {

    private static final String filePath = "./data/day_12";
    private static final String testInputSmallFile = filePath + "/test_input_small.txt";
    private static final String testInputMediumFile = filePath + "/test_input_medium.txt";
    private static final String testInputLargeFile = filePath + "/test_input_large.txt";
    private static List<String> testfileSmallInputList;
    private static List<String> testfileMediumInputList;
    private static List<String> testfileLargeInputList;

    @BeforeClass
    public static void init() throws IOException {
        testfileSmallInputList = FileReader.readFileToLines(testInputSmallFile);
        testfileMediumInputList = FileReader.readFileToLines(testInputMediumFile);
        testfileLargeInputList = FileReader.readFileToLines(testInputLargeFile);
    }

    @Test
    public void testCountPathsInGraphVisitSmallNodesOnce() throws IOException {
        CaveGraph graphSmall = new CaveGraph(testfileSmallInputList);
        assertEquals(10, graphSmall.countPathsInGraph(false));

        CaveGraph graphMedium = new CaveGraph(testfileMediumInputList);
        assertEquals(19, graphMedium.countPathsInGraph(false));

        CaveGraph graphLarge = new CaveGraph(testfileLargeInputList);
        assertEquals(226, graphLarge.countPathsInGraph(false));
    }

    @Test
    public void testCountPathsInGraphVisitSmallNodesTwice() throws IOException {
        CaveGraph graphSmall = new CaveGraph(testfileSmallInputList);
        int actualCountSmall = graphSmall.countPathsInGraph(true);
        assertEquals(36, actualCountSmall);

        CaveGraph graphMedium = new CaveGraph(testfileMediumInputList);
        int actualCountMedium = graphMedium.countPathsInGraph(true);
        assertEquals(103, actualCountMedium);

        CaveGraph graphLarge = new CaveGraph(testfileLargeInputList);
        int actualCountLarge = graphLarge.countPathsInGraph(true);
        assertEquals(3509, actualCountLarge);

    }

    private void print(List<String> list, String name) {
        Collections.sort(list);
        System.out.println(name);
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println("");
    }

}
