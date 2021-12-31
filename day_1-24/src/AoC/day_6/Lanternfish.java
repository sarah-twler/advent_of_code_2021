package AoC.day_6;

import java.math.BigInteger;
import java.util.ArrayList;

public class Lanternfish {

    static private final int MIN_TIMER = 0;
    static private final int RESET_TIMER = 6;
    static private final int MAX_TIMER = 8;
    static private final int NEW_TIMER = MAX_TIMER;
    ArrayList<BigInteger> fishPerTimerList = new ArrayList();

    public Lanternfish(int startTimer) {
        for (int timerIndex = 0; timerIndex <= MAX_TIMER; timerIndex++)
            fishPerTimerList.add(new BigInteger("0"));
        fishPerTimerList.set(startTimer, new BigInteger("1"));
    }

    public BigInteger countFishAfterDays(int numOfDays) {
        propagateFishUntil(numOfDays);

        BigInteger countFish = new BigInteger("0");
        for (int timerIndex = 0; timerIndex < fishPerTimerList.size(); timerIndex++) {
            countFish = countFish.add(fishPerTimerList.get(timerIndex));
        }
        return countFish;
    }

    public void propagateFishUntil(int numOfDays) {
        for (int day = 1; day <= numOfDays; day++) {
            propagateFishOneDay();
        }
    }

    private void propagateFishOneDay() {
        BigInteger numFishSpawns = fishPerTimerList.get(MIN_TIMER);

        for (int timerIndex = MIN_TIMER; timerIndex < MAX_TIMER; timerIndex++) {
            BigInteger numFishPrevTimer = fishPerTimerList.get(timerIndex + 1);
            fishPerTimerList.set(timerIndex, numFishPrevTimer);
        }

        BigInteger numFishResetTimer = fishPerTimerList.get(RESET_TIMER);
        fishPerTimerList.set(RESET_TIMER, numFishResetTimer.add(numFishSpawns));
        fishPerTimerList.set(NEW_TIMER, numFishSpawns);
    }
}
