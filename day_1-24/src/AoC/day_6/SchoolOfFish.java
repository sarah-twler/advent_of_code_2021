package AoC.day_6;

import java.math.BigInteger;
import java.util.ArrayList;

public class SchoolOfFish {

    private final ArrayList<Lanternfish> fishList;

    public SchoolOfFish(ArrayList<Integer> fishTimersList) {
        this.fishList = initFishList(fishTimersList);
    }

    private ArrayList<Lanternfish> initFishList(ArrayList<Integer> fishTimersList) {
        ArrayList<Lanternfish> tempFishList = new ArrayList();
        for (int startTimer : fishTimersList)
            tempFishList.add(new Lanternfish(startTimer));
        return tempFishList;
    }

    public BigInteger countTotalFishAfterDays(int numOfDays) {
        BigInteger totalCount = new BigInteger("0");

        for (Lanternfish fish : fishList)
            totalCount = totalCount.add(fish.countFishAfterDays(numOfDays));

        return totalCount;
    }

}
