package AoC.day_15;

import AoC.data_manager.DataConverter;
import AoC.data_manager.FileReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TestChitonRiskMatrix {

    private static final String filePath = "./data/day_15";
    private static final String testInputFile = filePath + "/test_input.txt";
    private static final String testInputFileEnlarged = filePath + "/enlarged_test_input.txt";
    private static List<String> inputLines;
    private static List<String> inputLinesEnlarged;

    @BeforeClass
    public static void initialize() throws IOException {
        inputLines = FileReader.readFileToLines(testInputFile);
        inputLinesEnlarged = FileReader.readFileToLines(testInputFileEnlarged);
    }

    @Test
    public void testCalcRiskValues() throws IOException {
        ChitonMatrix chitonMatrix = new ChitonMatrix(DataConverter.readLinesToMatrixNoDelimiter(inputLines));
        assertEquals(40, chitonMatrix.calcEndRiskValue());
    }

    @Test
    public void testEnlargeChitonMatrix() throws IOException {
        ChitonMatrix chitonMatrix = new ChitonMatrix(DataConverter.readLinesToMatrixNoDelimiter(inputLines));
        int[][] expectedMatrix = DataConverter.readLinesToMatrixNoDelimiter(inputLinesEnlarged);
        int[][] actualMatrix = chitonMatrix.enlargeChitonMatrix();
        assertArrayEquals(expectedMatrix, actualMatrix);


        LinkedList<String> actualShortestPath = chitonMatrix.calcPathLowestRisk(actualMatrix);
        String expectedShortestPath = "[<0|0>, <1|0>, <2|0>, <3|0>, <4|0>, <5|0>, <6|0>, <7|0>, <8|0>, <9|0>, <10|0>, <11|0>, " +
                "<12|0>, <12|1>, <12|2>, " +
//                "<13|2>, <13|3>, " + // alternative:
//                "<14|3>, <15|3>, " + // <13|2>, <14|2>, <15|2>, <15|3>
                "<13|2>, <14|2>, <15|2>, <15|3>, " + // see line above
                "<16|3>, <16|4>, <16|5>, <16|6>, <16|7>, <16|8>, <16|9>, " +
                "<17|9>, " +
                "<18|9>, <18|10>, <18|11>, <18|12>, " +
                "<19|12>, <19|13>, <19|14>, " +
                "<20|14>, " +
                "<21|14>, <21|15>, " +
                "<22|15>, <22|16>, " +
                "<23|16>, <24|16>, " +
                "<25|16>, <25|17>, <25|18>, <25|19>, " +
                "<26|19>, <27|19>, " +
                "<28|19>, <28|20>, <28|21>, <28|22>, " +
                "<29|22>, <29|23>, <29|24>, " +
                "<30|24>, <30|25>, <30|26>, <30|27>, " +
                "<31|27>, <32|27>, " +
                "<33|27>, <33|28>, <33|29>, " +
                "<34|29>, <34|30>, <34|31>, <34|32>, " +
                "<35|32>, " +
                "<36|32>, <36|33>, " +
                "<37|33>, <37|34>, " +
                "<38|34>, " +
                "<39|34>, <39|35>, <39|36>, <39|37>, " +
                "<40|37>, <41|37>, <42|37>, " +
                "<43|37>, <43|38>, <43|39>, <43|40>, <43|41>, " +
                "<44|41>, <45|41>, " +
                "<46|41>, <46|42>, " +
                "<47|42>, <47|43>, <47|44>, <47|45>, " +
                "<48|45>, " +
                "<49|45>, <49|46>, <49|47>, <49|48>, <49|49>]";
        assertEquals(expectedShortestPath, actualShortestPath.toString());
    }

    @Test
    public void testCalcPathLowestRisk() throws IOException {
        ChitonMatrix chitonMatrix = new ChitonMatrix(DataConverter.readLinesToMatrixNoDelimiter(inputLines));
        LinkedList<String> actualShortestPath = chitonMatrix.calcPathLowestRisk();
        String expectedPath = "[<0|0>, <1|0>, <2|0>, <2|1>, <2|2>, <2|3>, <2|4>, <2|5>, <2|6>, <3|6>, <3|7>, <4|7>, <5|7>, <5|8>, <6|8>, <7|8>, <8|8>, <8|9>, <9|9>]";
        assertEquals(expectedPath, actualShortestPath.toString());
    }

    @Test
    public void testCalcRiskValuesEnlarged() throws IOException {
        ChitonMatrix chitonMatrix = new ChitonMatrix(DataConverter.readLinesToMatrixNoDelimiter(inputLines));
        int[][] enlargedMatrix = chitonMatrix.enlargeChitonMatrix();
        int lowestTotalRisk = chitonMatrix.calcEndRiskValue(enlargedMatrix);
        assertEquals(315, lowestTotalRisk);
    }

    @Test
    public void testRotateMatrix() throws IOException {
        ChitonMatrix chitonMatrix = new ChitonMatrix(DataConverter.readLinesToMatrixNoDelimiter(inputLines));
        int[][] rotatedMatrix = chitonMatrix.rotateMatrix();
        int[][] expectedMatrix = new int[][] {
                { 2,2,8,9,1,7,1,9,1,1 },
                { 4,7,2,6,1,3,2,3,2,8 },
                { 7,6,3,5,1,1,4,6,5,5 },
                { 1,3,1,1,7,8,2,1,8,4 },
                { 5,7,1,3,1,2,1,2,3,4 },
                { 7,3,5,9,4,1,9,4,1,9 },
                { 3,1,6,4,3,9,9,5,3,1 },
                { 6,8,3,9,6,1,5,2,9,1 },
                { 1,3,1,6,4,3,3,1,2,3 },
                { 1,1,2,3,7,1,1,3,1,2 }
        };
        assertArrayEquals(expectedMatrix, rotatedMatrix);
    }
            /*1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581*/

}
