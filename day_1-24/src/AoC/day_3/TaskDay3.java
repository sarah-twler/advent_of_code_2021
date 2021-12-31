package AoC.day_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaskDay3 {

    public int calcPowerConsumption(LinkedList<LinkedList<Integer>> data) throws UnexpectedException {
        return calcGammaRate(data) * calcEpsilonRate(data);
    }

    public int calcLifeSupportRating(LinkedList<LinkedList<Integer>> data) throws UnexpectedException {
        return calcRating(data, LifeSupportRatingTypeEnum.OXYGEN_GENERATOR) * calcRating(data, LifeSupportRatingTypeEnum.CO2_SCRUBBER);
    }

     int calcGammaRate(LinkedList<LinkedList<Integer>> data) throws UnexpectedException {
        int[] gammaRateBinary = new int[data.size()];

        for (int i = 0; i < data.size(); i++) {
            gammaRateBinary[i] = getMostCommonBit(data.get(i));
        }
        return calcDecimal(gammaRateBinary);
    }

     int calcEpsilonRate(LinkedList<LinkedList<Integer>> data) throws UnexpectedException {
        int[] epsilonRateBinary = new int[data.size()];

        for (int i = 0; i < data.size(); i++) {
            epsilonRateBinary[i] = getMostCommonBit(data.get(i)) == 1 ? 0 : 1;
        }
        return calcDecimal(epsilonRateBinary);
    }

    int calcRating(LinkedList<LinkedList<Integer>> data, LifeSupportRatingTypeEnum ratingType) throws UnexpectedException {
        if (data.isEmpty())
            throw new UnexpectedException("Data array list must not be empty!");

        ArrayList<String> keepLinePos = initPosList(data.get(0).size());

        for (LinkedList<Integer> dataSet : data) {
            ArrayList<String> toRemoveList = new ArrayList();

            int bit = getMostCommonBit(keepLinePos, dataSet);
            if (ratingType == LifeSupportRatingTypeEnum.CO2_SCRUBBER)
                bit = bit == 1 ? 0 : 1;
            for (String linePos : keepLinePos) {
                if (dataSet.get(Integer.parseInt(linePos)) != bit) {
                    toRemoveList.add(linePos);
                }
            }
            keepLinePos.removeAll(toRemoveList);

            if (keepLinePos.size() < 1)
                throw new UnexpectedException("No values left in position array lis!");
            if (keepLinePos.size() == 1)
                break;
        }

        int[] binaryArray = getRowAsBinaryArray(data, Integer.parseInt(keepLinePos.get(0)));
        return calcDecimal(binaryArray);
    }

     int[] getRowAsBinaryArray(LinkedList<LinkedList<Integer>> data, int linePos) {
        int[] binaryArray = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            binaryArray[i] = data.get(i).get(linePos);
        }
        return binaryArray;
    }

    private int calcDecimal(int[] binaryArray) {
        int maxPos = binaryArray.length - 1;
        int factor = 2;
        int currPos = 1;
        int decimal = 0;
        for (int i=0; i <= maxPos; i++) {
            decimal += currPos * binaryArray[maxPos-i];
            currPos = factor * currPos;
        }
        return decimal;
    }

    private int getMostCommonBit(ArrayList<String> validPosList, LinkedList<Integer> bitList) throws UnexpectedException {
        LinkedList<Integer> validBitList = new LinkedList();
        for (String validPos : validPosList) {
            validBitList.add(bitList.get(Integer.parseInt(validPos)));
        }
        return  getMostCommonBit(validBitList);
    }

    private int getMostCommonBit(LinkedList<Integer> bitList) throws UnexpectedException {
        int countBit1 = 0;
        int countBit0 = 0;
        for (int bit : bitList) {
            switch (bit) {
                case 1:
                    countBit1++;
                    break;
                case 0:
                    countBit0++;
                    break;
                default:
                    throw new UnexpectedException("Unexpected character found!");
            }
        }
        if (countBit1 == countBit0)
            return 1;
        return countBit1 > countBit0 ? 1 : 0;
    }

    public LinkedList<LinkedList<Integer>> readDataFromFile(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));

        if (lines.isEmpty()) {
            throw new UnexpectedException("Files has no lines to read from!");
        }

        LinkedList<LinkedList<Integer>> colList = new LinkedList();

        int colNum = lines.get(0).length();
        for (String line : lines) {
            String[] lineAsArray = line.split("");
            if (lineAsArray.length != colNum) {
                throw new UnexpectedException("Column count of line does not match!");
            }
            for (int i = 0; i < colNum; i++) {
                if (colList.size() == i) {
                    colList.add(i, new LinkedList<Integer>());
                }
                colList.get(i).add(Integer.parseInt(lineAsArray[i]));
            }
        }
        return colList;
    }

    private ArrayList<String> initPosList(int size) {
        ArrayList<String> posList = new ArrayList();
        for (int i = 0; i < size; i++) {
            posList.add(String.valueOf(i)); // avoid ambiguity with position in list
        }
        return posList;
    }
}
