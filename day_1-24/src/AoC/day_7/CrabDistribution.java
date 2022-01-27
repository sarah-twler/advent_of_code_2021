package AoC.day_7;

import org.junit.platform.commons.util.StringUtils;

import java.util.*;

public class CrabDistribution {

    private final Map<Integer, Integer> crabDistributionMap;

    public CrabDistribution(Map<Integer, Integer> crabDistributionMap) {
        this.crabDistributionMap = crabDistributionMap;
    }

    public int calcLowestFuelConsumption() {
        fillAllPosInCrabDistributionList();

        int lowestFuelNeeded = Integer.MAX_VALUE;
        Iterator<Integer> posIter = crabDistributionMap.keySet().iterator();
        while (posIter.hasNext()) {
            int pos = posIter.next();
            int fuelConsumption = calcFuelConsumptionForPosition(pos);
            if (lowestFuelNeeded > fuelConsumption)
                lowestFuelNeeded = fuelConsumption;
        }
        return lowestFuelNeeded;
    }

    public int calcLowestExpensiveFuelConsumption() {
        fillAllPosInCrabDistributionList();

        int lowestFuelNeeded = Integer.MAX_VALUE;
        Iterator<Integer> posIter = crabDistributionMap.keySet().iterator();
        while (posIter.hasNext()) {
            int pos = posIter.next();
            int fuelConsumption = calcFuelExpensiveConsumptionForPosition(pos);
            if (lowestFuelNeeded > fuelConsumption)
                lowestFuelNeeded = fuelConsumption;
        }
        return lowestFuelNeeded;
    }

    private void fillAllPosInCrabDistributionList() {
        for (int i = 0; i <= getHighestPos(); i++) {
            if (!crabDistributionMap.containsKey(i)) {
                crabDistributionMap.put(i, 0);
            }
        }
    }

    private int calcFuelExpensiveConsumptionForPosition(int pos) {
        int totalFuelNeeded = 0;
        Iterator<Integer> posIter = crabDistributionMap.keySet().iterator();
        while (posIter.hasNext()) {
            int currPos = posIter.next();
            int steps = Math.abs(pos - currPos);
            int fuelNeeded = 0;
            for (int step = 1; step <= steps; step++)
                fuelNeeded += step;
            int numCrabs = crabDistributionMap.get(currPos);
            totalFuelNeeded += fuelNeeded * numCrabs;
        }
        return totalFuelNeeded;
    }

    private int calcFuelConsumptionForPosition(int pos) {
        int totalFuelNeeded = 0;
        Iterator<Integer> posIter = crabDistributionMap.keySet().iterator();
        while (posIter.hasNext()) {
            int currPos = posIter.next();
            int steps = Math.abs(pos - currPos);
            int numCrabs = crabDistributionMap.get(currPos);
            totalFuelNeeded += steps * numCrabs;
        }
        return totalFuelNeeded;
    }

    public int getFrequencyFor(int position) {
        return crabDistributionMap.get(position);
    }

    private int getHighestPos() {
        int highestPos = 0;
        Iterator<Integer> iter = crabDistributionMap.keySet().iterator();
        while (iter.hasNext()) {
            int pos = iter.next();
            if (pos > highestPos)
                highestPos = pos;
        }
        return highestPos;
    }

    public void printCrabDistribution() {
        String point = "x";
        int start = 0;
        int end = getHighestPos();
        for (int i = start; i <= end; i++) {
            String line = point.repeat(crabDistributionMap.getOrDefault(i, 0));
            System.out.println(String.format("% 8d: %s", i, line));
        }
    }
}
