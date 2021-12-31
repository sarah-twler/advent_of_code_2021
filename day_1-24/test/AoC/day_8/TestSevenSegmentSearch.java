package AoC.day_8;

import AoC.data_manager.FileReader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class TestSevenSegmentSearch {

    private static final String filePath = "./data/day_8";
    private static final String testInputFile = filePath + "/test_input.txt";
    private static LinkedList<String> inputLines;
    private static SevenSegmentSearch search;

    @BeforeClass
    public static void initialize() throws IOException {
        inputLines = new LinkedList(FileReader.readFileToLines(testInputFile));
    }

    @Before
    public void setup() {
        search = new SevenSegmentSearch();
    }

    @Test
    public void testCountUniqueDigitPatterns() {
        SevenSegmentSearch search = new SevenSegmentSearch();
        assertEquals(26, search.countUniqueDigitPatterns(inputLines));
    }

    @Test
    public void testCreateSegmentMapForLine() throws IOException {
        SevenSegmentSearch search = new SevenSegmentSearch();
        Map<String, String> map = search.createSegmentMapForLine("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf");
        assertEquals(10, map.keySet().size());     //   ddd
        assertEquals(map.get("ab"), "1");
        assertEquals(map.get("acdfg"), "2");
        assertEquals(map.get("abcdf"), "3");
        assertEquals(map.get("abef"), "4"); // d
        assertEquals(map.get("bcdef"), "5");
        assertEquals(map.get("bcdefg"), "6");
        assertEquals(map.get("abd"), "7");
        assertEquals(map.get("abcdefg"), "8");
        assertEquals(map.get("abcdef"), "9");
        assertEquals(map.get("abcdeg"), "0");
    }

    @Test
    public void testGetOutputValueForLine() throws IOException {
        testGetOutputValueForLineHelper("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf", "5353");

        testGetOutputValueForLineHelper(inputLines.get(0), "8394");
        testGetOutputValueForLineHelper(inputLines.get(1), "9781");
        testGetOutputValueForLineHelper(inputLines.get(2), "1197");
        testGetOutputValueForLineHelper(inputLines.get(3), "9361");
        testGetOutputValueForLineHelper(inputLines.get(4), "4873");
        testGetOutputValueForLineHelper(inputLines.get(5), "8418");
        testGetOutputValueForLineHelper(inputLines.get(6), "4548");
        testGetOutputValueForLineHelper(inputLines.get(7), "1625");
        testGetOutputValueForLineHelper(inputLines.get(8), "8717");
        testGetOutputValueForLineHelper(inputLines.get(9), "4315");
    }

    @Test
    public void testGetOutputValue() throws IOException {
//        assertEquals(new BigInteger("61229"), search.getOutputValue(inputLines));
        assertEquals(61229, search.getOutputValueAllLines(inputLines));
    }

    private void testGetOutputValueForLineHelper(String inputLine, String expectedResult) throws IOException {
//        BigInteger actualValue = search.getOutputValueForLine(inputLine);
//        assertEquals(new BigInteger(expectedResult), actualValue);
        Integer actualValue = search.getOutputValueForLine(inputLine);
        assertEquals(Integer.valueOf(expectedResult), actualValue);
    }



}
