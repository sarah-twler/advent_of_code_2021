package AoC.day_6;

import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestLanternfish {

    private static final String filePath = "./data/day_6";
    private static final String testInputFile = filePath + "/test_input.txt";

    @Test
    public void testCountFishAfterDays() {
        testCountFishAfterDays(3, 1, new BigInteger("1"));
        testCountFishAfterDays(3, 3, new BigInteger("1"));
        testCountFishAfterDays(3, 4, new BigInteger("2"));
        testCountFishAfterDays(3, 5, new BigInteger("2"));
        testCountFishAfterDays(3, 11, new BigInteger("3"));
        testCountFishAfterDays(3, 13, new BigInteger("4"));
        testCountFishAfterDays(3, 17, new BigInteger("4"));
        testCountFishAfterDays(3, 18, new BigInteger("5"));
        testCountFishAfterDays(3, 20, new BigInteger("7"));
        testCountFishAfterDays(3, 22, new BigInteger("8"));
    }

    @Test
    public void countTotalFishAfterDays() throws IOException {
        countTotalFishAfterDays(1, new BigInteger("5"));
        countTotalFishAfterDays(2, new BigInteger("6"));
        countTotalFishAfterDays(3, new BigInteger("7"));
        countTotalFishAfterDays(4, new BigInteger("9"));
        countTotalFishAfterDays(5, new BigInteger("10"));
        countTotalFishAfterDays(18, new BigInteger("26"));
        countTotalFishAfterDays(18, new BigInteger("26"));
        countTotalFishAfterDays(80, new BigInteger("5934"));
        System.out.println("Processing 256 days ...");
        countTotalFishAfterDays(256, new BigInteger("26984457539"));
        System.out.println("Done!");
    }

    @Test
    public void testReadDataFromFile() throws IOException {
        ArrayList<Integer> data = FileManager.readDataFromFile(testInputFile);
        assertArrayEquals(data.toArray(new Integer[0]), new Integer[] {3,4,3,1,2});
    }

    public void countTotalFishAfterDays(int numOfDays, BigInteger expectedCount) throws IOException {
        ArrayList<Integer> data = FileManager.readDataFromFile(testInputFile);
        SchoolOfFish schoolOfFish = new SchoolOfFish(data);
        assertEquals(expectedCount, schoolOfFish.countTotalFishAfterDays(numOfDays));
    }

    private void testCountFishAfterDays(int startTimer, int numOfDays, BigInteger expectedCount) {
        Lanternfish lanternfish = new Lanternfish(startTimer);
        assertEquals(expectedCount, lanternfish.countFishAfterDays(numOfDays));
    }
}
