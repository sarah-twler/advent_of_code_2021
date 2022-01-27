package AoC.day_14;

import AoC.data_manager.DataConverter;
import AoC.data_manager.FileReader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.Assert.*;

public class TestPolymerization {

    private static final String filePath = "./data/day_14";
    private static final String testInputFile = filePath + "/test_input.txt";
    private static final String INPUT_DELIMITER = " -> ";
    private static Map<String,String> fileInputMap;
    private static String inputTemplate;
    private Polymerization polymerization;

    @BeforeClass
    public static void init() throws IOException {
        fileInputMap = FileReader.readFileToMap(testInputFile, INPUT_DELIMITER);
        inputTemplate = FileReader.readFileToLines(testInputFile).get(0);
    }

    @Before
    public void setup() {
        polymerization = new Polymerization(fileInputMap);
    }

    @Test
    public void testCreatePolymer() {
        String[] compActualPolymer1 = createComparableArray(polymerization.createPolymer(inputTemplate, 1));
        String[] compExpectedPolymer1 = createComparableArray("NCNBCHB");
        assertArrayEquals(compExpectedPolymer1, compActualPolymer1);

        String[] compActualPolymer2 = createComparableArray(polymerization.createPolymer(inputTemplate, 2));
        String[] compExpectedPolymer2 = createComparableArray("NBCCNBBBCBHCB");
        assertArrayEquals(compExpectedPolymer2, compActualPolymer2);

        String[] compActualPolymer3 = createComparableArray(polymerization.createPolymer(inputTemplate, 3));
        String[] compExpectedPolymer3 = createComparableArray("NBBBCNCCNBBNBNBBCHBHHBCHB");
        assertArrayEquals(compExpectedPolymer3, compActualPolymer3);

        String[] compActualPolymer4 = createComparableArray(polymerization.createPolymer(inputTemplate, 4));
        String[] compExpectedPolymer4 = createComparableArray("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB");
        assertArrayEquals(compExpectedPolymer4, compActualPolymer4);
    }

    @Test
    public void testCreateFrequencyMap() {
        testCreateBigFrequencyMap(1, "NCNBCHB");
        testCreateBigFrequencyMap(2, "NBCCNBBBCBHCB");
        testCreateBigFrequencyMap(3, "NBBBCNCCNBBNBNBBCHBHHBCHB");
        testCreateBigFrequencyMap(4, "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB");
    }

    @Test
    public void testCreateBigFrequencyMap10Steps() {
        testCreateBigFrequencyMap(10,
                new BigInteger("1749"),
                new BigInteger("298"),
                new BigInteger("161"),
                new BigInteger("865"));
    }

    @Test
    public void testCreateBigFrequencyMap20Steps() {
        // performance test
        testCreateBigFrequencyMap(20, null, null, null, null);
    }

    @Test
    public void testCreateBigFrequencyMap25Steps() {
        // performance test
        testCreateBigFrequencyMap(25, null, null, null, null);
    }

    @Test
    public void testCreateBigFrequencyMap30Steps() {
        // performance test
        testCreateBigFrequencyMap(30, null, null, null, null);
    }

    @Test
    public void testCreateFrequencyMap40Steps() {
        testCreateBigFrequencyMap(40,
                new BigInteger("2192039569602"),
                null,
                new BigInteger("3849876073"),
                null);
    }

    @Test
    public void testCalcPolymerResultLong() {
        assertEquals(new BigInteger("2188189693529"), polymerization.calcBigPolymerResult(inputTemplate, 40));
    }

    private String[] createComparableArray(String str) {
        return createComparableArray(DataConverter.convertStringToStringList(str));
    }

    private String[] createComparableArray(LinkedList<String> list) {
        return list.toArray(new String[0]);
    }

    public void testCreateBigFrequencyMap(int numOfSteps, String expectedPolymerTemplate) {
        Map<PolymerTuple, BigInteger> polymer = polymerization.createBigPolymer(inputTemplate, numOfSteps);
        Map<String, BigInteger> frequencyMap = polymerization.createBigFrequencyMap(polymer, inputTemplate);
        LinkedList<String> expectedPolymer = DataConverter.convertStringToStringList(expectedPolymerTemplate);
//        System.out.println(String.format("Testing %s steps", numOfSteps));
        assertEquals(new BigInteger(String.valueOf(Collections.frequency(expectedPolymer, "N"))), frequencyMap.get("N"));
        assertEquals(new BigInteger(String.valueOf(Collections.frequency(expectedPolymer, "B"))), frequencyMap.get("B"));
        assertEquals(new BigInteger(String.valueOf(Collections.frequency(expectedPolymer, "C"))), frequencyMap.get("C"));
        assertEquals(new BigInteger(String.valueOf(Collections.frequency(expectedPolymer, "H"))), frequencyMap.get("H"));
    }

    public void testCreateBigFrequencyMap(int numOfSteps, BigInteger numExpectedB, BigInteger numExpectedC, BigInteger numExpectedH, BigInteger numExpectedN) {
//        System.out.println(String.format("Testing %s steps:", numOfSteps));
//        System.out.println("Creating polymer ...");
        Map<PolymerTuple, BigInteger> polymer = polymerization.createBigPolymer(inputTemplate, numOfSteps);
//        System.out.println("Creating frequency map ...");
        Map<String, BigInteger> frequencyMap = polymerization.createBigFrequencyMap(polymer, inputTemplate);
//        System.out.println("Do assertions ...");
        if (numExpectedN != null)
            assertEquals(numExpectedN, frequencyMap.get("N"));
        if (numExpectedN != null)
            assertEquals(numExpectedB, frequencyMap.get("B"));
        if (numExpectedN != null)
            assertEquals(numExpectedC, frequencyMap.get("C"));
        if (numExpectedN != null)
            assertEquals(numExpectedH, frequencyMap.get("H"));
//        System.out.println("Done!");
    }

}
