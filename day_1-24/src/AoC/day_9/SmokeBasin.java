package AoC.day_9;

import java.io.IOException;
import java.util.ArrayList;

import static AoC.day_9.MatrixHelper.*;

public class SmokeBasin {

    private final static int RISK_SURCHARGE = 1;
    private final int[][] heightMap;
    private int highestBasinSize1st = 0;
    private int highestBasinSize2nd = 0;
    private int highestBasinSize3rd = 0;

    public SmokeBasin(int[][] heightMap) {
        this.heightMap = heightMap;
    }

     int[][] createBasinMap(int lowPointRow, int lowPointCol) throws IOException {
        int[][] basinMap = createEmptyMapSameSize(heightMap);

        walkAndFillBasin(lowPointRow, lowPointCol, WalkLeftRightEnum.KEEP, WalkUpDownEnum.KEEP, basinMap);

        return basinMap;
    }

    public int multiplyLargestThreeBasinSizes() throws IOException {
        ArrayList<int[]> lowPointPositions = findLowPointPositions();
        for (int[] lowPointPosition : lowPointPositions) {
            int basinSize = calcBasinSize(lowPointPosition[0], lowPointPosition[1], createBasinMap(lowPointPosition[0], lowPointPosition[1]));
            compareAndSetHighestBasinSizes(basinSize);
        }
        return highestBasinSize1st * highestBasinSize2nd * highestBasinSize3rd;
    }

    public int calcBasinSize(int lowPointRow, int lowPointCol, int[][] basinMap) {
        int totalCount = countMarksInRow(lowPointRow, basinMap);
        totalCount += countMarksNextRows(lowPointRow + WalkUpDownEnum.UP.walkDirection, basinMap, WalkUpDownEnum.UP);
        totalCount += countMarksNextRows(lowPointRow + WalkUpDownEnum.DOWN.walkDirection, basinMap, WalkUpDownEnum.DOWN);
        return totalCount;
    }

    private int countMarksInRow(int row, int[][] basinMap) {
        if (!isRowInRange(row, basinMap))
            return 0;

        int count = 0;
        for (int col = 0; col < basinMap[row].length; col++) {
            if (isMarked(row, col, basinMap))
                count++;
        }
        return count;
    }

    private void compareAndSetHighestBasinSizes(int newBasinSize) {
        if (newBasinSize < highestBasinSize3rd)
            return;

        if (newBasinSize > highestBasinSize1st) {
            highestBasinSize3rd = highestBasinSize2nd;
            highestBasinSize2nd = highestBasinSize1st;
            highestBasinSize1st = newBasinSize;
        } else if (newBasinSize > highestBasinSize2nd) {
            highestBasinSize3rd = highestBasinSize2nd;
            highestBasinSize2nd = newBasinSize;
        } else if (newBasinSize > highestBasinSize3rd)
            highestBasinSize3rd = newBasinSize;
    }

    private int countMarksNextRows(int row, int[][] basinMap, WalkUpDownEnum walkDirection) {
        int count = countMarksInRow(row, basinMap);

        if (count != 0)
            count += countMarksNextRows(row + walkDirection.walkDirection, basinMap, walkDirection);
        return count;
    }

    private void walkAndFillBasin(int row, int col, WalkLeftRightEnum walkLeftRight, WalkUpDownEnum walkUpDown, int[][] basinMap) {
        row = row + walkUpDown.walkDirection;
        col = col + walkLeftRight.walkDirection;

        if (isRowInRange(row, basinMap)
                && isColInRange(row, col, basinMap)
                && isBasinIndicator(row, col, heightMap)
                && !isMarked(row, col, basinMap)) {
            markPosition(row, col, basinMap);
            walkAndFillBasin(row, col, WalkLeftRightEnum.KEEP, WalkUpDownEnum.UP, basinMap); // walk up
            walkAndFillBasin(row, col, WalkLeftRightEnum.RIGHT, WalkUpDownEnum.KEEP, basinMap); // walk right
            walkAndFillBasin(row, col, WalkLeftRightEnum.KEEP, WalkUpDownEnum.DOWN, basinMap); // walk down
            walkAndFillBasin(row, col, WalkLeftRightEnum.LEFT, WalkUpDownEnum.KEEP, basinMap); // walk left
        }
    }

    public int calcTotalRiskLevel() {
        int totalRiskLevel = 0;

        ArrayList<int[]> lowPointsPosList = findLowPointPositions();
        for (int[] lowPointPos : lowPointsPosList)
            totalRiskLevel += heightMap[lowPointPos[0]][lowPointPos[1]] + RISK_SURCHARGE;

        return  totalRiskLevel;
    }

    /** @return a list of positions ([row][col]) of low points of the heightMap */
    public ArrayList<int[]> findLowPointPositions() {
        ArrayList<int[]> lowPointsList = new ArrayList();
        for (int row = 0; row < heightMap.length; row++)
            for (int col = 0; col < heightMap[0].length; col++)
                if (isValueAboveHigher(row, col, heightMap)
                        && isValueRightHigher(row, col, heightMap)
                        && isValueBelowHigher(row, col, heightMap)
                        && isValueLeftHigher(row, col, heightMap))
                    lowPointsList.add(new int[] { row, col });
        return lowPointsList;
    }

     enum WalkLeftRightEnum {
        RIGHT(1),
        LEFT(-1),
         KEEP(0);

        private final int walkDirection;

         WalkLeftRightEnum(int walkDirection) {
             this.walkDirection = walkDirection;
         }
     }

     enum WalkUpDownEnum {
         UP(-1),
         DOWN(1),
         KEEP(0);

         private final int walkDirection;

         WalkUpDownEnum(int walkDirection) {
             this.walkDirection = walkDirection;
         }
     }

}
